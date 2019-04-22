package autoInstallTaskwarrior; 	

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
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
		CreateFiles.createVars(installData);
		System.out.println("Should have just printed vars");
		
		//get commands
		Command[] commands = GenerateCommandsV3.generateCommands(installData);
		
		// execute installation commands
		manageCommandGeneration(installData, commands);
		
		System.exit(0);
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 * @throws Exception 
	 */
	private static void manageCommandGeneration(InstallData installData, Command[] commands) throws Exception {
		String commandOutput = null;
		if (!installData.isTestrun()) {
			for (int i = 0; i < commands.length; i++) {    
				System.out.println("I="+i+" and commands.length="+commands.length);
				
				//check if command contains "yes | " and store result:
				Boolean hasYes =  startsWithYes(commands[i].getCommandLines()[0]);
				
				// remove the "yes | " of a command
				String[] preprocessedCommands = new String[commands.length];
				preprocessedCommands =removeYes(commands[i].getCommandLines());
				
				// verify system condition before command execution
				commands[i] = Verifications.preCommandProcess(installData,i,commands[i]);
				
				// run commands if it does not start with null
				if (commands[i].getCommandLines()[0]!=null) { 
					//commandOutput = RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
						//commandOutput = RunCommandsWithArgsV1.processBuilder(preprocessedCommands,hasYes);
						RunCommandsV3.executeCommands(commands[i],hasYes);
				}
				
				// verify system condition after command execution
				installData = Verifications.postCommandProcess(installData,i, commands[i], commandOutput);
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
	 * Generates a list of folders in a directory. 
	 * Used to get the taskwarrior uuid of the installation.
	 * TODO: Ensure the last uuid of the array is used to make sure
	 * the most recent installation tw uuid is used.
	 * TODO: verify the list is sorted on order of creation,
	 * 		either by generation of alphabetic order of tw uuid's by the installation.
	 * 		Or by this method automatically sorting on creation date 
	 * 		instead of alphabetic order.
	 * @param directoryPath
	 * @return
	 */
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
}

