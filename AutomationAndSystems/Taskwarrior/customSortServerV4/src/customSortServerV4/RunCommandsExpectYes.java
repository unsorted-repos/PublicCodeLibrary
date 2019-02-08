package customSortServerV4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RunCommandsExpectYes {
	/**
	 * This method automatically answers yes to any input the command requires
	 
	 * @param command
	 * @return
	 */
	public static void runCommands(String command) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("yes");
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
