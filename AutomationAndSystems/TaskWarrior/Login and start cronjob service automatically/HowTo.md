This manual explains how to make Taskwarrior automatically:

 0. Start/login upon opening Windows Subsystem for Linux (WSL) Ubuntu 16.04
 1. Create periodic backups when Opening WSL Ubuntu 16.04
 2. TODO: Automatically run sorting script to generate custom sorted reports created in the other folder in TaskWarrior in this repo.


It consist of 5 steps:
 0. Set your desired backup storage folder. (This is the folder where your taskwarrior data is backed up to).
 1. Copy the autoBackup.sh file in this repository to `/home/<yourUsername>/autoBackup`. (This script backs up your whole taskwarrior data {Except for the hooks} to a certain folder under the current time.)
 2. Create a cronjob (that is something that executes a list of commands(and scripts) every predetermined timeperiod. In this case it will execute the `autoBackup.sh` script, as well as the `customSortVx.sh`.
 3. Run/enable the cronjob service at startup, so that the cronjob of point 1 starts when you open the WSL Ubuntu.
 4. Remove prompting for password to start the cronjob service automatically, so you don't get bothered for input if you open WSL Ubuntu.

 **0. Set backup folder in autoBackup.sh:**
 
 0.1 Download the autoBackup.sh from this repository. To for example folder `E:\somefolder\TaskWarrior\auto startup\`.
 
 0.2 Right Mouse Button (RMB) on `autoBackup.sh`>Open with notepad, or whatever text editor/bash editor you use. 
 
 0.3 Right now it backs up everything to `C:/task backup`. If you want to change that: replace all the `c/task backup/` with whatever you want it to be. Rembember to put the slashes to the right like `/` in stead of (iso) `\` for Linux.

 **1. Copy autoBackup.sh:**

 1.1 Command (notice the change of `\` direction to `/`:

`cp -a "/mnt/e/somefolder/TaskWarrior/auto startup/autoBackup.sh" "/home/<yourUsername>/autoStartup/"`

 **2. Creating a functioning cronjob:**

 1. Browse to folder `/etc/` in your WSL Ubuntu.
 2. Then in folder `/etc/` enter:`sudo nano crontab`
 3. In that file named crontab enter your command.
 4. E.g.: `*/1 * * * * root touch /var/www/myFile`
 5. To create a file named `myFile` in location `/var/www/` every minute.
 6. For completeness: `*/2 * * * * root touch /var/www/myFile` means: the command "touch /var/myFile" is executed every two minutes with root access.

An example of the `crontab` file could look like (I only added the last line, the rest was already there in my setup): 

```
	# /etc/crontab: system-wide crontab
	# Unlike any other crontab you don't have to run the `crontab'
	# command to install the new version when you edit this file
	# and files in /etc/cron.d. These files also have username fields,
	# that none of the other crontabs do.

	SHELL=/bin/sh
	PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin

	# m h dom mon dow user  command
	# Backing up .task file/folder:
	*/1 * * * * root sh -v /home/a/autoBackup.sh
```




**2. Enabling cronjob service**

To run a command automatically at startup of WSL Ubuntu 16.04 you can:

 1. cd to `/home/<your ubuntu user name>`
 2. `sudo nano .bashrc`
 3. The text editor nano then creates/opens a file `.bashrc`
 4. In that file a lot of examples can be shown already, write/paste the following code on the first line/above what was already there in the `.bashrc` file.
 
 ``` 
#get root
if [ ! -f /home/a/getRootBool ]; then
    echo "Getting sudo rights now."
    touch /home/a/getRootBool
    sudo -s
fi

# remove got root boolean for next time you boot up Unix
sudo rm /home/a/getRootBool

#Start cron service
sudo -i service cron start

#Startup taskwarrior
export TASKDDATA=/var/taskd
cd $TASKDDATA
sudo taskd config --data $TASKDDATA

taskdctl start
task sync
 ```
 
 6. Close the editor with: `ctrl+x`
 7. Save the file with `Y`
 8. Exit ubuntu 
 9. Now when you restart Ubuntu will log in Taskwarrior and start the cronjob service, but still prompt you for your Ubuntu password.

 **3. Removing prompt for password:**
Using: https://askubuntu.com/questions/147241/execute-sudo-without-password

 1. Open WSL ubuntu 16.04 (terminal)
 2. `sudo visudo`
 3. At the bottom of the file add line:
`<your WSL ubuntu username> ALL=(ALL) NOPASSWD: ALL`
 4. E.g with username zq you would add the following line to the bottom of the file:
 5. `zq ALL=(ALL) NOPASSWD: ALL`
 6. ctrl+x to exit
 7. `y` followed by `<enter>` to save.
 8. Then again, close ubuntu and re-open it and verify that the cron service is running automatically when you boot/open WSL ubuntu 16.04 without prompting for password.
 10. (you can check with command: `sudo service cron status`.)

The code to prevent prompting for password at boot would for example look like (I only added the last line, the rest was already there in my setup):


	#
	# This file MUST be edited with the 'visudo' command as root.
	#
	# Please consider adding local content in /etc/sudoers.d/ instead of
	# directly modifying this file.
	#
	# See the man page for details on how to write a sudoers file.
	#
	Defaults        env_reset
	Defaults        mail_badpass
	Defaults        secure_path="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/s$
	# Host alias specification

	# User alias specification

	# Cmnd alias specification

	# User privilege specification
	root    ALL=(ALL:ALL) ALL

	# Members of the admin group may gain root privileges
	%admin ALL=(ALL) ALL

	# Allow members of group sudo to execute any command
	%sudo   ALL=(ALL:ALL) ALL

	# See sudoers(5) for more information on "#include" directives:

	#includedir /etc/sudoers.d
	%sudo ALL=NOPASSWD: /etc/init.d/cron
	zq ALL=(ALL) NOPASSWD: ALL

Working towards this solution, I learned cronjobs are intended for things to run periodically rather than at specific events such as startup. To run things at startup in WSL you can use the file  `/home/<username>/.bashrc`.


 ###TODO:###
  0. Make backup storage location a parameter in stead of requiring it to be replaced in all the lines.
  1. Add the location of the customsort and this script itself to the backup script autoBackup.sh to prevent requiring to execute this manual again if you re-install taskwarrior.
  2. Add customSort with 10 minute intervals to cronjob.
