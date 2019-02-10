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
