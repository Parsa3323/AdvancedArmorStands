import os
import re

BASE_PACKAGE = "me.parsa.aas"

def get_expected_package(file_path):
    # Find the index where the base package path starts
    base_path = os.path.join("src", "main", "java") + os.sep
    base_path_parts = BASE_PACKAGE.split('.')
    base_java_path = os.sep.join(base_path_parts)

    # Get the full path after src/main/java
    full_java_path = os.path.join("src", "main", "java", base_java_path)

    # Get the relative path from the base package directory
    relative_path = os.path.relpath(file_path, start=full_java_path)
    directory_structure = os.path.dirname(relative_path).replace(os.sep, ".")

    if directory_structure == '.':
        return BASE_PACKAGE
    return f"{BASE_PACKAGE}.{directory_structure}"

def check_package_name(file_path):
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
    src_path = os.path.join("src", "main", "java", *BASE_PACKAGE.split('.'))
    errors = []

    for root, _, files in os.walk(src_path):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                if not check_package_name(file_path):
                    errors.append(file_path)

    if errors:
        print("Package name errors found in:")
        print("\n".join(errors))
        print("Validation failed.")
        exit(1)
    else:
        print("Validation passed.")

validate_java_files()