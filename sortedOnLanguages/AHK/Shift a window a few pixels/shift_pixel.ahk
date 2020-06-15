; Shift the wsl Ubuntu terminal 1 pixel to the right
^+c::
{
 Send, ^c
 WinGetPos, X, Y, , , A
 WinMove, A, , 1 + X, 0 + Y

 Return
}