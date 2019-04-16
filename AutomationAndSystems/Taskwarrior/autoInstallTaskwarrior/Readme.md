This will* automatically install and configures taskwarrior and taskwarrior server. *Not yet finished/functional.

To install taskwarrior on windows 10:
start>store>search ubuntu>install WSL ubuntu 16.04.
Open the ubuntu app you just installed
wait 5 minutes till it's done, enter a username and pwd.

Then run: (1st command takes+-: 30 mins, 2nd command: 4 mins)

`yes | sudo apt update`

`yes | sudo apt install default-jre` 

`export TASKDDATA=/var/taskd`

`sudo mkdir -p $TASKDDATA`

If you run in to troubles in that installation of Java, you can try:

`yes | sudo apt install default-jre --fix-missing`

Then compile or download the .jar file in this repo.
Put it for example in `c:/path with space/` and in WSL ubuntu run it with:

`java -jar <the path to your file>autoInstallTaskwarrior.jar`

E.g. if you stored it in folder `C:/path with space/` the command becomes:

`sudo java -jar "/mnt/c/path with space/autoInstallTaskwarrior.jar"`
