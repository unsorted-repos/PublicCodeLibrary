package customSortServerV5;
import java.io.File;

public class CreateFolders {
	
	public static boolean checkIfFolderExists(String path) {	
		// merge file path and file name to file object
		File f = new File(path);
		
		// check if file exists
		if(f.isDirectory()) { 
//		    System.out.println("File:"+path+" exists");
		    return true;
		}
		return false;
	}
	
	/**
	 * create outputFolder if the path does not exist in linux
	 * @param installData
	 * @param folderPath
	 */
	public static void createOutputFolder(String folderPath) {
		if (!CreateFolders.checkIfFolderExists(folderPath)) {
			int nrOfCommands = 1;
			String[][] commandLines = new String[nrOfCommands][1];
			Command[] commands = new Command[nrOfCommands];
			commands[0] = new Command();
			
			commandLines[0] = new String[3];
			commandLines[0][0] = "sudo";
			commandLines[0][1] = "mkdir";
			commandLines[0][2] = folderPath;
			commands[0].setCommandLines(commandLines[0]);
			commands[0].setEnvVarContent("/var/taskd");
			commands[0].setEnvVarName("TASKDDATA");
			commands[0].setWorkingPath("");
			commands[0].setSetWorkingPath(false);
			try {
				RunCommandsV3.executeCommands(commands[0],false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean createFolderWithEclipse(String path) {
//		System.out.println("Making dir:"+path);
        File files = new File(path);
        if (!files.exists()) {
            if (files.mkdirs()) {
//                System.out.println("Multiple directories are created!");
                return true;
            } else {
                System.out.println("Failed to create multiple directories!");
                return false;
            }
        }
        return false;
    }
}
