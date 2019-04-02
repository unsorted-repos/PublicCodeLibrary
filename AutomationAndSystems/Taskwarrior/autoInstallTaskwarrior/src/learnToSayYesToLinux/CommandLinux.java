package learnToSayYesToLinux;

import java.io.*;
import java.util.ArrayList;


/**
 * TODO: Before compiling, set testRun to false!
 * Copy this script to /home/<your ubuntuUsername (which is the same as ~/)
 * So if you store this script on your Windows disc c:/copyToUbuntu you enter:
 * cp /mnt/c/copyToUbuntu/commandLinux.jar ~/
 * 
 * This script executes a series of commands to install taskwarrior on WSL Ubuntu.
 * @author a
 *
 */
public class CommandLinux {

	public static void main(String[] args) {
		boolean testRun = true;
		String vars = "vars";
		
		String[] storeUserInput =askUserInput.getUserInput();
		for (int i = 1; i <= 50; i++) {
			System.out.println('\n');
		}
		
		String unixUserName = storeUserInput[0];
		String unixPw = storeUserInput[1];
		String serverName = "CN=eai.ddns.net:53589";
		
		//get the path of this file
		String thisPath = getThisPath.getJarLocation()+"/";
		System.out.println("Path ="+thisPath);
		
		// create the vars file
		createFiles.createVars(serverName);
		
		// execute commands
		createUDA(testRun);

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
	private static void createUDA(boolean testRun) {
		//commands[1]="task config uda."+udaName+".label "+ label;

		// get file path of this script
		
		
		//get commands
		String[] commands = generateCommands.generateCommands(testRun);
		
		System.out.print("Command length="+commands.length);
		
		// run commands
		if (!testRun) {
			for (int i = 0; i == commands.length; i++) {
				runUnixCommands.runCommands(commands[i],true);
				System.out.println("Ran:"+commands[i]);
			}
			
		}
		
		runUnixCommands.runCommands(commands[8], true);
		System.out.println("Ran:"+commands[8]);
		runUnixCommands.runCommands(commands[9], true);
		System.out.println("Ran:"+commands[9]);
		
//		System.out.println("Ran:"+commands[1]);
//		System.out.println("Ran:"+commands[2]);
//		System.out.println("Ran:"+commands[3]);
		
		
		// Throw exception example
//		try {
//			throw new Exception();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	
	

	
	
	
}



