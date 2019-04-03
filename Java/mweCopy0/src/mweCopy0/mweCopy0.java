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

public class mweCopy0 {

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
	 * Generates the copying command and executes it.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 */
	private static void generateCommand(boolean testRun,String linuxPath,String vars) {
		//get commands
		String[] commands = new String[24];
		char quotation = (char)34; // quotation mark "
		
		//commands[8] = "cp "+quotation+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki/"+quotation+quotation;
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki/"+quotation;
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki"+quotation;
		//commands[8] = quotation+"cp "+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki"+quotation+quotation;
		//commands[8] = "cp "+quotation+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki"+quotation+quotation;
		//commands[8] = "cp "+quotation+quotation+linuxPath+vars+" "+"/usr/share/taskd/pki"+quotation+quotation;
		//commands[8] = "cp "+quotation+quotation+linuxPath+vars+" "+"~"+quotation+quotation;
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+"~";
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+quotation+"/usr/share/taskd/pki/vars"+quotation;
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+"/usr/share/taskd/pki/vars";
		//commands[8] = "cp "+quotation+linuxPath+vars+quotation+" "+"/usr/share/taskd/pki/";
		commands[8] = "cp "+linuxPath+vars	+" "+"/usr/share/taskd/pki/";
		
		runCommands(commands[8], false);
		System.out.println("Ran:"+commands[8]);

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
	 * This runs the command for the scenario where you are not prompted for yes.
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
					//System.out.println("Adding:"+s);
					goodExecutionOutput.add(s);
					System.out.println("Good execution: "+s);
				}

				// get the error message
				while ((s = brError.readLine()) != null) {
					errorExecutionOutput.add(s);
					System.out.println("Bad execution: "+s);
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
