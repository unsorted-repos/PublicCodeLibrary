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
		System.out.println("Creating lines=");
		for (int i = 0; i < taskList.length; i++) {
			lines[i]=taskList[i].getTextLine();
			System.out.println(lines[i]);
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
		printLines(lines);
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
					System.out.println("File is created! in path:"+linuxPath);
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
		System.out.println("Writing lines=");
		for (int i = 0; i < lines.length;i++) {
			System.out.println(lines[i]);
		}
	}
	
	/**
	 * Writes the powershell scripts that that launch wsl commands.
	 * @throws IOException
	 */
	public static void createTestLaunchers(String testFileName, String[] lines){
//		String linuxTestFilePath = HardCoded.getLinuxPath();
		
		String windowsTestFilePath = GetThisPath.getWindowsPath()+"src/"+HardCoded.getTestDataFolder()+"/"+HardCoded.getTestWslLaunchersFolder()+"/";
		System.out.println("windowsTestFilePath ="+windowsTestFilePath);
		
		// auto create wslLaunchers folder in testData folder
		CreateFolders.createFolderWithEclipse(windowsTestFilePath);

		// first delete the file in case an old version existed.
		deleteFile(windowsTestFilePath + testFileName);

		// create a file called vars with content "content"
		createFile2(windowsTestFilePath, testFileName);

		// write content of test file
		CreateFiles.writeFileContent(windowsTestFilePath, testFileName, lines);
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
	
	public static void createPowershellLauncherScript() {
		char quotation = (char) 34; // quotation mark "		
		String linuxJarPath = HardCoded.getLinuxPath();
		String[] lines = new String[1];
		lines[0] = "wsl java -jar "+quotation+linuxJarPath+HardCoded.getCompiledJarName()+quotation;
		CreateFiles.createTestLaunchers(HardCoded.getWslLauncherScriptName(),lines);
	}
	
	public static void createPowershellWhoamiScript() {
		System.out.println("Creating whoami");
		char quotation = (char) 34; // quotation mark "		
		String linuxJarPath = HardCoded.getLinuxPath();
		String[] lines = new String[1];
		lines[0] = "wsl whoami";
		CreateFiles.createTestLaunchers(HardCoded.getWslWhoamiScriptName(),lines);
	}
}
