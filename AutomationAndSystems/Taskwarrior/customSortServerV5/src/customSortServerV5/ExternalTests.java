package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExternalTests {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	/**
	 * This method uses MoveTestFiles to temporarily absorb (copy to internal temporary memory) the backlog.data and pending.data
	 * file and puts the respective custom made backlog.data and pending.data files back into the taskwarrior.
	 * Then it runs the main custom sort script
	 * Verifies the results
	 * And then copies the original files back to their respective locations.
	 * 
	 * TODO: Make different test files with single and multiple tasks without recurrence and print what happens to them.
	 */
}
