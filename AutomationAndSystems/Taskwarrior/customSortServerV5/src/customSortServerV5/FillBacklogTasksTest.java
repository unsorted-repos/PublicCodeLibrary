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
	
	@BeforeEach
	public void initEach() {
		
		System.out.println("Ran before each test");
		
		
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
			task[i] = new BacklogTask(twUuid[i], parentUuid[i], textLine[i], recurring[i]);
		}
	}
	
	@Test
	void testManageBacklogFilling() {
		fail("Not yet implemented");
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
			FillBacklogTasks.generateCatalog(catalog, task[i]);
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

}
