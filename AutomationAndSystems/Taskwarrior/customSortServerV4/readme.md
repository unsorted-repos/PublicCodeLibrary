Hi, thanks for checking this project out!

I made a code skeleton for a javaproject that applies a custom sorting to my taskwarrior task lists. To be precise:

0. It first divides the tasks into 2 lists; below and above a user defined urgency threshold 
(the threshold and urgencies are simple numbers like 13.3). 

1. Then it divides the below-urgency-threshold-list again in two lists; tasks with and without projects.

2. Then it sorts the below-urgency-threshold-with-project-list on alphabetical project order. (projects can be uni.coursename.subject)

3. Then it sorts the below-urgency-threshold-without-project-list on urgency.

4. Then it sorts the above-urgencythreshold-list on urgency.

5. That list is then merged, a custom sort number is assigned to each task so that taskwarrior can sort the tasks
on their custom sort number.

I currently have the following doubts:

0. Could I have applied any inheritance in a logical sense?  
TaskDataSet contains TaskLists, 
TaskList contains Tasks
But neither have mutliple variants with different properties, hence I thought it would not be a correct
application of abstract classes and implements.

1. Neither do I currently see the advantage of adding interfaces, I should look up the benefits of them again.

2. Testing the "task.setId()" using "task.getId()" seems like thorough testing, the methods could individually still 
be faulty, but compensate eachothers faults. I have not yet been creative enough to test them individually.

And I do not yet know how I can overcome the following two challenges:

0. How do I execute unix/Ubuntu commands directly from a .jar file/java code?


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
	

### Steps: ###

0. First, Ensure in Eclipse, open the project and remove/comment out all "package customSortServerV4" lines  at the top of all the `.java` files in that package. (As indicated in the respective .java files). Ignore the warning/error symbol Eclipse shows when doing that.

0. open cmd

1. browse to "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/compilation

Enter (for backwards compatibility since this script currently ensures highest JDK version 8. = 1.8 in Linux.):


First clear up the old compiled files
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
Then generate the new compiled files:
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

Next create a txt file named `manifest` that indicates what the main class of this project is, by entering the following line in it:
`Main-Class: Main`
Then rename the file extentions from `manifest.txt` to `manifest.mf`

And then compile the .jar file that will run the combined compiled files:
```
jar -cvmf manifest.mf JavaServerSort.jar *.class
```

Make it runnable:


That's it. (You can run the java separately with: java -jar JavaServerSort.jar) 

# User interface instructions:
0. Source: https://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-33.htm 

Or you can create the .jar file with eclipse by:
1. enabling package (uncomment line one stating: `package customSortServerV4`) in:
```
Main.java
ReadTasks.java
hardCoded.java
Task.java
CreateSorts.java
ConditionalComparator.java
```
2. Try enabling package customSortServerV4 in `IcheckTresholdCondition.java` as well.

3. In package explorer of eclipse, select those 7 files.

4. Click top left: file>Export>expand node/folder "java">Select: Runnable JAR file>Click Next.

5. At Launch configuration select: `Main - customSortServerV4`

6. Chose an export destination (e.g. `...<yourcomputerpath>/PublicCodeLibrary/AutomationAndSystems/Taskwarrior/customSortServerV4/`) and name the file `JavaServerSort`. 

7. select: "Package required libraries into generated Jar"

8. Press finish.

9. That's it. (You can run the java separately by:

10. Open command prompt (cmd)

11. In cmd browse to `...<yourcomputerpath>/PublicCodeLibrary/AutomationAndSystems/Taskwarrior/customSortServerV4/` 

12. Type: java -jar JavaServerSort.jar
## Ubuntu


2. Open (WSL) Ubuntu (16.04) and browse in terminal(=command prompt for Linux) to: "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/

3. Enter: 
./customSort.sh

4. Wait for 3+minutes

5. If it's done it has sorted all tasks (excluding messed up dependency tasks) from top to bottom on: 
	 	If below threshold:(currently 13.4) 
			If has project: 
				on project
			Else:
				On urgency (low to high)
		Else:
			On urgency (low to high)

6. And it has made a new custom report type with User Defined Attribute (UDA) secretSort to store the customSorted Order.

7. You can now view your tasks in an overview using command: task nice0

8. To see how, after compilation you can put the sorting in a cron job to run the sorting code in the background without user 
interaction, see quick use.
