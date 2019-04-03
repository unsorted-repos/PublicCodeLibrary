package mweCopy0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main {
	
	/**
	 * First the main gets the location for the file that it wants to copy somewhere.
	 * Next it creates the file it wants to copy somewhere.
	 * Then it generates the command to copy the file somewhere.
	 * In method generateCommand, the command is passed through
	 * to the module that actually executes the command in WSL Ubuntu 16.04.
	 * 
	 * You can quickly compile this script in Eclipse by:
	 * Selecting Main.java in the Package Explorer,
	 * press: alt+f>o>
	 * Then select Java>Runnable JAR file>next
	 * Note: You need to run it once in Eclipse before it is listed in "launch configuration"
	 * In launch configuration, select the Main.java
	 * Select Package required libraries into generated JAR
	 * press finish.
	 * @param args
	 */
	public static void main(String[] args) {
		String vars = "vars";
		char quotation = (char)34; // quotation mark "
		String serverName = "exampleName";
			
		//get the path of this file
		String linuxPath = getJarLocation()[0];
		
		// create the vars file
		createVars(vars,serverName);
		
		// execute commands
		generateCommand(false,linuxPath,vars);
	}

	/**
	 * Generates the copying command and calls the class that executes it.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 */
	private static void generateCommand(boolean testRun,String linuxPath,String vars) {
		String[] commands = new String[24];
		char quotation = (char)34; // quotation mark "
		
		// attempt 1: Original command 
		// cp /mnt/e/18-09-19 Document structure/personal/Programming/PublicCodeLibrary/Java/mweCopy0/vars /usr/share/taskd/pki/
		
		// separate the command from its arguments with a space between the arguments:
		// note it is irrelevant whether the arguments themselves contain spaces.
		String[] commandAndArgs = new String[3];
		commandAndArgs[0] = "cp";
		commandAndArgs[1] = "/mnt/e/18-09-19 Document structure/personal/Programming/PublicCodeLibrary/Java/mweCopy0/vars"; 
		commandAndArgs[2] = "/usr/share/taskd/pki/";
				
		RunCommandsWithArgs.runCommands(commandAndArgs);
		System.out.println("Ran:"+commandAndArgs);
	}
	
	/**
	 * This creates the Vars file required in command 8
	 * @param serverName
	 */
	private static void createVars(String fileName, String serverName) {
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
			writer.println("LOCALITY="+quotation+"Göteborg Verification"+quotation);
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
	private static  void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //surround it in try catch block
	}
	
	/**
	 * This gets the current location of the compiled.jar file
	 * @return
	 */
	public static String[] getJarLocation() {
		String[] paths= new String[2];
		
		// get path of location of compiled .jar file of this project in windows format 
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		paths[0] = dir.toString()+"/";	
		return paths;
	}
}