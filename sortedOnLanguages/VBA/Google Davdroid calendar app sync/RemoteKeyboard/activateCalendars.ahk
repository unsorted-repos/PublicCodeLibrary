; Started by pressing alt+arrow right
!Right::


Click, 1300,400
Sleep, 500

counting := 36
nrOfSteps :=0 


Send {Up}
Send {Up}
Send {Up}
Send {Up}
Send {Up}
Send {Up}
Send {Up}
Send {Up}
Send {Up}

Loop, %counting%
{
nrOfSteps := nrOfSteps + 1

Loop, %nrOfSteps%
{
	Sleep, 500
	Send {Down}
	Sleep, 500
}
Send {Enter}
Sleep, 1500
Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {Down}
Sleep, 500
Send {Enter}
Sleep, 500
Send {Ctrl Down}Q{Ctrl Up}
Sleep, 500
}

;Learn how to activate all:
;100* up
;down 
;enter
;down
;down
;down
;enter
;ctrl+Q

