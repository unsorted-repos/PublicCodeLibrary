#!/bin/bash
#Version: 6.
# No dynamic path yet.

#Start runtime timer
START=$(date +%s)

# TODO 0.: Change this to your own personal backup location! (In this linux bash script /mnt/c means the C:/ in windows, /mnt/e/ = E:/)
backupPath="$/mnt/c/task backup/"

for x in /home/*; do
  
  userFolder="$x"
  echo "$userFolder"
done



# Creating backup folders
# Backing up .task file/folder:
sudo mkdir -p "$backupPath"$(date +%Y%m%d%H%M)"/dotTask/"
# Backing up .taskrc file/folder:
mkdir -p "$backupPath"$(date +%Y%m%d%H%M)"/dotTaskrc/"
# Backing up .bashrc file: (for automatic startup of TW)
mkdir -p "$backupPath"$(date +%Y%m%d%H%M)"/autoStartTw/"
# Backing up Cronjobs: (for periodic backing up)
mkdir -p "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"
# Backing up mbackup script, as well as custom sort script:
mkdir -p "$backupPath"$(date +%Y%m%d%H%M)"/maintenance/"


# The backupfolder is named: "date + <the current date and time in minutes>", however if the backup is started at 13:59:50 and takes longer than 10 seconds,
# The last file will be placed in backupfolder " date + <the current date and time in 1 minute later that the previous file>"" That would smear out 
# a single backup over multiple folders. I don't want that so it first checks if the folder for the previous minute already existed, 
# and puts it in there if it indeed exists. The example for a check 2 minutes ago stands on line 31. 

# TODO 0: If you get the message saying backup took longer than 58 seconds, copy this code analogous to the other backing up of files. 
# 	0.1 (Notice the 2 (representing a reference to a backup folder of 2 minutes ago ) occurs in the first line and 2nd)
#	0.2 Don't forget to change your cronjob from once every 2 minutes to once every 3 minutes as it could otherwise not keep up the backup process.

# if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-2))"/dotTask/" ]; then
# 	sudo cp -r /home/a/.task/* "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-2))"/dotTask/"
# 	echo "Backup 1/4 successfull for previous minute"
# fi

if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTask/" ]; then
	sudo cp -r "$userFolder"/.task/* "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTask/"
	echo "Backup 1/4 successfull for previous minute"
fi


if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/dotTask/" ]; then
	sudo cp -r "$userFolder"/.task/* "$backupPath"$(date +%Y%m%d%H%M)"/dotTask/"  
	echo "Backup 1/4 successfull"
fi


# Backing up .taskrc file/folder:
#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTaskrc/" ]; then
	sudo cp -r 	"$userFolder"/.taskrc "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/dotTaskrc/"
	echo "Backup 2/4 successfull for previous minute"
fi

if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/dotTaskrc/" ]; then
	sudo cp -r "$userFolder"/.taskrc "$backupPath"$(date +%Y%m%d%H%M)"/dotTaskrc/"
	echo "Backup 2/4 successfull"
fi


# Backing up .bashrc file: (for automatic startup of TW)

#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/autoStartTw/" ]; then
	sudo cp -r "$userFolder"/.bashrc "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/autoStartTw/"
	echo "Backup 3/4 successfull for previous minute"
fi

if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/autoStartTw/" ]; then
	sudo cp -r "$userFolder"/.bashrc "$backupPath"$(date +%Y%m%d%H%M)"/autoStartTw/"
	echo "Backup 3/4 successfull"
fi


# Backing up Cronjobs: (for periodic backing up)
#In case the minute just passed (No backup is made at 00 minutes.)#<copy and adjust>
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r /etc/crontab "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/"
	echo "Backup 4/4 successfull for previous minute"
fi
if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r /etc/crontab "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"
	echo "Backup 4/4 successfull"
fi


# Backing up cronjob called autoBackup.sh(this file): (for periodic backing up)#<copy and adjust>
#In case the minute just passed (No backup is made at 00 minutes.)
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/autoBackup.sh "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/"
	echo "Backup 5/5 successfull for previous minute"
fi
if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/autoBackup.sh "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/"
	echo "Backup 5/5 successfull"
fi

# Backing up cronjob called autoBackup.sh(this file): (for periodic backing up)#<copy and adjust>
#In case the minute just passed (No backup is made at 00 minutes.)
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/customSort.sh "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/maintenance/"
	echo "Backup 6/6 successfull for previous minute"
fi
if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/customSort.sh "$backupPath"$(date +%Y%m%d%H%M)"/maintenance/"
	echo "Backup 6/6 successfull"
fi

# Backing up cronjob called autoBackup.sh(this file): (for periodic backing up)#<copy and adjust>
#In case the minute just passed (No backup is made at 00 minutes.)
if [ -d "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/javaSort.jar "$backupPath"$(date +%Y%m%d%H)$(($(date +%M)-1))"/maintenance/"
	echo "Backup 6/6 successfull for previous minute"
fi
if [ -d "$backupPath"$(date +%Y%m%d%H%M)"/scheduledCronjobs/" ]; then
	sudo cp -r "$userFolder"/maintenance/javaSort.jar "$backupPath"$(date +%Y%m%d%H%M)"/maintenance/"
	echo "Backup 6/6 successfull"
fi



#compute time to store backups
ls -R /etc > /tmp/x
rm -f /tmp/x
# your logic ends here
END=$(date +%s)
DIFF=$(( $END - $START ))

if [ "$DIFF" -gt "50" ]; then
	echo "Took longer than 58 seconds, you should add a check for creation of a backupfolder 2 minutes earlier in stead of just 1 minute earlier. Do that in file: /home/<username>/autoBackup.sh for all 5 instances/file/folder type backups (Example in line 27 of this file)"
fi
