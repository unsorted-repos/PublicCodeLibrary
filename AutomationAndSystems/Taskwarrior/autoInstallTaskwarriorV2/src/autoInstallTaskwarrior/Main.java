package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Before compiling, set testRun to false! Copy this script to /home/<your
 * ubuntuUsername (which is the same as ~/) So if you store this script on your
 * Windows disc c:/copyToUbuntu you enter: cp
 * /mnt/c/copyToUbuntu/commandLinux.jar ~/
 * 
 * Before you run it: yes | sudo apt update yes | sudo apt install default-jre
 * 
 * This script executes a series of commands to install taskwarrior on WSL
 * Ubuntu.
 * 
 * @author a
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {

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

		//run shell
//		RunShell.runScript("/home/a/.bashrc");
		
		// get commands to instal calendar sync
		commands = GCalSyncCommands.generateCommands(installData);
		
		// execute installation commands
		manageCommandGeneration(installData, commands);

		
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
		AskUserInput.promptReboot(installData);
		System.exit(0);

	}

	/**
	 * Scenario: Multi-device: client. Imports the certificates from the folder as
	 * specified in HardCoded (currently: <your
	 * harddrive>/Taskwarrior/certificateInput) Reads the tw uuid of the server from
	 * the file as specified in HardCoded (currently twUuid.txt). That tw uuid of
	 * the server is entered into the client configuration with command: sudo task
	 * config taskd.credentials -- <twOrg><twUserName><twUuid>
	 * 
	 * @param installData
	 */
	private static void importData(InstallData installData) {
		System.out.println("useSingledevice true=" + installData.isUseSingleDevice());
		if (!installData.isUseSingleDevice() && installData.isServer()) {
			// import certificates if this is client installation.
			ImportFiles.importCertificates(installData);
			ModifyTwConfig.setTwServerUuid(installData);
		}
		if (installData.isRestoreBackup()) {
			System.out.println("importing backup");
			ImportFiles.importBackups(installData);
		}
	}

	/**
	 * The .bashrc file 
	 * @param installData
	 * @throws Exception
	 */
	private static void exportBashrc(InstallData installData) throws Exception {
		// File testFile = new File(installData.getLinuxPath()+"testFile");
		File testFile = new File(installData.getBashrcPath(), installData.getBasrcFileName());

		// write the first two lines of the .bashrc bootup procedure to compare with
		// original
		// to determine whether the automatic login procedure is already contained in
		// the .bashrc
		// TODO: Refactor this check to the modify Files method prependLines.
		// TODO: by writing the String array of lines once and passing it to both that
		// prependLines
		// and this check method. (purpose: reduce code duplication).
		String[] comparisonLines = new String[2];
		comparisonLines[0] = "#get root";
		comparisonLines[1] = "if [ ! -f /home/" + installData.getLinuxUserName() + "/maintenance/getRootBool ]; then";

		// split the original lines of the .bashrc file into an array with one line per
		// element
		String[] originalLines = ReadFiles.readFiles(testFile.getAbsolutePath()).toString().split("\\r?\\n");

		if (!ReadFiles.firstLinesMatch(originalLines, 2, comparisonLines)) {
			System.out.println("The .bashrc did not contain the automated login procedure yet, so it is added now");
			ModifyFiles.prependText(installData, testFile);
		} else {
			System.out.println("The .bashrc already contained the automatic login procedure, so it is not modified.");
		}
	}

	private static void modifyVisudo(InstallData installData) throws Exception {
		// File testFile = new File(installData.getLinuxPath()+"testFile");
		File testFile = new File(installData.getVisudoPath(), installData.getVisudoFileName());

		// write the first two lines of the .bashrc bootup procedure to compare with
		// original
		// to determine whether the automatic login procedure is already contained in
		// the .bashrc
		// TODO: Refactor this check to the modify Files method prependLines.
		// TODO: by writing the String array of lines once and passing it to both that
		// prependLines
		// and this check method. (purpose: reduce code duplication).
		String[] comparisonLines = new String[1];
		comparisonLines[0] = "zq ALL=(ALL) NOPASSWD: ALL";

		// split the original lines of the .bashrc file into an array with one line per
		// element
		String[] originalLines = ReadFiles.readFiles(testFile.getAbsolutePath()).toString().split("\\r?\\n");

		System.out.println("TESTFILE VISUDO ALREADY HAS LAST LINE="
				+ ReadFiles.lastLinesMatch(originalLines, comparisonLines.length, comparisonLines));

		if (!ReadFiles.lastLinesMatch(originalLines, comparisonLines.length, comparisonLines)) {
			System.out.println(
					"The visudo did not yet contain the last line that removes prompt for password, so it is added now");
			ModifyFiles.appendText(installData, testFile);
		} else {
			System.out.println(
					"The visudo did alread contained the last line that removes prompt for password, so it is not modified.");
		}
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is
	 * correct Thows error datatype is not correct. TODO: write proper exception
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
				// for (int i = 0; i < 49; i++) {

				printCommand(i, commands[i].getCommandLines());

				// check if command contains "yes | " and store result:
				Boolean hasYes = startsWithYes(commands[i].getCommandLines()[0]);

				// remove the "yes | " of a command
				String[] preprocessedCommands = new String[commands.length];
				preprocessedCommands = removeYes(commands[i].getCommandLines());

				// verify system condition before command execution
				commands[i] = Verifications.preCommandProcess(installData, i, commands[i]);

				// run commands if it does not start with null
				if (commands[i].getCommandLines()[0] != null) {
					// commandOutput =
					// RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
					// commandOutput =
					// RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
					commandOutput = RunCommandsV3.executeCommands(commands[i], hasYes);
					System.out.println("Output=" + commandOutput);
				}

				// verify system condition after command execution
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