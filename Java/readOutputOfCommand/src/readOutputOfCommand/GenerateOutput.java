package readOutputOfCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

public class GenerateOutput {

	/**
	 * This code can execute a command and return the binary value of it's output if it 
	 * is actually binary.
	 * 
	 * compile this project into a .jar and run it with for example:
	 * java -jar readOutputOfCommand.jar
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		boolean answerYes = false; // no yes answer to any command prompts is needed.
		
		// to execute a command with spaces in it in terminal, put them in an array of Strings.
		String[] binaryCommand = new String[2];
		
		// write a command that gives a binary output:
		binaryCommand[0] = "echo";
		binaryCommand[1] = "1";
		
		// pass the commands to a method that executes them
		System.out.println("The output of the echo command = "+executeCommands(binaryCommand,answerYes));
	}

	/**
	 * This executes the commands in terminal. 
	 * Additionally it sets an environment variable (not necessary for your particular solution)
	 * Additionally it sets a working path (not necessary for your particular solution)
	 * @param commandData
	 * @param ansYes
	 * @throws Exception 
	 */
	public static boolean executeCommands(String[] commands,Boolean ansYes) throws Exception {
		String capturedCommandOutput = null;
		System.out.println("Incoming commandData = "+Arrays.deepToString(commands));
		File workingDirectory = new File("/mnt/c/testfolder b/");

		// create a ProcessBuilder to execute the commands in
		ProcessBuilder processBuilder = new ProcessBuilder(commands);
		
		// this is not necessary but can be used to set an environment variable for the command
		processBuilder = setEnvironmentVariable(processBuilder); 
		
		// this is not necessary but can be used to set the working directory for the command
		processBuilder.directory(workingDirectory);
		
		// execute the actual commands
		try {
		 
			 Process process = processBuilder.start();
			
			 // capture the output stream of the command
			 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
			reader.lines().iterator().forEachRemaining(sj::add);
			capturedCommandOutput = sj.toString();
			System.out.println("The output of this command ="+ capturedCommandOutput);
			 
			 // here you connect the output of your command to any new input, e.g. if you get prompted for `yes`
			 new Thread(new SyncPipe(process.getErrorStream(), System.err)).start();
			 new Thread(new SyncPipe(process.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(process.getOutputStream());
			
			//This is not necessary but can be used to answer yes to being prompted
			if (ansYes) {
				System.out.println("WITH YES!");
			stdin.println("yes");
			}
			
			// write any other commands you want here
			
			stdin.close();
			
			// this lets you know whether the command execution led to an error(!=0), or not (=0).
			int returnCode = process.waitFor();
			System.out.println("Return code = " + returnCode);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return retrieveBooleanOutput(capturedCommandOutput);
    }
	
	private static boolean retrieveBooleanOutput(String commandOutput) throws Exception {
		if (commandOutput != null && commandOutput.length() == 1) { 
			if (commandOutput.contains("0")) {
				return false;
			} else if (commandOutput.contains("1")) {
				return true;
			}
		}
		throw new Exception("The output is not binary.");
	}
	
	/**
	 * source: https://stackoverflow.com/questions/7369664/using-export-in-java
	 * @param processBuilder
	 * @param varName
	 * @param varContent
	 * @return
	 */
	private static ProcessBuilder setEnvironmentVariable(ProcessBuilder processBuilder){
		String varName = "variableName";
		String varContent = "/mnt/c/testfolder a/";
		
		Map<String, String> env = processBuilder.environment();
		 System.out.println("Setting environment variable "+varName+"="+varContent);
		 env.put(varName, varContent);
		 
		 processBuilder.environment().put(varName, varContent);
		 
		 return processBuilder;
	}
}


class SyncPipe implements Runnable
{	
	/**
	 * This class pipes the output of your command to any new input you generated
	 * with stdin. For example, suppose you run cp /mnt/c/a.txt /mnt/b/
	 * but for some reason you are prompted: "do you really want to copy there yes/no?
	 * then you can answer yes since your input is piped to the output of your
	 * original command. (At least that is my practical interpretation might be wrong.)
	 * @param istrm
	 * @param ostrm
	 */
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