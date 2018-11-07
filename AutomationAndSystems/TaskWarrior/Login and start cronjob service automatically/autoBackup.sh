#!/bin/bash
#If you need to add a 2 (or n) minute backupfolder check, search for the ifstatement with keyword <Copy and adjust>
#Start runtime timer
START=$(date +%s)


# Creating backup folders
# Backing up .task file/folder:
sudo mkdir -p "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTask/"
# Backing up .taskrc file/folder:
mkdir -p "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTaskrc/"
# Backing up .bashrc file: (for automatic startup of TW)
mkdir -p "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/autoStartTw/"
# Backing up Cronjobs: (for periodic backing up)
mkdir -p "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"


#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTask/" ]; then
	sudo cp -r /home/a/.task/* "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTask/"
	echo "Backup 1/4 successfull for previous minute"
fi


if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTask/" ]; then
	sudo cp -r /home/a/.task/* "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTask/"  
	echo "Backup 1/4 successfull"
fi


# Backing up .taskrc file/folder:
#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTaskrc/" ]; then
	sudo cp -r /home/a/.taskrc "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTaskrc/"
	echo "Backup 2/4 successfull for previous minute"
fi

if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTaskrc/" ]; then
	sudo cp -r /home/a/.taskrc "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/dotTaskrc/"
	echo "Backup 2/4 successfull"
fi


# Backing up .bashrc file: (for automatic startup of TW)

#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/autoStartTw/" ]; then
	sudo cp -r /home/a/.bashrc "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/autoStartTw/"
	echo "Backup 3/4 successfull for previous minute"
fi

if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/autoStartTw/" ]; then
	sudo cp -r /home/a/.bashrc "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/autoStartTw/"
	echo "Backup 3/4 successfull"
fi


# Backing up Cronjobs: (for periodic backing up)
#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r /etc/crontab "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/"
	echo "Backup 4/4 successfull for previous minute"
fi
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r /etc/crontab "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"
	echo "Backup 4/4 successfull"
fi


# Backing up cronjob called autoBackup.sh(this file): (for periodic backing up)#<copy and adjust>
#In case the minute just passed (No backup is made at 00 minutes.)
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r ~/autoBackup.sh "/mnt/c/task backup/date"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/"
	echo "Backup 5/5 successfull for previous minute"
fi
if [ -d "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r /home/a/autoBackup.sh "/mnt/c/task backup/date"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"
	echo "Backup 5/5 successfull"
fi

#compute time to store backups
ls -R /etc > /tmp/x
rm -f /tmp/x
# your logic ends here
END=$(date +%s)
DIFF=$(( $END - $START ))

if [ "$DIFF" -gt "50" ]; then
	echo "Took longer than 58 seconds, you should add a check for creation of a backupfolder 2 minutes earlier in stead of just 1 minute earlier. Do that in file: /home/<username>/autoBackup.sh for all 5 instances/file/folder type backups"
fi
