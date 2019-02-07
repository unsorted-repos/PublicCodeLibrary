/**
 * 
 */
package customSortServerV4;
import java.io.*;
/**
 * @author a
 *
 */
public class RunCommands {

	/**
	 * Source: https://github.com/AlvinFDK/FDK/blob/18d61bcc2121b13ae1b02345930f6f2264feb813/src/main/java/blackflames/alvin/bar/io/TerminalUnix.java
	 */
	public static void runCommands() {

		String s = null;

		try {

			// run the Unix "task nice0" command
			Process p = Runtime.getRuntime().exec("sudo task nice0");

			BufferedReader brGood = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader brError = new BufferedReader(new 
					InputStreamReader(p.getErrorStream()));

			// get output
			System.out.println("Good output:\n");
			while ((s = brGood.readLine()) != null) {
				System.out.println(s);
			}

			// get the error message
			System.out.println("Error output:\n");
			while ((s = brError.readLine()) != null) {
				System.out.println(s);
			}

			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("Error: ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
