/**
 * 
 */
package customSortServerV4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author a
 *
 */
class CreateSortsTest {

	private String testTxtLine0= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
	private String testTxtLine1= "[description:\"Backup emulators\" entry:\"1535050398\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"32\" status:\"pending\" uuid:\"931b463b-a130-44fe-96c0-ad60e88aaa0e\"]";
	private String testTxtLine2= "[description:\"Automate emulator controller installation\" entry:\"1535050399\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"31\" status:\"pending\" uuid:\"5d0e0cbb-173f-4ef4-a603-0dffd4caaa97\"]";
	private ArrayList<String> taskAttributes =new ArrayList<>();
	private ArrayList<String> taskAttributeSetMethods =new ArrayList<>();


	/**
	 * Create a test that inputs a tasklist with urg 3, project order z,c,a
	 * Expect return order a,c,z
	 */
	@Test
	public void testMainSort() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description\" project:\"z\" urgency:\"3.4\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description\" project:\"c\" urgency:\"3.4\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description\" project:\"a\" urgency:\"3.4\"]";
		task3=ReadTasks.readPerLine(task3String);

		//Create an expected task to verify the attribute urgency is set correctly
		String expectedUrgency = "3.4";
		Task expectedTask=new Task();

		//test assignment and retreival of urgency attribute.
		expectedTask.setUrgency(expectedUrgency);
		assertTrue(expectedTask.getUrgency()==(task1.getUrgency()));
		assertTrue(3.4==(task1.getUrgency()));

		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("z".equals(unSortedTaskList.get(0).getProject()));
		assertTrue("c".equals(unSortedTaskList.get(1).getProject()));
		assertTrue("a".equals(unSortedTaskList.get(2).getProject()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);

		//verify order before sorting:
		assertTrue("a".equals(sortedTaskList.get(0).getProject()));
		assertTrue("c".equals(sortedTaskList.get(1).getProject()));
		assertTrue("z".equals(sortedTaskList.get(2).getProject()));

	}

	/**
	 * Create a test that inputs a tasklist with urg 3.4, project order z,a,c
	 * Expect return order a,c,z
	 */
	@Test
	public void testMainSort0() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description\" project:\"z\" urgency:\"3.4\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description\" project:\"a\" urgency:\"3.4\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description\" project:\"c\" urgency:\"3.4\"]";
		task3=ReadTasks.readPerLine(task3String);

		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("z".equals(unSortedTaskList.get(0).getProject()));
		assertTrue("a".equals(unSortedTaskList.get(1).getProject()));
		assertTrue("c".equals(unSortedTaskList.get(2).getProject()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		assertTrue("a".equals(sortedTaskList.get(0).getProject()));
		assertTrue("c".equals(sortedTaskList.get(1).getProject()));
		assertTrue("z".equals(sortedTaskList.get(2).getProject()));
	}

	/**
	 * Create a test that inputs a tasklist with urg 3.4, project order z,a,null
	 * Expect return order 2,1,3
	 */
	@Test
	public void testMainSort1() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" project:\"z\" urgency:\"3.4\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" project:\"a\" urgency:\"3.4\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"3.4\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 1 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description2".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description1".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(sortedTaskList.get(2).getDescription()));		
	}


	/**
	 * Create a test that inputs a tasklist with urg 3.4,3.5,3.4, project order z,null,null
	 * Expect return order 1,3,2
	 */
	@Test
	public void testMainSort2() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" project:\"z\" urgency:\"3.4\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" urgency:\"3.5\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"3.4\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 1 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description1".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description3".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description2".equals(sortedTaskList.get(2).getDescription()));		
	}	

	/**
	 * Create a test that inputs a tasklist with urg 3.5,3.4,3.4, project order null,z,null
	 * Expect return order 2,3,1
	 */
	@Test
	public void testMainSort3() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" urgency:\"3.5\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" project:\"z\" urgency:\"3.5\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"3.4\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 1 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description2".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description3".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description1".equals(sortedTaskList.get(2).getDescription()));		
	}
	
	
	/**
	 * Create a test that inputs a tasklist with urg 11.2,3.4,3.1, project order null,z,null
	 * Expect return order 2,3,1
	 */
	@Test
	public void testMainSort4() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" urgency:\"11.2\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" project:\"z\" urgency:\"3.5\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"3.1\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 4 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description2".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description3".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description1".equals(sortedTaskList.get(2).getDescription()));		
	}	
	
	
	/**
	 * Create a test that inputs a tasklist with urg 11.5,11.4,11.3, project order null,z,null
	 * Expect return order 3,2,1
	 */
	@Test
	public void testMainSort5() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" urgency:\"11.5\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" project:\"z\" urgency:\"11.4\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"11.3\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 5 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description3".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description1".equals(sortedTaskList.get(2).getDescription()));		
	}	

	/**
	 * Create a test that inputs a tasklist with urg 11.2,11.3,3.1, project order null,a,z
	 * Expect return order 3,1,2
	 */
	@Test
	public void testMainSort6() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" urgency:\"11.2\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" project:\"a\" urgency:\"11.3\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" project:\"z\" urgency:\"3.1\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 4 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		assertTrue("description3".equals(sortedTaskList.get(0).getDescription()));
		assertTrue("description1".equals(sortedTaskList.get(1).getDescription()));
		assertTrue("description2".equals(sortedTaskList.get(2).getDescription()));		
	}	

	/**
	 * Create a test that inputs a tasklist with urg 11.2,11.3,3.1, project order null,a,z
	 * Expect return order 3,1,2
	 */
	@Test
	public void testMainSort7() throws Exception {

		//Create tasks
		Task task1 = new Task();
		String task1String = "[description:\"description1\" urgency:\"11.2\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[description:\"description2\" urgency:\"11.3\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[description:\"description3\" urgency:\"3.1\"]";
		task3=ReadTasks.readPerLine(task3String);

		System.out.println("Getting non-existant project:"+task3.getProject());
		
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);

		//verify order before sorting:
		assertTrue("description1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("description2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("description3".equals(unSortedTaskList.get(2).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test 4 order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		//Ensure it could print the sorted list and reaches this assertion.
		assertTrue(1==1);
		}	
	
}
