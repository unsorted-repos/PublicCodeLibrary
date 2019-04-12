This will* automatically install and configures taskwarrior and taskwarrior server. *Not yet finished/functional.

To install taskwarrior on windows 10:
start>store>search ubuntu>install WSL ubuntu 16.04.
Open the ubuntu app you just installed
wait 5 minutes till it's done, enter a username and pwd.

Then run:

`yes | sudo apt update && sudo apt upgrade`

`yes | sudo apt install default-jre` 

If you run in to troubles in that installation of Java, you can try:

`yes | sudo apt install default-jre --fix-missing`

Then compile or download the .jar file in this repo.
Put it for example in `c:/path with space/` and in WSL ubuntu run it with:

`java -jar <the path to your file>autoInstallTaskwarrior.jar`

E.g.

`sudo java -jar "/mnt/c:/path with space/autoInstallTaskwarrior.jar"`
