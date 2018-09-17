Scenario: You have a list of cells with text that you need to copy to a cmd or Windows console
environment. (Where you can paste the clipboard content with a right mouse button click (RMB). 

Short usage instructions:
0. Install open source software Autohotkey
1. Open "Copy whole list of cells.ahk"
2. Open source window in left half of screen
3. Open Target window in right half of screen
4. Select starting cell
5. press alt+right arrow.



Nomenclature:
a. The cells are located in for example an excel sheet. The excel sheet/workbook is your "source window".
b. The cmd where you need to paste it in is called your "target window".

Complete usage instructions:
0. Put source window in the left half of your screen, you can do this:
	0.a (in Windows: activate your source window, press start key and fiddle with the left, right up and down arrows)
	0.b (In Windows: click and hold/drag the source windows top bar through the left border of your screen and release 
	the mouse button)

1. Put your target window on the right half of the windows

2. Calculate how many items need to be copied: e.g. copy cell 65 up to and including cell 74 = 10 items, then the number after 
loop becomes 10. In the script: 

2. Select the first item in the list

3. Press alt+arrow to the right. (First press and hold alt, then press the right button, and then quickly releace them both, 
preferably quite fast).

4. That is it, now it should start copying

How it copies:
0. It copies the active cell.
1. Makes clicks relative to the target- and source windows, depending on which one is currently active/selected.
	1.a Problem: So if your screen is much more than 1600 pixels wide, the relative 1300 pixels to the right to reach 
	the right window will not reach the right window (so it would just click the left hand screen, not switching tabs).
	1.b Solution: Adjust the horizontal 13xx to more than half of your screen width in pixels.
2. It clicks somewhere random in the body of the right window (target window) 
3. A RMB click to paste.
4. It clicks the top bar of source window. 
5. Then presses arrow down to select next item.
