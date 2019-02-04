package customSortServerV4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReadTasksTest {

	@Test
	void testReadUnixUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testReadTwPath() {
		fail("Not yet implemented");
	}

	@Test
	void testFindTwFiles() {
		fail("Not yet implemented");
	}

	@Test
	void testReadJSONLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testReadTasksFromJSON() {
		fail("Not yet implemented");
	}

	/**
	 * This test checks if there is no file found, if no file found & throws the IO exception its green
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
	void testReadTasksPerLine() {
		fail("Not yet implemented");
	}

	@Test
	void testAddTaskToList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUrgThresholdLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMaxNrOfUrgentTasksLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMaxPercentageOfNrOfUrgentLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testReadUrgThreshold() {
		fail("Not yet implemented");
	}

	@Test
	void testAskUrgThreshold() {
		fail("Not yet implemented");
	}

	@Test
	void testAskMaxNrOfUrgentTasks() {
		fail("Not yet implemented");
	}

	@Test
	void testAskMaxPercentageOfNrOfUrgentTasks() {
		fail("Not yet implemented");
	}

	@Test
	void testIsWindows() {
		fail("Not yet implemented");
	}

	@Test
	void testSetWindows() {
		fail("Not yet implemented");
	}

}