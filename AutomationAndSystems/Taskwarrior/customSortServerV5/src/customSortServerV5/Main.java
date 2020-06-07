package customSortServerV5;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * You can not run this main in eclipse in windows directly. You can only test the methods in windows.
 * You can run the main in Linux, once it's compiled.
 * 
 * V5 will set the customSort of a parent/template recurrent task to 0, and set it's children as whatever
 * they are computed at. This check is performed in method assignCustomSortToTw of this main.
 * 
 * @author a
 *
 */
public class Main {
	static boolean testingInWindows=false; //BEFORE COMPILING TO JAR:Switch to false before execution in Ubuntu.

	public static void main(String[] args) {
//		MoveTestFiles.startWSL();
	
		// create hardcoded object
		HardCoded hardCoded = new HardCoded();
		
		// TODO Auto-generated method stub
		String filepath=ReadTasks.getFilePath(hardCoded,testingInWindows);
		ArrayList<String> lines=ReadTasks.readFile(filepath);
		ArrayList<Task> unSortedTaskList = ReadTasks.separarateLines(lines);
		ArrayList<Task> sortedTaskList = ReadTasks.separarateLines(lines);

//		MoveTestFiles moveTestFiles = new MoveTestFiles();

		
		//Create customSortUDA cSort in tw:
		createUDA(hardCoded.getNameOfCustomSortParameterLabel(), hardCoded.getNameOfCustomSortParameterLabel(),hardCoded.getCustomSortDataType());
		//Create UDA estimate in tw:
		createUDA(hardCoded.getUdaName(), hardCoded.getUdaLabel(),hardCoded.getUdaDataType());
		
		//create customReport
		createCustomReport(hardCoded.getCustomReportName());
		
		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<unSortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getId()+" and cSort First="+sortedTaskList.get(i).getCustomSort()+" "+unSortedTaskList.get(i).getDescription()+" and task uuid = "+unSortedTaskList.get(i).getUuid());
		}

		//Get urgency and add it to tasks
		addUrgency(hardCoded, unSortedTaskList);

		//Sort taskList:
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);

		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<sortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getId()+" and cSort second="+sortedTaskList.get(i).getCustomSort()+" "+sortedTaskList.get(i).getDescription()+" and task uuid = "+sortedTaskList.get(i).getUuid());
		}
		assignCustomSortToTw(hardCoded, sortedTaskList);

		// Set the customSort values of the recurring parent/template tasks to 0:
		
		// Read backlog file
		FillBacklogTasks.manageBacklogFilling(hardCoded);
		System.exit(0);
		
		//Print command output and return urgency
		RunCommands.runCommands("task sync", true);
	}

	/**
	 * Documentation on recurrence in context of JavaServerSort:
	 * Terminology: 
	 * Parent recurrent task: "Do dishes recurs every week"
	 * Child recurrent task: "Do dishes friday at 14:00"  
	 * 
	 * Before sorting, all child recurrent tasks have the same cSort value as their parents. 
	 * E.g. 115 Parent task is:
	 * -1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 9a69a841-0acb-4611-8bf2-8e2149cbdd3c
	 *  And 115 child tasks are:
	 *  -1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 7d474b06-ee9c-438d-81fd-0a2af26509fb
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 16a1ae55-33c5-4078-93f6-ef2d9a18af0d
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 8a64bf39-cde1-4692-99fa-3bfb6655de73
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 32200115-bc48-4d42-8c2f-eec867cc268d
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 06a28ea9-5363-49fa-aa63-26117347b86a
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = b2e6ac54-b0b3-483c-9844-2bb511b8a3da
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 2d88cfdd-22d0-4737-8ee9-a1ed1b94b42d
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = abab6499-d07f-460a-b915-b20bbecb01b3
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = d3526d89-5a1b-4196-bf10-fb2163623d08
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 17bcdc50-3f7d-417b-b414-1f46158c1a4e
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 6b6bc57e-d881-4c17-9b00-ea9ac93cb56e
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 66449d2c-5ccc-4313-a020-784bc2e1158a
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 3d0f5aef-9440-4e9e-a293-44a1b6705943
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 2783cc95-6319-4d4b-bb9e-56c3cbf74345
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 99df7cbc-e20d-497c-b78f-74bb4391dc7a
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = be050172-07bb-455f-8809-4dd1a598d96e
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 6ee0a7f3-81b2-4707-8099-bdb099d7bf38
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = aba5b2dd-725e-4a61-80d1-8520b8a0d66d
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 6c1d7486-b0e5-4748-829c-64cbafac8542
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = a5e0073e-68f1-48c6-b566-7ce6d0cee7fe
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = d52d52bd-0e5c-4086-8d2c-f1b76e06ecd4
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = b28e755f-dd8e-408c-8eff-6da7bcd8b134
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = f0de72c2-bd3f-46c0-8d4e-ef7b40d3ee2c
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = b74726cb-5748-4bd5-ba0b-c72bcf5c362f
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = ba3cf444-2c19-4c9a-9b59-61e685f26f71
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 2b7657b6-9fc1-4b1d-93c0-127ba8a6fa14
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 1f1980b5-9b76-4c08-b5d1-829dde2b10f9
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 95a6b8ab-710d-493e-89b8-45c77710faf2
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 76f8d140-64f6-4a2c-8129-af46256977c7
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = d63cb586-001f-4c5a-a8e4-e2d86bf913c7
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = b29616f6-fe2a-4aa7-aa99-67c62b6db76b
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 8b559ab5-c6ae-46dc-a434-f29eb06a34e6
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 7ce836dc-2bbe-45f8-90fb-cdc267c543a4
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 02180283-5391-4fbe-ab50-c11f08fa2c6b
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = a95ab810-37f0-4204-82e7-f3c5f3a06e7a
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 2c9f9d1b-fd94-49e4-84e3-627c11da65de
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = c1907a57-e770-4cad-bc20-58679e39b56c
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 5c3120c6-0142-4888-932c-02b989f0f17a
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 3c2138dc-79c8-431e-8223-6bf8b738b979
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 5ea1b2bc-a9f5-48de-8c48-9a9a9e859d7e
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = abb07130-d786-4151-bb64-c95918d314ea
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = a2e66c00-6e70-4284-b1e8-0f2b19e27b29
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = cffa30f7-6f9a-4a95-aeb6-2588334eb14b
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 3ac8c2b7-87b1-4f53-afd9-73f4081fed44
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 605014b1-3225-4ea8-9747-0de9ad24a4b1
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 88e1181f-37cb-42b1-a198-c2000d110d04
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 9cc3d9c7-3c87-475d-a69a-a3e2c4ecda13
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 9393513a-1d7d-4228-90ae-10b829b06af4
-1.0 and cSort First=115 Prepare clothes for next day, Daily. and task uuid = 26065615-e5ad-4207-862d-8800690e672a

	 *
	 * 0. improvement could be to skip the child-recurring tasks alltogether in the sorting procedure, and afterwards assign them all
	 * the same cSort value as their parent recurring task.
	 * 1. Count the nr of child-recurring tasks per parent-recurring task, and keep this amount of spots open in the cSort order, 
	 * ignore the child-recurring tasks for the remainder of the sorting procedure, and then sort them separately on date, and then
	 * assign them the gapped cSort values accordingly. E.g. normal task 1 has csort=10 parent-recurring task 2 has 5 childs and cSort =11
	 * then normal task 3 has cSort 17, and the 5 extra are filled in after the overal sorting has been completed.
	 * 
	 * PROBLEM STATEMENT: with blowing up the backlog/modification file might be explained by the "yes | " that is answered automatically to
	 * every question of "do you really want to modify this task?" Because for a recurrent task it asks: 
	 * "This is a recurring task.  Do you want to modify all pending recurrences of this same task? (yes/no)". Then it automatically says yes
	 * and then instead of modifying just one task, it modifies 50 or so. And it PROBABLY does that for all child-recurring tasks (It does not go in 
	 * task-id order). An example is "Modifying recurring task 607" which is modified when task 609 is modified and when task 608 is modified.  
	 * 
	 * SOLUTION STATEMENTS
	 * 1. detect if a task is a child-recurrent task, check if the sort script/tw also asks "modify all recurrent tasks?" for these child-tasks, 
	 * and answering "no" accordingly. (Verify that the child-task itself DOES get modified.
	 * 2. detect if a task is a child-recurrent task and ignore it all together. Verify whether it gets modified if the parent task is modified, by the 
	 * question "modify all recurent tasks?".
	 * 3. Quick fix: Scan backlog.data for cSort modifications and delete those lines immediately after sorting. Problem: The backlog does not 
	 * store any modifications, it just stores new versions of tasks in their respective order of occurence, (old ones first, new ones later).
	 * So you could only delete duplicate tasks, and then only if the only difference is their value in the value of the customSort UDA
	 * 
	 *  Recur example parent: [customSort:"126.000000" description:"Saturdays Bike to gym." due:"1554531000" entry:"1554033797" mask:"-" modified:"1554042539" project:"rout" recur:"weekly" scheduled:"1554530100" status:"recurring" uuid:"e8576b9b-da0c-47f1-86dd-941d46aa6cde"]
	 *  Recur example child: [customSort:"126.000000" description:"Saturdays Bike to gym." due:"1554531000" entry:"1554034229" imask:"0.000000" modified:"1554042539" parent:"e8576b9b-da0c-47f1-86dd-941d46aa6cde" project:"rout" recur:"weekly" scheduled:"1554530100" status:"pending" uuid:"181d9c90-8818-484b-abc6-0075d4b55df3"]
	 */
	
	/**
	 * Locates backlogTest.data
	 * reads all lines and searches per line for duplicates of tw UUID's. If it finds duplicate twUuid it removes ONLY the lines that ONLY contain a change in
	 * the customSort value. (It removes the top line of the two). (Possibly scan entire file at once for that TW UUID to eliminate all but last.).
	 * removes those lines that concern customsort modification. 
	 * Stores the resulting modified backlog.
	 * 
	 * Create Arraylist of objects that contain: the uuid, line, line number. Put them all in an array list, and 
	 * create an array list of those objects per uuid. so create an arraylist of uuids, and then per uuid create a list
	 * that contains the line and line number. Once all lines are processed, just write the line of the last object in that arraylist of an uuid.
	 */
	public static void scanBacklog() {
	
	}
	
	/**
	 * This method assigns the customSortValue
	 * 
	 * TODO: ensure the customSort is not set for the recurrent template.parent tasks
	 * 
	 * @param sortedTaskList
	 */
	private static void assignCustomSortToTw(HardCoded hardCoded, ArrayList<Task> sortedTaskList) {
		// TODO Auto-generated method stub
		String uuid = null;
		String command = null;
		String status = "recurring";
//		System.out.println("Assigning the custom sort values to"+hardCoded.getNameOfCustomSortParameter()+ " for all tasks.");
		for (int i =0;i<sortedTaskList.size();i++) {
			uuid=sortedTaskList.get(i).getUuid();
			
			// if the status is recurrent it is a recurrent template/parent task
			if (status.equals(sortedTaskList.get(i).getStatus())) {
				
				// clear the customSort UDA of the recurrent parent/template task 
				command = hardCoded.getSudo()+"task "+uuid+" modify "+hardCoded.getNameOfCustomSortParameterLabel()+":";
				RunCommandsExpectYes.runCommands(command);
			}else {
				
				// assign the customSort UDA of the recurrent child/actual task
				command = hardCoded.getSudo()+"task "+uuid+" modify "+hardCoded.getNameOfCustomSortParameterLabel()+":"+i;
				RunCommandsExpectYes.runCommands(command);
			}
		}
//		System.out.println("Finished assigning the custom sort values to"+hardCoded.getNameOfCustomSortParameter()+ " for all tasks.");
	}

	/**
	 * This method:
	 * 0. iterates through the tasks, 
	 * 1. Gets the uuid of the tasks
	 * 2. Creates per task a command task <uuid>
	 * 3. executes a unix command that returns the task properties.
	 * 4. Stores the output of that command
	 * 5. gets the Urgency
	 * 6. Stores the urgency to that task.
	 * @param unSortedTaskList
	 */
	private static void addUrgency(HardCoded hardCoded, ArrayList<Task> taskList) {
		String uuid=null;
		String command=null;
		double urgency;
		ArrayList<ArrayList<String>> commandOutput = new ArrayList<ArrayList<String>>();
		System.out.println("Absorbing the urgency to the internal temporary task database");
		//Loop through taskList
		for (int i = 0;i<taskList.size();i++) {
			
			//get uuid
			uuid=taskList.get(i).getUuid();
			
			//create command
			command = hardCoded.getSudo()+"task "+uuid;
			
			//run command
			commandOutput=RunCommands.runCommands(command,false);
			
			//print output and get urgency:
			urgency=ReadCommandOutput.splitOutput(commandOutput,true);	
			
			//store urgency in task
			taskList.get(i).setUrgency(urgency);
		}
		System.out.println("Finished absorbing the urgency to the internal temporary task database");
	}

	/**
	 * This method:
	 * 0. iterates through the (sorted) tasks, 
	 * 1. Gets the uuid of the tasks
	 * 2. Creates per task a command task <uuid> customSort:+i
	 * 3. executes a unix command that returns the task properties.
	 * 4. Stores the output of that command
	 * 5. gets the Urgency
	 * 6. Stores the urgency to that task.
	 * @param unSortedTaskList
	 */
	private static void getUrgency(HardCoded hardCoded, ArrayList<Task> taskList) {
		String uuid=null;
		String command=null;
		double urgency;
		ArrayList<ArrayList<String>> commandOutput = new ArrayList<ArrayList<String>>();

		//Loop through taskList
		for (int i = 0;i<taskList.size();i++) {
			
			//get uuid
			uuid=taskList.get(i).getUuid();
			
			//create command
			command = hardCoded.getSudo()+"task "+uuid;
			System.out.println("Running command:"+command);
			
			//run command
			commandOutput=RunCommands.runCommands(command,false);
			
			//print output and get urgency:
			urgency=ReadCommandOutput.splitOutput(commandOutput,true);
			System.out.println("Urgency="+urgency);			
			
			//store urgency in task
			taskList.get(i).setUrgency(urgency);
		}
	}

	/**
	 * Extra info, remove UDA's with:
	 * task config uda.<UDA name>.label
	 * task config uda.<UDA name>.type
	 * task config uda.<UDA name>.values
	 * 
	 * Method creates a user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 */
	private static void createUDA(String udaName, String label,String type) {
		char c = (char)124;
		char bs = (char)92;
		
		String[] commands = new String[2];
		if (type.equals("numeric") || type.equals("string") || type.equals("date") || type.equals("duration")){
			commands[0]="task config uda."+udaName+".type "+type;
			commands[1]="task config uda."+udaName+".label "+ label;
			
			//run commands that expect a yes input from the user:
			for (int i = 0;i<commands.length;i++) {
				RunCommandsExpectYes.runCommands(commands[i]);
			}
		}else {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	/**
	 * Should create a custom report with the name reportName
	 * 
	 * Extra info, show all (UD)A's with
	 * task columns
	 * 
	 * Extra info, remove UDA's with:
	 * task config uda.<UDA name>.label
	 * task config uda.<UDA name>.type
	 * task config uda.<UDA name>.values
	 * 
	 * Show reports with:
	 * task show report.<reportname>.description
	 * task show report.<reportname>.columns
	 * task show report.<reportname>.labels
	 * task show report.<reportname>.sort
	 * task show report.<reportname>.filter
	 * 
	 * Extra info, remove custom reports with:
	 * task config report.<report name>.description
	 * task config report.<report name>.columns
	 * task config report.<report name>.label
	 * task config report.<report name>.sort
	 * task config report.<report name>.filter
	 * 
	 * Extra info, Show a report with:
	 * task show report.<report name>
	 * TODO: Verify with: task show report.nice0
	 * 
	 * @param reportName
	 */
	private static void createCustomReport(String reportName) {
		ArrayList<String> commands=new ArrayList<>();
		commands.add("task config report."+reportName+".description Custom sorted list of all tasks 1.");
		commands.add("task config report."+reportName+".columns     id,depends,due,priority,urgency,estimate,project,recur,tags,description,start");
		commands.add("task config report."+reportName+".labels      id,dep,due,prio,urgy,est,proj,again,tag, descr,start");
		commands.add("task config report."+reportName+".sort        customSort+");
		commands.add("task config report."+reportName+".filter      status:pending");

		//run commands if the reportname is not empty:
		if (reportName!=null && reportName.length()>0) {
			removeCustomReport(reportName); //reset the current report if it exists
			runMultipleCommandsExpectYes(commands); //fill in the new report
		}
	}


	/**
	*
	* Extra info, remove custom reports by just not specifying the data after the command.
	* add a new report by: 
	*task config report.sortDue.description Sort on nearing due date earliest due date last
	*task config report.sortDue.columns id,depends,due,estimate,priority,urgency,project,recur,tags,description,start
	*task config report.sortDue.label sortDue
	*task config report.sortDue.sort due-
	*task config report.sortDue.filter status:pending -due:
	*
	* Change dateformat to include DUE TIME in due date:
	* task config report.testReport.dateformat Y-M-D H:N
	*/
	
	private static void removeCustomReport(String reportName) {
		ArrayList<String> commands=new ArrayList<>();
		
		commands.add("task config report."+reportName+".description");
		commands.add("task config report."+reportName+".columns");
		commands.add("task config report."+reportName+".label");
		commands.add("task config report."+reportName+".type");
		commands.add("task config report."+reportName+".values");
		
		//run commands if the reportname is not empty:
		if (reportName!=null && reportName.length()>0) {
			runMultipleCommandsExpectYes(commands);
		}
	}
	
	
	
	/**
	 * Runs the commands given in an arrayList
	 * @param commands
	 */
	private static void runMultipleCommandsExpectYes(ArrayList<String> commands) {
		for (int i = 0; i <commands.size();i++) {
			System.out.println("Running:"+commands.get(i));
			RunCommandsExpectYes.runCommands(commands.get(i));
		}
	}
	
	public static boolean isTestingInWindows() {
		return testingInWindows;
	}

	public static void setTestingInWindows(boolean testingInWindows) {
		Main.testingInWindows = testingInWindows;
	}

	public static ArrayList<Task> createSortingCommands(ArrayList<Task> unsorted){
		return null;
	}
}
