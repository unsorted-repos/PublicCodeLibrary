/**
 * 
 */
package customSortServerV4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author a
 *
 */
class ReadTasksTest {
	private String testTxtLine0= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
	private String testTxtLine1= "[description:\"Backup emulators\" entry:\"1535050398\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"32\" status:\"pending\" uuid:\"931b463b-a130-44fe-96c0-ad60e88aaa0e\"]";
	private String testTxtLine2= "[description:\"Automate emulator controller installation\" entry:\"1535050399\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"31\" status:\"pending\" uuid:\"5d0e0cbb-173f-4ef4-a603-0dffd4caaa97\"]";
	private ArrayList<String> taskAttributes =new ArrayList<>();
	private ArrayList<String> taskAttributeSetMethods =new ArrayList<>();

	/**
	 * This fills an ArrayList of strings with the names of the Task Attributes.
	 * the names of the Task Attributes are the names of the fields of object Task.
	 * If you create a new UDA in taskwarrior, 
	 * 0. go to object Task
	 * 1. add the uda as a field (exact same uda name as in JSON format of pending.data)
	 * 2. generate setters and getters for the new UDA
	 * 3. add the field to the list in this method
	 *   
	 */
	@BeforeEach
	private void generateAttributeNames() {
		taskAttributes.add("depends");
		taskAttributes.add("description"); 
		taskAttributes.add("due"); //date
		taskAttributes.add("end"); //date
		taskAttributes.add("entry"); //date
		taskAttributes.add("estimate"); 
		taskAttributes.add("id");;
		taskAttributes.add("imask");; 
		taskAttributes.add("mask"); 
		taskAttributes.add("modified"); //date
		taskAttributes.add("parent"); 
		taskAttributes.add("priority"); 
		taskAttributes.add("project"); 
		taskAttributes.add("recur"); 
		taskAttributes.add("scheduled"); //date
		taskAttributes.add("start"); //date
		taskAttributes.add("status"); 
		taskAttributes.add("tags"); 
		taskAttributes.add("tracnumber");; 
		taskAttributes.add("tracsummary"); 
		taskAttributes.add("tracurl"); 
		taskAttributes.add("until"); //date
		taskAttributes.add("urgency");;
		taskAttributes.add("uuid"); 
		taskAttributes.add("wait"); //date
		taskAttributes.add("customSort"); //date	
	}
	
	@BeforeEach
	private void generateAttributeSetMethods() {
		taskAttributeSetMethods.add("setDepends");
		taskAttributeSetMethods.add("setDescription"); 
		taskAttributeSetMethods.add("setDue"); //date
		taskAttributeSetMethods.add("setEnd"); //date
		taskAttributeSetMethods.add("setEntry"); //date
		taskAttributeSetMethods.add("setEstimate"); 
		taskAttributeSetMethods.add("setId");;
		taskAttributeSetMethods.add("setImask");; 
		taskAttributeSetMethods.add("setMask"); 
		taskAttributeSetMethods.add("setModified"); //date
		taskAttributeSetMethods.add("setParent"); 
		taskAttributeSetMethods.add("setPriority"); 
		taskAttributeSetMethods.add("setProject"); 
		taskAttributeSetMethods.add("setRecur"); 
		taskAttributeSetMethods.add("setScheduled"); //date
		taskAttributeSetMethods.add("setStart"); //date
		taskAttributeSetMethods.add("setStatus"); 
		taskAttributeSetMethods.add("setTags"); 
		taskAttributeSetMethods.add("setTracnumber");; 
		taskAttributeSetMethods.add("setTracsummary"); 
		taskAttributeSetMethods.add("setTracurl"); 
		taskAttributeSetMethods.add("setUntil"); //date
		taskAttributeSetMethods.add("setUrgency");;
		taskAttributeSetMethods.add("setUuid"); 
		taskAttributeSetMethods.add("setWait"); //date
		taskAttributeSetMethods.add("setCustomSort"); //date
	}
	
