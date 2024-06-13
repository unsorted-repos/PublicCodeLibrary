import os
from PyPDF2 import PdfReader, PdfMerger

files_dir = "/home/a/Documents/merge"
pdf_files = sorted([f for f in os.listdir(files_dir) if f.endswith(".pdf")])

merger = PdfMerger()  # Use PdfMerger instead of PdfFileMerger

for filename in pdf_files:
    filepath = os.path.join(files_dir, filename)
    merger.append(PdfReader(filepath, "rb"))  # No need for "rb" mode with PdfReader

merger.write(os.path.join(files_dir, "merged_full.pdf"))
