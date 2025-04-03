import os
import re

BASE_PACKAGE = "me.parsa.aas"

def get_expected_package(file_path):
    """
    Given the file path, return the expected package name based on its directory.
    """
    relative_path = os.path.relpath(file_path, start="src/main/java")
    directory_structure = os.path.dirname(relative_path).replace(os.sep, ".")
    return f"{BASE_PACKAGE}.{directory_structure}"

def check_package_name(file_path):
    """
    Check the package name in the Java file to see if it matches the expected one based on its directory structure.
    """
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    match = re.search(r'package\s+([a-zA-Z0-9_.]+);', content)
    if match:
        actual_package_name = match.group(1)
        expected_package_name = get_expected_package(file_path)

        if actual_package_name != expected_package_name:
            print(f"Package name error in {file_path}: Expected {expected_package_name}, but found {actual_package_name}")
            return False
    return True

def validate_java_files():
    """
    Walk through the project directory and validate package names for all Java files.
    """
    src_path = "src/main/java/me/parsa/aas"
    errors = []

    for root, _, files in os.walk(src_path):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                if not check_package_name(file_path):
                    errors.append(f"Package name error in {file_path}")

    if errors:
        print("\n".join(errors))
        print("Validation failed.")
        exit(1)
    else:
        print("Validation passed.")

validate_java_files()
