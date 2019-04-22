package autoInstallTaskwarrior;

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

public class RunCommandsV3 {
	

//		/**
//		 * This code can execute a command and return the binary value of it's output if it 
//		 * is actually binary.
//		 * 
//		 * compile this project into a .jar and run it with for example:
//		 * java -jar readOutputOfCommand.jar
//		 * 
//		 * @param args
//		 * @throws Exception 
//		 */
//		public static void main(String[] args) throws Exception {
//			boolean answerYes = false; // no yes answer to any command prompts is needed.
//			
//			// to execute a command with spaces in it in terminal, put them in an array of Strings.
//			String[] binaryCommand = new String[2];
//			
//			// write a command that gives a binary output:
//			binaryCommand[0] = "echo";
//			binaryCommand[1] = "1";
//			
//			// pass the commands to a method that executes them
//			System.out.println("The output of the echo command = "+executeCommands(binaryCommand,answerYes));
//		}

		/**
		 * This executes the commands in terminal. 
		 * Additionally it sets an environment variable (not necessary for your particular solution)
		 * Additionally it sets a working path (not necessary for your particular solution)
		 * @param commandData
		 * @param ansYes
		 * @throws Exception 
		 */
		public static String executeCommands(Command command,Boolean ansYes) throws Exception {
			String capturedCommandOutput = null;
			File workingDirectory = new File(command.getWorkingDirectory());

			// create a ProcessBuilder to execute the commands in
			ProcessBuilder processBuilder = new ProcessBuilder(command.getCommandLines());
			
			// this is set an environment variable for the command (if needed)
			if (command.isSetEnvVar()) {processBuilder = setEnvironmentVariable(processBuilder,command);} 
			
			// this is not necessary but can be used to set the working directory for the command
			if (command.isSetWorkingPath()) {processBuilder.directory(workingDirectory);}
			
			// execute the actual commands
			try {
			 
				Process process = processBuilder.start();
				// capture the output stream of the command
				if (command.isGetOutput()) {
					// capture the output stream of the command
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				    StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
					reader.lines().iterator().forEachRemaining(sj::add);
					capturedCommandOutput = sj.toString();
					System.out.println("The output of this command ="+ capturedCommandOutput);
				}
				 
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
			
			// return output if required:
			return capturedCommandOutput;
	    }
		
//		private static boolean retrieveBooleanOutput(String commandOutput) throws Exception {
//			if (commandOutput != null && commandOutput.length() == 1) { 
//				if (commandOutput.contains("0")) {
//					return false;
//				} else if (commandOutput.contains("1")) {
//					return true;
//				}
//			}
//			throw new Exception("The output is not binary.");
//		}
		
		/**
		 * source: https://stackoverflow.com/questions/7369664/using-export-in-java
		 * @param processBuilder
		 * @param varName
		 * @param varContent
		 * @return
		 */
		private static ProcessBuilder setEnvironmentVariable(ProcessBuilder processBuilder, Command command){
//			String envVarName = "variableName";
//			String envVarContent = "/mnt/c/testfolder a/";
			
			Map<String, String> env = processBuilder.environment();
			 //System.out.println("Setting environment variable "+command.getEnvVarName()+"="+command.getEnvVarContent());
			 env.put(command.getEnvVarName(), command.getEnvVarContent());
			 
			 processBuilder.environment().put(command.getEnvVarName(), command.getEnvVarContent());
			 
			 return processBuilder;
		}
		
		
	}


