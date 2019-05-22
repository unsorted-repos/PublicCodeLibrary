package autoInstallTaskwarrior;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunShell {
	public static void runScript(String path, String... args) {
	    try {
	        String[] cmd = new String[args.length + 1];
	        cmd[0] = path;
	        int count = 0;
	        for (String s : args) {
	            cmd[++count] = args[count - 1];
	        }
	        Process process = Runtime.getRuntime().exec(cmd);
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        try {
	            process.waitFor();
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        while (bufferedReader.ready()) {
	            System.out.println("Received from script: " + bufferedReader.readLine());
	        }
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        System.exit(1);
	    }
	}
	
	/**
	 * The destination path is only made if it does not yet exist. If it does exist
	 * and contains files, those files are preserved.
	 * 
	 * @param destinationPath
	 * @throws Exception
	 */
	public static void runShellWithSudo(String destinationPath,String destinationFile) throws Exception {
		Command command = new Command();
		String[] commandLines = new String[3];
		commandLines[0] = "sudo";
		commandLines[1] = "bash";
		commandLines[2] = destinationPath+destinationFile;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("");
		command.setSetWorkingPath(false);

		// execute command to create destination folder
		RunCommandsV3.executeCommands(command, false);
	}
}
