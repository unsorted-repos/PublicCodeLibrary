#Instructions:

1. How this works is: a crontab is a file/list that contains a schedule for commands/programs to run periodically. the crontab/cronjob is executed by the cron service. The cron service you can automatically run at startup with a .bashrc file. a .bashrc file is a file that contains a list of commands that are executed automatically when you start up WSL Ubuntu. This way you don't have to do anything for your sort script to run in the background. That's also how the automatic periodic backups of your taskwarrior instance are created.

2. Copy the JavaServerSort.jar file to your taskwarrior installation first with:
```
sudo cp "/mnt/c/...<your path to JavaServerSort.jar>/JavaServerSort.jar" ~/maintenance/    
```
3. Then enter:
 ```
cd ~
```

4. Create your .bashrc file by typing:
```
cd ~
sudo nano .bashrc
```
5. Then you have 2 choices. Either you get sudo rights automatically when you startup, this is appaerently bad security protocol, cause that is like always doing everything as administrator, instead of knowing when and where you should do what with which rights. Practically, if all you do with WSL is run taskwarrior/timewarrior, currently the choice is: Do you wanna type "sudo task.." everytime you type a command, or just "task.."? 

##Important: Make sure you chose Either of the methods, do not create both crontabs because then there's a chance the Option I crontab interferes with the Option II crontab! So comment out all the commands of the crontab you're not using with `#`.

6. Option I:
    1. If you choose to adhere to best practice and not raise everything automatically to sudo, but just the commands that require, (so typing sudo task...) you put the following into .bashrc
	```
	#get root
	if [ ! -f /home/a/maintenance/getRootBool ]; then
	   echo "Getting sudo rights now."
	   touch /home/a/maintenance/getRootBool
	   #sudo -s
	fi

	# remove got root boolean for next time you boot up Unix
	sudo rm /home/a/maintenance/getRootBool

	#Start cron service
	#sudo -i service cron start #The -i starts the root cronjob
	sudo service cron start

	#Startup taskwarrior
	export TASKDDATA=/var/taskd
	cd $TASKDDATA
	sudo taskd config --data $TASKDDATA

	taskdctl start
	sudo task sync
	```    

    2. and edit/create the crontab/cronjob file with:
    ```
	crontab -e
    ```
    3. Substitute the following data in it:
	```
	##Check if it works:
	*/1 * * * * touch /home/a/maintenance/nonSudoCronjobIsRunning
	### m h dom mon dow user command

	# Backing up .task file/folder:
	*/3 * * * * sh -v /home/a/maintenance/autoBackup.sh

	### The next line is for the custom sorting, leave it out if you don’t use it:
	*/5 * * * * sudo java -jar ~/maintenance/JavaServerSort.jar
	```
	4. ctrl+x to quit, yes to save.
   
    5. that runs the crontab -e script with the above .bashrc without sudo. 

	6. restart WSL Ubuntu.
	7. Now if you remove ~/maintenance/nonSudoCronjobIsRunning it should re-appear after a minute. If it does not, it tells you the sudo cron service is not running correctly.

7. Option II: run crontab -e so you don't have to type sudo su every time:

	1. put the following into .bashrc
	```
	#get root
	if [ ! -f /home/a/maintenance/getRootBool ]; then
	   echo "Getting sudo rights now."
	   touch /home/a/maintenance/getRootBool
	   sudo -s
	fi

	# remove got root boolean for next time you boot up Unix
	sudo rm /home/a/maintenance/getRootBool

	#Start cron service
	#sudo -i service cron start #The -i starts the root cronjob
	sudo service cron start

	#Startup taskwarrior
	export TASKDDATA=/var/taskd
	cd $TASKDDATA
	sudo taskd config --data $TASKDDATA

	taskdctl start
	sudo task sync
	```	
	
	2. Restart WSL Ubuntu
	    
	3. Now when you type crontab -e command automatically refers to the crontab that was called with "sudo crontab -e" in the non-raised/normal permission profile.

	4. Hence modify the crontab -e of the raised permission profile as well by setting it to:
	```
	##Check if it works:
	*/1 * * * * touch /home/a/maintenance/sudoCronjobIsRunning
	### m h dom mon dow user command

	# Backing up .task file/folder:
	*/3 * * * * sh -v /home/a/maintenance/autoBackup.sh

	### The next line is for the custom sorting, leave it out if you don’t use it:
	*/5 * * * * sudo java -jar ~/maintenance/JavaServerSort.jar
	``` 

	5. ctrl+x to quit, yes to save.
	
	6. restart WSL Ubuntu.
	
	7. Now if you remove ~/maintenance/sudoCronjobIsRunning it should re-appear after a minute. If it does not, it tells you the sudo cron service is not running correctly.
