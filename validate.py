import os
import re

EXPECTED_PACKAGE = "me.parsa.aas"
PLUGIN_YML_RELATIVE_PATH = "src/main/resources/plugin.yml"

def check_package(file_path):
    with open(file_path, 'r', encoding="utf-8") as file:

        content = file.read()
        match = re.search(r'^\s*/\*.*?\*/\s*package\s+([a-zA-Z0-9\.]+);', content, re.DOTALL)
        if match:
            return match.group(1) == EXPECTED_PACKAGE
        return False

def check_plugin_yml(module_path):
    plugin_yml_path = os.path.join(module_path, PLUGIN_YML_RELATIVE_PATH)
    if not os.path.exists(plugin_yml_path):
        print(f"Missing {plugin_yml_path}")
        return False

    with open(plugin_yml_path, 'r') as file:
        content = file.read()
        if "main: me.parsa.aas.AdvancedArmorStands" not in content:
            print(f"Incorrect main class in {plugin_yml_path}")
            return False
    return True

def scan_repo():
    valid = True
    for root, dirs, files in os.walk("."):
        if "src" in dirs and "pom.xml" in files:
            module_path = os.path.abspath(root)
            print(f"Checking module: {module_path}")

            for sub_root, _, sub_files in os.walk(os.path.join(module_path, "src/main/java")):
                for file in sub_files:
                    if file.endswith(".java"):
                        file_path = os.path.join(sub_root, file)
                        if not check_package(file_path):
                            print(f"Package name error in {file_path}")
                            valid = False
            if not check_plugin_yml(module_path):
                valid = False

    return valid

if __name__ == "__main__":
    if scan_repo():
        print("Validation passed.")
    else:
        print("Validation failed.")
        exit(1)
