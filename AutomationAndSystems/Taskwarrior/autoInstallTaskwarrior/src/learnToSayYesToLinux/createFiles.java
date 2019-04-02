package learnToSayYesToLinux;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class createFiles {
	
	/**
	 * This creates the Vars file required in command 8
	 * @param serverName
	 */
	public static void createVars(String serverName) {
		char quotation = (char)34; // quotation mark "
		PrintWriter writer;
		try {
			writer = new PrintWriter("vars", "UTF-8");
			writer.println("BITS=4096");
			writer.println("EXPIRATION_DAYS=365");
			writer.println("ORGANIZATION="+quotation+"Göteborg Bit Factory"+quotation);
			writer.println(serverName);
			writer.println("COUNTRY=SE");
			writer.println("STATE=\"+quotation+\"Västra Götaland"+quotation);
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
}
