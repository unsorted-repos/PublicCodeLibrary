package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Before compiling, set testRun to false! Before you run this
 * project/script, enter the following commands in WSL Ubuntu:
 * 
 * yes | sudo apt update
 * 
 * yes | sudo apt install default-jre
 * 
 * If you store this script on your Windows harddrive in
 * c:/copyToUbuntu/autoInstallTaskwarrior.jar you run the script with:
 * 
 * java -jar "/mnt/c/copyToUbuntu/autoInstallTaskwarrior.jar
 */
public class Main {

	public static void main(String[] args) throws Exception {
		char quotation = (char) 34; // quotation mark "
		InstallData installData = HardCoded.hardCoded();

		skipToNewPage();
		
		System.out.println("Backuprestore=" + installData.isRestoreBackup());
		// create the external non-resource files (export with commands 9,57 iso
		// exportResource.
		CreateFiles.createVars(installData);
		CreateFiles.createSudoers(installData);

		System.out.println("Server and port are:" + installData.getServerName() + "and=" + installData.getServerPort());

		// create cronjob
		CreateCron c = new CreateCron();
		c.doStuff(installData);
		c.writeJobs();

		// export resources autoBackup.sh and javaServerSort.jar
		CopyFiles.exportResource(installData, "autoBackup.sh", true);
		CopyFiles.exportResource(installData, "JavaServerSort.jar", true);

		// get commands
		Command[] commands = GenerateCommandsV3.generateCommands(installData);

		// execute installation commands
		manageCommandGeneration(installData, commands);

		installGCalSyn(installData);

		// run shell
//		RunShell.runScript("/home/a/.bashrc");

		// Create gCalSync installation shell
		CreateFiles.writeFileContent(installData, "gCalSyncInstaller.sh");

		// create the .basshrc file.
		System.out.println("backupScriptDestination=" + installData.getBackupScriptDestination());
		exportBashrc(installData);

		// run JavaServerSort once
		System.out.println("Running javasort");
		runJavaServerSort(installData);

		// import certificates
		importData(installData);
		// export certificates if this is the server installation.
		CopyFiles.exportServerCertificates(installData);

		// Loop asking user to reboot WSL Ubuntu 16.04
		System.out.println("Please enter:sudo python3 /home/" + installData.getLinuxUserName() + "/"
				+ installData.getMaintenanceFolder() + "/" + installData.getgCalSyncCloneFolder() + "/tw_gcal_sync -c "
				+ quotation + "TW Reminders" + quotation + " -t remindme");

		// Run gCalSync installation shell
		RunShell.runShellWithSudo(installData.getBackupScriptDestination(), installData.getgCalSyncInstallScriptName());
		
		AskUserInput.promptReboot(installData);
		System.exit(0);
	}

