package mweCopy0;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RunCommandsWithArgs {
	/**
	 * This method actually executes the command in WSL Ubuntu 16.04 if you run the 
	 * compiled .JAR file.
	 * You can automatically answers yes to any input the command requires with the
	 * stdin.println("yes"); line
	 * @param command
	 * @return
	 */
	public static void runCommands(String[] commandPart) {
		Process p;
			
		try {
			p = Runtime.getRuntime().exec(commandPart);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			//stdin.println("yes");
			
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