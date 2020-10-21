0. Had to install libraries from:
https://pypi.python.org/pypi/PyPDF2/1.25.1
0.1. Downlaod tar
0.2. extract tar 
0.3. go to folder with setup.py
0.4. open cmd
0.5 cd to <folder with setup.py>
0.6 python setup.py --install 
0.7 enter

Then to use the script:
1. Put the pdf files you want to merge in 1 folder.  (No spaces in pathname)
1.0 E.g. "C:\merge"

2. thats it then run the python code mergingpdf.py (Located in this/the same directory as this readme) with: 
	2.a Open CMD> cd E: (or which ever drive you put this folder in.)> cd "E:\full path to mergingpdf.py file\"
	2.b Linux: terminal: E: (or whichever drive you put this folder in.) cd "E:\full path to mergingpdf.py file\"
	2.c python mergingpdf.py

3. (The mergingpdf.py is pre-configured to folder "C:\merge") 

4. The merged document is now located in the folder "C:\merge"

If you get error: ..package not installed, Install and open anaconda, browse to this directory and enter:
py -3   -m pip install PyPDF2


## Linux instructions
pip install pypdf
pip install pypdf2
