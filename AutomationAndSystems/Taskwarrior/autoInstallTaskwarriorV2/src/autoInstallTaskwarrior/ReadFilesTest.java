/**
 * 
 */
package autoInstallTaskwarrior;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author a
 *
 */
class ReadFilesTest {

	/**
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#readFiles(java.lang.String)}.
	 */
	@Test
	void testReadFiles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#firstLinesMatch(java.lang.String[], int, java.lang.String[])}.
	 */
	@Test
	void testFirstLinesMatch() {
		fail("Not yet implemented");
	}

	/**
	 * Tests proper functioning of intended use.
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#lastLinesMatch(java.lang.String[], int, java.lang.String[])}.
	 */
	@Test
	void testLastLinesMatch() {
		String[] original = new String[4];
		original[0]="a";
		original[1]="b";
		original[2]="c";
		original[3]="d";
		String[] comparison = new String[3];
		comparison[0]="Wrong";
		comparison[1]="c";
		comparison[2]="d";
		
		assertTrue(ReadFiles.lastLinesMatch(original,1,comparison));
		assertTrue(ReadFiles.lastLinesMatch(original,2,comparison));
		assertFalse(ReadFiles.lastLinesMatch(original,3,comparison));
	}

	/**
	 * Tests proper functioning of intended use.
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#lastLinesMatch(java.lang.String[], int, java.lang.String[])}.
	 */
	@Test
	void testLastLinesMatch1() {
		String[] original = new String[4];
		original[0]="a";
		original[1]="b";
		original[2]="c";
		original[3]="d";
		String[] comparison = new String[3];
		comparison[0]="b";
		comparison[1]="c";
		comparison[2]="d";
		
		assertTrue(ReadFiles.lastLinesMatch(original,1,comparison));
		assertTrue(ReadFiles.lastLinesMatch(original,2,comparison));
		assertTrue(ReadFiles.lastLinesMatch(original,3,comparison));
	}
	
	/**
	 * Tests proper functioning of intended use.
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#lastLinesMatch(java.lang.String[], int, java.lang.String[])}.
	 */
	@Test
	void testLastLinesMatch2() {
		String[] original = new String[4];
		original[0]="a";
		original[1]="b";
		original[2]="c";
		original[3]="d";
		String[] comparison = new String[1];
		comparison[0]="d";
		
		assertTrue(ReadFiles.lastLinesMatch(original,1,comparison));
	}
	
	/**
	 * Tests whether it throws exception in erroneous useage.
	 * Test method for {@link autoInstallTaskwarrior.ReadFiles#lastLinesMatch(java.lang.String[], int, java.lang.String[])}.
	 */
	@Test
	void testLastLinesMatchBadUseage() {
		String[] original = new String[4];
		original[0]="a";
		original[1]="b";
		original[2]="c";
		original[3]="d";
		String[] comparison = new String[3];
		comparison[0]="Wrong";
		comparison[1]="c";
		comparison[2]="d";
		
		
		for (int nrOfLinesToCompare = 0; nrOfLinesToCompare > -4; nrOfLinesToCompare--) {	
		    try {
		    	ReadFiles.lastLinesMatch(original,nrOfLinesToCompare ,comparison);
		        fail("Exception not thrown");
		    } catch (IllegalArgumentException e) {
		        assertEquals("Method lastLinesMatch must have more than 1 nr of lines to compare. But you requested:"+nrOfLinesToCompare+" lines.", e.getMessage());
		    }
		}
	}
	
	//TODO: Test index out of bounds error for both original array as comparison array.
}
