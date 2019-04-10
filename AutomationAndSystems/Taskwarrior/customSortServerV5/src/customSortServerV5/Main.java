package customSortServerV5;

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

		// TODO Auto-generated method stub
		String filepath=ReadTasks.getFilePath(testingInWindows);
		ArrayList<String> lines=ReadTasks.readFile(filepath);
		ArrayList<Task> unSortedTaskList = ReadTasks.separarateLines(lines);
		ArrayList<Task> sortedTaskList = ReadTasks.separarateLines(lines);

		//Create customSortUDA in tw:
		createUDA(hardCoded.getNameOfCustomSortParameter(), hardCoded.getNameOfCustomSortParameterLabel(),hardCoded.getCustomSortDataType());
		//create customReport
		createCustomReport(hardCoded.getCustomReportName());
		
		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<unSortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getId()+" and cSort="+sortedTaskList.get(i).getCustomSort()+" "+unSortedTaskList.get(i).getDescription()+" and task uuid = "+unSortedTaskList.get(i).getUuid());
		}

		//Get urgency and add it to tasks
		addUrgency(unSortedTaskList);

		//Sort taskList:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);

		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<sortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getId()+" and cSort="+sortedTaskList.get(i).getCustomSort()+" "+sortedTaskList.get(i).getDescription()+" and task uuid = "+sortedTaskList.get(i).getUuid());
		}
		assignCustomSortToTw(sortedTaskList);

		// Set the customSort values of the recurring parent/template tasks to 0:
		
		//Print command output and return urgency
		RunCommands.runCommands("task sync", true);
	}

	/**
	 * This method assigns the customSortValue
	 * 
	 * TODO: ensure the customSort is not set for the recurrent template.parent tasks
	 * 
	 * @param sortedTaskList
	 */
	private static void assignCustomSortToTw(ArrayList<Task> sortedTaskList) {
		// TODO Auto-generated method stub
		String uuid = null;
		String command = null;
		String status = "recurring";
		
		for (int i =0;i<sortedTaskList.size();i++) {
			uuid=sortedTaskList.get(i).getUuid();
			
			// if the status is recurrent it is a recurrent template/parent task
			if (status.equals(sortedTaskList.get(i).getStatus())) {
				
				// clear the customSort UDA of the recurrent parent/template task 
				command = hardCoded.getSudo()+"task "+uuid+" modify "+hardCoded.getNameOfCustomSortParameter()+":";
				RunCommandsExpectYes.runCommands(command);
			}else {
				
				// assign the customSort UDA of the recurrent child/actual task
				command = hardCoded.getSudo()+"task "+uuid+" modify "+hardCoded.getNameOfCustomSortParameter()+":"+i;
				RunCommandsExpectYes.runCommands(command);
			}
		}
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
	private static void addUrgency(ArrayList<Task> taskList) {
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
			
			//run command
			commandOutput=RunCommands.runCommands(command,false);
			
			//print output and get urgency:
			urgency=ReadCommandOutput.splitOutput(commandOutput,true);	
			
			//store urgency in task
			taskList.get(i).setUrgency(urgency);
		}
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
	private static void getUrgency(ArrayList<Task> taskList) {
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
		commands.add("task config report."+reportName+".columns     id,depends,due,priority,urgency,duration,project,recur,tags,description,start");
		commands.add("task config report."+reportName+".labels      id,dep,due,prio,urgy,dura,proj,again,tag, descr,start");
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
	*TODO: Create extra report to sort purely on due date:
	*TODO: Determine why some results without due date are still shown in this filter
	*Extra info, remove custom reports by just not specifying the data after the command.
	* add a new report by: 
	*task config report.sortDue.description Sort on nearing due date earliest due date last
	*task config report.sortDue.columns id,depends,due,duration,priority,urgency,project,recur,tags,description,start
	*task config report.sortDue.label sortDue
	*task config report.sortDue.sort due-
	*task config report.sortDue.filter status:pending -due:
	*
	*Change dateformat to include DUE TIME in due date:
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