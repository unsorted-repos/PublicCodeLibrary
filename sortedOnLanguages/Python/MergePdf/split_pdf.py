import os
import sys
from PyPDF2 import PdfReader, PdfWriter

def split_pdf(input_pdf, output_prefix, start_page, end_page):
    directory = os.path.dirname(input_pdf)
    with open(input_pdf, 'rb') as file:
        pdf_reader = PdfReader(file)

        # Ensure start_page and end_page are within the valid range
        start_page = max(1, start_page)
        if end_page is None:
            end_page = len(pdf_reader.pages)
        else:
            end_page = min(len(pdf_reader.pages), end_page)

        for page_number in range(start_page - 1, end_page):
            pdf_writer = PdfWriter()
            pdf_writer.add_page(pdf_reader.pages[page_number])

            output_pdf = f"{output_prefix}{page_number + 1}.pdf"

            with open(f"{directory}/{output_pdf}", 'wb') as output_file:
                pdf_writer.write(output_file)
if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python script.py input_pdf start_page end_page")
        sys.exit(1)

    input_pdf = sys.argv[1]
    output_prefix = "output_page_"
    start_page = int(sys.argv[2])
    if len(sys.argv) > 3:
        end_page = int(sys.argv[3])
    else:
        # just print all pages.
        end_page=None

    
    split_pdf(input_pdf, output_prefix, start_page, end_page)

