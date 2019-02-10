Hi, this part of the repo customizes your taskwarrior setup. You can:
1. login to sudo automatically in {Windows Subsystems for Linux (WSL)} Ubuntu (16.04) when you start up ubuntu.
    1. To do so, look in folder `Login and start cronjob service automatically` file `.bashrc`.
2. Start a cronjob automatically when starting up (WSL) ubuntu file `crontab....`. 
    1. To do so, look in folder `Login and start cronjob service automatically`.
3. Automatically backup your taskwarior files every `x` minutes, or on every minute `y`.
    1. To do so first setup the automatic login with `.bashrc`,
    followed by an automatic `crontab` running the autobackup `.sh` shell script. 
    2. For the `.sh` shell script, look into folder: AutoBackup.
4. Sort your tasks in a custom way. Imagine your own urgency threshold. 
    1. Every task above this threshold is listed at the bottom of your report, sorted on urgency.
    (The bottom is what you see immediatly when you run a {custom} report)
    2. Everything below this urgency threshold is sorted on project (alphabetically) so that you have a good coherent 
    overview of your non-urgent project tasks. 
    3. All tasks below the urgency threshold without project are placed between the tasks sorted on project,
    and the tasks above the urgency threshold. That way you can see quite fast which tasks you have not yet
    allocated to a proper project. 
5. Automatically scan your `pending.data` tasks for `JSON` formatting errors. If you're learning to use
taskwarrior and do some funky stuff, generate a "taskwarrior file format,
