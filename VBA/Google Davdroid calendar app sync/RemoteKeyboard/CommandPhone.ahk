; The semicolon renders the line a comment.
; If your screen width is larger than 1300*2 then:
; 0. Increase the 1300 listed in line 15, to at least 100 more than half of your screen width
; 1. You might want to decrease the -414 even more to for example -600 to make sure it clicks on an empty part of your excel/calc top bar.

; Started by pressing alt+arrow right
!Right::

Loop, 30

{

;Starts in excel cell F3

;Switch to CMD remote keyboard
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500

;this is where you send the command.
Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {enter}

; Back in Excel
Sleep, 500
Send {AltTab}
Click, -414,10

Sleep, 1000
Send {Down} ;back in column F in the next row
; Row 18
Send {Ctrl Down}c{Ctrl Up}

;Switch to CMD remote keyboard
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500
Send {Down}
Sleep, 500
Click, right
Send, {LCtrl Down}v{LCtrl Up}
Send, {Left}
Sleep, 2000
Send {Up}
Sleep, 2000

; Row 19
; Back in Excel
Sleep, 500
Send {AltTab}
Click, -414,10
Sleep, 1000

Send {Right} ;back in column G in the next row
Send {Ctrl Down}c{Ctrl Up}

;Switch to CMD remote keyboard
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500

Click, right
Send, {LCtrl Down}v{LCtrl Up}

; Row 20
; Back in Excel
Sleep, 500
Send {AltTab}
Click, -414,10
Sleep, 1000

Send {Right} ;back in column H in the next row
Send {Ctrl Down}c{Ctrl Up}

;Switch to CMD remote keyboard
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500

Click, right
Send, {LCtrl Down}v{LCtrl Up}


Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {Enter}
Sleep, 6000

; Row 22
; Back in Excel
Sleep, 500
Send {AltTab}
Click, -414,10
Sleep, 1000

Send {Right} ;back in column H in the next row
Send {Ctrl Down}c{Ctrl Up}

;Switch to CMD remote keyboard
Send {AltTab}
Sleep, 500
Click, 1300,400
Sleep, 500

Send {Down}
Sleep, 500

Loop, 200
{
	Send {Right}
}
Send {Up}
Send {Up}
Loop, 200
{
	Send {Backspace}
}
Sleep, 1000
Click, Right
Send, {LCtrl Down}v{LCtrl Up}
Sleep, 1000
Send {Down}
Sleep, 500
Send {Right}
Sleep, 500
Send {Enter}
Sleep, 1000

MsgBox [, 0, Title, Please click the plus on your phone in Dravdoid before proceeding, ]

; Row 22
; Back in Excel
Sleep, 500
Send {AltTab}
Click, -414,10
Sleep, 1000

Send {Left} ;back in column G in the next row
Sleep, 500
Send {Left} ;back in column F in the next row
Sleep, 500
Send {Left} ;back in column F in the next row
Sleep, 1000
}

return ;