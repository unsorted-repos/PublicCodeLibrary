## Linux instructions
pip install pypdf
pip install pypdf2


## Split
To split the pages 1 to 8:
```
python split_pdf.py some_file.pdf 1 8 
```
to split the `some_file.pdf` into `1.pdf, 2.pdf .. to 8.pdf`

To split all the pages and output them in the same dir as the original pdf:

```
python split_pdf.py some_file.pdf 1
```