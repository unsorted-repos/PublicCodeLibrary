package customSortServerV4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadTasksTest {
	String fakeInputLine= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
	
//	@BeforeEach
//	public void generateTestData() {
//		
//	}
	
	
	/**
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

	@Test
	public void testReadTasks() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadUnixUsername() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadTwPath() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testFindTwFiles() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadJSONLocation() throws Exception {
		throw new RuntimeException("not yet implemented");
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
	 * This test checks the returned testing filepath is not equal to a incorrect filepath
	 */
	@Test
	public void testGetFilePathWrongInput(){
		boolean testing = true;
		String wrongPath="incorrect filepath";
		assertNotEquals(wrongPath,ReadTasks.getFilePath(testing));
	}
	
	@Test
	public void testReadTwPendingFile() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadTasksFromJsonPerLine() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadTasksPerLine() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testAddTaskToList() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetUrgThresholdLocation() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetMaxNrOfUrgentTasksLocation() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetMaxPercentageOfNrOfUrgentLocation() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadUrgThreshold() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testAskUrgThreshold() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testAskMaxNrOfUrgentTasks() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testAskMaxPercentageOfNrOfUrgentTasks() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testReadPerLine() throws Exception {
		//String fakeInputLine="[description:"+ Char(34) +"backup system"+ Chr(34) +" entry:"+ Chr(34) +"1535050397"+ Chr(34) +" modified:"+ Chr(34) +"1548757876"+ Chr(34) +" priority:"+ Chr(34) +"L"+ Chr(34) +" project:"+ Chr(34) +"Automation"+ Chr(34) +" secretSort:"+ Chr(34) +"33"+ Chr(34) +" status:"+ Chr(34) +"pending"+ Chr(34) +" uuid:"+ Chr(34) +"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2"+ Chr(34) +"]"
		String fakeInputLine= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
		ReadTasks.readPerLine(fakeInputLine);
		
	}

	@Test
	public void testEatPropertyName() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCheckPropertyLength() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGenerateTaskPropertyList() throws Exception {
		ReadTasks.generateTaskPropertyList(fakeInputLine);
	}

	@Test
	public void testGetTaskPropertyGetters() {
		ReadTasks.getTaskPropertyGetters();
		System.out.println("Done");
	}

}
