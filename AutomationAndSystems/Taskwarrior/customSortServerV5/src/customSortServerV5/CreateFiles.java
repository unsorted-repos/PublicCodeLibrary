package customSortServerV5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;


public class CreateFiles {

	/**
	 * Loops through the task array and copies the lines of the tasks into a string array and returns it.
	 * @param taskList
	 * @return
	 */
	public static String[] backlogTaskArrayToStringArray(BacklogTask[] taskList) {
		String[] lines = new String[taskList.length];
//		System.out.println("Creating lines=");
		for (int i = 0; i < taskList.length; i++) {
			lines[i]=taskList[i].getTextLine();
			System.out.println("TASK put into backlog="+lines[i]);
		}
		return null;
	}
	
	/**
	 * This method writes the content of the vars file.
	 * 
	 * @param installData
	 */
//	public static void writeFileContent(String filePathName, String fileName, BacklogTask[] taskList) {
	public static void writeFileContent(String filePathName, String fileName, String[] lines) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(filePathName + fileName, "UTF-8");
			writer = writeLines(writer, lines);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing " + fileName + e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing " + fileName + e);
		}
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
//					System.out.println("File is created! in path:"+linuxPath);
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkIfFilesExist(String path, String[] filenames) {
		for (int i = 0; i < filenames.length; i++) {
			if (!checkIfFileExist(path, filenames[i])) {
				return false;
			}
			// String absFilePath = "/home/"+ubuntuUsername+"/.task/"+filename[i];
		}
		return true;
	}

	/**
	 * Checks if the file filename in folder path exists.
	 * 
	 * @param path
	 * @param filename
	 * @return
	 */
	public static boolean checkIfFileExist(String path, String filename) {

		// merge file path and file name to file object
		File f = new File(path + filename);

		// check if file exists
		if (f.exists() && !f.isDirectory()) {
//			System.out.println("File:" + path + filename + " exists");
			return true;
		} else {
//			System.out.println("ERROR!! The file:" + path + filename + " does NOT exist");
			return false;
		}
	}
	

	/**
	 * Used to accept BacklogTask[] instead of String[]
	 * @param writer
	 * @param taskList
	 * @return
	 */
//	public static PrintWriter writeLinesBacklog(PrintWriter writer, BacklogTask[] taskList) {
	public static PrintWriter writeLines(PrintWriter writer, String[] lines) {	
		// Start with writing on a new line.
		
		for (int i = 0; i < lines.length;i++) {
			writer.println(lines[i]);
		}
		return writer;
	}
	
	public static void printLines(String[] lines) {	
		// Start with writing on a new line.
		System.out.println("The lines that are put back into backlog.data are=");
		for (int i = 0; i < lines.length;i++) {
			System.out.println(lines[i]);
		}
		System.out.println("Those are the lines");
	}
	
	/**
	 * Writes the powershell scripts that that launch wsl commands.
	 * @throws IOException
	 */
	public static void managePowershellSciptCreation(HardCoded hardCoded,String commandPath, String testFileName, String[] lines){
//		String linuxTestFilePath = hardCoded.getLinuxPath();
		
//		String windowsTestFilePath = GetThisPath.getWindowsPath()+"src/"+hardCoded.getTestDataFolder()+"/";
//		System.out.println("windowsTestFilePath ="+windowsTestFilePath);
		
		// auto create wslLaunchers folder in testData folder
		CreateFolders.createFolderWithEclipse(commandPath);

		// first delete the file in case an old version existed.
//		System.out.println("Deleting filename="+testFileName);
		deleteFile(commandPath+ testFileName);

		// create a file called vars with content "content"
//		System.out.println("Creating file:"+testFileName);
		createFile2(commandPath, testFileName);

		// write content of test file
		CreateFiles.writeFileContent(commandPath, testFileName, lines);
	}
	
	/**
	 * Delete a file that is located in the same folder as the src folder of this
	 * project is located.
	 * 
	 * @param fileName
	 */
	public static void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
//			System.out.println("While deleting"+fileName+"This path is not empty:"+file.toPath());
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // surround it in try catch block
	}
	
	/**
	 * creates the powershell script that launches the wsl with the command that launches
	 * the JavaServerSort.jar 
	 */
	
	public static void createPowershellLauncherScript(HardCoded hardCoded) {
		char quotation = (char) 34; // quotation mark "		
		String launchCustomSortShellPath= hardCoded.getWindowsPath()+"src/"+hardCoded.getTestDataFolder()+"/"+hardCoded.getTestWslLaunchersFolder()+"/";
		
		String[] lines = new String[1];
		lines[0] = "wsl java -jar "+quotation+hardCoded.getLinuxPath()+hardCoded.getCompiledJarName()+quotation;
		CreateFiles.managePowershellSciptCreation(hardCoded, launchCustomSortShellPath, hardCoded.getWslLauncherScriptName(),lines);
	}
	
	public static void createPowershellWhoamiScript(HardCoded hardCoded ) {
//		System.out.println("Creating whoami");
		char quotation = (char) 34; // quotation mark "		
		String linuxJarPath = hardCoded.getLinuxPath();
		String[] lines = new String[1];
		lines[0] = "wsl whoami";
		CreateFiles.managePowershellSciptCreation(hardCoded, linuxJarPath, hardCoded.getWslWhoamiScriptName(),lines);
	}
}
