package autoInstallTaskwarrior;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFolders {
	private String driveLetter;
	
	/**
	 * TODO: rethink method implementation to allow for small separate unit tests.
	 * TODO: write unit tests for this chain of methods/class.
	 * @return
	 */
	public static InstallData findHardDrive(InstallData installData) {
		boolean foundDrive = false;
		String requestedDriveLetter=null;
		String[] driveLetter = new String[3];
		driveLetter[0] = "c";
		driveLetter[1] = "d";
		driveLetter[2] = "e";
		
		for (int i = 0;i <driveLetter.length;i++) {
			if (!foundDrive && checkIfDriveExists(driveLetter[i])) {
				installData.setOutputFolderDriveLetter(driveLetter[i]);
				foundDrive = true;
			}
		}
		
		while (!foundDrive) {
			requestedDriveLetter = AskUserInput.requestDriveLetter();
			foundDrive = checkIfDriveExists(requestedDriveLetter);
			installData.setOutputFolderDriveLetter(requestedDriveLetter);
		}
//		System.out.println("drive letter stored="+installData.getOutputFolderDriveLetter());
		listOutputFolders(installData);
		return installData;
	}
	
	private static boolean checkIfDriveExists(String driveLetter) {
		if (driveLetter!=null) {
			return checkIfFolderExists("/mnt/"+driveLetter+"/");
		} else {
			return false;
		}
	}
	
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
	
	private static void listOutputFolders(InstallData installData) {
		createOutputFolder(installData, installData.getOutputPath());
		createOutputFolder(installData, installData.getBackupInputPath());
		createOutputFolder(installData, installData.getBackupOutputPath());
		createOutputFolder(installData, installData.getCertificateInputPath());
		createOutputFolder(installData, installData.getCertificateOutputPath());
	}
	
	/**
	 * create outputFolder if the path does not exist
	 * @param installData
	 * @param folderPath
	 */
	public static void createOutputFolder(InstallData installData, String folderPath) {
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
}
