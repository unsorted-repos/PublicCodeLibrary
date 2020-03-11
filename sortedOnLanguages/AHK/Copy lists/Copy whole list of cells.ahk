; The semicolon renders the line a comment.
; If your screen width is larger than 1300*2 then:
; 0. Increase the 1300 listed in line 15, to at least 100 more than half of your screen width
; 1. You might want to decrease the -414 even more to for example -600 to make sure it clicks on an empty part of your excel/calc top bar.

!Right::

Loop, 44

{
Send {Ctrl Down}c{Ctrl Up}
Sleep, 500
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500
Click, right
Sleep, 500
Send {AltTab}
Click, -414,10
Sleep, 1000
Send {Down}
}

return ;
