# Instructions see tw pdf guide chapters:
Converting tw to CSV
Troubleshooting: Find errors with tool:Converting csv to HTML

# In short:
1. Put it in a folder you want, for example copy export-html4.pl to: C:/taskToCSV/ with command:
```
sudo cp -r /usr/share/perl5/JSON.pm "/mnt/c/taskToCSV/perl/
```
2. The pl file will automatically look for files in `/home/a/.task/pending.data`, so you don't have to export the pending.data file. (TODO: check if it automatically looks in `/home/a/task` or in `/home/<your ubuntu username>/.task/pending.data`)

3. Then execute the pl file with:
```
sudo perl /mnt/c/taskToCSV/export-html4.pl
```

4. That file will scan all tasks from JSON format and try to convert them to HTML format. It will then automatically tell you where the error in the file format occurs.

5. Then you can find the error, for example, I added the duration wrong for 3 tasks. After deleting the duration attribute for those 3 tasks using:
```
sudo task <task id> modify duration:
```
The pl script ran fine. But the `sudo task sync` command still returned: 
```
Sync failed.  The Taskserver returned error: 500 Unrecognized taskwarrior file format.
```

6. type `exit` untill WSL Ubuntu 16.04 closes. Then restart it, check if it syncs fine. if not proceed with step 7:

7. If the `.pl` script runs fine but you still can't sync, run `sudo task sync init`. This pushes the tasks to the server like it's the first time. Then it'll return something like: 
```
Sync failed.  The Taskserver returned error: 500 ERROR: Could not find common ancestor for 931b463b-a130-44fe-96c0-ad60e88b810e
```
8. Now you know which additinoal task has a problem, perhaps in taskwarrior 2.6 purging the task would solve it, but in taskwarrior 2.5 you cannot purge task yet, you can only delete or mark it done, but then it is just moved to another tasklist `backlog.data` or `completed.data` or `undo.data`, which would mean the error persists. So to fix it, you first:

9. Find the organisation your taskwarrior (tw) is installed in with command: (if it says permission denied, first type `sudo su` `<enter>`) 

```
cd $TASKDDATA/orgs
```
To see your organisation name type:
```
dir
```

10. For me, I named my organisation `Public` hence I follow up with:
```
cd Public/users
```

11. Find my user code with:
```
dir
```
it shows a long code like `5v8a156-12e4-4478-8ae2-24b123a3a493`.

12. go into your user folder with:
```
cd <your taskwarrior user code>
```
E.g. for me it was:

```
cd 5v8a156-12e4-4478-8ae2-24b123a3a493
```

13. Then type
```
sudo rm tx.data
```

14. For me the file `tx.data` was not there to start with. So I typed `exit` untill Ubuntu closed, then restarted it and it synced fine.