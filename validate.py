import os
import re
import sys
import json
import argparse
from pathlib import Path

def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('--changed-files', help='JSON list of changed files')
    parser.add_argument('--base-package', required=True, help='Base Java package name')
    return parser.parse_args()

def validate_package_structure(file_path, base_package):
    try:
        content = Path(file_path).read_text(encoding='utf-8')
        match = re.search(r'^\s*package\s+([a-zA-Z0-9_.]+)\s*;', content, re.MULTILINE)

        if not match:
            return False, "No package declaration found"

        actual_package = match.group(1)
        expected_package = calculate_expected_package(file_path, base_package)

        if actual_package != expected_package:
            return False, f"Package mismatch. Expected: {expected_package}, Found: {actual_package}"

        return True, ""

    except Exception as e:
        return False, f"Validation error: {str(e)}"

def calculate_expected_package(file_path, base_package):
    rel_path = Path(file_path).relative_to('src/main/java')
    dir_structure = str(rel_path.parent).replace(os.sep, '.')
    return dir_structure

def main():
    args = parse_args()
    base_package = args.base_package
    errors = []

    if args.changed_files:
        changed_files = json.loads(args.changed_files)
    else:
        changed_files = list(Path('src/main/java').rglob('*.java'))

    for file_path in changed_files:
        file_path = str(file_path)
        if not file_path.endswith('.java'):
            continue

        is_valid, message = validate_package_structure(file_path, base_package)
        if not is_valid:
            errors.append(f"### {file_path}\n{message}\n")

    if errors:
        error_message = "## Package Validation Errors\n\n" + "\n".join(errors)
        Path("validation_errors.md").write_text(error_message)
        print(error_message)
        sys.exit(1)
    else:
        print("All package declarations are valid")

if __name__ == "__main__":
    main()