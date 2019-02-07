package customSortServerV4;

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

}
