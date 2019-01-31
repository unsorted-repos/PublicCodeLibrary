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
