package autoInstallTaskwarrior;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Verifications {

	public static void beforeCommand(int commandIndex, String[] command) throws FileNotFoundException {
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
	 * @throws FileNotFoundException 
	 */
	public static void before7(String[] command) throws FileNotFoundException {
		Path path = Paths.get("/var/taskd");
		
		System.out.println("Incoming array command 7="+Arrays.toString(command));
		//=[sudo, mkdir, -p, /var/taskd, /mnt/c/twInstall/]
		if (command[0].equals("sudo") && command[1].equals("mkdir") && command[2].equals("-p") && command[3].equals("/var/taskd")) {
			if (!Files.exists(path)) {
				throw new FileNotFoundException("The folder /var/taskd does not exist, even though it should!");
			}
			System.out.println("Command 7 is recognized for verification");
		}
		System.out.println("The folder /var/taskd exists");
	}
	
	public static void after19(String[] command) {
		//check and print whether the copied certs of command 19 exist in /<ubuntu username>/.task/
		System.out.println("VERIFYING COPYING OF FILES");
		//CreateFiles.checkIfFileExist(installData.getLinuxUserName(),installData.getCopyVerifications19());
	}
}
