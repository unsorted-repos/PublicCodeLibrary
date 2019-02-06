package customSortServerV4;

import java.util.ArrayList;

public class Main {
	static boolean testingInWindows=true; //BEFORE COMPILING TO JAR:Switch to false before execution in Ubuntu.

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filepath=ReadTasks.getFilePath(testingInWindows);
		ArrayList<String> lines=ReadTasks.readFile(filepath);
		ArrayList<Task> taskList = ReadTasks.separarateLines(lines);
		
		for (int i=0;i<taskList.size();i++) {
			System.out.println(taskList.get(i).getDescription()+" and task uuid = "+taskList.get(i).getUuid());
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
