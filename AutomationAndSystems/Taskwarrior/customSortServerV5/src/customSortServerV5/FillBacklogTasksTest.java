package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FillBacklogTasksTest {
	public int nrOFTestTask = 6;
	public String[] twUuid = new String[nrOFTestTask ];
	public String[] parentUuid = new String[nrOFTestTask ];
	public String[] textLine = new String[nrOFTestTask ];
	public boolean[] recurring = new boolean[nrOFTestTask ];
	public BacklogTask[] task = new BacklogTask[nrOFTestTask ];
	
	char quotation = (char) 34;
	
	String line0ContainsCSort = "(asdgasd,"+quotation+"customSort"+quotation+":"+"342,"+quotation+"description";
	String line1ContainsCSortSame = "(asdgasd,"+quotation+"customSort"+quotation+":"+"344,"+quotation+"description";
	String line1ContainsCSortDifferent = "(asdgasd,"+quotation+"customSort"+quotation+":"+"342,"+quotation+"descriptionDIFFERENT";
	
	String line0NotContainsCSort = "(asdgasd,"+quotation+"description";
	String line1NotContainsCSortSame = "(asdgasd,"+quotation+"description";
	String line1NotContainsCSortDifferent = "(asdgasd,"+quotation+"descriptionDIFFERENT";
	
	@BeforeEach
	public void initEach() {
//		System.out.println("Ran before each test");
		twUuid[0] = "aaa";
		twUuid[1] = "bbb";
		twUuid[2] = "ccc";
		twUuid[3] = "ddd";
		twUuid[4] = "bbb";
		twUuid[5] = "ccc";
		
		textLine[0] = "0aaa1";
		textLine[1] = "0bbb1";
		textLine[2] = "0ccc1";
		textLine[3] = "0ddd1";
		textLine[4] = "0bbb1";
		textLine[5] = "0ccc1";
		
		recurring[0] = false;
		recurring[1] = false;
		recurring[2] = false;
		recurring[3] = false;
		recurring[4] = false;
		recurring[5] = false;
		
		for (int i= 0;i<nrOFTestTask ;i++) {
			task[i] = new BacklogTask(twUuid[i], parentUuid[i], textLine[i], recurring[i],i);
		}
	}
	


	
	/**
	 * Expect task 0 to be allocated to multiple 0
	 * expect task 1 and 4 to be allocated to multiple 1
	 * expect task 2 and 5 to be allocated to multiple 2
	 * expect task 3 to be allocated to multiple 3
	 * expect the total nr of multiples to equal 4. with indices 0 to 3.
	 */
	@Test
	void testGenerateCatalog() {
		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog catalog = new BacklogTaskCatalog(multiples);
		
		for (int i= 0;i<nrOFTestTask ;i++) {
			catalog = FillBacklogTasks.generateCatalog(catalog, task[i]);
		}
		
		assertTrue(1==1);
		assertTrue(catalog.getMultiples().get(0).getMultiples().get(0).getTwUUID().equals(task[0].getTwUUID()));
		assertTrue(catalog.getMultiples().get(1).getMultiples().get(0).getTwUUID().equals(task[1].getTwUUID()));
		assertTrue(catalog.getMultiples().get(1).getMultiples().get(1).getTwUUID().equals(task[4].getTwUUID()));
		assertTrue(catalog.getMultiples().get(2).getMultiples().get(0).getTwUUID().equals(task[2].getTwUUID()));
		assertTrue(catalog.getMultiples().get(2).getMultiples().get(1).getTwUUID().equals(task[5].getTwUUID()));
		assertTrue(catalog.getMultiples().get(3).getMultiples().get(0).getTwUUID().equals(task[3].getTwUUID()));
		
		// verify multiple 3 does not contain the (twUuid) of task 0.
		assertFalse(catalog.getMultiples().get(3).getMultiples().get(0).getTwUUID().equals(task[0].getTwUUID()));
		
		assertTrue(catalog.getMultiples().get(0).getMultiples().size()==1);
		assertTrue(catalog.getMultiples().get(1).getMultiples().size()==2);
		assertTrue(catalog.getMultiples().get(2).getMultiples().size()==2);
		assertTrue(catalog.getMultiples().get(3).getMultiples().size()==1);
		
		// verify multiple 3 does not have size 2
		assertFalse(catalog.getMultiples().get(3).getMultiples().size()==2);
	}
	
	/**
	 * This test is only valid in case all but the last entry of a task in backlog is modified.
	 * It will be invalid if only cSort modifications are removed (since every multiple contains
	 * more than 1 task that does not consist of a cSort modification).
	 * expect the total nr of multiples to equal 4. with indices 0 to 3 and sizes 1.
	 */
	@Test
	void testFilterBacklogCatalog() {
		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog catalog = new BacklogTaskCatalog(multiples);
		
		for (int i= 0;i<nrOFTestTask ;i++) {
			catalog = FillBacklogTasks.generateCatalog(catalog, task[i]);
		}
		
		BacklogTaskCatalog newCatalog = FillBacklogTasks.filterBacklogCatalog(catalog);
		
		assertTrue(newCatalog.getMultiples().get(0).getMultiples().size()==1);
		assertTrue(newCatalog.getMultiples().get(1).getMultiples().size()==1);
		assertTrue(newCatalog.getMultiples().get(2).getMultiples().size()==1);
		assertTrue(newCatalog.getMultiples().get(3).getMultiples().size()==1);
		
		// verify multiple 3 does not have size 2
		assertFalse(newCatalog.getMultiples().get(1).getMultiples().size()==2);
		
	}
	
	@Test
	void testManageBacklogFilling() {
		fail("Not yet implemented");
	}	
	
	@Test
	void testReadLines() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateBacklogTask() {
		fail("Not yet implemented");
	}

	@Test
	void testAssignMultiples() {
		fail("Not yet implemented");
	}

	@Test
	void testIsRecurringTask() {
		fail("Not yet implemented");
	}

	@Test
	void testFindTaskUuid() {
		fail("Not yet implemented");
	}

	@Test
	void testFindSubstring() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test removes null at start of array.
	 */
	@Test
	void testRemoveNullValuesAtStart() {
		BacklogTask[] taskListWithNulls = new BacklogTask[4];
		for (int i= 0;i<4;i++) {
			taskListWithNulls[i]=task[i];
		}
		taskListWithNulls[0]= null;
		
		taskListWithNulls = FillBacklogTasks.removeNullValues(taskListWithNulls);
		
		assertFalse(taskListWithNulls[0]==null);
		assertTrue(taskListWithNulls.length==3);
	}
	
	/**
	 * Test removes null at end of array.
	 */
	@Test
	void testRemoveNullValuesAtEnd() {
		BacklogTask[] taskListWithNulls = new BacklogTask[4];
		for (int i= 0;i<4;i++) {
			taskListWithNulls[i]=task[i];
		}
		taskListWithNulls[3]= null;
		
		taskListWithNulls = FillBacklogTasks.removeNullValues(taskListWithNulls);
		
		assertFalse(taskListWithNulls[2]==null);
		assertTrue(taskListWithNulls.length==3);
	}
	
	/**
	 * Test removes null at middle of array.
	 */
	@Test
	void tesRemoveNullValuesAtMiddle() {
		BacklogTask[] taskListWithNulls = new BacklogTask[5];
		for (int i= 0;i<5;i++) {
			taskListWithNulls[i]=task[i];
		}
		taskListWithNulls[3]= null;
		
		taskListWithNulls = FillBacklogTasks.removeNullValues(taskListWithNulls);
		
		assertFalse(taskListWithNulls[3]==null);
		assertTrue(taskListWithNulls.length==4);
	}
	
	/**
	 * Test removes multiple adjacent nulls at middle of array.
	 */
	@Test
	void testRemoveNullValuesMultNullsMiddle() {
		BacklogTask[] taskListWithNulls = new BacklogTask[4];
		for (int i= 0;i<4;i++) {
			taskListWithNulls[i]=task[i];
		}
		taskListWithNulls[1]= null;
		taskListWithNulls[2]= null;
		
		taskListWithNulls = FillBacklogTasks.removeNullValues(taskListWithNulls);
		
		assertFalse(taskListWithNulls[1]==null);
		assertTrue(taskListWithNulls.length==2);
	}

	/**
	 * Test removes separated  nulls at middle of array.
	 */
	@Test
	void testRemoveNullValuesSeparated() {
		BacklogTask[] taskListWithNulls = new BacklogTask[5];
		for (int i= 0;i<5;i++) {
			taskListWithNulls[i]=task[i];
		}
		taskListWithNulls[2]= null;
		taskListWithNulls[4]= null;
		
		taskListWithNulls = FillBacklogTasks.removeNullValues(taskListWithNulls);
		
		assertFalse(taskListWithNulls[2]==null);
		assertTrue(taskListWithNulls.length==3);
	}
	
	
	/**
	 * test whether A string contanis CSort 
	 */
	@Test
	void testContainsCSort() {
		char quotation = (char) 34;
		String testStringTrue = "asdgasd"+quotation+"customSort"+quotation+":"+"342";
		String testStringFalse = "asdf";
		String testStringFalseNull = null;
		assertTrue(FillBacklogTasks.containsCSort(testStringTrue));
		assertFalse(FillBacklogTasks.containsCSort(testStringFalse));
		assertFalse(FillBacklogTasks.containsCSort(testStringFalseNull));
	}
	
	
	/**
	 * 
	 */
	@Test
	void testRemoveCSortInfoFromLine() {
		char quotation = (char) 34;
		String before = "(asdgasd"+quotation+"customSort"+quotation+":"+"342,"+quotation+"description";
		String after = "(asdgasd"+quotation+"description";
		
//		System.out.println(before);
//		System.out.println(FillBacklogTasks.removeCSortInfoFromLine(before));
//		System.out.println(after);
		String testStringFalseNull = null;
		assertTrue(after.equals(FillBacklogTasks.removeCSortInfoFromLine(before)));
	}
	
	/**
	 * 
	 */
	@Test
	void testOnlyDifferenceIsCSort() {
		char quotation = (char) 34;
		String line0 = "(asdgasd"+quotation+"customSort"+quotation+":"+"342,"+quotation+"description";
		String line1True = "(asdgasd"+quotation+"customSort"+quotation+":"+"342,"+quotation+"description";
		String line1False = "(asdgasd"+quotation+"customSort"+quotation+":"+"342,"+quotation+"descriptionDIFFERENT";
		
		assertTrue(FillBacklogTasks.onlyDifferenceIsCSort(line0,line1True));
		assertFalse(FillBacklogTasks.onlyDifferenceIsCSort(line0,line1False));
	}
	
	
	/**
	 * passed 
	 */
	@Test
	void testKeepLinesSame11() {
		// both contain cSort
		// cSort is only difference
		assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1ContainsCSortSame)[0]);
		assertFalse(FillBacklogTasks.keepLines(line0ContainsCSort,line1ContainsCSortSame)[1]);
	}
	
	/**
	 * passed 
	 */
	@Test
	void testKeepLinesDifferent11() {
		// both contain cSort
			// more differences
			assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1ContainsCSortDifferent)[0]);
			assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1ContainsCSortDifferent)[1]);
	}

	/**
	 * passed
	 */
	@Test
	void testKeepLinesSame10() {
		//First contains cSort second does not
		// cSort is only difference
		assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1NotContainsCSortSame)[0]);
		// user actively deleted customSort data from task so keep it
		assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1NotContainsCSortSame)[1]); 		
	}
	
	/**
	 * passed
	 */
	@Test
	void testKeepLinesDifferent10() {
		//First contains cSort second does not
		// more differences
		assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1NotContainsCSortDifferent)[0]);
		assertTrue(FillBacklogTasks.keepLines(line0ContainsCSort,line1NotContainsCSortDifferent)[1]);
	}
			
	/**
	 * passed
	 */
	@Test
	void testKeepLinesSame01() {
		//First does not contain cSort, second does
		// cSort is only difference
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1ContainsCSortSame)[0]);
		assertFalse(FillBacklogTasks.keepLines(line0NotContainsCSort,line1ContainsCSortSame)[1]);
	}
	
	/**
	 * passed 
	 */
	@Test
	void testKeepLinesDifferent01() {			
		//First does not contain cSort, second does
		// more differences
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1ContainsCSortDifferent)[0]);
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1ContainsCSortDifferent)[1]);
	}
		
	/**
	 * passed
	 */
	@Test
	void testKeepLinesSame00() {
		//neither contain cSort
		// cSort is only difference
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1NotContainsCSortSame)[0]);
		// I can currently not think of a scenario where taskwarrior would add the exact same task twice 
		// without a CSort modification, but if it does, just keep it as a fail safe (and assertTrue).
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1NotContainsCSortSame)[1]);
	}
	
	/**
	 * pass 
	 */
	@Test
	void testKeepLinesDifferent00() {
		//neither contain cSort
		// more differences
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1NotContainsCSortDifferent)[0]);
		assertTrue(FillBacklogTasks.keepLines(line0NotContainsCSort,line1NotContainsCSortDifferent)[1]);
	}
	
	
	/**
	 * pass 
	 */
	@Test
	void testOnlyRemoveCSortModifications1_0() {
		ArrayList<BacklogTask> taskList = generateTaskList1();
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(0).getLineNr()==0);

	}
	
	/**
	 * pass 
	 */
	@Test
	void testOnlyRemoveCSortModifications1_1() {
		ArrayList<BacklogTask> taskList = generateTaskList1();
		System.out.println("tasklist.length="+taskList.size());
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(1).getLineNr()==2);

	}

	/**
	 * pass 
	 */
	@Test
	void testOnlyRemoveCSortModifications1_2() {
		ArrayList<BacklogTask> taskList = generateTaskList1();
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(2).getLineNr()==3);
	}

	/**
	 * pass
	 */
	@Test
	void testOnlyRemoveCSortModifications1_3() {
		ArrayList<BacklogTask> taskList = generateTaskList1();
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(3).getLineNr()==4);
	}

	
	private static ArrayList<BacklogTask> generateTaskList1() {
		System.out.println("generator1");
		String[] t = new String[5];
		char quotation = (char) 34;
		t[0] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"3,"+quotation+"ab";
		t[1] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"4,"+quotation+"ab";
		t[2] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"4,"+quotation+"ac";
		t[3] = "(asdgasd,"+quotation+"ac";
		t[4] = "(asdgasd,"+quotation+"ab";
		BacklogTask[] backlogTask = new BacklogTask[5];
//		BacklogTask(String twUuid, String parentUuid, String textLine, boolean recurring, int lineNr){
		backlogTask[0] = new BacklogTask("uuid","parentUuid",t[0],false,0);
		backlogTask[1] = new BacklogTask("uuid","parentUuid",t[1],false,1);
		backlogTask[2] = new BacklogTask("uuid","parentUuid",t[2],false,2);
		backlogTask[3] = new BacklogTask("uuid","parentUuid",t[3],false,3);
		backlogTask[4] = new BacklogTask("uuid","parentUuid",t[4],false,4);
		ArrayList<BacklogTask> taskList = new ArrayList<BacklogTask>();
		taskList.add(backlogTask[0]);
		taskList.add(backlogTask[1]);
		taskList.add(backlogTask[2]);
		taskList.add(backlogTask[3]);
		taskList.add(backlogTask[4]);
		return taskList;
	}
	
	/**
	 * pass 
	 */
	@Test
	void testOnlyRemoveCSortModifications2_1() {
		ArrayList<BacklogTask> taskList = generateTaskList2();
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(0).getLineNr()==0);
	}

	/**
	 * Pass
	 */
	@Test
	void testOnlyRemoveCSortModifications2_2() {
		ArrayList<BacklogTask> taskList = generateTaskList2();
		int indicator =FillBacklogTasks.onlyRemoveCSortModifications(taskList).get(1).getLineNr();
		System.out.println("Indicator="+indicator); //1
		assertTrue(indicator==3);
	}
	
	/**
	 * pass 
	 */
	@Test
	void testOnlyRemoveCSortModifications2_3() {
		ArrayList<BacklogTask> taskList = generateTaskList2();
		assertTrue(FillBacklogTasks.onlyRemoveCSortModifications(taskList).size()==2);
	}
	
	private static ArrayList<BacklogTask> generateTaskList2() {
		System.out.println("generator2");
		String[] t = new String[5];
		char quotation = (char) 34;
		t[0] = "(asdgasd,"+quotation+"ab";
		t[1] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"3,"+quotation+"ab";
		t[2] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"4,"+quotation+"ab";
		t[3] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"4,"+quotation+"ac";
		t[4] = "(asdgasd,"+quotation+"customSort"+quotation+":"+"3,"+quotation+"ac";
		BacklogTask[] backlogTask = new BacklogTask[5];
//		BacklogTask(String twUuid, String parentUuid, String textLine, boolean recurring, int lineNr){
		backlogTask[0] = new BacklogTask("uuid","parentUuid",t[0],false,0);
		backlogTask[1] = new BacklogTask("uuid","parentUuid",t[1],false,1);
		backlogTask[2] = new BacklogTask("uuid","parentUuid",t[2],false,2);
		backlogTask[3] = new BacklogTask("uuid","parentUuid",t[3],false,3);
		backlogTask[4] = new BacklogTask("uuid","parentUuid",t[4],false,4);
		ArrayList<BacklogTask> taskList = new ArrayList<BacklogTask>();
		taskList.add(backlogTask[0]);
		taskList.add(backlogTask[1]);
		taskList.add(backlogTask[2]);
		taskList.add(backlogTask[3]);
		taskList.add(backlogTask[4]);
		return taskList;
	}
}