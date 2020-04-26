package customSortServerV5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExternalTests {

//	private HardCoded hardCoded;
//
//	
//	public void createHardCoded() {
//		hardCoded  = new HardCoded();
//	}

	/**
	 * creates the powershell script that launches the wsl with the command that
	 * launches the JavaServerSort.jar
	 */
	@BeforeAll
	public static void createPowershellScript() {
		HardCoded hardCoded = new HardCoded();
//		System.out.println("CreatedHardCODED");
		CreateFiles.createPowershellLauncherScript(hardCoded);
		CreateFiles.createPowershellWhoamiScript(hardCoded);
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


	/** ID:0 Test A: Test that has a single task in the tasklist without any backlog without customSortAttribute.
	 * Expect A: Expect the task to be placed in the backlog since the first task is
	 * always stored. 
	 * 
	 * Test B: Repeat with all non-recurrent Expect B: That task is
	 * not placed in the backlog!! 
	 * Result B: That task was added to the backlog.
	 * 
	 * Test C: Repeat with all recurrent parent Expect C: Test D: Repeat with all
	 * recurrent child Expect D:
	 */
	@Test
	public void integrationTestJavaServerSort0() {
		String testBacklogFilename = "backlog0.data";
		String testPendingFilename = "pending0.data";
		
		ArrayList<String> expectedBacklogLines = new ArrayList<String>();
		expectedBacklogLines.add("This is a tw uuid filler that will be skipped");
		//{"description":"testHi","entry":"20190608T191027Z","modified":"20190608T191027Z","status":"pending","uuid":"8e7de71c-0cc3-4645-8414-59df506fa6ee"}
		expectedBacklogLines.add("{\"description\":\"testHi\",\"entry\":\"20190608T191027Z\",\"modified\":\"20190608T191027Z\",\"status\":\"pending\",\"uuid\":\"8e7de71c-0cc3-4645-8414-59df506fa6ee\"}");		
		
		ArrayList<String> actualBacklogLines = manageIntegrationTestsJavaServerSort(testBacklogFilename,testPendingFilename);
		
		assertTrue(actualBacklogLines.size()==2);
		// test the imported backlog with the expected backlog.
		assertEquals(expectedBacklogLines.get(1),actualBacklogLines.get(1));
	}

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
	 * TODO: refactor general integration test functionality to separate method.
	 * TODO: Write actual expected backlog lines per test
	 * TODO: Compare those actual backlog lines per test
	 */
	public ArrayList<String> manageIntegrationTestsJavaServerSort(String testBacklogFilename, String testPendingFilename){
//	public void manageIntegrationTestsJavaServerSort(){
		HardCoded hardCoded = new HardCoded();

		// absorb original backlog.data and pending.data files for safekeeping
		MoveTestFiles moveTestFiles = new MoveTestFiles(hardCoded);

		// get tw uuid from backlog
		ArrayList<String> originalBacklogLines = ReadFiles
				.readFiles(moveTestFiles.returnTempBacklogFile(hardCoded).getPath());
		System.out.println("Linenrs=" + originalBacklogLines.size());

		// print lines of backlog
//		printLines(originalBacklogLines);

		// Copy the current tw uuid to the testFiles
		copyOriginalTwUuidToTestBacklogs(hardCoded);

		// copy the testfile backlog0.data to home
		copyBacklogAndPendingMockFiles(hardCoded, testBacklogFilename, testPendingFilename);

		// run main. (Including execution bypass in wslLauncher.ps1 is NOT necessary.)
		System.out.println("Running the main!");
		ArrayList<String> output = RunPowershell.runPowershell(RunPowershell.powershellCommand(hardCoded), true);
		printLines(output);

		// create testOutput folder
		CreateFolders.createFolderWithEclipse(hardCoded.getWindowsPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestDataOutputFolderName() + "/");

		// copy backlog.data back from wsl to testData/testOutput
		copyModifiedBacklogToOutput(hardCoded);
		copyModifiedPendingToOutput(hardCoded);

		// read lines of backlog starting at 2nd line
		ArrayList<String> outputBacklogLines = readOutputBacklogLines(hardCoded);
		printLines(outputBacklogLines);
		
		// restore original backlog.data and pending.data files.
		restoreOriginalBacklog(hardCoded);
		restoreOriginalPending(hardCoded);
		
		return outputBacklogLines;
		
	}
	
	
	/**
	 * create a file in c.
	 * 
	 * @param content
	 */
	public static void createFile2(String linuxPath, String fileName) {
		{
			try {
				File file = new File(linuxPath + fileName);

				if (file.createNewFile()) {
					System.out.println("File is created! in Linuxpath" + linuxPath);
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void printLines(ArrayList<String> lines) {
		System.out.println("Printing taskList of lenght="+lines.size());
		for (int i = 0; i < lines.size(); i++) {
			System.out.println("i="+i);
			if (lines.get(i) != null) {
					System.out.println("line="+lines.get(i));
			}else {
				System.out.println("Line i="+i+" is null");
			}
			
		}
		System.out.println("Those are the lines");
	}
	
	/**
	 * Absorbs the lines from the backlog that is modified by execution of the main sorting script.
	 * @param hardCoded
	 * @return the lines of the backlog.data file that is put in the output folder of testing.
	 */
	private ArrayList<String> readOutputBacklogLines(HardCoded hardCoded){
		String outputBacklogFilePath = hardCoded.getWindowsPath()+"src/"+hardCoded.getTestDataFolder()+"/"+hardCoded.getTestDataOutputFolderName()+"/";
		String outputBacklogFilename = hardCoded.getBacklogFileName();
		return ReadFiles.readFiles(outputBacklogFilePath+outputBacklogFilename);
	}
	
	/**
	 * Copy the tw uuid from the testDataOriginals backlog.data file first line to
	 * all the backlogX.data files.
	 * 
	 */
	private void copyOriginalTwUuidToTestBacklogs(HardCoded hardCoded) {
		String testBacklogFilePath = hardCoded.getWindowsPath() + "src/" + hardCoded.getTestDataFolder() + "/";
		String originalBacklogPath = hardCoded.getWindowsPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestOriginalDataFolderName() + "/";
		String originalFilename = hardCoded.getBacklogFileName();

		// read first line of orignal backlog
		ArrayList<String> lines = ReadFiles.readFiles(originalBacklogPath + originalFilename);

		// copy first line to all other test backlogs
		// read tw uuid
		ArrayList<String> twUuid = new ArrayList<String>();
		twUuid.add(ReadFiles.readFiles(originalBacklogPath + originalFilename).get(0));

		for (int i = 0; i < hardCoded.getNrOfBacklogTestFile(); i++) {
			String[] splitTestFilename = splitFilenameOnDot(originalFilename);
			String testFilename = splitTestFilename[0] + i + splitTestFilename[1];

			// create file representing test backlog.data file
			File backlogFile = new File(testBacklogFilePath + testFilename);

			// put tw uuid in first line of backlog
			ModifyFiles.replaceFirstLine(backlogFile, twUuid.get(0));
		}
	}

	public String[] splitFilenameOnDot(String filename) {
		String[] splittedFilename = new String[2];
		splittedFilename[0] = filename.substring(0, filename.indexOf("."));
		splittedFilename[1] = filename.substring(filename.indexOf("."), filename.length());
		return splittedFilename;
	}

	public void promptUserInputPause() {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter a number: ");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		// once finished
		System.out.println(n);
	}

	public void copyBacklogAndPendingMockFiles(HardCoded hardCoded, String backlogFileName, String pendingFileName) {
		String sourcePath = hardCoded.getLinuxPath() + "src/" + hardCoded.getTestDataFolder() + "/";
		String destinationPath = hardCoded.getUbuntuFilePath();

		String destinationBacklogFileName = hardCoded.getBacklogFileName();
		File mockBacklogFile = new File(sourcePath + backlogFileName);
		MoveTestFiles.manageCopyFilesFromWinInLinux(hardCoded, mockBacklogFile, destinationPath,
				destinationBacklogFileName, false);

		String destinationPendingFileName = hardCoded.getPendingFileName();
		File mockPendingFile = new File(sourcePath + pendingFileName);
		MoveTestFiles.manageCopyFilesFromWinInLinux(hardCoded, mockPendingFile, destinationPath,
				destinationPendingFileName, false);
	}

	public void copyModifiedBacklogToOutput(HardCoded hardCoded) {
		String sourcePath = hardCoded.slashDirToRight(hardCoded.getUbuntuFilePath());
		String backlogFileName = hardCoded.getBacklogFileName();
		String destinationPath = hardCoded.getLinuxPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestDataOutputFolderName() + "/";
		String destinationFileName = hardCoded.getBacklogFileName();
		MoveTestFiles.copyResource(hardCoded, sourcePath, backlogFileName, destinationPath, destinationFileName, false);
	}
	
	//TODO: Remove/refactor duplicate codes.
	public void copyModifiedPendingToOutput(HardCoded hardCoded) {
		String sourcePath = hardCoded.slashDirToRight(hardCoded.getUbuntuFilePath());
		String backlogFileName = hardCoded.getPendingFileName();
		String destinationPath = hardCoded.getLinuxPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestDataOutputFolderName() + "/";
		String destinationFileName = hardCoded.getPendingFileName();
		MoveTestFiles.copyResource(hardCoded, sourcePath, backlogFileName, destinationPath, destinationFileName, false);
	}

	public void restoreOriginalBacklog(HardCoded hardCoded) {
		String sourcePath = hardCoded.getLinuxPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestOriginalDataFolderName() + "/";
		String sourceFileName = hardCoded.getBacklogFileName();
		String destinationPath = hardCoded.slashDirToRight(hardCoded.getUbuntuFilePath());
		String destinationFileName = hardCoded.getBacklogFileName();

		MoveTestFiles.copyResource(hardCoded, sourcePath, sourceFileName, destinationPath, destinationFileName, false);
	}

	public void restoreOriginalPending(HardCoded hardCoded) {
		String sourcePath = hardCoded.getLinuxPath() + "src/" + hardCoded.getTestDataFolder() + "/"
				+ hardCoded.getTestOriginalDataFolderName() + "/";
		String sourceFileName = hardCoded.getPendingFileName();
		String destinationPath = hardCoded.slashDirToRight(hardCoded.getUbuntuFilePath());
		String destinationFileName = hardCoded.getPendingFileName();

		MoveTestFiles.copyResource(hardCoded, sourcePath, sourceFileName, destinationPath, destinationFileName, false);
	}
}
