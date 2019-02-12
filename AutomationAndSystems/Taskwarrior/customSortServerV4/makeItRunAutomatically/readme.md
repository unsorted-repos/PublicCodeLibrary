Todo: 
1.Clean up instructions
2. Make sure order is logical, now it says create bashrc then do something crontab in the middle first, and then user doesn't know where to go next. Make it clear it is just: create crontab, create .bashr afterwards.
2. Test crontab in sudo shell
3. Reduce amount of text in instructions
4. Make instructions general
5. Find out how to use taskwarrior without requireing sudo, then rewrite instructions.


# Instructions:

1. How this works is: a crontab is a file/list that contains a schedule for commands/programs to run periodically. the crontab/cronjob is executed by the cron service. The cron service you can automatically run at startup with a .bashrc file. a .bashrc file is a file that contains a list of commands that are executed automatically when you start up WSL Ubuntu. This way you don't have to do anything for your sort script to run in the background. That's also how the automatic periodic backups of your taskwarrior instance are created.

2. Copy the JavaServerSort.jar file to your taskwarrior installation first with:
```
sudo cp "/mnt/c/...<your path to JavaServerSort.jar>/JavaServerSort.jar" ~/maintenance/    
```
3. Then enter:
 ```
cd ~
```
4. Then first make the crontab file before you make the .bashr file, because the .bashr starts/opens a new "shell" as "sudo/?root?". That basically means that in your terminal a new terminal is opened without you noticing. Terminal normally executes all commands you feed it, as a certain user. normally you start as a "normal user". but in the sudo shell you are a sudo user with sudo permissions. That way you don't have to type "sudo task..." everytime, but you can just type `task ..`. This is relevant because your crontab also runs as a certain user. We will run our crontab in the normal user, but work in the sudo user. If you want to create a crontab/cronjob, you type `crontab -e`. That opens the crontab for the user you are in, so in the normal user/terminal `crontab -e` opens the crontab of the normal user and `sudo crontab -e` opens the crontab of the sudo user. But in the .bashrc created sudo shell, `crontab -e` opens the crontab of the sudo user, and I currently do not know how to open the normal user crontab from sudo shell and I have not tested the crontab in the sudo user. Hence we run the crontab from the normal user and work in the sudo shell opened by .bashrc at startup. 

## First go to step 6. Option 1 step

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
	
    2. Get the path variable as:
    `echo $Path`
    
    3. find the path of your java installation with: either: whereis java but sometimes that returns a path that does not really exist on your ubuntu. so browse to that path and check if it exists, if yes, find the bin folder in there. If not perhaps try whereis jvm.
	then look into those folders. And look int /usr/lib/jvm for me that resulted in:
```
default-java  
java-1.8.0-openjdk-amd64  
java-8-openjdk-amd64
```
    4. Get the /bin folders of those, look inside, for me that resulted in:
```
/usr/lib/jvm/default-java/bin
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin
/usr/lib/jvm/java-8-openjdk-amd64/bin
```
    5. Now you have 3 folders in which Java might be and a path folderr, for me the path folder (Get the path in normal user, not in the sudo shell) was:
```
/home/a/bin:/home/a/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/mnt/c/Program Files (x86)/Common Files/Oracle/Java/javapath_target_124854515:/mnt/c/python36/Scripts:/mnt/c/python36:/mnt/c/ProgramData/Oracle/Java/javapath_target_1790890:/mnt/c/Python27/Lib/site-packages/PyQt4:/mnt/c/Windows/System32:/mnt/c/Windows:/mnt/c/Windows/System32/wbem:/mnt/c/Windows/System32/WindowsPowerShell/v1.0:/mnt/c/Program Files (x86)/ATI Technologies/ATI.ACE/Core-Static:/mnt/c/Python27:/mnt/c/Python27/DLLs:/mnt/c/Python27/Scripts:/mnt/c/Python27/gnuplot/binary:/mnt/c/Program Files (x86)/pythonxy/SciTE-3.3.2-3:/mnt/c/Program Files (x86)/pythonxy/console:/mnt/c/MinGW32-xy/bin:/mnt/c/Program Files/OpenVPN/bin:/mnt/e/Program Files/Java/jdk-10.0.2/bin:/mnt/c/Users/a/AppData/Local/Microsoft/WindowsApps:/mnt/e/texlive/2018/bin/win32:/mnt/c/Program Files/Git/bin:/snap/bin
```
   6. Now add the 3 paths before this path, separate paths with a double dot:
