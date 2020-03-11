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
  2. Open the excel and click the "0" button, followed by the "start" button.
  3. Press alt+arrow to the right to engage the copy process.
 
Possible improvements:

0. Automating the downloading the source of webpage https://calendar.google.com/calendar/r?pli=1 from VBA (without entering your login data in Excel.)

Or:

2. Finding the settings file of Davdroid
2.1 Finding in that file, the storage location of the calendar base url, user name, password and calendar name.
2.2 Simply writing that file directly from Excel.
2.3 Automatically transferring that file to the right location on your phone. (E.g. Through mega or ftp).
