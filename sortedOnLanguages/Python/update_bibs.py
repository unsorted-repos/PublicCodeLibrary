# Importing library
import bibtexparser
from bibtexparser.bparser import BibTexParser
from bibtexparser.customization import *
import os, fnmatch

import Levenshtein as lev


# Let's define a function to customize our entries.
# It takes a record and return this record.
def customizations(record):
    """Use some functions delivered by the library

    :param record: a record
    :returns: -- customized record
    """
    record = type(record)
    record = author(record)
    record = editor(record)
    record = journal(record)
    record = keyword(record)
    record = link(record)
    record = page_double_hyphen(record)
    record = doi(record)
    return record


def get_references(filepath):
    with open(filepath) as bibtex_file:
        parser = BibTexParser()
        parser.customization = customizations
        bib_database = bibtexparser.load(bibtex_file, parser=parser)
        # print(bib_database.entries)
    return bib_database


def get_reference_mapping(main_filepath, sub_filepath):
    found_sub = []
    found_main = []
    main_into_sub = []

    main_references = get_references(main_filepath)
    sub_references = get_references(sub_filepath)

    for main_entry in main_references.entries:
        for sub_entry in sub_references.entries:

            # Match the reference ID if 85% similair titles are detected
            lev_ratio = lev.ratio(
                remove_curly_braces(main_entry["title"]).lower(),
                remove_curly_braces(sub_entry["title"]).lower(),
            )
            if lev_ratio > 0.85:
                print(f"lev_ratio={lev_ratio}")

                if main_entry["ID"] != sub_entry["ID"]:
                    print(f'replace: {sub_entry["ID"]} with: {main_entry["ID"]}')
                    main_into_sub.append([main_entry, sub_entry])

                    # Keep track of which entries have been found
                    found_sub.append(sub_entry)
                    found_main.append(main_entry)
    return (
        main_into_sub,
        found_main,
        found_sub,
        main_references.entries,
        sub_references.entries,
    )


def remove_curly_braces(string):
    left = string.replace("{", "")
    right = left.replace("{", "")
    return right


def replace_references(main_into_sub, directory):
    for pair in main_into_sub:
        main = pair[0]["ID"]
        sub = pair[1]["ID"]
        print(f"replace: {sub} with: {main}")

        # UNCOMMENT IF YOU WANT TO ACTUALLY DO THE PRINTED REPLACEMENT
        # findReplace(latex_root_dir, sub, main, "*.tex")
        # findReplace(latex_root_dir, sub, main, "*.bib")


def findReplace(directory, find, replace, filePattern):
    for path, dirs, files in os.walk(os.path.abspath(directory)):
        for filename in fnmatch.filter(files, filePattern):
            filepath = os.path.join(path, filename)
            with open(filepath) as f:
                s = f.read()
            s = s.replace(find, replace)
            with open(filepath, "w") as f:
                f.write(s)


def list_missing(main_references, sub_references):
    for sub in sub_references:
        if not sub["ID"] in list(map(lambda x: x["ID"], main_references)):
            print(f'the following reference has a changed title:{sub["ID"]}')


latex_root_dir = "/home/name/Documents/uni/AE5810-Thesis-SOW-MKI92-Research-Project/"
main_filepath = f"{latex_root_dir}latex/4_Literature_study/zotero.bib"
sub_filepath = f"{latex_root_dir}latex/4_Literature_study/references.bib"
(
    main_into_sub,
    found_main,
    found_sub,
    main_references,
    sub_references,
) = get_reference_mapping(main_filepath, sub_filepath)
replace_references(main_into_sub, latex_root_dir)
list_missing(main_references, sub_references)


# For those references which have levenshtein ratio below 85 you can specify a manual swap:
manual_swap = []  # main into sub
# manual_swap.append(["marinella_radiation_2019","marinella2019radiation"])
# manual_swap.append(["davies_intel_nodate","intel_nrc"])
# manual_swap.append(["garcia_fernandez_memristor-based_2020","mastersthesis_memristor_based_neuromorphic_computing"])
# manual_swap.append(["cantley_impact_2021","cantley2021impact"])
# manual_swap.append(["widemann_envision_2021","widemann2020envision"])
# manual_swap.append(["some_author_this_2021","todo_this_2021_this_2021_this_2021"])
# manual_swap.append(["baker_impenetrable_2014","baker2014"])
for pair in manual_swap:
    main = pair[0]
    sub = pair[1]
    print(f"replace: {sub} with: {main}")

    # UNCOMMENT IF YOU WANT TO ACTUALLY DO THE PRINTED REPLACEMENT
    # findReplace(latex_root_dir, sub, main, "*.tex")
    # findReplace(latex_root_dir, sub, main, "*.bib")
