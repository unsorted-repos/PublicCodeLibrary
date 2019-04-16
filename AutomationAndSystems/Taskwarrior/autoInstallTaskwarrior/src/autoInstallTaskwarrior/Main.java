package autoInstallTaskwarrior; 	

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * TODO: Before compiling, set testRun to false!
 * Copy this script to /home/<your ubuntuUsername (which is the same as ~/)
 * So if you store this script on your Windows disc c:/copyToUbuntu you enter:
 * cp /mnt/c/copyToUbuntu/commandLinux.jar ~/
 * 
 * Before you run it: 
 * yes | sudo apt update
 * yes | sudo apt install default-jre
 * 
 * This script executes a series of commands to install taskwarrior on WSL Ubuntu.
 * @author a
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		InstallData installData = HardCoded.hardCoded();		
		//SetEnvVar.setEnvVar();
		
		// move pw out of screen
		skipToNewPage();
		
		// create the vars file
		CreateFiles.createVars(installData.getLinuxPath(),installData.getVars(),installData.getServerName(),installData.getServerPort());
		System.out.println("Should have just printed vars");
		
		//get commands
		String[][] commands = GenerateCommandsV2.generateCommands(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),installData.getServerName(),installData.getServerPort());
		
		// execute installation commands
		manageCommandGeneration(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),commands,2);
		
		//get second list of commands after taskwarrior uuid has been determined:
		commands = GenerateCommandsV2.generateSecondCommands(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),installData.getServerName(), installData.getServerPort());
		
		// execute second list of installation commands
		manageCommandGeneration(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),commands,2);
		
		//check and print whether the copied certs of command 19 exist in /<ubuntu username>/.task/
		System.out.println("VERIFYING COPYING OF FILES");
		CreateFiles.checkIfFileExist(installData.getLinuxUserName(),installData.getCopyVerifications19());	
		
		//get second list of commands after taskwarrior uuid has been determined:
		commands = GenerateCommandsV2.generateThirdCommands(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),installData.getServerName(), installData.getServerPort());
		
		// execute second list of installation commands
		manageCommandGeneration(installData.isTestrun(),installData.getLinuxPath(),installData.getVars(),installData.getUserInput(),commands,3);
		
		System.exit(0);
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 * @throws InterruptedException 
	 */
	private static void manageCommandGeneration(boolean testRun,String linuxPath,String vars,String[] storeUserInput,String[][] commands,int execType) throws InterruptedException {
		if (!testRun) {
			for (int i = 0; i < commands.length; i++) {    
				
				//check if command contains "yes | " and store result:
				Boolean hasYes =  startsWithYes(commands[i][0]);
				
				// remove the "yes | " of a command
				String[] preprocessedCommands = new String[commands[i].length];
				preprocessedCommands =removeYes(commands[i]);
				
				// verify system condition before command execution
				Verifications.beforeCommand(i,commands[i]);
				
				// run commands if it does not start with null
				if (commands[i][0]!=null) { 
					System.out.println("RUNNING COMMAND:"+Arrays.toString(preprocessedCommands));
					if (execType ==2) {
						RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
					}else if (execType == 3) {
						RunCommandsWithArgsV1.commandAndSetPath(preprocessedCommands,hasYes);
					}
				}
				
				// verify system condition after command execution
				Verifications.afterCommand(i,commands[i]);
			}			
		}
	}

	/**
	 * Checks whether the command starts with "yes | ".
	 * If it does it returns true, else false
	 * @param string
	 * @return
	 */
	private static Boolean startsWithYes(String command) {
		if (command!=null && command.length()>5) {
			if (command.substring(0, 6).contentEquals("yes | ")) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Checks whether the command starts with "yes | ".
	 * If it does it removes the "yes | " from the command, 
	 * else it returns the full comand as it is. 
	 * @param string
	 * @return
	 */
	private static String[] removeYes(String[] commands) {
		if (commands[0]!=null && commands[0].length()>5) {
			if (commands[0].substring(0, 6).contentEquals("yes | ")) {
				commands[0] = commands[0].substring(6, commands[0].length());
				return commands;
			}
		}
		return commands;
	}
	
	/**
	 * Get the uuid of the taskwarrior user from:
	 * cd $TASKDDATA/orgs/<your tw organization>/users/
	 * @param folder
	 */
	private static void listFilesForFolder(final File folder) {
		System.out.println("Finding folders in:"+folder.getPath());
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}

	/**
	 * Get the uuid of the taskwarrior user from:
	 * cd $TASKDDATA/orgs/<your tw organization>/users/
	 * @param folder
	 */	
//	private static void getFolderList() {
//	File file = new File("/path/to/directory");
//	String[] directories = file.list(new FilenameFilter() {
//	  @Override
//	  public boolean accept(File current, String name) {
//	    return new File(current, name).isDirectory();
//	  }
//	});
//	System.out.println(Arrays.toString(directories));
//	}
//	public File[] listFiles(FileFilter filter) {
//		return null;
//	}
	
	
	public static List<String> findFoldersInDirectory(String directoryPath) {
	    File directory = new File(directoryPath);
		
	    System.out.println("Incoming file = "+directory.getAbsolutePath());
	    
	    FileFilter directoryFileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
			
	    File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
	    System.out.println("file = "+directoryListAsFile.toString());
	    List<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
	    for (File directoryAsFile : directoryListAsFile) {
	        foldersInDirectory.add(directoryAsFile.getName());
	    }

	    return foldersInDirectory;
	}
	
	/**
	 * Todo: change such that the password is hidden when typed and remove skipping 50 lines.
	 */
	private static void skipToNewPage() { 
		for (int i = 1; i <= 50; i++) {
			System.out.println('\n');
		}
	}
//	final File folder = new File("/home/you/Desktop");
//	listFilesForFolder(folder);
}