PATH=/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin:/home/a/bin:/home/a/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/mnt/c/Program Files (x86)/Common Files/Oracle/Java/javapath_target_124854515:/mnt/c/python36/Scripts:/mnt/c/python36:/mnt/c/ProgramData/Oracle/Java/javapath_target_1790890:/mnt/c/Python27/Lib/site-packages/PyQt4:/mnt/c/Windows/System32:/mnt/c/Windows:/mnt/c/Windows/System32/wbem:/mnt/c/Windows/System32/WindowsPowerShell/v1.0:/mnt/c/Program Files (x86)/ATI Technologies/ATI.ACE/Core-Static:/mnt/c/Python27:/mnt/c/Python27/DLLs:/mnt/c/Python27/Scripts:/mnt/c/Python27/gnuplot/binary:/mnt/c/Program Files (x86)/pythonxy/SciTE-3.3.2-3:/mnt/c/Program Files (x86)/pythonxy/console:/mnt/c/MinGW32-xy/bin:/mnt/c/Program Files/OpenVPN/bin:/mnt/e/Program Files/Java/jdk-10.0.2/bin:/mnt/c/Users/a/AppData/Local/Microsoft/WindowsApps:/mnt/e/texlive/2018/bin/win32:/mnt/c/Program Files/Git/bin:/snap/bin

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
	*/5 * * * * sudo sh -c "java -jar /home/a/maintenance/JavaServerSort.jar > sudo /home/a/maintenance/log.txt 2>&1"
	```
	4. ctrl+x to quit, yes to save.
	
    4. Yielding a total crontab file of:
```
PATH=/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin:/usr/lib/jvm/java-8-openjdk-amd64/bin:/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin:/home/a/bin:/home/a/.local/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/mnt/c/Program Files (x86)/Common Files/Oracle/Java/javapath_target_124854515:/mnt/c/python36/Scripts:/mnt/c/python36:/mnt/c/ProgramData/Oracle/Java/javapath_target_1790890:/mnt/c/Python27/Lib/site-packages/PyQt4:/mnt/c/Windows/System32:/mnt/c/Windows:/mnt/c/Windows/System32/wbem:/mnt/c/Windows/System32/WindowsPowerShell/v1.0:/mnt/c/Program Files (x86)/ATI Technologies/ATI.ACE/Core-Static:/mnt/c/Python27:/mnt/c/Python27/DLLs:/mnt/c/Python27/Scripts:/mnt/c/Python27/gnuplot/binary:/mnt/c/Program Files (x86)/pythonxy/SciTE-3.3.2-3:/mnt/c/Program Files (x86)/pythonxy/console:/mnt/c/MinGW32-xy/bin:/mnt/c/Program Files/OpenVPN/bin:/mnt/e/Program Files/Java/jdk-10.0.2/bin:/mnt/c/Users/a/AppData/Local/Microsoft/WindowsApps:/mnt/e/texlive/2018/bin/win32:/mnt/c/Program Files/Git/bin:/snap/bin
*/1 * * * * touch /home/a/maintenance/sudoNonRootCron
*/1 * * * * sudo java -jar /home/a/maintenance/JavaServerSort.jar > /home/a/maintenance/log.txt 2>&1
*/1 * * * * sudo java -jar /home/a/maintenance/JavaServerSort.jar > /home/a/maintenance/log.txt 2>&1
*/3 * * * * sudo sh -c "java -jar /home/a/maintenance/JavaServerSort.jar > sudo /home/a/maintenance/log.txt 2>&1"
*/1 * * * * sudo touch /home/a/maintenance/afterJava
*/3 * * * * Sudo touch /home/maintenance/startingNewJava
*/3 * * * * sudo sh -v /home/a/maintenance/autoBackup.sh
```
    (The touches are just to check if the crontab is running. It will not output the files created in java, but it will run the customSort).
    


   ## That's it for crontab.
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
