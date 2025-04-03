import os
import re

EXPECTED_PACKAGE_PREFIX = "me.parsa.aas"

def check_package_name(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    match = re.search(r'package\s+([a-zA-Z0-9_.]+);', content)
    if match:
        package_name = match.group(1)
        if package_name.startswith(EXPECTED_PACKAGE_PREFIX):
            return True
        else:
            print(f"Package name error in {file_path}: {package_name} does not start with {EXPECTED_PACKAGE_PREFIX}")
            return False
    return False

def validate_java_files():
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
