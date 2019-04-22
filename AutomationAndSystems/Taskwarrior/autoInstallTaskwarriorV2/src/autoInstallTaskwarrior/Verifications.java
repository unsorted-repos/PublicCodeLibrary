package autoInstallTaskwarrior;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Verifications {

	public static Command preCommandProcess(InstallData installData,int commandIndex, Command command) throws Exception {
		switch (commandIndex) {
			case 0: before0(installData,command);
		}
		return command;
	}
	
	public static InstallData postCommandProcess(InstallData installData, int commandIndex, Command command, String commandOutput) throws FileNotFoundException {
		switch (commandIndex) {
			case 7: after0(installData,command);
//			TODO: After generate command of 10 create verification for files
//			client.cert.pem
//			client.key.pem
//			server.sert.pem
//			server.key.pem
//			server.crl.pem
//			ca.cert.pem
//			in location:/usr/share/taskd/pki/
			
//			TODO: After copy of command 16 verify those files are also located in: /var/taskd/
			case 30: installData = after30(installData, commandOutput);
		}
		return installData;
	}
	
	
	/**
	 * Gets the output of the command that adds a new user to the setup
	 * Then reads the taskwarrior uuid
	 * and sets the taskwarrior uuid in object installData
	 * @param commandOutput
	 */
	private static InstallData after30(InstallData installData, String commandOutput) {
		if (commandOutput !=null && commandOutput.length() > 20 && commandOutput.substring(0, "New user key: ".length()).contentEquals("New user key: ")) {
			System.out.println("The tw uuid ="+commandOutput.substring("New user key: ".length(), "New user key: ".length()+36));
			installData.setTwUuid(commandOutput.substring("New user key: ".length(), "New user key: ".length()+36));
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
	public static void after0(InstallData installData, Command command) throws FileNotFoundException {
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
}
