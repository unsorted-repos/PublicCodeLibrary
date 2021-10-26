#!/bin/sh
# Download specific sub:
youtube-dl --write-sub --sub-lang en --skip-download https://www.youtube.com/watch?v=BtCVvKFjQbU
# Download auto generated subtitles:
youtube-dl --write-auto-sub --sub-lang en --skip-download https://www.youtube.com/watch?v=BtCVvKFjQbU
