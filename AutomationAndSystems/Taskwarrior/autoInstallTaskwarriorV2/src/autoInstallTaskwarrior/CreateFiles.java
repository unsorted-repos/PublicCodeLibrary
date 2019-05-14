package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

public class CreateFiles {

	/**
	 * This creates the Vars file required in command 8
	 * 
	 * @param serverName
	 * @throws IOException
	 */
	public static void createVars(InstallData installData) throws IOException {

		System.out.println("Incoming path inc. filename:" + installData.getLinuxPath() + installData.getVars());
		deleteFile(installData.getVars());

		// create a file called vars with content "content"
		createFile2(installData.getLinuxPath(), installData.getVars());
		// createFile1(installData.getLinuxPath(),installData.getVars());

		// write content of vars file
		writeFileContent(installData, installData.getVars());
	}

	/**
	 * This creates the sudoers.sh file required in command 8
	 * 
	 * @param serverName
	 * @throws IOException
	 */
	public static void createSudoers(InstallData installData) throws IOException {

		System.out.println(
				"Incoming path inc. filename:" + installData.getLinuxPath() + installData.getSudoersFileName());
		deleteFile(installData.getSudoersFileName());

		// create a file called vars with content "content"
		createFile2(installData.getLinuxPath(), installData.getSudoersFileName());
		// createFile1(installData.getLinuxPath(),installData.getSudoersFileName());

		// write content of vars file
		writeFileContent(installData, installData.getSudoersFileName());
	}

	/**
	 * This method writes the content of the vars file.
	 * 
	 * @param installData
	 */
	public static void writeFileContent(InstallData installData, String fileName) {
		char quotation = (char) 34; // quotation mark "

		PrintWriter writer;
		try {
			writer = new PrintWriter(installData.getLinuxPath() + fileName, "UTF-8");
			switch (fileName) {
			case "vars":
				writer = writeLinesVars(installData, writer);
				break;
			case ".bashrc":
				writer = writeLinesBashrc(installData, writer);
				break;
			case "sudoers.sh":
				writer = writeLinesSudoers(installData, writer);
				break;
			}

			writer.close();
			System.out.println("JUST WROTE CONTENT of " + fileName + " FILE!");
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

//	public static void createFile1(String linuxPath, String fileName) {
//		try {
//			System.out.println("Creating new file1:"+linuxPath+fileName);
//            File file = new File(linuxPath+fileName);
//            file.createNewFile();
//            //file.createNewFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}

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
			System.out.println("File:" + path + filename + " exists");
			return true;
		} else {
			System.out.println("ERROR!! The file:" + path + filename + " does NOT exist");
			return false;
		}
	}

	public static void makeScriptRunnable(String Path, String scriptName) {

		// create chmod command
		Command command = new Command();
		String[] commandLines = new String[4];

		commandLines[0] = "chmod";
		commandLines[1] = "+x";
		commandLines[2] = scriptName;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath(Path);
		command.setSetWorkingPath(true);
	}

	public static PrintWriter writeLinesVars(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("BITS=4096");
		writer.println("EXPIRATION_DAYS=365");
		// writer.println("ORGANIZATION="+quotation+"Göteborg Bit Factory"+quotation);
		writer.println("ORGANIZATION=" + quotation + "Goteborg Bit Factory" + quotation);
		writer.println("CN=" + installData.getServerName() + ":" + installData.getServerPort());
		writer.println("COUNTRY=SE");
		// writer.println("STATE="+quotation+"Västra Götaland"+quotation);
		writer.println("STATE=" + quotation + "Vastra Gotaland" + quotation);
		// writer.println("LOCALITY="+quotation+"Göteborg"+quotation);
		writer.println("LOCALITY=" + quotation + "Goteborg" + quotation);
		return writer;
	}

	public static PrintWriter writeLinesBashrc(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("#get root");
		writer.println("if [ ! -f /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool ]; then");
		writer.println("   echo " + quotation + "Getting sudo rights now." + quotation);
		writer.println("   sudo touch /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool");
		writer.println("   sudo -s");
		writer.println("fi");

		writer.println("# remove got root boolean for next time you boot up Unix");
		writer.println("sudo rm /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool");

		writer.println("#Start cron service");
		writer.println("sudo -i service cron start");

		writer.println("#Startup taskwarrior");
		writer.println("export TASKDDATA=/var/taskd");
		writer.println("cd $TASKDDATA");
		writer.println("sudo taskd config --data $TASKDDATA");

		writer.println("taskdctl start");
		writer.println("task sync");
		return writer;
	}

	public static PrintWriter writeLinesSudoers(InstallData installData, PrintWriter writer) {
		char quotation = (char) 34; // quotation mark "
		writer.println("#!/bin/sh");
		writer.println("sudo bash -c 'echo \"" + installData.getLinuxUserName()
				+ " ALL=(ALL) NOPASSWD:ALL\" >> /etc/sudoers'");
		return writer;
	}
}
