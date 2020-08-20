import cv2
print(cv2.__version__)

from pyzbar.pyzbar import decode
from PIL import Image
decode(Image.open('test.png'))
