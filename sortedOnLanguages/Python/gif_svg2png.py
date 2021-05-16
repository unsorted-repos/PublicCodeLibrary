#!/usr/bin/env python
# -*- coding: utf-8 -*-
# pip install svglib
from PIL import Image, ImageDraw, ImageFont #dynamic import
from svglib.svglib import svg2rlg
from reportlab.graphics import renderPM

import os
rootdir = 'gifs'
extensions = ('.gif')

for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        ext = os.path.splitext(file)[-1].lower()
        if ext in extensions:
            print (os.path.join(subdir, file))

            gif=os.path.join(subdir, file)
            img = Image.open(gif)
            img.save(gif+".png",'png', optimize=True, quality=100)            

extensions = ('.svg')
for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        ext = os.path.splitext(file)[-1].lower()
        if ext in extensions:
            print (os.path.join(subdir, file))

            gif=os.path.join(subdir, file)
            print(f'svgpath={gif}')
            drawing = svg2rlg(gif)
            renderPM.drawToFile(drawing, gif+".png", fmt="PNG")
