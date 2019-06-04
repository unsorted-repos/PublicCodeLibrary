package autoInstallTaskwarrior;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Verifications {

	public static Command preCommandProcess(InstallData installData,int commandIndex, Command command) throws Exception {
		switch (commandIndex) {
			case 0: {
				before0(installData,command);
				break;
			}
			case 42: {
				command = before42(installData,command);
				break;
			}
		}
		return command;
	}
	
	public static InstallData postCommandProcess(InstallData installData, int commandIndex, Command command, String commandOutput) throws FileNotFoundException {
		System.out.println("i="+commandIndex);
		switch (commandIndex) {
			case 7: {
				after7(installData,command);
				break;
			}
//			TODO: After generate command of 10 create verification for files
//			client.cert.pem
//			client.key.pem
//			server.sert.pem
//			server.key.pem
//			server.crl.pem
//			ca.cert.pem
//			in location:/usr/share/taskd/pki/
			
//			TODO: After copy of command 16 verify those files are also located in: /var/taskd/
			
			// get the tw uuid
			case 30: {
				installData = after30(installData, commandOutput);
				break;
			}
		}
		return installData;
	}
	
	/**
	 * verification 7 checks whether command
	 * 
	 *  	commands[7] = new String[5];
	 *		commands[7][0] = "sudo";
	 *		commands[7][1] = "mkdir";
	 *		commands[7][2] = "-p";
	 *		commands[7][3] = "/var/taskd";
	 *		commands[7][4] = linuxPath;
	 * exists, and whether the file path does not exist before the command is executed
	 * @throws Exception 
	 */
	public static void before0(InstallData installData, Command command) throws Exception {
		Path path = Paths.get(command.getEnvVarContent());
		if (installData.isDeleteOtherTwUsers()) {
			// verify it is the correct command:
			// TODO: Make switch statement
			if (command.getCommandLines()[0].equals("sudo") && command.getCommandLines()[1].equals("mkdir") && command.getCommandLines()[2].equals("-p") && command.getCommandLines()[3].equals("/var/taskd")) {
				if (Files.exists(path)) {
					
					deleteFolder(path);
					//Files.delete(path);
					
					System.out.println("The path:"+path.toString()+" already existed so I deleted it");
					//throw new FileNotFoundException("The folder already exists, so this is not a clean install!");
				}
			}
		}
	}
	
	/**
	 * Updates the empty tw uuid to the one read stored in install data,
	 * which is read from the output of executing command 30.
	 * @param installData
	 * @param command
	 * @return
	 */
	public static Command before42(InstallData installData, Command command) {
		String[] temp = command.getCommandLines();
		temp[temp.length-1]=installData.getTwOrganisation()+"/"+installData.getTwUserName()+"/"+installData.getTwUuid();
		command.setCommandLines(temp);
		return command;
	}
	
	/**
	 * verification 7 checks whether command
	 * 
	 *  	commands[7] = new String[5];
	 *		commands[7][0] = "sudo";
	 *		commands[7][1] = "mkdir";
	 *		commands[7][2] = "-p";
	 *		commands[7][3] = "/var/taskd";
	 *		commands[7][4] = linuxPath;
	 * exists, and whether the file path exists after the command is executed
	 * @throws FileNotFoundException 
	 */
	public static void after7(InstallData installData, Command command) throws FileNotFoundException {
		Path path = Paths.get(command.getEnvVarContent());
		//=[sudo, mkdir, -p, /var/taskd, /mnt/c/twInstall/]
		if (command.getCommandLines()[0].equals("sudo") && command.getCommandLines()[1].equals("mkdir") && command.getCommandLines()[2].equals("-p") && command.getCommandLines()[3].equals("/var/taskd")) {
			if (!Files.exists(path)) {
				throw new FileNotFoundException("The folder /var/taskd does not exist, even though it should!");
			}
		}
		System.out.println("The folder /var/taskd exists");
	}
	
	public static void after19(String[] command) {
		//check and print whether the copied certs of command 19 exist in /<ubuntu username>/.task/
		System.out.println("VERIFYING COPYING OF FILES");
		//CreateFiles.checkIfFileExist(installData.getLinuxUserName(),installData.getCopyVerifications19());
	}
	
	/**
	 * Gets the output of the command that adds a new user to the setup
	 * Then reads the taskwarrior uuid
	 * and sets the taskwarrior uuid in object installData
	 * @param commandOutput
	 */
	private static InstallData after30(InstallData installData, String commandOutput) {
		System.out.println("TotalLen="+commandOutput.length());
		System.out.println("Lenght of:New user key: "+"="+"New user key: ".length());
		System.out.println("Key="+commandOutput.substring("New user key: ".length(),commandOutput.length()));
		if (commandOutput !=null && commandOutput.length() > 20 && commandOutput.substring(0, "New user key: ".length()).contentEquals("New user key: ")) {
			System.out.println("The tw uuid ="+commandOutput.substring("New user key: ".length(), "New user key: ".length()+36));
			installData.setTwUuid(commandOutput.substring("New user key: ".length(), "New user key: ".length()+36));
		} else {
			System.out.println("DID NOT CATCH UUID IN:"+commandOutput);
		}
		return installData;
	}
	
	/**
	 * Delete the (end) folder of path, if path exists.
	 * Automatically requests sudo pw.
	 * @param path
	 * @throws Exception
	 */
	public static void deleteFolder(Path path) throws Exception {
		if (Files.exists(path)) {
			System.out.println("DELETING="+path.toString());
			Command deletionCommand = new Command();
			String[] commandLines = new String[4];
			commandLines[0] = "sudo";
			commandLines[1] = "rm";
			commandLines[2] = "-r";
			commandLines[3] = path.toString();
			deletionCommand.setWorkingPath("");
			deletionCommand.setCommandLines(commandLines);
			deletionCommand.setSetEnvVar(false);
			deletionCommand.setSetWorkingPath(false);
			deletionCommand.setGetOutput(false);
			RunCommandsV3.executeCommands(deletionCommand,true);
		}
	}
	
	/**
	 * Testing parameters
	 * linuxUsername:testlinuxname
	 * taskwarrior organisation:testOrg
	 * taskwarrior username:testtwname 
	 */
		
	/**
	 * Sorting checks.
	 */
	//TODO: Test whether sort runs when server in multi-device on laptop: 
	//TODO: Test whether sort runs when client in multi-device on laptop: 
	//TODO: Test whether sort runs when server in single-device on laptop: 
	//TODO: Test whether sort runs when server in multi-device on desktop: 2019-05-24T12:55 Ubuntu 18.04 with backup restoration
	//TODO: Test whether sort runs when client in multi-device on desktop:
	//TODO: Test whether sort runs when server in single-device on desktop:

	/**
	 * Sorting efficiency:
	 */
	//TODO: Rewrite absorbing taskdata from using taskwarrior commands, to using the json backlog.data/pending.data files directly.
	//TODO: Test whether blows up the backlog or undo.data when server in multi-device on desktop with a sync from client:
	//TODO: Test whether blows up the backlog or undo.data when server in single-device on desktop:
	//TODO: Test whether blows up the backlog or undo.data when server in single-device on laptop:
	//TODO: Test if sort blows up with recurrent tasks on desktop
	//TODO: Test if sort blows up with recurrent tasks on laptop
	//TODO: Optimize sorting algorithm and nr of modifications.

	/**
	 * Generality
	 */
	//TODO: READ THE CURRENT tw uuid/code from the backlog.data first line and modify it in the first line of the backup restoration AFTER IMPORTING, so that the taskwarrior is able to sync after backup imporation
	//TODO: disable names with anything other than abc-_ in them (so no new lines no spaces no enters either).
	//TODO: Test if sync works with certificates with different username for client than for host.
	//TODO: Change the hardcoded /home/a/.. of javaserversort to a dynamic linux username using an automated username detection script.
	
	/**
	 * Functioning
	 */
	//TODO: Check if backups are actually written to C:/taskwarrior/backupOutput
	//TODO: Copy current tw uuid to the top of backlog.data AFTER it has been imported
	//TODO: Remove bashrcServer file from the src folder of autoInstallTaskwarrior and verify it still works (hardcoded linux username). (Deleted, not verified)
	
	/**
	 * Robustness
	 */
	//TODO: Change .bashrc content of creating a getInitBool that must exist untill the end of times to prevent another task sync init (iso just task sync) at bootup, to: create the getInitBool before during installation commands, and delete it once during first time bootup.
	
	/**
	 * Security
	 */
	//TODO: Test if sync works if 1 letter is changed in: ca.cert.pem, First.cert.pem or First,key.pem 
	//TODO: compile taskwarrior in Java code to 2.6
	//TODO: auto compile timewarrior in Java code
	//TODO: auto compile sorting project in java code
	
	/**
	 * Quality
	 */
	//TODO: Fix TODO's in code.
	//TODO: Refactor code
	//TODO: Write Javadoc for all methods
	//TODO: Clean code.
	//TODO: Ask advice on usage of interfaces.
	//TODO: run through online quality check untill code quality at least B.
	//TODO: CTRL+SHIFT+F all code at once to enforce text witdth
	//TODO: Ask advice on how this would be tested properly in a professional environment.
	//TODO: Increase testing coverage to at least 30%.
	
	/**
	 * Automation
	 */
	//TODO: Make a script that runs from cmd and uses command lxrun /install /y /update to install and update the entire WSL automatically.
	//TODO: Pull, compile and run the .jar file within the wsl automatically from the cmd script.
	//TODO: Include the initial questions of for the user, that ask about the setup; server, phone etc.
	//TODO: make bash/exe that completely automatically installs everything, including wsl.
	//TODO: Setup auto encrypted ftp for certificates
	//TODO: Auto detect linux username, if there is only one folder in /home/

	/**
	 * Android
	 */
	//TODO: Auto install tw on android (through adb requires windows adb of the same level).
	//TODO: Auto copy certificates to android (from multi-device-setup both client and server) 
}
