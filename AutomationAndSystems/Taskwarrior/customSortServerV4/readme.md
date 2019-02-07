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

0. First, Ensure in Eclipse, open the project and remove/comment out all "package customSortTaskwarrior.compilation" lines  at the top of files: "ClassFive.java", "Pair.java" and "StringPair.java". (As indicated in the respective .java files). Ignore the warning/error symbol Eclipse shows when doing that.

0. open cmd

1. browse to "the folder this Readme.md is in"/compileYourself/javaCustomSort/src/customSortTaskwarrior/compilation

Enter (for backwards compatibility since this script currently ensures highest JDK version 8. = 1.8 in Linux.):

```
del ClassFive.class

del Pair.class

del StringPair.class

del javaSort.jar

javac ClassFive.java -target 1.8 -source 1.8

javac Pair.java -target 1.8 -source 1.8

javac StringPair.java -target 1.8 -source 1.8

jar -cvmf manifest.mf javaSort.jar *.class
```

(You can run the java separately with: java -jar javaSort.jar (but that requires the output of the customSort.sh)

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
