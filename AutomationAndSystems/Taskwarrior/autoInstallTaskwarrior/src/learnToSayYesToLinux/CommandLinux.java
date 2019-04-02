package learnToSayYesToLinux;

import java.io.*;
import java.util.ArrayList;


/**
 * TODO: Before compiling, set testRun to false!
 * Copy this script to /home/<your ubuntuUsername (which is the same as ~/)
 * So if you store this script on your Windows disc c:/copyToUbuntu you enter:
 * cp /mnt/c/copyToUbuntu/commandLinux.jar ~/
 * 
 * This script executes a series of commands to install taskwarrior on WSL Ubuntu.
 * @author a
 *
 */
public class CommandLinux {

	public static void main(String[] args) {
		boolean testRun = true;
		String vars = "vars";
		
		String[] storeUserInput =askUserInput.getUserInput();
		for (int i = 1; i <= 50; i++) {
			System.out.println('\n');
		}
		
		String unixUserName = storeUserInput[0];
		String unixPw = storeUserInput[1];
		String serverName = "CN=eai.ddns.net:53589";
		
		//get the path of this file
		String thisPath = getJarLocation()+"/";
		System.out.println("Path ="+thisPath);
		
		// create the vars file
		createVars(serverName);
		
		// execute commands
		createUDA(testRun);

		System.exit(0);
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 */
	private static void createUDA(boolean testRun) {
		//commands[1]="task config uda."+udaName+".label "+ label;

		// get file path of this script
		
		
		//get commands
		String[] commands = generateCommands(testRun);
		
		System.out.print("Command length="+commands.length);
		
		// run commands
		if (!testRun) {
			for (int i = 0; i == commands.length; i++) {
				runCommands(commands[i],true);
				System.out.println("Ran:"+commands[i]);
			}
			
		}
		//runCommands(commands[1], true);

		
//		System.out.println("Ran:"+commands[1]);
//		System.out.println("Ran:"+commands[2]);
//		System.out.println("Ran:"+commands[3]);
		
		
		// Throw exception example
//		try {
//			throw new Exception();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private static String[] generateCommands(boolean testRun) {
		String[] commands = new String[25];
		char vd = (char)124; //vertical dash: |
		char bs = (char)92; //backslash: \		
		char and = (char)38; // and $
		char quotation = (char)34; // quotation mark "
		
		//In combination with Attempt 1:
		if (!testRun) {
			commands[0] = "yes | sudo apt update "+and+and+" sudo apt upgrade";
			commands[1] = "sudo apt install task";
			commands[2] = "sudo apt install taskd";
			commands[3] = "cd ..";
			commands[4] = "cd ..";
			commands[5] = "export TASKDDATA=/var/taskd";
			commands[6] = "sudo mkdir -p $TASKDDATA";
			commands[7] = "sudo taskd init --data $TASKDDATA";
		}else {
				System.out.println("Skipped some lines for testing");
		}
		commands[8] = "cd /usr/share/taskd/pki";
		commands[9] = "sudo nano vars";
		commands[10] = "";
		commands[11] = "";
		commands[12] = "";
		commands[13] = "";
		commands[14] = "";
		commands[15] = "";
		commands[16] = "";
		
		return commands;
	}
	

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
	
	private static void createVars(String serverName) {
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
	
	/**
	 * Source:https://stackoverflow.com/questions/15359702/get-location-of-jar-file#15359999
	 * Warning, appearently does not work in certain scenarios, the updated solution did not work in my scenario;
	 * 		calling the .jar file from a different folder in cmd (e.g. java -jar test\myjar.jar) did not work.
	 * @return actual location of the compiled runnable .jar file that is executed is returned from this method
	 */
	public static String getJarLocation() {
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		return path;
	}
}

class SyncPipe implements Runnable
{
public SyncPipe(InputStream istrm, OutputStream ostrm) {
      istrm_ = istrm;
      ostrm_ = ostrm;
  }
  public void run() {
      try
      {
          final byte[] buffer = new byte[1024];
          for (int length = 0; (length = istrm_.read(buffer)) != -1; )
          {
              ostrm_.write(buffer, 0, length);
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
  }
  private final OutputStream ostrm_;
  private final InputStream istrm_;
}

