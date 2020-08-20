# How to use opencv on anaconda
Short instructions on how to set up your programming "environment"/ensure you can run (certain) codes (that use packages like opencv etc).

## Install anaconda
0. Install anaconda
```
with setup.exe
```

## Create a python 3.6 environment
1. Open anaconda prompt as administrator and create a new envirionment:
```
conda create -n py36 python=3.6 anaconda

```
2. Activate new environment (do this e3very time you re-open anaconda)
```
conda activate py36
```

### opencv installation
Source: https://anaconda.org/anaconda/opencv

Source:https://stackoverflow.com/questions/42994813/installing-opencv-on-windows-10-with-python-3-6-and-anaconda-3-6

```
conda install -c menpo opencv
```
3. Verify in anaconda prompt with a python file named `test.py` with content:
```
import cv2
print(cv2.__version__)
```

4. If it says: ` import cv2" ... "ImportError: DLL load failed: The specified module could not be found.` then:
```
conda install -c menpo opencv
```
4.1 Instal Visual Studio Code from https://code.visualstudio.com/Download
4.2 Click the Extensions view icon on the Sidebar (orCtrl+Shift+X keyboard combination).
4.3 Search of C++. And install (the most popular/top extension named:"C/C++"
4.6 *SOLUTION* https://support.microsoft.com/en-us/help/2977003/the-latest-supported-visual-c-downloads download and install visual studio 2015, 2017 and 2019

Verify opencv is installed correctly in anaconda prompt by creating a python file named `test.py` with content:
```
import cv2
print(cv2.__version__)
```
by browsing to the directory of the `test.py` file in anaconda prompt and executing it in the python 3.6 environment with command:
```
python test.py
```
It should return a version number like `3.3.1`.


The following packages require a bit more effort:
### Pillow installation
Source:https://anaconda.org/conda-forge/pillow
Command:
```
conda install -c conda-forge pillow
```

### Pyzbar installation
Source: https://stackoverflow.com/questions/63296571/decode-a-qr-code-in-python-3-6-in-anaconda-4-8-3-on-64-bit-windows

Command:
```
pip install pyzbar
```
Then from source: https://www.microsoft.com/en-US/download/details.aspx?id=40784
download `vcredist_x64.exe` (if you have an 64 bit pc, for x86 pick the 32 bit version).
You don't even have to restart anaconda prompt and you can verify pyzbar with a python file named `test.py` with content:
```
from pyzbar.pyzbar import decode
decode(Image.open('test.png'))
```
Next include an image named `test.png` in the same folder as `test.py`.
You can execute  `test.py` in Anaconda prompt in a python 3.6 environment with command:
```
python test.py
```
It shouldn't output anything.