	/**
	 * First writes the gCalSyncInstaller.sh shell script that will install the
	 * google calendar synchronisation tool.
	 * 
	 * Then it copies the file from within the project, to the usage destination.
	 * 
	 * After it is copied, it is made runnable with chmod +x, so that it can be
	 * installed at the end of the installation.
	 * 
	 * @param installData
	 */
	private static void installGCalSyn(InstallData installData) {
		try {
			CreateFiles.createGCalSyncInstall(installData);
			CopyFiles.copyFileWithSudo(installData, installData.getLinuxPath(),
					installData.getgCalSyncInstallScriptName(), installData.getBackupScriptDestination(),
					installData.getgCalSyncInstallScriptName());
			CreateFiles.makeScriptRunnable(installData.getBackupScriptDestination(),
					installData.getgCalSyncInstallScriptName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Manages the importation of the:
	 * 
	 * Certificates and tw uuid of the server, in case the installation is for a
	 * multi-device setup with the current device as client.
	 * 
	 * Backup files pending.data etc of a previous installation (if the user
	 * requests to do so).
	 * 
	 * @param installData
	 */
	private static void importData(InstallData installData) {

		// import certificates if this is client installation.
		if (!installData.isUseSingleDevice() && installData.isServer()) {
			ImportFiles.importCertificates(installData);
			ModifyTwConfig.setTwServerUuid(installData);
		}

		// import backup data
		if (installData.isRestoreBackup()) {
			System.out.println("importing backup");
			ImportFiles.importBackups(installData);

		}
	}

//	private static void testOverWritingBacklog(InstallData installData) {
//		installData.setTwUuid("d5c234ab-71d4-4fa1-b978-c4f586d15222");
//		//copy to backlog1.data
//		String sourcePath = installData.getBackupInputPath();
//		String sourceFileName = installData.getRestoreBackupNames()[0];
//		String destinationPath = sourcePath;
//		String destinationFileName = sourceFileName+installData.getCopyText();
//		CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName,
//				destinationPath, destinationFileName);
//		System.out.println("Copied:"+sourcePath+sourceFileName+" to:"+destinationPath+destinationFileName);
//		overWriteOldTwUuidImportedBacklog(installData);
//		System.exit(0);
//	}
	
	

	/**
	 * First the method checks whether the .bashrc file that is located in
	 * /home/<your tw username> is already modified. If it is not modified, it is
	 * modified and stored back into /home/<your tw username>.
	 * 
	 * @param installData
	 * @throws Exception
	 */
	private static void exportBashrc(InstallData installData) throws Exception {
		File testFile = new File(installData.getBashrcPath(), installData.getBasrcFileName());
		// TODO: Refactor the first 2 lines check to the a separate method. (purpose:
		// reduce code duplication).

		// manually re-create first 2 lines of .bashrc file modification.
		String[] comparisonLines = new String[2];
		comparisonLines[0] = "#get root";
		comparisonLines[1] = "if [ ! -f /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool ]; then";

		// split the original lines of .bashrc into an array with one line per element.
		String[] originalLines = ReadFiles.readFiles(testFile.getAbsolutePath()).toString().split("\\r?\\n");

		// Check if the .bashrc file has been modified, if yes, skip modification.
		if (!ReadFiles.firstLinesMatch(originalLines, 2, comparisonLines)) {
			ModifyFiles.prependText(installData, testFile, ModifyFiles.createLinesBashrc(installData));
		}
	}

//	private static void modifyVisudo(InstallData installData) throws Exception {
//		// File testFile = new File(installData.getLinuxPath()+"testFile");
//		File testFile = new File(installData.getVisudoPath(), installData.getVisudoFileName());
//
//		// write the first two lines of the .bashrc bootup procedure to compare with
//		// original
//		// to determine whether the automatic login procedure is already contained in
//		// the .bashrc
//		// TODO: Refactor this check to the modify Files method prependLines.
//		// TODO: by writing the String array of lines once and passing it to both that
//		// prependLines
//		// and this check method. (purpose: reduce code duplication).
//		String[] comparisonLines = new String[1];
//		comparisonLines[0] = "zq ALL=(ALL) NOPASSWD: ALL";
//
//		// split the original lines of the .bashrc file into an array with one line per
//		// element
//		String[] originalLines = ReadFiles.readFiles(testFile.getAbsolutePath()).toString().split("\\r?\\n");
//
//		System.out.println("TESTFILE VISUDO ALREADY HAS LAST LINE="
//				+ ReadFiles.lastLinesMatch(originalLines, comparisonLines.length, comparisonLines));
//
//		if (!ReadFiles.lastLinesMatch(originalLines, comparisonLines.length, comparisonLines)) {
//			System.out.println(
//					"The visudo did not yet contain the last line that removes prompt for password, so it is added now");
//			ModifyFiles.appendText(installData, testFile);
//		} else {
//			System.out.println(
//					"The visudo did alread contained the last line that removes prompt for password, so it is not modified.");
//		}
//	}

	/**
	 * Method manages: Removal of "yes | " before a command and storing the "yes | "
	 * as boolean. Pre-processing of command if required. Execution of the command.
	 * Printing of command ouput Post-processing of the command if required.
	 * 
	 * TODO: write proper exception
	 * 
	 * @param udaName
	 * @param label
	 * @param type
	 * @throws Exception
	 */
	private static void manageCommandGeneration(InstallData installData, Command[] commands) throws Exception {
		String commandOutput = null;
		if (!installData.isTestrun()) {
			for (int i = 0; i < commands.length; i++) {
				printCommand(i, commands[i].getCommandLines());

				// Pre-process command if needed: check if command has "yes | ".
				Boolean hasYes = startsWithYes(commands[i].getCommandLines()[0]);

				// Pre-process command if needed: Removing "yes | " and store as boolean.
				String[] preprocessedCommands = new String[commands.length];
				preprocessedCommands = removeYes(commands[i].getCommandLines());

				// Pre-process: verify system condition before command execution.
				commands[i] = Verifications.preCommandProcess(installData, i, commands[i]);

				// Execute commands and print output.
				if (commands[i].getCommandLines()[0] != null) {
					commandOutput = RunCommandsV3.executeCommands(commands[i], hasYes);
					System.out.println("Command output=" + commandOutput);
				}

				// Post process command.
				installData = Verifications.postCommandProcess(installData, i, commands[i], commandOutput);
			}
		}
	}

	/**
	 * Checks whether the command starts with "yes | ". If it does it returns true,
	 * else false
	 * 
	 * @param string
	 * @return
	 */
	private static Boolean startsWithYes(String command) {
		if (command != null && command.length() > 5) {
			if (command.substring(0, 6).contentEquals("yes | ")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the command starts with "yes | ". If it does it removes the
	 * "yes | " from the command, else it returns the full comand as it is.
	 * 
	 * @param string
	 * @return
	 */
	private static String[] removeYes(String[] commands) {
		if (commands[0] != null && commands[0].length() > 5) {
			if (commands[0].substring(0, 6).contentEquals("yes | ")) {
				commands[0] = commands[0].substring(6, commands[0].length());
				return commands;
			}
		}
		return commands;
	}

	/**
	 * Get the uuid of the taskwarrior user from: cd $TASKDDATA/orgs/<your tw
	 * organization>/users/
	 * 
	 * @param folder
	 */
	private static void listFilesForFolder(final File folder) {
		System.out.println("Finding folders in:" + folder.getPath());
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}

	/**
	 * Generates a list of folders in a directory. Used to get the taskwarrior uuid
	 * of the installation. TODO: Ensure the last uuid of the array is used to make
	 * sure the most recent installation tw uuid is used. TODO: verify the list is
	 * sorted on order of creation, either by generation of alphabetic order of tw
	 * uuid's by the installation. Or by this method automatically sorting on
	 * creation date instead of alphabetic order.
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static List<String> findFoldersInDirectory(String directoryPath) {
		File directory = new File(directoryPath);

		System.out.println("Incoming file = " + directory.getAbsolutePath());

		FileFilter directoryFileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};

		File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
		System.out.println("file = " + directoryListAsFile.toString());
		List<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
		for (File directoryAsFile : directoryListAsFile) {
			foldersInDirectory.add(directoryAsFile.getName());
		}

		return foldersInDirectory;
	}

	/**
	 * Todo: change such that the password is hidden when typed and remove skipping
	 * 50 lines.
	 */
	private static void skipToNewPage() {
		for (int i = 1; i <= 50; i++) {
			System.out.println('\n');
		}
	}

	public static void printCommand(int commandNr, String[] commands) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < commands.length; i++) {
			sb.append(commands[i] + " ");
		}
		System.out.println(commandNr + "RUNNINGCOMMAND=" + sb.toString());
	}

	private static void runJavaServerSort(InstallData installData) {
		int nrOfCommands = 1;
		String[][] commandLines = new String[nrOfCommands][1];
		Command[] commands = new Command[nrOfCommands];
		commands[0] = new Command();

		commandLines[0] = new String[3];
		commandLines[0][0] = "java";
		commandLines[0][1] = "-jar";
		commandLines[0][2] = "/home/" + installData.getLinuxUserName() + "/" + installData.getMaintenanceFolder() + "/"
				+ installData.getSortScriptName();
		System.out.println("commandLines[0]=" + commandLines[0]);
		commands[0].setCommandLines(commandLines[0]);
		commands[0].setEnvVarContent("/var/taskd");
		commands[0].setEnvVarName("TASKDDATA");
		commands[0].setWorkingPath("/home/" + installData.getLinuxUserName() + "/");
//		commands[0].setSetWorkingPath(false);
		try {
			RunCommandsV3.executeCommands(commands[0], false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void exitUbuntu() {
		int nrOfCommands = 1;
		String[][] commandLines = new String[nrOfCommands][1];
		Command[] commands = new Command[nrOfCommands];
		commands[0] = new Command();

		commandLines[0] = new String[1];
		commandLines[0][0] = "exit";
		commands[0].setCommandLines(commandLines[0]);
		commands[0].setEnvVarContent("/var/taskd");
		commands[0].setEnvVarName("TASKDDATA");
		commands[0].setWorkingPath("/home/");
//		commands[0].setSetEnvVar(false);
//		commands[0].setSetWorkingPath(false);
		try {
			RunCommandsV3.executeCommands(commands[0], false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}