package customSortServerV4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedList;


public class ReadTasks {
	private String unixUsername=null;
	private String JSONLocation=null;
	private LinkedList<Task> allTasks = new LinkedList<Task>();	



	/**
	 * Constructor.
	 */
	public void readTasks() {

	}

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
	public static String readTwPath() {
		return null;
	}

	/**
	 * This method verifies that the taskwarrior is indeed installed at the getTwPath().
	 * TODO: determine list and location of files.
	 * @return true if all the files listed in "hardCoded" are found in the location predicted by getTwPath(). 
	 * false if not all files listed in "hardCoded" are found in the location predicted by getTwPath().
	 */
	public static boolean findTwFiles(String twPath,String pendingFilename) {
		return false;
	}

	/**
	 * This method reads the location of the JSON file that contains all the pending taskdata.
	 * @return contains the string of the path to the JSON file including the JSON file.
	 */
	public static String readJSONLocation() {
		//check if files exist
		if (findTwFiles(readTwPath(),hardCoded.getUbuntuFileName())) {
			return readTwPath()+hardCoded.getUbuntuFileName();
		}				

		//TODO: Throw exception instead of returning null.
		return null;
	}

	public static String getFilePath(boolean testing) {
		if (testing) {
			String filepath=hardCoded.getEclipseFilePath()+hardCoded.getEclipseFileName();
			System.out.println("filepathJsonLocation="+filepath);
			return filepath;
		}else {
			return readJSONLocation();
		}
	}

	/**
	 * This method read the task file from the windows location when you're in Eclipse
	 * and uses readJSONLocation() if it is compiled and in Ubuntu.
	 */
	public void readFile(String filename) {
		FileReader reader;
		BufferedReader br;
		String line = null;
		try {		
			br = new BufferedReader(new FileReader(filename));

			while ((line = br.readLine()) != null) {
				readPerLine(line);
			}  
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Reads the taskwarrior tasks from the JSON file given in readJSONLocation() per line 
	 * and puts the property-name and -values into an array passes the array to 
	 * createTasksPerLine().
	 */
	public static void readPerLine(String line) {
		System.out.println(line);
		String propertyName= "description:";

	}

	/**
	 * Iterates through all the names of the fields of object Task.
	 * these fields have the same name as the Task
	 * @param line
	 */
	public static void generateTaskPropertyList(String line) {
		Task emptyTask = new Task();
		Field[] fields = emptyTask.getClass().getDeclaredFields();
		//Field[] fields = emptyTask.class.getFields();
		for(Field f : fields){
			System.out.println(f.getName());//or do other stuff with it
			readProperty(line,f.getName());
		}
	}

	/**
	 * Iterates through all the methods of object Task.
	 * If these methods start with "set" and are equal to a fieldname, 
	 * it will set the property of that task
	 * @param line
	 */
	public static void getTaskPropertyGetters() {
		String testTemp="PropertyValue";
		Task emptyTask = new Task();
		java.lang.reflect.Method methodCalling = null;


		Method[] methods = emptyTask.getClass().getDeclaredMethods();
		//Field[] fields = emptyTask.class.getFields();
		for(Method m : methods){
			System.out.println(m.getName());//or do other stuff with it
			try {
				methodCalling = emptyTask.getClass().getMethod(m.getName(), Parameter.class);
			} catch (SecurityException e) {}
			catch (NoSuchMethodException e) {}
			try {
				methodCalling.invoke(emptyTask, testTemp);
			} catch (IllegalArgumentException e) {}
			catch (IllegalAccessException e) {  }
			catch (InvocationTargetException e) { }
		}
		
	}


	public static String[] readProperty(String line,String propertyName) {
		String propertyValue = null;		
		String lineRemainder;
		int endPropertyValue;

		//If the property name is found
		if (line.indexOf(propertyName) != -1) {
			//Remove the name of the property of the remaining string:
			lineRemainder=eatPropertyName(line,propertyName);

			//Find the next " to close the property.value
			endPropertyValue=lineRemainder.indexOf("\"");
			checkPropertyLength(endPropertyValue);		

			//Get propvalue:
			propertyValue=lineRemainder.substring(0, endPropertyValue);
			System.out.println(lineRemainder);
			System.out.println(propertyValue);
		}

		//return the property name and the property value
		String returnString[]=new String[1];
		returnString[0]=propertyName.substring(0, propertyName.length()-1);
		returnString[1]=propertyValue;
		return returnString;
	}

	/**
	 * This method eats the name of the property, e.g. for property "description"
	 * it will remove the substring description:" from the incoming line and return
	 * the right hand side remainder of that line.
	 * @param line is a line from the pending.data and contains 1 task in JSON format
	 * @param propertyName the name of the property that is being absorbed
	 * @return
	 */
	public static String eatPropertyName(String line,String propertyName) {
		int startPropertyName;
		int startPropertyValue;
		startPropertyName = line.indexOf(propertyName);
		startPropertyValue=startPropertyName+propertyName.length()+1;
		return line.substring(startPropertyValue, line.length());
	}

	/**
	 * This method checks whether the property length is larger than 0. 
	 * If it is not, it should throw an error since the closing " of the
	 * property value is not found.
	 * @param endPropValue
	 */
	public static void checkPropertyLength(int endPropValue) {
		//throw exception if endprop value =<startPropValue
		if (endPropValue<=0) {
			try {
				throw new PropertyValueNotFoundException("Did not find the property");
			} catch (PropertyValueNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Receives a line containing a task from readTasksFromJSON() and creates
	 * a task object from it. Then passes the task object to addTaskToList.
	 */
	public void createTasksPerLine() {

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


}
class PropertyValueNotFoundException extends Exception{  
	PropertyValueNotFoundException(String s){  
		super(s);  
	}  
}  