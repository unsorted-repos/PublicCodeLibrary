package customSortServerV5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class CreateFiles {

	
	/**
	 * This method writes the content of the vars file.
	 * 
	 * @param installData
	 */
	public static void writeFileContent(String filePathName, String fileName, BacklogTask[] taskList) {

		PrintWriter writer;
		try {
			writer = new PrintWriter(filePathName + fileName, "UTF-8");
			writer = writeLinesBacklog(writer,taskList);
			writer.close();
			System.out.println("JUST WROTE CONTENT of " + fileName + " FILE! To path:"+filePathName);
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
	

	public static PrintWriter writeLinesBacklog(PrintWriter writer, BacklogTask[] taskList) {
		char quotation = (char) 34; // quotation mark "
		for (int i = 0; i < taskList.length;i++) {
			writer.println(taskList[i].getTextLine());
		}
		return writer;
	}

}
