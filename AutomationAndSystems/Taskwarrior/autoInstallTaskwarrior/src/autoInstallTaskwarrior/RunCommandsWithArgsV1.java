package autoInstallTaskwarrior;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RunCommandsWithArgsV1 {
	/**
	 * This method actually executes the command in WSL Ubuntu 16.04 if you run the 
	 * compiled .JAR file.
	 * You can automatically answers yes to any input the command requires with the
	 * stdin.println("yes"); line
	 * @param command
	 * @return
	 */
	public static void runCommands(String[] commandPart,Boolean ansYes) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(commandPart);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			if (ansYes) {
				stdin.println("yes");
			}
			
			// write any other commands you want here
			stdin.close();
			int returnCode = p.waitFor();
			System.out.println("Return code = " + returnCode);

		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	/**
	 * Suggestion: you should generally use ProcessBuilder instead of Runtime.exec()
	 * ProcessBuilder allows you to change the working directory of the script while running the script.
	 * Source: https://askubuntu.com/questions/1132950/pass-set-current-directory-to-for-a-shell-script-from-java-in-ubuntu-16-04
	 */
	public static void processBuilder(String[] commandData,Boolean ansYes) {
		// store the commands (last entry of commandData contains working path)
		String[] commands = new String[commandData.length-1]; 	
		for (int i = 0; i < commandData.length-1; i++) {commands[i] = commandData[i];}    
		
		// store the working path (stored in last entry of commandData)
		File workingDirectory = new File(commandData[commandData.length-1]);

		// create a ProcessBuilder to execute the commands in
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.directory(workingDirectory);
		try {
			Process p = pb.start();
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			if (ansYes) {
				stdin.println("yes");
			}
			
			// write any other commands you want here
			stdin.close();
			int returnCode = p.waitFor();
			System.out.println("Return code = " + returnCode);

		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
}