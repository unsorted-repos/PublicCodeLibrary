package customSortServerV5;

import java.io.File;
import java.io.IOException;

public class GetThisPath {


	
	/**
	 * This method is not necessary since the compiled .jar file
	 * automatically returns the file path in linux format when it is
	 * run in linux.
	 * @param windowsPath
	 * @return
	 */
	public static String convertToLinuxPath(String windowsPath) {
		
		// swap backwards slashes to forward slashes
		String linuxPath = windowsPath.replace("\\", "/");
		String driveLetter =linuxPath.substring(0, 1).toLowerCase();
		String remainderPath = linuxPath.substring(2,windowsPath.length());
				
		// switch to lowercase drive letter and convert X:/ to /mnt/x
		if (linuxPath.substring(0, 1).matches ("[a-zA-Z]\\.?")) {
			return "/mnt/"+driveLetter+remainderPath;
		}
		
		return null;
	}
	
	public static String getLinuxUserName() {
		Command command = new Command();
		String[] commandLines = new String[1];
		commandLines[0] = "whoami";
		
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("");
		command.setSetWorkingPath(false);
		command.setGetOutput(true);

		// execute command to create destination folder
		return RunCommandsV3.executeCommands(command, false);
	}
	
	public static String getWindowsPath() {
		String windowsPath =getPath(); 
		System.out.println("ReturningWINDOWSPATH="+windowsPath);
		return windowsPath;
	}
	
	public static String getLinuxPath() {
		return convertToLinuxPath(getPath());
	}
	
	/**
	 * Returns the path of the project that contains the src folder
	 * 
	 * @return platform dependant format of location of compiled .jar of this project in [0], Linux format in [1]
	 */
	public static String getPath() {
		String path=null;	
		try {
			path = new File(".").getCanonicalPath()+"/";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Found path = "+path);
		return path;
	}
//	
//	public static String absorbTillSemicolon(String line) {
//		int indexSemicolon =-1;
//		indexSemicolon=line.indexOf(";");
//		String path = line.substring(0,indexSemicolon-3);
//		System.out.println("RETURNING:"+path);
//		return path;
//	}
}
