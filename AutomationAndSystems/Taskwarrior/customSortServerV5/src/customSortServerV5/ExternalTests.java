package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ExternalTests {

	/**
	 * Writes the wslLauncherX.ps1 with X = natural number.
	 * @throws IOException
	 */
	public void createTestLaunchers(String testFileName, String[] lines){
//		String linuxTestFilePath = HardCoded.getLinuxPath();
		
		String windowsTestFilePath = GetThisPath.getWindowsPath()+"src/"+HardCoded.getTestDataFolder()+"/"+HardCoded.getTestWslLaunchersFolder()+"/";
		
		//TODO: Auto create wslLaunchers folder in testData folder
		CreateFolders.createFolderWithEclipse(windowsTestFilePath);

		
		System.out.println("Creating testfile:" + windowsTestFilePath );

		// First delete the file in case an old version existed.
		deleteFile(windowsTestFilePath + testFileName);

		// create a file called vars with content "content"
		createFile2(windowsTestFilePath, testFileName);

		// write content of test file
		CreateFiles.writeFileContent(windowsTestFilePath, testFileName, lines);
	}
	
	/**
	 * TODO: Remove additional paths from getLinuxSRCPath
	 */
	@Test
	void test() {
		
		char quotation = (char) 34; // quotation mark "		
		String linuxJarPath = HardCoded.getLinuxPath();
		
		System.out.println("JarPath = "+linuxJarPath+HardCoded.getCompiledJarName());
		String[] lines = new String[1];
		lines[0] = "wsl java -jar "+quotation+linuxJarPath+HardCoded.getCompiledJarName()+quotation;
		createTestLaunchers(HardCoded.getWslLauncherScriptName(),lines);
		System.out.println("Created test file");
		RunPowershell.runPowershell();
		fail("Not yet implemented");
	}

	/**
	 * This method uses MoveTestFiles to temporarily absorb (copy to internal
	 * temporary memory) the backlog.data and pending.data file and puts the
	 * respective custom made backlog.data and pending.data files back into the
	 * taskwarrior. Then it runs the main custom sort script Verifies the results
	 * And then copies the original files back to their respective locations.
	 * 
	 * TODO: Make different test files with single and multiple tasks without
	 * recurrence and print what happens to them.
	 */

	/**
	 * ID:0 Test A: Test that has a single task in the tasklist without any backlog.
	 * Expect A: Expect the task to be placed in the backlog since the first task is
	 * always stored. Test B: Repeat with all non-recurrent Expect B: That task is
	 * not placed in the backlog!! Result B: That task was added to the backlog.
	 * Test C: Repeat with all recurrent parent Expect C: Test D: Repeat with all
	 * recurrent child Expect D:
	 */
//	@Test
//	public void testMainSort2() {
//		// absorb original backlog.data and pending.data files for safekeeping
//		MoveTestFiles moveTestFiles = new MoveTestFiles();
//
//		// copy backlog0.data to home
//		String sourcePath = HardCoded.getTestDataFolder();
//		String sourceFileName = "backlog0.data";
//		String destinationPath = HardCoded.getUbuntuFilePath();
//		String destinationFileName = HardCoded.getBacklogFileName();
//		File mockTestFile = new File(sourcePath + sourceFileName);
//		MoveTestFiles.exportResource(mockTestFile, destinationPath, destinationFileName, false);
//
//		// run main.
//
//		// read backlog.data
//
//		// restore original backlog.data and pending.data files.
//
//		assertTrue(false);
//	}

	/**
	 * ID:1 Test A: that has a single task in the tasklist with that same task in
	 * backlog. Expect A: Expect the task to NOT be placed in the backlog since the
	 * first task is always stored. Test B: Repeat with all non-recurrent Expect B:
	 * Test C: Repeat with all recurrent parent Expect C: Test D: Repeat with all
	 * recurrent child Expect D:
	 */

	/**
	 * ID:2 Test A: that has two tasks in the tasklist without any backlog Expect A:
	 * Expect both task to be placed in the backlog since the first task is always
	 * stored. Test B: Repeat with all non-recurrent Expect B: Test C: Repeat with
	 * all recurrent parent Expect C: Test D: Repeat with all recurrent child Expect
	 * D:
	 */

	/**
	 * ID:3 Test A: that has two tasks in the tasklist both in backlog already, but
	 * with sole modification: they don't have customsort value (and they have a
	 * different modified value than the new backlog tasks will have). Expect A:
	 * Expect none of the two tasks will be added, since it is just the modification
	 * of the customsort value (backlog remains same) Test B: Repeat with all
	 * non-recurrent Expect B: Test C: Repeat with all recurrent parent Expect C:
	 * Test D: Repeat with all recurrent child Expect D:
	 */

	/**
	 * ID:4 Test A: that has two tasks in the tasklist both in backlog already, but
	 * with some extra modification in description: value (and they have a different
	 * modified value than the new backlog tasks will have). Expect A: Expect none
	 * of the two tasks will be added, since it is just the modification of the
	 * customsort value (backlog remains same) Test B: Repeat with all non-recurrent
	 * Expect B: Test C: Repeat with all recurrent parent Expect C: Test D: Repeat
	 * with all recurrent child Expect D:
	 */



	/**
	 * Delete a file that is located in the same folder as the src folder of this
	 * project is located.
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // surround it in try catch block
	}

	/**
	 * create a file in c.
	 * 
	 * @param content
	 */
	public static void createFile2(String linuxPath, String fileName) {
		{
			try {

				// File file = new File("c:\\vars.txt");
				System.out.println("Creating new file0:" + linuxPath + fileName);
				File file = new File(linuxPath + fileName);

				if (file.createNewFile()) {
					System.out.println("File is created!");
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
