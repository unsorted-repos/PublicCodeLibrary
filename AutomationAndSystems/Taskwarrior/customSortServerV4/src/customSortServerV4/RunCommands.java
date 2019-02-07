/**
 * 
 */
package customSortServerV4;
import java.io.*;
import java.util.ArrayList;
/**
 * @author a
 *
 */
public class RunCommands {

	/**
	 * Source: https://github.com/AlvinFDK/FDK/blob/18d61bcc2121b13ae1b02345930f6f2264feb813/src/main/java/blackflames/alvin/bar/io/TerminalUnix.java
	 */
	public static ArrayList<ArrayList<String>> runCommands(String command,boolean ignoreOutput) {

		String s = null;
		String outputLines=null;
		ArrayList<String> goodExecutionOutput=new ArrayList<String>();
		ArrayList<String> errorExecutionOutput=new ArrayList<String>();
		ArrayList<ArrayList<String>> returnLists = new ArrayList<ArrayList<String>>();
		
		try {

			// run the Unix "task nice0" command
			Process p = Runtime.getRuntime().exec(command);

			BufferedReader brGood = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader brError = new BufferedReader(new 
					InputStreamReader(p.getErrorStream()));

			// get output
			if (!ignoreOutput) {
				while ((s = brGood.readLine()) != null) {
					System.out.println("Adding:"+s);
					goodExecutionOutput.add(s);
				}

				// get the error message
				while ((s = brError.readLine()) != null) {
					errorExecutionOutput.add(s);
				}	
			}
			
			//System.exit(0);
		}
		catch (IOException e) {
			System.out.println("Error: ");
			e.printStackTrace();
			System.exit(-1);
		}
		
		//Merge outputLists and return
		returnLists.add(goodExecutionOutput);
		returnLists.add(errorExecutionOutput);
		return returnLists;
	}
}
