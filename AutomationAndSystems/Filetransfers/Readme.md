Open cmd with admin, then if you just want to copy a folder:
```
robocopy "E:\Users\somename" "J:\2019-01-13" /MIR  /LOG:J:\2019-11-13-backup-somename.log
```

other attempts, excluding files with /xf:
```
robocopy "E:\18-09-19 Document structure" "J:\19-05-16 restore" /MIR /XF on-modify.timetracking /LOG:J:\todaysdate-backup.log \
```