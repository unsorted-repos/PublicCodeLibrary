::If you want the output that SomeBatFile.bat generates, so not a copy of the commands that SomeBatFile.bat executes, but the actual response on those executions, then this is your answer! Don't put this code in SomeBatFile.bat but create a second separate CallSomeBatFile.bat and put this code in there. Then Execute CallSomeBatFile.bat, and it works like a charm!


::Normally echo >> log.txt only stores the commands, and not the output of what that command returns.
::This batch file 1. calls a different batch file 2., and prints the output  of 2. in file log2.txt 
::(which is located in the same folder as file1).

@echo off
:: A single arrow like: 
call SomeBatchFile.BAT >log.txt 2>&1
:: Overwrites the content of the file with the new outputs.

:: A double arrow before like:
::call SomeBatchFile.BAT >>log.txt 2>&1 appends the new output to the file