	/**
	 * This test does not test any actual code in method readTasks.
	 * This test checks if there is no file found, if no file found & throws the IO
	 * exception its green
	 */
	@Test
	void testExpectedException() {
		Assertions.assertThrows(IOException.class, () -> {
			FileReader reader = new FileReader("nonExistant.data");
			reader.read();
			reader.close();
		});
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readTasks()}.
	 */
	@Test
	void testReadTasks() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readUnixUsername()}.
	 */
	@Test
	void testReadUnixUsername() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readTwPath()}.
	 */
	@Test
	void testReadTwPath() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#findTwFiles(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testFindTwFiles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readJSONLocation()}.
	 */
	@Test
	void testReadJSONLocation() {
		fail("Not yet implemented");
	}

	/**
	 * This test checks the returned testing filepath is not equal to the hardcoded testing filepath
	 */
	@Test
	public void testGetFilePath(){
		boolean testing = true;
		assertEquals(hardCoded.getEclipseFilePath()+hardCoded.getEclipseFileName(),ReadTasks.getFilePath(testing));
	}

	/**
	 * Checks whether the read method returns an arraylist that contains the strings
	 * of the lines that are in the file test.data.
	 * Test method for {@link customSortServerV4.ReadTasks#readFile(java.lang.String)}.
	 */
	@Test
	void testReadFile() {
		ArrayList<String> expectedLines = new ArrayList<>();
		ArrayList<String> returnedLines = new ArrayList<>();
		returnedLines =ReadTasks.readFile(ReadTasks.getFilePath(true)); 
		expectedLines.add(testTxtLine0);
		expectedLines.add(testTxtLine1);
		expectedLines.add(testTxtLine2);

		assertEquals(expectedLines,returnedLines);
		//Excessive test: iterate through the ArrayLists to compare whether all their elements are equal.
		for (int i=0;i<expectedLines.size();i++) {
			assertTrue(expectedLines.get(i).equals(returnedLines.get(i)));
		}
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readPerLine(java.lang.String)}.
	 */
	@Test
	void testReadPerLine() {
		//String fakeInputLine="[description:"+ Char(34) +"backup system"+ Chr(34) +" entry:"+ Chr(34) +"1535050397"+ Chr(34) +" modified:"+ Chr(34) +"1548757876"+ Chr(34) +" priority:"+ Chr(34) +"L"+ Chr(34) +" project:"+ Chr(34) +"Automation"+ Chr(34) +" secretSort:"+ Chr(34) +"33"+ Chr(34) +" status:"+ Chr(34) +"pending"+ Chr(34) +" uuid:"+ Chr(34) +"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2"+ Chr(34) +"]"
		Task testTask = new Task();
		String fakeInputLine= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
		Task expectedTask = new Task();
		String description = "\"backup system\"";
		String entry="\"1535050397\"";
		String modified = "\"1548757876\"";
		String priority = "\"L\"";
		String project = "\"Automation\"";
		String secretSort = "\"33\"";
		String status = "\"pending\"";
		String uuid = "\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"";

		//Assign description
		expectedTask.setDescription(description);
		testTask=ReadTasks.readPerLine(fakeInputLine);
		
		System.out.println("DescriptionKeyword="+testTask.getDescription());
		assertTrue(expectedTask.getDescription().equals(testTask.getDescription()));
		assertTrue(description.equals(testTask.getDescription()));
	}

	/**
	 * Assumes the f.getName method loops through the fields of 
	 * object task in the same order as they are typed in the
	 * Javacode. It tests whether the fields returned by method
	 * generateTaskAttributeList equals the list of attributes
	 * which are defined as fields in Task.
	 * This method will fail if new UDA's are added to method task.
	 */
	@Test
	void testGenerateTaskAttributeList() {
		ArrayList<String> returnedAttributes =ReadTasks.generateTaskAttributeList();
		
		assertEquals(taskAttributes,returnedAttributes );
		//Excessive test: iterate through the ArrayLists to compare whether all their elements are equal.
		for (int i=0;i<taskAttributes.size();i++) {
			assertTrue(taskAttributes.get(i).equals(returnedAttributes.get(i)));
		}
	}

	/**
	 * Checks if all set methods are returned of an object.
	 */
	@Test
	void testGetTaskAttributeGetMethods() {
		
		ArrayList<Method> returnedAttributeSetMethods =ReadTasks.getTaskAttributeGetMethods();
		ArrayList<String> returnedAttributeSetMethodNames =new ArrayList<>();
		for(int i=0;i<returnedAttributeSetMethods.size();i++) {
			returnedAttributeSetMethodNames.add(returnedAttributeSetMethods.get(i).getName());
		}

		

		assertTrue(taskAttributeSetMethods.containsAll(returnedAttributeSetMethodNames));
		assertTrue(returnedAttributeSetMethodNames.containsAll(taskAttributeSetMethods));
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readAttribute(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testReadAttribute() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#eatAttributeName(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testEatAttributeName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#checkAttributeLength(int)}.
	 */
	@Test
	void testCheckAttributeLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#createTasksPerLine()}.
	 */
	@Test
	void testCreateTasksPerLine() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#addTaskToList(customSortServerV4.Task)}.
	 */
	@Test
	void testAddTaskToList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#getUrgThresholdLocation()}.
	 */
	@Test
	void testGetUrgThresholdLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#getMaxNrOfUrgentTasksLocation()}.
	 */
	@Test
	void testGetMaxNrOfUrgentTasksLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#getMaxPercentageOfNrOfUrgentLocation()}.
	 */
	@Test
	void testGetMaxPercentageOfNrOfUrgentLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#readUrgThreshold()}.
	 */
	@Test
	void testReadUrgThreshold() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#askUrgThreshold()}.
	 */
	@Test
	void testAskUrgThreshold() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#askMaxNrOfUrgentTasks()}.
	 */
	@Test
	void testAskMaxNrOfUrgentTasks() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link customSortServerV4.ReadTasks#askMaxPercentageOfNrOfUrgentTasks()}.
	 */
	@Test
	void testAskMaxPercentageOfNrOfUrgentTasks() {
		fail("Not yet implemented");
	}

	@Test
	public void testSepararateLines() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadAttributeValue() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Checks if findMatchingSetMethod indeed correctly detects/matches
	 * the attribute name in the ArrayList of methods.
	 * @throws Exception
	 */
	@Test
	public void testFindMatchingSetMethod() throws Exception {
		//Get methods in an ArrayList
		ArrayList<Method> returnedAttributeSetMethods =ReadTasks.getTaskAttributeGetMethods();
		ArrayList<String> returnedAttributeSetMethodNames =new ArrayList<>();
		for(int i=0;i<returnedAttributeSetMethods.size();i++) {
			returnedAttributeSetMethodNames.add(returnedAttributeSetMethods.get(i).getName());
		}

		//check for all attribute names if it finds them in the method array
		for (int i=0;i<taskAttributes.size();i++) {
			assertTrue(returnedAttributeSetMethods.contains(ReadTasks.findMatchingSetMethod(taskAttributes.get(i),returnedAttributeSetMethods)));
		}
	}

	/**
	 * tests if it returns false for a non-existent property.
	 * @throws Exception
	 */
	@Test
	public void testFindMatchingSetMethodNonExistantAttributeName() throws Exception {
		//Get methods in an ArrayList
		ArrayList<Method> returnedAttributeSetMethods =ReadTasks.getTaskAttributeGetMethods();
		ArrayList<String> returnedAttributeSetMethodNames =new ArrayList<>();
		for(int i=0;i<returnedAttributeSetMethods.size();i++) {
			returnedAttributeSetMethodNames.add(returnedAttributeSetMethods.get(i).getName());
		}

		//put all attribute names into an array.
		ArrayList<String> taskAttributes =new ArrayList<>();
		taskAttributes.add("nonExistantAttribute");

		//check for all attribute names if it finds them in the method array
		for (int i=0;i<taskAttributes.size();i++) {
			assertFalse(returnedAttributeSetMethods.contains(ReadTasks.findMatchingSetMethod(taskAttributes.get(i),returnedAttributeSetMethods)));
		}
		for (int i=0;i<taskAttributes.size();i++) {
			assertEquals(null,(ReadTasks.findMatchingSetMethod(taskAttributes.get(i),returnedAttributeSetMethods)));
		}

	}

	/**
	 * Tests if findMatchingSetMethod returns false for an empty
	 * or null attribute name.
	 * @throws Exception
	 */
	@Test
	public void testFindMatchingSetMethodEmpty() throws Exception {
		//Get methods in an ArrayList
		ArrayList<Method> returnedAttributeSetMethods =ReadTasks.getTaskAttributeGetMethods();
		ArrayList<String> returnedAttributeSetMethodNames =new ArrayList<>();
		for(int i=0;i<returnedAttributeSetMethods.size();i++) {
			returnedAttributeSetMethodNames.add(returnedAttributeSetMethods.get(i).getName());
		}

		//put all attribute names into an array.
		ArrayList<String> taskAttributes =new ArrayList<>();
		taskAttributes.add("");
		taskAttributes.add(null);
		//check for all attribute names if it finds them in the method array
		for (int i=0;i<taskAttributes.size();i++) {
			assertFalse(returnedAttributeSetMethods.contains(ReadTasks.findMatchingSetMethod(taskAttributes.get(i),returnedAttributeSetMethods)));
		}
		for (int i=0;i<taskAttributes.size();i++) {
			assertEquals(null,(ReadTasks.findMatchingSetMethod(taskAttributes.get(i),returnedAttributeSetMethods)));
		}

	}

	/**
	 * Tests whether the method setTaskAttribute correctly sets the task
	 * attribute "description" if it is given the correct method.
	 * @throws Exception
	 */
	@Test
	public void testSetTaskAttribute() throws Exception {
		//setTaskAttribute(task,method,propertyValue)
		Task expected = new Task();
		Task testTask = new Task();
		Method method=null;
		String methodName = "setDescription";
		String description = "test";
		expected.setDescription(description);

		//get method setDescription:
		try {
			method = testTask.getClass().getMethod(methodName, String.class);
		} catch (SecurityException e) {}
		catch (NoSuchMethodException e) {}
		
		
		testTask=ReadTasks.setTaskAttribute(testTask,method,description);
		assertTrue(expected.getDescription().equals(testTask.getDescription()));
		assertTrue(description.equals(testTask.getDescription()));
	}
	
	/**
	 * Tests whether the string.equals of testSetTaskAttribute does
	 * not return a positive if the description is not set for the
	 * testTask object.
	 * @throws Exception
	 */
	@Test
	public void testSetTaskAttributeNotExecuted() throws Exception {
		//setTaskAttribute(task,method,propertyValue)
		Task expected = new Task();
		Task testTask = new Task();
		Method method=null;
		String methodName = "setDescription";
		String description = "test";
		expected.setDescription(description);

		//get method setDescription:
		try {
			method = testTask.getClass().getMethod(methodName, String.class);
		} catch (SecurityException e) {}
		catch (NoSuchMethodException e) {}
		
		//testTask=ReadTasks.setTaskAttribute(testTask,method,description);
		
		assertFalse(expected.getDescription().equals(testTask.getDescription()));
		assertFalse(description.equals(testTask.getDescription()));
	}
	
	
}
