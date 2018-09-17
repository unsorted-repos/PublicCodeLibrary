Hi, if you often use different calendars, 
for example if you change courses you take, it can be time consuming to manually add them in davdroid.

These scripts allows one to add the calendars you have in your google calendar account to quickly be entered in Davdroid.
Davdroid then syncs those calendars to your phone.

Advantage:
You don't have to have a google app on your phone. 

Disadvantage: 

It is not completely automated. To sync the calendars in the phone, a user has to:

  0. Download the webpage: https://calendar.google.com/calendar/r?pli=1 to (relative to this repository folder)/WebsiteSource
  1. rename that downloaded <some name>.html to "google.txt"
  2. Open the excel and click the start button.
  3. Then open the CommandPhone.ahk
  4. Press alt+arrow to the right to engage the copy process.
    4.1 Press a + symbol in the davdroid app for every calendar
    4.2 Press "enter" on the pc after the "+" symbol has been pressed.

Possible improvements:

0. Automate pressing the "+"-symbol in Davdroid through keyboard commands from PC using AHK.

Or:

1. Finding the settings file of Davdroid
1.1 Finding in that file, the storage location of the calendar base url, user name, password and calendar name.
1.2 Simply writing that file directly from Excel.
1.3 Automatically transferring that file to the right location on your phone. (E.g. Through mega or ftp).


