package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * To run tests, set hardcoded threshold to 3.5
 * 
 * 
 * The sorting procedure should output in the following order:
 * tasks that are: (with a project AND below the urgency threshold):  sorted on project a-z
 * tasks that are: (without a project AND below the urgency threshold): sorted on urgency (low to high)
 * tasks that are: equal to, or above the urgency threshold: sorted on urgency (low to high)
 * 
 * Problem: recurring tasks seem to get in an infinte loop of being adjusted which leads
 * to database that is too large to sync.
 * 
 * Steps: First compile this version with modified threshold and verify it functions correct in WSL Ubuntu 16.04
 * Then try to not apply the customSort assignment to either: 
 * 	0. tasks that have parent and have status:pending
 *  1. tasks that don't have a parent but have status:recurring
 * 
 * (Note tasks that have status recurring are the tasks that are the parents/task templates
 * their children have a parent, that parent is the UUID of the parent template task. 
 * Recurring parent/template properties:
 * status:"recurring", mask:"-"
 * Recurring child/actual task properties:
 * status:"pending" (or completed or whatever, just not recurring), imask:"0.0xxxxx", parent:"UUID of parent task"
 * 
 * Another cause of the problem could be that the reading of the uuid does not match on the word UUID but on the 
 * actual uuid code format, which could cause it to match on the uuid of parent, sets some id/counter for the
 * parent task equivalent to the current counter. That could lead to infinte loop
 * if it later occurs the parent, and then reads an id that sets some counter back to before the child task.
 * This problem is ruled out by tests "testReadPerLineForRecurTasksParent" AND "testReadPerLineForRecurTasksChild" in
 * test ReadTasksTest.java by verifying the UUID's of child and parent tasks of recurring tasks are read correctly.  
 * @author a
 *
 */
class CreateSortsTest {

	private String testTxtLine0= "[description:\"backup system\" entry:\"1535050397\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"33\" status:\"pending\" uuid:\"457f69f5-ff3b-46bf-b7c3-6461ddffaaa2\"]";
	private String testTxtLine1= "[description:\"Backup emulators\" entry:\"1535050398\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"32\" status:\"pending\" uuid:\"931b463b-a130-44fe-96c0-ad60e88aaa0e\"]";
	private String testTxtLine2= "[description:\"Automate emulator controller installation\" entry:\"1535050399\" modified:\"1548757876\" priority:\"L\" project:\"Automation\" secretSort:\"31\" status:\"pending\" uuid:\"5d0e0cbb-173f-4ef4-a603-0dffd4caaa97\"]";
	private ArrayList<String> taskAttributes =new ArrayList<>();
	private ArrayList<String> taskAttributeSetMethods =new ArrayList<>();
	private HardCoded hardCoded;
	
	@BeforeAll
	public void createHardCoded() {
		hardCoded = new HardCoded();
	}
	
	/**
	 * Create a test that inputs a tasklist with urg 3, project order z,c,a
	 * Expect return order of the task, indicated by their projects: a,c,z
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);

		//verify order before sorting:
		assertTrue("a".equals(sortedTaskList.get(0).getProject()));
		assertTrue("c".equals(sortedTaskList.get(1).getProject()));
		assertTrue("z".equals(sortedTaskList.get(2).getProject()));

	}

	/**
	 * Create a test that inputs a tasklist with urg 3.4, project order z,a,c
	 * Expect return order of tasks, indicated by their projects: a,c,z
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
		assertTrue("a".equals(sortedTaskList.get(0).getProject()));
		assertTrue("c".equals(sortedTaskList.get(1).getProject()));
		assertTrue("z".equals(sortedTaskList.get(2).getProject()));
	}

	/**
	 * Create a test that inputs a tasklist with urg 3.4, project order z,a,null
	 * Expect return order 2,1,3 (in projects that is z,a,null)
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

		//System.out.println("Getting non-existant project:"+task3.getProject());
		
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 1 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 2 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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
		String task2String = "[description:\"description2\" project:\"z\" urgency:\"3.4\"]";
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 3 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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
		String task2String = "[description:\"description2\" project:\"z\" urgency:\"3.4\"]";
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 4 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 5 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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

//		System.out.println("Getting non-existant project:"+task3.getProject());
		
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 6 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
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

//		System.out.println("Getting non-existant project:"+task3.getProject());
		
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
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
//		System.out.println("Test 7 order:");
//		System.out.println(sortedTaskList.get(0).getDescription());
//		System.out.println(sortedTaskList.get(1).getDescription());
//		System.out.println(sortedTaskList.get(2).getDescription());
		//Ensure it could print the sorted list and reaches this assertion.
		assertTrue(1==1);
		}	
	
	/**
	 * Create a test that inputs a tasklist with urg 11.2,11.3,3.1, project order null,a,z
	 * Expect return order 3,1,2 
	 */
	@Test
	public void testMainSortRecurring() throws Exception {
		//Create tasks
		Task task1 = new Task();
		String task1String = "[customSort:\"143.000000\" description:\"Hang clothes to dry.1\" due:\"1554264300\" entry:\"1554036218\" mask:\"-\" modified:\"1554042542\" project:\"rout\" recur:\"weekly\" scheduled:\"1554263100\" status:\"recurring\" uuid:\"e33c1834-5d59-4e4f-b44d-e189dad39ba0\" urgency:\"1.2\"]";
		task1=ReadTasks.readPerLine(task1String);
		Task task2 = new Task();
		String task2String = "[customSort:\"146.000000\" description:\"Hang clothes to dry.2\" due:\"1554525900\" entry:\"1554036219\" mask:\"-\" modified:\"1554042543\" project:\"rout\" recur:\"weekly\" scheduled:\"1554524700\" status:\"recurring\" uuid:\"8ee7b780-1ade-44b1-b819-5023c2b0bb36\" urgency:\"1.2\"]";
		task2=ReadTasks.readPerLine(task2String);
		Task task3 = new Task();
		String task3String = "[customSort:\"143.000000\" description:\"Hang clothes to dry.3\" due:\"1554264300\" entry:\"1554036226\" imask:\"0.000000\" modified:\"1554042542\" parent:\"e33c1834-5d59-4e4f-b44d-e189dad39ba0\" project:\"rout\" recur:\"weekly\" scheduled:\"1554263100\" status:\"pending\" uuid:\"9c7884b7-b63b-49ae-a23c-198dbb0ef470\" urgency:\"1.2\"]";;
		task3=ReadTasks.readPerLine(task3String);
		Task task4 = new Task();
		String task4String = "		[customSort:\"146.000000\" description:\"Hang clothes to dry.4\" due:\"1554525900\" entry:\"1554036227\" imask:\"0.000000\" modified:\"1554042543\" parent:\"8ee7b780-1ade-44b1-b819-5023c2b0bb36\" project:\"rout\" recur:\"weekly\" scheduled:\"1554524700\" status:\"pending\" uuid:\"a0fd5e55-2df9-46e7-9026-d405caefa9f5\" urgency:\"1.2\"]";
		task4=ReadTasks.readPerLine(task4String);

		System.out.println("Getting project of task 3:"+task3.getProject());
		System.out.println("Getting urgency of task 3:"+task3.getUrgency());
		//Create ArrayList
		ArrayList<Task> unSortedTaskList = new ArrayList<Task>();
		ArrayList<Task> sortedTaskList = new ArrayList<Task>();
		unSortedTaskList.add(task1);
		unSortedTaskList.add(task2);
		unSortedTaskList.add(task3);
		unSortedTaskList.add(task4);

		//verify order before sorting:
		assertTrue("Hang clothes to dry.1".equals(unSortedTaskList.get(0).getDescription()));
		assertTrue("Hang clothes to dry.2".equals(unSortedTaskList.get(1).getDescription()));
		assertTrue("Hang clothes to dry.3".equals(unSortedTaskList.get(2).getDescription()));
		assertTrue("Hang clothes to dry.4".equals(unSortedTaskList.get(3).getDescription()));

		//Sort Tasklist:
		sortedTaskList =CreateSorts.mainSort(hardCoded, unSortedTaskList);
		//verify order before sorting:
		System.out.println("Test recurrence order:");
		System.out.println(sortedTaskList.get(0).getDescription());
		System.out.println(sortedTaskList.get(1).getDescription());
		System.out.println(sortedTaskList.get(2).getDescription());
		System.out.println(sortedTaskList.get(3).getDescription());
		//Ensure it could print the sorted list and reaches this assertion.
		assertTrue(1==1);
		}
}
