package customSortServerV4;

import java.util.ArrayList;

public class Main {
	static boolean testingInWindows=true; //BEFORE COMPILING TO JAR:Switch to false before execution in Ubuntu.

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

		//Sort taskList:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);

		//Print description and uuids of unsorted tasklist:
		for (int i=0;i<sortedTaskList.size();i++) {
			System.out.println(sortedTaskList.get(i).getDescription()+" and task uuid = "+sortedTaskList.get(i).getUuid());
		}
		
		//Run a command:
		RunCommands.runCommands(); 
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
