Hi let's first discuss the meaning of bash and shell. (my interpretation of:)
Source: https://stackoverflow.com/questions/5725296/difference-between-sh-and-bash#5725402

sh = Shell Command Language is a programming language, 
bash is an implementation that is compatible* with shell.

Both languages are described/constrained by the POSIX standard, yet Bash "predates=existed"
before the POSIX standard, to wich it adheres.

So if you draw a ven diagram with a large circle of shell, bash used to be in there.

*Nowadays there are parts of bash which fall out of that circle. For example:
    0. Modern Debian and Ubuntu Systems.
    1. Busybox 
    3. BSD's and in general any non-Linux systems.

*********************************************************************************************
Source: https://stackoverflow.com/questions/11899843/fixing-sublime-text-2-line-endings#11900286
Important:
0. Problem:
0.1 If you edit/write/create .sh files in windows an "enter/new line" is stored in somewhere
in the file. The way these are stored are good for windows but produce errors like: 

    "./PrintAnArray.sh: line 30: $'\r': command not found"

if you run them from Debian/Ubuntu.

1. Solution:
1.1 In for example Subliminal, you can change the way these line endings are stored by:
"Open the .sh file in subliminal>click view>click line endings>click unix"

2. Now the line endings are stored different, not causing that error in Linux (Debian/Ubuntu).
*********************************************************************************************
