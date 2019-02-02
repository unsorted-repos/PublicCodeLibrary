package customSortServerV4;

import java.util.ArrayList;
import java.util.LinkedList;

public class ReadTasks {
private String unixUsername=null;
private String JSONLocation=null;
private LinkedList<Task> allTasks = new LinkedList<Task>();	

boolean windows=true;



	/**
	 * 0. This function gets the Unix username based on the current path of this code.
	 * 1. TODO: Verify this code is independent of the Unix username.
	 *  
	 * @return contains the string of the unix username
	 */
	public String readUnixUsername() {
		return null;
	}
	
	/**
	 * 0. This function gets the taskwarrior installation path
	 * 1. It can verify the path by checking if the taskwarrior files are present 
	 * in the folders of higher hierarchie.
	 * 2. It could also check whether Unix has multiple taskwarrior installations.
	 *     2.a If Unix has multiple taskwarrior installations, it should ask the user
	 *     what the correct path is, or use it's own path, and store it to an
	 *     external variable that the user could easily change.
	 * @return
	 */
	public String readTwPath() {
		return null;
	}
	
	/**
	 * This method verifies that the taskwarrior is indeed installed at the getTwPath().
	 * TODO: determine list and location of files.
	 * @return true if all the files listed in "hardCoded" are found in the location predicted by getTwPath(). 
	 * false if not all files listed in "hardCoded" are found in the location predicted by getTwPath().
	 */
	public boolean findTwFiles() {
		return false;
	}
	
	/**
	 * This method reads the location of the JSON file that contains all the pending taskdata.
	 * @return contains the string of the path to the JSON file including the JSON file.
	 */
	public String readJSONLocation() {
		return null;
	}
	
	/**
	 * Reads the taskwarrior tasks from the JSON file given in readJSONLocation() and 
	 * passes the lines to a per-lineReader.
	 */
	public void readTasksFromJSON() {
		
	}
	
	/**
	 * Receives a line containing a task from readTasksFromJSON() and creates
	 * a task object from it. Then passes the task object to addTaskToList.
	 */
	public void readTasksPerLine() {
		
	}
	
	/**
	 * Adds task to allTasks.
	 */
	public void addTaskToList(Task task) {
		
	}
	
	
	/**
	 * Gets the location of the urgency threshold location from hardCoded.
	 * @return location of the file containing the userThreshold, null if it cannot find the file
	 */
	public String getUrgThresholdLocation() {
		return null;
	}
	
	/**
	 * Gets the location of the userThresholdLocation from hardCoded.
	 * @return location of the file containing the userThreshold, null if it cannot find the file
	 */
	public String getMaxNrOfUrgentTasksLocation() {
		return null;
	}
	
	/**
	 * Gets the location of the userThresholdLocation from hardCoded.
	 * @return location of the file containing the userThreshold, null if it cannot find the file
	 */
	public String getMaxPercentageOfNrOfUrgentLocation() {
		return null;
	}
	
	/**
	 * Gets the userThreshold from file using filepath from getUserThresholdLocation. If the path returns null
	 * It should ask the user what the threshold is and store it using askUrgThreshold and writeUrgThreshold.
	 * @return
	 */
	public double readUrgThreshold() {
		return 0;
		
	}
	
	
	/**
	 * Asks the user what the threshold is. 
	 * 1. Test: check if it is a double that is entered and whether it is within double storage limits. 
	 */
	public double askUrgThreshold() {
		return 0;
	}
	
	
	/**
	 * Alternatively to the urgency threshold the user might want to define a max nr of tasks to be
	 * listed at the bottom as the most urgent tasks. This method asks it.
	 * @return the nr of tasks that the user wants to see as most critical tasks, sorted on urgency
	 */
	public int askMaxNrOfUrgentTasks() {
		return 0;
	}
	
	/**
	 * Alternatively to the urgency threshold the user might want to define a max percentage of the nr of tasks to be
	 * listed at the bottom as the most urgent tasks. This method asks it.
	 * @return
	 */
	public double askMaxPercentageOfNrOfUrgentTasks() {
		return 0;
	}

	public boolean isWindows() {
		return windows;
	}

	public void setWindows(boolean windows) {
		this.windows = windows;
	}

}
