package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

public class CreateFiles {
	
	/**
	 * This creates the Vars file required in command 8
	 * @param serverName
	 */
	public static void createVars(String fileName, String serverName) {
		char quotation = (char)34; // quotation mark "
		
		deleteFile(fileName);
		PrintWriter writer;
		try {
			writer = new PrintWriter("vars", "UTF-8");
			writer.println("BITS=4096");
			writer.println("EXPIRATION_DAYS=365");
			writer.println("ORGANIZATION="+quotation+"Göteborg Bit Factory"+quotation);
			writer.println(serverName);
			writer.println("COUNTRY=SE");
			writer.println("STATE="+quotation+"Västra Götaland"+quotation);
			writer.println("LOCALITY="+quotation+"Göteborg"+quotation);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete a file that is located in the same folder as the src folder of this project
	 * is located.
	 * @param fileName
	 */
	public static  void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //surround it in try catch block
	}
}
