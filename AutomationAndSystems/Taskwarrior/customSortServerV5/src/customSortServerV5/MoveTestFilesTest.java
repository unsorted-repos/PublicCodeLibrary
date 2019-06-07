package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveTestFilesTest {

	@Test
	void testSplitFilenameOnDot() {
		HardCoded hardCoded = new HardCoded();

		// absorb original backlog.data and pending.data files for safekeeping
		MoveTestFiles moveTestFiles = new MoveTestFiles(hardCoded);
		
		String filename = "backlog.data";
		String prepend = "backlog";
		String suffix = ".data";
		
		String[] output = moveTestFiles.splitFilenameOnDot(filename);
		

		assertEquals(output[0],prepend);
		assertEquals(output[1],suffix);
	}

}
