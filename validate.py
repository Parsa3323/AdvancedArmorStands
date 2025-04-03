import os
import re
import sys
from pathlib import Path

def find_java_base_path(file_path):
    """Determine the correct base path for package calculation"""
    path = Path(file_path)
    # Look for src/main/java in the path
    for parent in path.parents:
        if parent.name == 'java' and parent.parent.name == 'main' and parent.parent.parent.name == 'src':
            return parent.parent.parent
    return None

def get_expected_package(file_path, base_package):
    """Calculate the expected package name based on file path"""
    java_base = find_java_base_path(file_path)
    if not java_base:
        return None

    rel_path = Path(file_path).relative_to(java_base).parent
    package_path = str(rel_path).replace(os.sep, '.')

    # For multi-module projects, remove duplicate base package
    if package_path.startswith(base_package):
        return package_path
    return f"{base_package}.{package_path}" if package_path else base_package

def check_package_name(file_path, base_package):
    """Validate the package declaration in a Java file"""
    try:
        content = Path(file_path).read_text(encoding='utf-8')
        match = re.search(r'^\s*package\s+([a-zA-Z0-9_.]+)\s*;', content, re.MULTILINE)

        if not match:
            return False, "No package declaration found"

        actual_package = match.group(1)
        expected_package = get_expected_package(file_path, base_package)

        if not expected_package:
            return False, "File not in standard Java source structure"

        if actual_package != expected_package:
            return False, f"Expected: {expected_package}"

        return True, ""
    except Exception as e:
        return False, f"Error: {str(e)}"

def validate_project(base_package="me.parsa.aas"):
    """Main validation function"""
    errors = []
    java_files = list(Path('.').rglob('src/main/java/**/*.java'))

    if not java_files:
        print("Error: No Java files found in src/main/java")
        return 1

    print(f"Validating {len(java_files)} Java files...")

    for java_file in java_files:
        file_path = str(java_file)
        is_valid, message = check_package_name(file_path, base_package)
        if not is_valid:
            errors.append(f"{file_path}\n  {message}\n")

    if errors:
        print("\nValidation failed with errors:")
        print("="*50)
        print("\n".join(errors))
        print("\nTotal errors:", len(errors))
        return 1

    print("✓ All package declarations are valid")
    return 0

if __name__ == "__main__":
    # Try to get base package from environment variable or use default
    base_package = os.getenv("BASE_PACKAGE", "me.parsa.aas")
    sys.exit(validate_project(base_package))