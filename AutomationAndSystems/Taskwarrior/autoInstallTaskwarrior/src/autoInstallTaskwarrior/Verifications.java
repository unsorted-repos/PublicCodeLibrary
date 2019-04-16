package autoInstallTaskwarrior;

import java.util.Arrays;

public class Verifications {

	public static void beforeCommand(int commandIndex, String[] command) {
		switch (commandIndex) {
			case 7: before7(command);
		}
	}
	
	public static void afterCommand(int commandIndex, String[] command) {
		
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
	 */
	public static void before7(String[] command) {
		System.out.println("Incoming array command 7="+Arrays.toString(command));
		if (command[0].equals("sudo") && command[1].equals("mkdir") && command[1].equals("-p") && command[1].equals("/var/taskd")) {
			
		} else {
			System.exit(0);
		}
	}
}
