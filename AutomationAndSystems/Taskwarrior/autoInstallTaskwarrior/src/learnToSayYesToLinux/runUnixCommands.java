package learnToSayYesToLinux;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class runUnixCommands {

	/**
	 * This method takes the commands and sends them to unix so that they are executed.
	 * @param command
	 * @param ignoreOutput
	 * @return
	 */
	public static ArrayList<ArrayList<String>> runCommands(String command,boolean ignoreOutput) {

		String s = null;
		String outputLines=null;
		ArrayList<String> goodExecutionOutput=new ArrayList<String>();
		ArrayList<String> errorExecutionOutput=new ArrayList<String>();
		ArrayList<ArrayList<String>> returnLists = new ArrayList<ArrayList<String>>();

		// execute the command
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
		
		//Attempt 1: did not work
		try {
			// Execute command
			Process child = Runtime.getRuntime().exec(command);

			// Get output stream to write from it
			OutputStream out = child.getOutputStream();

			out.write("yes".getBytes());
			out.flush();
			out.write("yes".getBytes());
			out.close();
		} catch (IOException e) {
			System.out.println("Errors are:"+e);
		}

		//Merge outputLists and return
		returnLists.add(goodExecutionOutput);
		returnLists.add(errorExecutionOutput);
		return returnLists;
	}
}
