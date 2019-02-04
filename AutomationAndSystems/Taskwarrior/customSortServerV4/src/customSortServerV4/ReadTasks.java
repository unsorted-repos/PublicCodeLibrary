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

	/**
	 * Tested in Eclipse.
	 * Method returns the hardcoded filepath for testing in Eclips if incoming parameter
	 * testing is true. If testing is false, it asks the location to pending.data(hardcoded)
	 * in Ubuntu using readJSONLocation. 
	 * @param testing boolean indicating whether it is testing in eclipse or compiled to jar
	 * and functioning in Ubuntu.
	 * @return the string containing the path to an input file. 
	 */
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
	 * Tested.
	 * To test it could return a string array of lines. Or one could test
	 * the sequence of methods to check if tasks are made after readPerLine.
	 * 
	 * This method read the task file from the windows location when you're in Eclipse
	 * and uses readJSONLocation() if it is compiled and in Ubuntu.
	 */
	public static ArrayList<String> readFile(String filename) {
		FileReader reader;
		BufferedReader br;
		String line = null;
		ArrayList<String> lines = new ArrayList<>();
		try {		
			br = new BufferedReader(new FileReader(filename));

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}  
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * This method takes in the arraylist of lines of a text file
	 * and passes each line separately to method readPerLine.
	 * @param lines contains the lines of the text file that is read in.
	 */
	public static void separarateLines(ArrayList<String> lines) {
		for (int i = 0; i<lines.size();i++) {
			readPerLine(lines.get(i));
		}
	}

	/**
	 * Not tested.
	 * Per incoming line it will create and return 1 task object.
	 * 
	 * 0. gets all the attributes and uda's from the Task object 
	 * from method generateTaskAttributeList.
	 * 1. gets all the attribute and uda setters (methods)
	 * from method getTaskAttributeGetMethods.
	 * 2. for each attribute it will call method readAttribute which will check if the
	 * attribute is in the line/task. 
	 * 2.b if readAttribute does not return null, it will pass the attribute value
	 * to the corrosponding setmethod to set the task attribute.
	 * 
	 * and puts the attribute-name and -values into an array passes the array to 
	 * createTasksPerLine().
	 */
	public static void readPerLine(String line) {
		Task task = new Task();
		ArrayList<String> attributes=generateTaskAttributeList();
		ArrayList<Method> attributeSetMethods=getTaskAttributeGetMethods();
		String[] attribute = new String[2];
		
		for (int i=0;i<attributes.size();i++) {
			if ((attribute=readAttributeValue(line,attributes.get(i)))!=null) {
				if (findMatchingSetMethod(attribute[0],attributeSetMethods)!=null) {
					//call method that executes method.
				}
			}
		}
		String attributeName= "description:";
	}
	
	/**
	 * creates a task object
	 * Executes the method set<attribute name> on the task object
	 * passes the propertyValue parsed as the method argument.
	 *  
	 * @param method
	 * @param propertyValue
	 */
	public static void setTaskAttribute(Method method,String propertyValue) {
		
	}
	
	/**
	 * Tested
	 * Iterates through ArrayList<Method> with all the set methods
	 * and if it finds a method that matches the attribute name it will
	 * return the method else it will return null;
	 * @param attributeName
	 * @param setMethods
	 * @return
	 */
	public static Method findMatchingSetMethod(String attributeName,ArrayList<Method> setMethods) {
		if (attributeName==null || attributeName.length()==0) {
			return null;
		}
		//set first letter to capital
		attributeName = attributeName.substring(0, 1).toUpperCase()+attributeName.substring(1, attributeName.length());
		System.out.println("attribute starting with captial:"+attributeName);
		for (int i=0;i<setMethods.size();i++) {
			if (setMethods.get(i).getName().equals("set"+attributeName)) {
				return setMethods.get(i);
			}
		}		
		return null;
	}

	/**
	 * Tested
	 * Iterates through all the names of the fields of object Task.
	 * these fields have the same name as the Task
	 * @param line
	 */
	public static ArrayList<String> generateTaskAttributeList() {
		Task emptyTask = new Task();
		Field[] fields = emptyTask.getClass().getDeclaredFields();
		ArrayList<String> taskProperties = new ArrayList<>();

		//Field[] fields = emptyTask.class.getFields();
		for(Field f : fields){
			taskProperties.add(f.getName());
			System.out.println("field:"+f.getName());//or do other stuff with it			
		}
		return taskProperties;
	}

	/**
	 * Tested
	 * Iterates through all the methods of object Task.
	 * It will return an ArrayList<Object> containing all the
	 * set.. methods of object task.
	 * If these methods start with "set" and are equal to a fieldname, 
	 * it will set the attribute of that task
	 * @param line
	 */
	public static ArrayList<Method> getTaskAttributeGetMethods() {
		String testTemp="AttributeValue"; //TODO: REMOVE
		Task emptyTask = new Task();
		java.lang.reflect.Method methodCalling = null;
		ArrayList<Method> setMethods = new ArrayList<>();
		
		Method[] methods = emptyTask.getClass().getDeclaredMethods();
		//Field[] fields = emptyTask.class.getFields();
		for(Method m : methods){
			System.out.println("method 3 char="+m.getName().substring(0, 3));
			if (m.getName().substring(0, 3).equals("set")) {
				System.out.println("method:"+m.getName());//or do other stuff with it
				setMethods.add(m);
//				try {
//					methodCalling = emptyTask.getClass().getMethod(m.getName(), Parameter.class);
//				} catch (SecurityException e) {}
//				catch (NoSuchMethodException e) {}
//				try {
//					methodCalling.invoke(emptyTask, testTemp);
//				} catch (IllegalArgumentException e) {}
//				catch (IllegalAccessException e) {  }
//				catch (InvocationTargetException e) { }
			}

		}
		return setMethods;
	}
	
	/**
	 * 
	 * @param line
	 * @param attributeName
	 * @return
	 */
	public static String[] readAttributeValue(String line,String attributeName) {
		String attributeValue = null;		
		String lineRemainder;
		int endAttributeValue;

		//If the attribute name is found
		if (line.indexOf(attributeName) != -1) {
			//Remove the name of the attribute of the remaining string:
			lineRemainder=eatAttributeName(line,attributeName);

			//Find the next " to close the attribute.value
			endAttributeValue=lineRemainder.indexOf("\"");
			checkAttributeLength(endAttributeValue);		

			//Get propvalue:
			attributeValue=lineRemainder.substring(0, endAttributeValue);
			System.out.println(lineRemainder);
			System.out.println(attributeValue);
		}else {
			System.out.println("attribute not found:"+attributeName);
			return null;
		}

		//return the attribute name and the attribute value
		String returnString[]=new String[1];
		returnString[0]=attributeName.substring(0, attributeName.length()-1);
		returnString[1]=attributeValue;
		return returnString;
	}

	/**
	 * This method eats the name of the attribute, e.g. for attribute "description"
	 * it will remove the substring description:" from the incoming line and return
	 * the right hand side remainder of that line.
	 * @param line is a line from the pending.data and contains 1 task in JSON format
	 * @param attributeName the name of the attribute that is being absorbed
	 * @return
	 */
	public static String eatAttributeName(String line,String attributeName) {
		int startAttributeName;
		int startAttributeValue;
		startAttributeName = line.indexOf(attributeName);
		startAttributeValue=startAttributeName+attributeName.length()+1;
		return line.substring(startAttributeValue, line.length());
	}

	/**
	 * This method checks whether the attribute length is larger than 0. 
	 * If it is not, it should throw an error since the closing " of the
	 * attribute value is not found.
	 * @param endPropValue
	 */
	public static void checkAttributeLength(int endPropValue) {
		//throw exception if endprop value =<startPropValue
		if (endPropValue<=0) {
			try {
				throw new AttributeValueNotFoundException("Did not find the attribute");
			} catch (AttributeValueNotFoundException e) {
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
class AttributeValueNotFoundException extends Exception{  
	AttributeValueNotFoundException(String s){  
		super(s);  
	}  
}  
