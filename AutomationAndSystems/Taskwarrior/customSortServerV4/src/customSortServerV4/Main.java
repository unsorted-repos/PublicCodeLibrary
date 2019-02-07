package customSortServerV4;

import java.util.ArrayList;

public class Main {
	static boolean testingInWindows=false; //BEFORE COMPILING TO JAR:Switch to false before execution in Ubuntu.

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filepath=ReadTasks.getFilePath(testingInWindows);
		ArrayList<String> lines=ReadTasks.readFile(filepath);
		ArrayList<Task> unSortedTaskList = ReadTasks.separarateLines(lines);
		ArrayList<Task> sortedTaskList = ReadTasks.separarateLines(lines);


		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<unSortedTaskList.size();i++) {
			System.out.println(unSortedTaskList.get(i).getDescription()+" and task uuid = "+unSortedTaskList.get(i).getUuid());
		}

		//Get urgency and add it to tasks
		addUrgency(unSortedTaskList);

		//Sort taskList:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);

		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<sortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getDescription()+" and task uuid = "+sortedTaskList.get(i).getUuid());
		}

		//Create customSortUDA in tw:
		createUDA(hardCoded.getNameOfCustomSortParameter(), hardCoded.getNameOfCustomSortParameterLabel(),hardCoded.getCustomSortDataType());
		
		//create customReport
		createCustomReport(hardCoded.getCustomReportName());
		
		//Run a command and store the output:
		//commandOutput=RunCommands.runCommands();

		//Print command output and return urgency
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
			commandOutput=RunCommands.runCommands(command);
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
	private static void setCustomSort(ArrayList<Task> taskList) {
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
			commandOutput=RunCommands.runCommands(command);
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
		if (type.equals("numeric") || type.equals("string") || type.equals("date") || type.equals("duration")){
//			RunCommands.runCommands(hardCoded.getSudo()+"yes | task config uda."+udaName+".type "+type);
//			RunCommands.runCommands(hardCoded.getSudo()+"yes | task config uda."+udaName+".label"+ label);
			RunCommands.runCommands(hardCoded.getSudo()+"task config uda."+udaName+".type "+type);
			RunCommands.runCommands(hardCoded.getSudo()+"task config uda."+udaName+".label"+ label);
		}else {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private static void createCustomReport(String reportName) {
		int commandDimensions=6;
		String[] commands =new String[commandDimensions];

//		commands[0]=hardCoded.getSudo()+"yes | task config report."+reportName+".description 'Custom sorted list of all tasks.'";
//		commands[1]=hardCoded.getSudo()+"yes | task config report."+reportName+".columns     'id,depends,due,priority,urgency,duration,project,recur,tags,description,start'";
//		commands[2]=hardCoded.getSudo()+"yes | task config report."+reportName+".labels      'id,dep,due,prio,urgy,dura,proj,again,tag, descr,start'";
//		commands[3]=hardCoded.getSudo()+"yes | task config report."+reportName+".sort        'customSort+'";
//		commands[4]=hardCoded.getSudo()+"yes | task config report."+reportName+".filter      'status:pending";
		commands[0]=hardCoded.getSudo()+"task config report."+reportName+".description 'Custom sorted list of all tasks.'";
		commands[1]=hardCoded.getSudo()+"task config report."+reportName+".columns     'id,depends,due,priority,urgency,duration,project,recur,tags,description,start'";
		commands[2]=hardCoded.getSudo()+"task config report."+reportName+".labels      'id,dep,due,prio,urgy,dura,proj,again,tag, descr,start'";
		commands[3]=hardCoded.getSudo()+"task config report."+reportName+".sort        'customSort+'";
		commands[4]=hardCoded.getSudo()+"task config report."+reportName+".filter      'status:pending";

		//run commands if the reportname is not empty:
		if (reportName!=null && reportName.length()>0) {
			for (int i=0;i<commandDimensions;i++) {
				RunCommands.runCommands(commands[i]);
			}
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