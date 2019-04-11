package autoInstallTaskwarrior;

import java.util.Arrays;


/**
 * TODO: Before compiling, set testRun to false!
 * Copy this script to /home/<your ubuntuUsername (which is the same as ~/)
 * So if you store this script on your Windows disc c:/copyToUbuntu you enter:
 * cp /mnt/c/copyToUbuntu/commandLinux.jar ~/
 * 
 * Before you run it: 
 * yes | sudo apt install default-jre --fix-missing
 * 
 * 
 * This script executes a series of commands to install taskwarrior on WSL Ubuntu.
 * @author a
 *
 */
public class Main {

	public static void main(String[] args) {
		boolean testRun = false;
		String vars = "vars";
		
		String[] storeUserInput =AskUserInput.getUserInput();
		for (int i = 1; i <= 50; i++) {
			System.out.println('\n');
		}
		
		String unixUserName = storeUserInput[0];
		String unixPw = storeUserInput[1];
		String serverName = "CN=eai.ddns.net:53589";
		
		//get the path of this file
		String windowsPath = GetThisPath.getJarLocation()[0];
		//when it's run in linux it automatically returns linux path. (No need for conversion)
		//String linuxPath = getThisPath.getJarLocation()[1]; 
		String linuxPath = windowsPath;
		
		System.out.println("Path ="+windowsPath);
		System.out.println("Path ="+linuxPath);
		
		// create the vars file
		CreateFiles.createVars(vars,serverName);
		
		// execute commands
		createUDA(testRun,linuxPath,vars);
		
		System.exit(0);
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 */
	private static void createUDA(boolean testRun,String linuxPath,String vars) {
		
		//get commands
		String[][] commands = GenerateCommandsV2.generateCommands(testRun,linuxPath,vars);
		
		// run commands
		if (!testRun) {
			for (int i = 0; i <= commands.length; i++) {
				
				//check if command contains "yes | " and store result:
				Boolean hasYes =  startsWithYes(commands[i][0]);
				
				// remove the "yes | " of a command
				String[] preprocessedCommands = new String[commands[i].length];
				preprocessedCommands =removeYes(commands[i]);
				
				// run commands if it does not start with null
				if (commands[i][0]!=null) { 
					System.out.println("RUNNING COMMAND:"+Arrays.toString(preprocessedCommands));
					RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
					for (int j =0;j<commands[i].length;j++) {
						System.out.println("Ran:"+commands[i][j]);
					}
				}
			}			
		}
	}

	/**
	 * Checks whether the command starts with "yes | ".
	 * If it does it returns true, else false
	 * @param string
	 * @return
	 */
	private static Boolean startsWithYes(String command) {
		if (command!=null && command.length()>5) {
			if (command.substring(0, 6).contentEquals("yes | ")) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Checks whether the command starts with "yes | ".
	 * If it does it removes the "yes | " from the command, 
	 * else it returns the full comand as it is. 
	 * @param string
	 * @return
	 */
	private static String[] removeYes(String[] commands) {
		if (commands[0]!=null && commands[0].length()>5) {
			if (commands[0].substring(0, 6).contentEquals("yes | ")) {
				commands[0] = commands[0].substring(6, commands[0].length());
				return commands;
			}
		}
		return commands;
	}
}

