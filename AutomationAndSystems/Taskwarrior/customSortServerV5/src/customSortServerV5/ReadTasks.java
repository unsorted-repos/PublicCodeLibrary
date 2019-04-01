package customSortServerV5;

import java.io.BufferedReader;
import java.io.File;
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
		return hardCoded.getUbuntuFilePath();
	}

	/**
	 * This method verifies that the taskwarrior is indeed installed at the getTwPath().
	 * TODO: determine list and location of files.
	 * @return true if all the files listed in "hardCoded" are found in the location predicted by getTwPath(). 
	 * false if not all files listed in "hardCoded" are found in the location predicted by getTwPath().
	 */
	public static boolean findTwFiles(String twPath,String pendingFilename) {
		
		System.out.println("Checking filepath:"+twPath+pendingFilename);
		System.out.println("returning the file is found:"+new File(twPath+pendingFilename).isFile());
		return new File(twPath+pendingFilename).isFile();
	}

	/**
	 * This method reads the location of the JSON file that contains all the pending taskdata.
	 * @return contains the string of the path to the JSON file including the JSON file.
	 */
	public static String readJsonLocation() {
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
			return filepath;
		}else {
			return readJsonLocation();
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
	public static ArrayList<Task> separarateLines(ArrayList<String> lines) {
		ArrayList<Task> taskList = new ArrayList<Task>();
		for (int i = 0; i<lines.size();i++) {
			taskList.add(readPerLine(lines.get(i)));
		}
		return taskList;
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
	public static Task readPerLine(String line) {
		Task task = new Task();
		ArrayList<String> attributes=generateTaskAttributeList();
		ArrayList<Method> attributeSetMethods=getTaskAttributeGetMethods();
		String[] attribute = new String[2];

		//Loop through all attributes of a task=fields of Object Task
		for (int i=0;i<attributes.size();i++) {
			//if line contains attribute:
			if ((attribute=readAttributeValue(line,attributes.get(i)))!=null) {
				//System.out.println("Found attribute:"+attribute[0]);
				//Find accompanying method 
				Method method = findMatchingSetMethod(attribute[0],attributeSetMethods);
				if (method!=null) {
					//call method that executes method and pass the attribute value.
					task=setTaskAttribute(task,method,attribute[1]);
				}
			}
		}
		return task;
	}
	
	/**
	 * Tested with Task attribute: description. 
	 * TODO: test for all fields/attributes of Task.
	 * creates a task object
	 * Executes the method set<attribute name> on the task object
	 * passes the propertyValue parsed as the method argument.
	 *  
	 * @param method
	 * @param attributeValue
	 */
	public static Task setTaskAttribute(Task task, Method method,String attributeValue) {
		
		try {
			//System.out.println("method="+method.getName()+" AttributeValue="+attributeValue);
			method.invoke(task, attributeValue);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
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
		//System.out.println("attribute starting with captial:"+attributeName);
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
			//System.out.println("field:"+f.getName());//or do other stuff with it			
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

		for(Method m : methods){
			//System.out.println("method 3 char="+m.getName().substring(0, 3));
			if (m.getName().substring(0, 3).equals("set")) {
				//System.out.println("method:"+m.getName());//or do other stuff with it
				setMethods.add(m);
			}

		}
		return setMethods;
	}
	
	/**
	 * This method get a task line and an attribute name and returns a String array
	 * with element 0 the attributeName, and element 1 the attribute value.
	 * @param line
	 * @param attributeName
	 * @return
	 */
	public static String[] readAttributeValue(String line,String attributeName) {
		String attributeValue = null;		
		String lineRemainder;
		int endAttributeValue;
		String returnString[]=new String[2];
		//System.out.println("attribute name ="+attributeName);
		
		//If the attribute name is found
		if (getAttributeNameIndex(line,attributeName) != -1) {

			//Remove the name of the attribute of the remaining string:
			lineRemainder=eatAttributeName(line,attributeName);
			//System.out.println("Found attribute name:"+attributeName+ " with remainder = "+lineRemainder);
			
			//Find the next " to close the attribute.value,
			endAttributeValue=lineRemainder.indexOf("\"");
			checkAttributeLength(endAttributeValue);		

			//Get propvalue:
			attributeValue=lineRemainder.substring(0, endAttributeValue);
			//System.out.println("Attribute value found ="+attributeValue);
		}else {
			//System.out.println("attribute not found:"+attributeName);
			return null;
		}

		//return the attribute name and the attribute value
		returnString[0]=attributeName;
		returnString[1]=attributeValue;
		return returnString;
	}

	/**
	 * This gets the index of the start character of the attribute name.
	 * Convention, do not include the [ or " " preceding an attribute name,
	 * just use it for detection but return index of the first character of
	 * the attribute name.
	 * @param line
	 * @param attributeName
	 * @return
	 */
	public static int getAttributeNameIndex(String line,String attributeName) {
		if (line.indexOf(" "+attributeName+":\"") != -1) {
			return line.indexOf(attributeName+":\"");
		}else if (line.indexOf("["+attributeName+":\"") != -1) {
			return line.indexOf(attributeName+":\"");
		}
		return -1;
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
		
		if (line.indexOf(" "+attributeName+":\"") != -1) {
			startAttributeName = line.indexOf(" "+attributeName+":\"")+1;
		}else if (line.indexOf("["+attributeName+":\"") != -1) {
			startAttributeName = line.indexOf("["+attributeName+":\"")+1;
		}
		
		startAttributeName = line.indexOf(attributeName+":\"");
		startAttributeValue=startAttributeName+attributeName.length()+1;
		return line.substring(startAttributeValue+1, line.length());
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
				//e.printStackTrace();
			}
		}
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
