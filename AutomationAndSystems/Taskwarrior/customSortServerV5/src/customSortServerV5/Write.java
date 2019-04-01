package customSortServerV5;

public class Write {
	/**
	 * Stores the urgency Threshold as specified by the user in location from hardCoded.
	 */
	public void writekUrgThreshold(double urgency) {
	}
	
	/**
	 * Stores the max nr of urgent tasks displayed at the bottom of the custom sort view
	 * as specified by the user,  to a file location from hardCoded.
	 */
	public void writekMaxNrOfUrgentTasks(double urgency) {
	}	
	
	/**
	 * Stores the max percentage of the nr of urgent tasks displayed at the bottom of the custom sort view
	 * as specified by the user,  to a file location from hardCoded.
	 * @return 
	 */
	public void writeMaxPercentageOfNrOfUrgentTasks() {
	}
	
	/**
	 * This will either directly communicate to taskwarrior to set a customReport as 
	 * defined in "hardcoded", or it will create an output shell script that is added to
	 * the crontab.
	 */
	public void createCustomReport() {
		
	}
	
	/**
	 * This method modifies the crontab file, in case the .jar is ran the first time.
	 * It allows the jar to run any output shell scripts automatically as well as itself.
	 */
	public void modifyCrontab() {
		
	}
	
	/**
	 * Takes the final taskList and either commands Taskwarrior directly, or 
	 * creates an output.shell that is ran automatically by the crontab to modify
	 * the taskwarrior tasks of the list.
	 * @param finalTaskList
	 */
	public void writeCustomSortToTw(TaskList finalTaskList) {
		
	}
}
