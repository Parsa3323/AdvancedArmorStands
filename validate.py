import os
import re

BASE_PACKAGE = "me.parsa.aas"

def get_expected_package(file_path):
    relative_path = os.path.relpath(file_path, start="src/main/java")
    directory_structure = os.path.dirname(relative_path).replace(os.sep, ".")
    return directory_structure

def check_package_name(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    match = re.search(r'^\s*package\s+([a-zA-Z0-9_.]+)\s*;', content, re.MULTILINE)
    if match:
        actual_package_name = match.group(1)
        expected_package_name = get_expected_package(file_path)

        if actual_package_name != expected_package_name:
            print(f"Package name error in {file_path}:")
            print(f"Expected: {expected_package_name}")
            print(f"Found:    {actual_package_name}")
            return False
    else:
        print(f"No package declaration found in {file_path}")
        return False
    return True

def validate_java_files():
    src_path = "src/main/java"
    errors = []

    for root, _, files in os.walk(src_path):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                if not check_package_name(file_path):
                    errors.append(file_path)

    if errors:
        print("\nFound package name errors in these files:")
        for error in errors:
            print(f" - {error}")
        print(f"\nTotal errors: {len(errors)}")
        exit(1)
    else:
        print("All package declarations are correct.")

validate_java_files()