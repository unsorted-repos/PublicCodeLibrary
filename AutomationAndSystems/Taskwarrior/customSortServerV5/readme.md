Hi, thanks for checking this project out!

I made a code skeleton for a javaproject that applies a custom sorting to my taskwarrior task lists. You can also run this script in the background automatically as soon as you startup WSL Ubuntu. To do so, create the `crontab` and `.bashrc` file described in the readme from folder`makeItRunAutomatically`.

I currently have the following doubts:

0. Could I have applied any inheritance in a logical sense?  
TaskDataSet contains TaskLists, 
TaskList contains Tasks
But neither have mutliple variants with different properties, hence I thought it would not be a correct
application of abstract classes and implements.

1. Recurrent tasks lead to a too large dataset that can't be set, because the custom sort ID is set/changed multiple times per second per recurrent task. All those modifications are stored increasing some file size to over 21800 lines. 

	1.b The attempted solution is to not set the customSort value of the recurrent parent/template tasks.

2. Testing the "task.setId()" using "task.getId()" seems like it is not thorough testing, the methods could individually still 
be faulty, but compensate eachothers faults. I have not yet been creative enough to test them individually.

Any feedback is much appreciated!


# Instructions

Compile yourself instructions (in Windows):
------------
### Prerequisites: ###

0. Installed JDK (8+) or higher (not sure if JRE is also required)

1. Link to JDK (8+ or higher:https://www.oracle.com/technetwork/java/javase/downloads/index.html)

2. Added Java Developement Kit (JDK (8+) to path.)

	2.1 Can be done by going to/finding the following path on your pc: <yourharddrive>:/programfiles/java/jdk-<versionnr>/bin
	
	2.2 click on start>type "environment variables" 
	
	2.3 then again click on environmentvariables or advanced/something like that=smt
	
	2.4 then there is a "path" variable for the user and again for the pc. 
	
	2.5 open it/edit and "ADD" in top name jdk and in the bottom field the path you just found.	
	
	2.6 (reboot?)
	
	2.7 More accurate description/manual is found via duckduckgo/ecosia 
	
## Clickable instructions:
0. Source: https://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-33.htm 

Or you can create the .jar file with eclipse by:
1. enabling package (uncomment line one stating: `package customSortServerV4`) in:
```
ConditionalComparator.java
CreateSorts.java
hardCoded.java
IcheckTresholdCondition.java
Main.java
ReadTasks.java
Task.java
```
2. In package explorer of eclipse, select those 7 files.

3. Click top left: file>Export>expand node/folder "java">Select: Runnable JAR file>Click Next.

4. At Launch configuration select: `Main - customSortServerV5`

5. Chose an export destination (e.g. `...<yourcomputerpath>/PublicCodeLibrary/AutomationAndSystems/Taskwarrior/customSortServerV5/`) and name the file `JavaServerSort`. 

6. select: "Package required libraries into generated Jar"

7. Press finish.

8. That's it. (You can run the java separately by:

9. Open command prompt (cmd) or WSL Ubuntu 16.04 (terminal):

10. In cmd/terminal browse to `...<yourcomputerpath>/PublicCodeLibrary/AutomationAndSystems/Taskwarrior/customSortServerV5/` 

11. Type: java -jar JavaServerSort.jar

12. Eclipse compile shortcut pattern: First select the 7 files>`alt+f>o>enter>enter`
### In Ubuntu

12. Open (WSL) Ubuntu (16.04) and browse in terminal(=command prompt for Linux) to: "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/

13. Enter: 
./customSort.sh

14. Wait for 3+minutes

15. If it's done it has sorted all tasks (excluding messed up dependency tasks) from top to bottom on: 
	 	If below threshold:(currently hardcoded threshold of 11.2) 
			If has project: 
				on project
			Else:
				On urgency (low to high)
		Else:
			On urgency (low to high)

16. And it has made a new custom report type with User Defined Attribute (UDA) secretSort to store the customSorted Order.

17. You can now view your tasks in an overview using command: task nice0

18. To see how, after compilation you can put the sorting in a cron job to run the sorting code in the background without user 
interaction, see quick use.


## Terminal/command/written instructions

0. First, Ensure in Eclipse, open the project and remove/comment out all "package customSortServerV4" lines  at the top of all the `.java` files in that package. (As indicated in the respective .java files). Ignore the warning/error symbol Eclipse shows when doing that.

1. open cmd

2. browse to "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/compilation

Enter (for backwards compatibility since this script currently ensures highest JDK version 8. = 1.8 in Linux.):


3. First clear up the old compiled files
```
del ConditionalComparator.class

del CreateSorts.class

del CreateSortsTest.class

del ExportCommands.class

del hardCoded.class

del ICheckThresholdCondition.class

del Main.class

del ReadTasks.class

del ReadTasksTest.class

del Task.class

del TaskDataSet.class

del TaskList.class

del TaskTest.class

del Write.class

del JavaServerSort.jar
```
4. Then generate the new compiled files:
```
javac ConditionalComparator.java -target 1.8 -source 1.8

javac CreateSorts.java -target 1.8 -source 1.8

javac CreateSortsTest.java -target 1.8 -source 1.8

javac ExportCommands.java -target 1.8 -source 1.8

javac hardCoded.java -target 1.8 -source 1.8

javac ICheckThresholdCondition.java -target 1.8 -source 1.8

javac Main.java -target 1.8 -source 1.8

javac ReadTasks.java -target 1.8 -source 1.8

javac ReadTasksTest.java -target 1.8 -source 1.8

javac Task.java -target 1.8 -source 1.8

javac TaskDataSet.java -target 1.8 -source 1.8

javac TaskList.java -target 1.8 -source 1.8

javac TaskTest.java -target 1.8 -source 1.8

javac Write.java -target 1.8 -source 1.8
```
That will yield a warning saying bootstrap class path not set in conjunction with -source 8. Furthermore it will not compile the test classes.

4. Next create a txt file named `manifest` that indicates what the main class of this project is, by entering the following line in it:
`Main-Class: Main`

6. Then rename the file extentions from `manifest.txt` to `manifest.mf`

7. And then compile the .jar file that will run the combined compiled files:
```
jar -cvmf manifest.mf JavaServerSort.jar *.class
```

8. Make it runnable:
```
sudo chmod +x JavaServerSort.jar
```
9. That's it. (You can run the java separately with: `java -jar JavaServerSort.jar`) 
