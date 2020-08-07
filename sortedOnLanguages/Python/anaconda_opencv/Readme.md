# How to use opencv on anaconda
0. Install anaconda
```
with setup.exe
```
1. Open anaconda prompt as administrator and create a new envirionment:
```
conda create -n py36 python=3.6 anaconda

```
2. Activate new environment (do this e3very time you re-open anaconda)
```
conda activate py36
```
2. Add new 3.6 environment to path with.
2. Install opencv 
2.1 Source: https://anaconda.org/anaconda/opencv
```
conda install -c anaconda opencv
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
