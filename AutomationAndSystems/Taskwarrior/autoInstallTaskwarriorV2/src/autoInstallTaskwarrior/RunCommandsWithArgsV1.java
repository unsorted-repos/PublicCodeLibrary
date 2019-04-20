package autoInstallTaskwarrior;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class RunCommandsWithArgsV1 {
	
	/**
	 * Suggestion: you should generally use ProcessBuilder instead of Runtime.exec()
	 * ProcessBuilder allows you to change the working directory of the script while running the script.
	 * Source: https://askubuntu.com/questions/1132950/pass-set-current-directory-to-for-a-shell-script-from-java-in-ubuntu-16-04
	 */
	public static String processBuilder(String[] commandData,Boolean ansYes) {
		String outputLine;
		StringBuilder lines = new StringBuilder();
		String commandOutput = null;
		
		// store the commands (last entry of commandData contains working path)
		String[] commands = new String[commandData.length-1]; 	
		for (int i = 0; i < commandData.length-1; i++) {commands[i] = commandData[i];}    
		
		// store the working path (stored in last entry of commandData)
		File workingDirectory = new File(commandData[commandData.length-1]);

		// create a ProcessBuilder to execute the commands in
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.directory(workingDirectory);
		try {
			Process p = pb.start();
			
			//capture output 
		    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((outputLine = r.readLine()) != null) {
		        //System.out.println("THE OUTPUT ="+outputLine); //<--this works
		        lines.append(outputLine);
		    }
		    commandOutput = lines.toString();
		    
			
		    //continue
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();		        
			
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			if (ansYes) {
				stdin.println("yes");
			}
			
			// write any other commands you want here
			
			stdin.close();
			r.close(); // also close the buffered reader
			int returnCode = p.waitFor();
			
			System.out.println("Return code = " + returnCode); // 0=good, 1/else=error.

		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return commandOutput;
	}
	
	/**
	 * set environment variable: by extracting the env variable from last entry of commandData
	 * Then extract the working path from the second to last entry of commandData
	 */
	public static String commandAndSetPath(String[] commandData,Boolean ansYes) throws InterruptedException {
		String outputLine;
		StringBuilder lines = null;
		String commandOutput = null;
		// store the commands (last entry of commandData contains working path)
		String envVarName ="TASKDDATA";
		String[] commands = new String[commandData.length-2];
		
		//System.out.println("Incoming commandData = "+Arrays.deepToString(commandData));
	
		// extract the environment variable TASKDDATA(Last entry)
		String envPath = commandData[commandData.length-1];
		
		// store the working path (stored in second last entry of commandData)
		File workingDirectory = new File(commandData[commandData.length-2]);

		// absorb commands from commandData
		for (int i = 0; i < commandData.length - 2; i++) {commands[i] = commandData[i];}
		
		// create a ProcessBuilder to execute the commands in
		ProcessBuilder pb = new ProcessBuilder(commands);
		//System.out.println("The final list of commands before execution = "+Arrays.toString(commands));
		pb.directory(workingDirectory);
		try {
			
			 Map<String, String> env = pb.environment();
			 System.out.println("Setting environment variable "+envVarName+"="+envPath);
			 env.put(envVarName, envPath);
			 //source: https://stackoverflow.com/questions/7369664/using-export-in-java
			 pb.environment().put(envVarName, envPath);
			 Process process = pb.start();
	
			 if (!commandData[1].contentEquals("cp")) {
				//capture output 
			    BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
			    while ((outputLine = r.readLine()) != null) {
			        //System.out.println("THE OUTPUT ="+outputLine); //<--this works
			        lines.append(outputLine);
			    }
			    commandOutput = lines.toString();
			 } 
		    
			 new Thread(new SyncPipe(process.getErrorStream(), System.err)).start();
			 new Thread(new SyncPipe(process.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(process.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			if (ansYes) {
				//System.out.println("WITH YES!");
				stdin.println("yes");
			}
			
			// write any other commands you want here
			stdin.close();
			int returnCode = process.waitFor();
			System.out.println("Return code = " + returnCode);
			 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return commandOutput;
    }	
}