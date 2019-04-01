package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
	private Task taskOne;
	private Task taskTwo;
	
	@BeforeEach
    public void initTasks() {
        taskOne = new Task();
        taskTwo = new Task();
    }
	
	
	@Test
	void testTask() {
		fail("Not yet implemented");
	}

	
	@Test
	void testGetId() {
		taskOne.setId(3);
		assertEquals(3, taskOne.getId());
	}

	@Test
	void testSetId() {
		taskOne.setId(4);
		assertEquals(4, taskOne.getId());
	}

	@Test
	void testGetProject() {
		assertEquals(null, taskOne.getProject());
	}

	@Test
	void testSetProject() {
		String project="a";
		taskOne.setProject(project);
		assertEquals("a",taskOne.getProject());
		assertNotEquals("b",taskOne.getProject());
	}

	@Test
	void testGetUrgency() {
		fail("Not yet implemented");
	}

	@Test
	void testSetUrgency() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCustomSort() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCustomSort() {
		fail("Not yet implemented");
	}


	
	/**
	 * Check if it removes zeros:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals() throws Exception {
		String withZeros="423.000";
		String expected="423";
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(expected.equals(testTask.removeZeroDecimals(withZeros)));
	}

	/**
	 * Check if it removes the dot:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals0() throws Exception {
		String withZeros="423.";
		String expected="423";
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(expected.equals(testTask.removeZeroDecimals(withZeros)));
	}
	
	/**
	 * Check if it returns a complete integer:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals1() throws Exception {
		String withZeros="423";
		String expected="423";
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(expected.equals(testTask.removeZeroDecimals(withZeros)));
	}
	
	/**
	 * Check if it returns a complete integer ending at 0:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals2() throws Exception {
		String withZeros="4230";
		String expected="4230";
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(expected.equals(testTask.removeZeroDecimals(withZeros)));
	}

	/**
	 * Check if it returns a the original number if it has a nonzero decimal:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals3() throws Exception {
		String withZeros="4230.0300";
		String expected="4230.0300";
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(expected.equals(testTask.removeZeroDecimals(withZeros)));
	}
	
	/**
	 * Check if it returns a the original number if it has a nonzero decimal:
	 * @throws Exception
	 */
	@Test
	public void testRemoveZeroDecimals4() throws Exception {
		String withZeros=null;
		String expected=null;
		Task testTask = new Task();
		System.out.println("Returned:"+testTask.removeZeroDecimals(withZeros));
		assertTrue(testTask.removeZeroDecimals(withZeros)==null);
	}
}

