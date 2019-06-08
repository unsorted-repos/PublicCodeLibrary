package customSortServerV5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ModifyFiles {

	
	public static void replaceFirstLine(File backlogTestFile,String twUuidLine) {
		try{
		    FileReader fr = new FileReader(backlogTestFile);
		    String s;
		    String totalStr = "";
		    try (BufferedReader br = new BufferedReader(fr)) {
		    	br.readLine(); //skip first line of copying
		    	totalStr += twUuidLine+'\n'; // add tw uuid
		        while ((s = br.readLine()) != null) {
		            totalStr += s+'\n';
		        }
		        FileWriter fw = new FileWriter(backlogTestFile);
		    fw.write(totalStr);
		    fw.close();
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}
	}
	
	
	public static boolean deleteFile(HardCoded hardCoded, File file) {
		try
        { 
            Files.deleteIfExists(Paths.get(hardCoded.slashDirToRight(file.getPath()))); 
        } 
        catch(NoSuchFileException e) 
        { 
            System.out.println("No such file/directory exists"); 
        } 
        catch(DirectoryNotEmptyException e) 
        { 
            System.out.println("Directory is not empty."); 
        } 
        catch(IOException e) 
        { 
            System.out.println("Invalid permissions."); 
        } 
    	return false;
	}

//	public static void appendText(InstallData installData, File fileName) {
//		FileOutputStream fileOutputStream = null;
//
//		BufferedReader br = null;
//		FileReader fr = null;
//		String newFileName = fileName.getAbsolutePath() + "@";
//
//		try {
//			fileOutputStream = new FileOutputStream(newFileName);
//
//			fr = new FileReader(fileName);
//			br = new BufferedReader(fr);
//
//			String sCurrentLine;
//
//			while ((sCurrentLine = br.readLine()) != null) {
//				fileOutputStream.write((sCurrentLine + "\n").getBytes());
//
//			}
//
//			fileOutputStream = writeVisudo(installData, fileOutputStream);
//
//			fileOutputStream.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fileOutputStream != null)
//					fileOutputStream.close();
//				if (br != null)
//					br.close();
//
//				if (fr != null)
//					fr.close();
//
//				new File(newFileName).renameTo(new File(newFileName.replace("@", "")));
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}

	private static FileOutputStream writeLines(FileOutputStream fileOutputStream, ArrayList<String> lines) {
		for (int i = 0; i <lines.size();i++) {
			try {
				fileOutputStream.write(lines.get(i).getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileOutputStream;
	}
	
	/**
	 * Write the lines you wish to prepend to the text file. A line/string ending
	 * with \n starts a new line. The last line does not need a \n to make the
	 * original text start at a new line.
	 * 
	 * @param fileOutputStream
	 * @return
	 * @throws IOException
	 */
//	private static FileOutputStream writeBashrc(InstallData installData, FileOutputStream fileOutputStream)
//			throws IOException {
//		char quotation = (char) 34; // quotation mark "
//		fileOutputStream.write(("#get root" + "\n").getBytes());
//		fileOutputStream
//				.write(("if [ ! -f /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool ]; then" + "\n")
//						.getBytes());
//		fileOutputStream.write(("   echo " + quotation + "Getting sudo rights now." + quotation + "\n").getBytes());
//		fileOutputStream
//				.write(("   sudo touch /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool" + "\n")
//						.getBytes());
//		fileOutputStream.write(("   sudo -s" + "\n").getBytes());
//		fileOutputStream.write(("fi" + "\n").getBytes());
//		fileOutputStream.write(("\n").getBytes());
//
//		fileOutputStream.write(("# remove got root boolean for next time you boot up Unix" + "\n").getBytes());
//		fileOutputStream.write(
//				("sudo rm /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool" + "\n").getBytes());
//		fileOutputStream.write(("\n").getBytes());
//
//		fileOutputStream.write(("#Start cron service" + "\n").getBytes());
//		fileOutputStream.write(("sudo -i service cron start" + "\n").getBytes());
//		fileOutputStream.write(("\n").getBytes());
//
//		fileOutputStream.write(("#Startup taskwarrior" + "\n").getBytes());
//		fileOutputStream.write(("export TASKDDATA=/var/taskd" + "\n").getBytes());
//		fileOutputStream.write(("cd $TASKDDATA" + "\n").getBytes());
//		fileOutputStream.write(("sudo taskd config --data $TASKDDATA" + "\n").getBytes());
//		fileOutputStream.write(("\n").getBytes());
//
//		fileOutputStream.write(("taskdctl start" + "\n").getBytes());
//		fileOutputStream.write(("task sync" + "\n").getBytes());
//		fileOutputStream.write(("\n").getBytes());
//
//		return fileOutputStream;
//	}
	




	/**
	 * Removes the first n-lines of a file.
	 * 
	 * @param installData
	 * @param filePath
	 * @param fileName
	 * @param nrOfLinesToSkip
	 */
	public static void removeFirstNLines(String filePath, String fileName, int nrOfLinesToSkip) {
		File file = new File(filePath + fileName);
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			for (int i = 0; i < nrOfLinesToSkip; i++) {
				if (fileScanner.hasNext()) {
					System.out.println("Skipping line:"+fileScanner.nextLine());
				}
				
			}
			FileWriter fileStream = new FileWriter(filePath + fileName);
			BufferedWriter out = new BufferedWriter(fileStream);
			while (fileScanner.hasNextLine()) {
				String next = fileScanner.nextLine();
				if (next.equals("\n"))
					out.newLine();
				else
					out.write(next);
				out.newLine();
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//chmod o+x /home/user
	public static String enforceWriteAccess(String filePath, String fileName) {
		Command command = new Command();
		String[] commandLines = new String[4];
		commandLines[0] = "sudo";
		commandLines[1] = "chmod";
		commandLines[2] = "o+x";
		commandLines[3] = filePath+fileName;
		command.setCommandLines(commandLines);
//		command.setEnvVarContent("/var/taskd");
//		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("");
		command.setSetEnvVar(false);
		command.setSetWorkingPath(false);	
		command.setGetOutput(true);

		// execute command to create destination folder
		try {
			return RunCommandsV3.executeCommands(command, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}