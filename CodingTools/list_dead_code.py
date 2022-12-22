import ast
import os
from pprint import pprint
from typing import List

from typeguard import typechecked


@typechecked
def get_all_py_filepaths(root_dir: str) -> List[str]:
    """Returns a list of file paths for all Python files in root_dir and its
    subdirectories.

    Args:
        root_dir: The root directory to search for Python files.

    Returns:
        A list of file paths for all Python files in root_dir and its
        subdirectories.
    """
    filepaths = []
    for dir_name, subdir_list, file_list in os.walk(root_dir):
        for file_name in file_list:
            if file_name.endswith(".py"):
                full_path = os.path.join(dir_name, file_name)
                if "build/lib" not in full_path:
                    filepaths.append(full_path)
    return list(set(filepaths))


@typechecked
def find_function_calls(
    filepath: str, found_function_calls: List[str]
) -> List[str]:
    """Finds all function calls in the Python file at filepath and adds them to
    found_function_calls.

    Args:
        filepath: The file path of the Python file to search for function
        calls.
        found_function_calls: A list of function calls that have already been
        found.

    Returns:
        A list of function calls found in the Python file at filepath.
    """
    with open(filepath) as f:
        code = f.read()

    tree = ast.parse(code)
    for node in ast.walk(tree):
        if isinstance(node, ast.Call):
            if "func" in node.__dict__:
                if "id" in node.func.__dict__:
                    if node.func.id not in found_function_calls:
                        found_function_calls.append(node.func.id)
                if "attr" in node.func.__dict__:
                    if node.func.attr not in found_function_calls:
                        found_function_calls.append(node.func.attr)
    return found_function_calls


@typechecked
def get_func_declarations(filepath: str) -> List[str]:
    """Returns a list of function names declared in the Python file at
    filepath.

    Args:
        filepath: The file path of the Python file to search for function
        declarations.

    Returns:
        A list of function names declared in the Python file at filepath.
    """
    declared_functions = []
    with open(filepath) as f:
        tree = ast.parse(f.read())

    for node in ast.walk(tree):
        if isinstance(node, ast.FunctionDef):
            if node.name not in ["__init__", "get"]:
                declared_functions.append(node.name)
    return declared_functions


# Get a list of file paths for all Python files in the current directory and
# its sub
filepaths = sorted(get_all_py_filepaths(os.getcwd()))

# Get a list of declared functions from all Python files
declared_functions = []
for filepath in filepaths:
    declared_functions.extend(get_func_declarations(filepath))
declared_functions = sorted(list(set(declared_functions)))

# Get a list of found function calls from all Python files
found_function_calls = []
for filepath in filepaths:
    found_function_calls = find_function_calls(filepath, found_function_calls)
found_function_calls = sorted(list(set(found_function_calls)))

# Remove any remaining duplicates.
declared = set(declared_functions)
found = set(found_function_calls)

# Get a list of uncalled functions
uncalled_functions = list(declared - found)

# Filter out functions that are test functions (i.e. start with "test_")
dead_functions = [
    uncalled_function
    for uncalled_function in uncalled_functions
    if uncalled_function[:5] != "test_"
]

# Print the list of dead functions
pprint(dead_functions)
