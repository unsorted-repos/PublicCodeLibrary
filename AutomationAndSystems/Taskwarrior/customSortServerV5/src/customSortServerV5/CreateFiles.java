package customSortServerV5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class CreateFiles {

	/**
	 * Loops through the task array and copies the lines of the tasks into a string array and returns it.
	 * @param taskList
	 * @return
	 */
	public static String[] backlogTaskArrayToStringArray(BacklogTask[] taskList) {
		String[] lines = new String[taskList.length];
		for (int i = 0; i < taskList.length; i++) {
			lines[i]=taskList[i].getTextLine();
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
					System.out.println("File is created!");
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
}
