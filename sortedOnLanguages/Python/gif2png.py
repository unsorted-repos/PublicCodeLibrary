#!/usr/bin/env python
# -*- coding: utf-8 -*-
from PIL import Image, ImageDraw, ImageFont #dynamic import


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

