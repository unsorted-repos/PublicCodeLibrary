package autoInstallTaskwarrior;

import java.io.IOException;

public class GenerateCommands {
	public static String[][] generateCommands(boolean testRun,String linuxPath,String vars) {
		String[][] commands = new String[25][1];
		char vd = (char)124; //vertical dash: |
		char bs = (char)92; //backslash: \		
		char and = (char)38; // and $
		char quotation = (char)34; // quotation mark "
		
		//In combination with Attempt 1:
		if (!testRun) {
			commands[0] = new String[3];
			//commands[0][0] = "yes | sudo apt update "+and+and+" sudo apt upgrade";
			commands[0][0] = "yes | sudo";
			commands[0][1] = "apt";
			commands[0][2] = "update";
			
			commands[1] = new String[3];
			commands[1][0] = "yes | sudo";
			commands[1][1] = "apt";
			commands[1][2] = "upgrade";

//			commands[1][0] = "sudo apt install task";
			commands[2] = new String[4];
			commands[2][0] = "yes | sudo";
			commands[2][1] = "apt";
			commands[2][2] = "install";
			commands[2][3] = "task";
			
			
//			commands[2][0] = "sudo apt install taskd";
			commands[3] = new String[4];
			commands[3][0] = "yes | sudo";
			commands[3][1] = "apt";
			commands[3][2] = "install";
			commands[3][3] = "taskd";
			
			//TODO: rewrite the original cd commands such that they just use direct path rather than cd.
			
//			commands[3][0] = "cd ..";
			commands[4] = new String[2];
			commands[4][0] = "cd";
			commands[4][1] = "..";
			
//			commands[4][0] = "cd ..";
			commands[5] = new String[2];
			commands[5][0] = "cd";
			commands[5][1] = "..";
			
		
			
			/**
			 * Probable problem: the script is not added to working path, so when you try to add the export environment
			 * variable, it does that to the wrong path, or in path it looks for this script and it is not there.
			 * Probable solution:
			 * Source: https://stackoverflow.com/questions/11787479/java-io-ioexception-cannot-run-program-error-2-no-such-file-or-directory
			 */
			
//			commands[5][0] = "export TASKDDATA=/var/taskd";
			commands[6] = new String[2];
			commands[6][0] = "export";
			commands[6][1] = "TASKDDATA=/var/taskd";
			
//			commands[6][0] = "sudo mkdir -p $TASKDDATA";
			commands[7] = new String[4];
			commands[7][0] = "sudo";
			commands[7][1] = "mkdir";
			commands[7][2] = "-p";
			commands[7][3] = "$TASKDDATA";
			
//			commands[7][0] = "sudo taskd init --data $TASKDDATA";
			commands[8] = new String[6];
			commands[8][0] = "sudo";
			commands[8][2] = "taskd";
			commands[8][3] = "init";
			commands[8][4] = "--data";
			commands[8][5] = "$TASKDDATA";
			
		}else {
				System.out.println("Skipped some lines for testing");
		}
		
		commands[8] = new String[3];
		commands[8][0] = "cp";
		commands[8][1] = linuxPath+vars; 
		commands[8][2] = "/usr/share/taskd/pki/";
		
        
		commands[9] = new String[2];
		commands[9][0] = "echo";
		commands[9][1] = "hello world";
		commands[10][0] = "";
		commands[11][0] = "";
		commands[12][0] = "";
		commands[13][0] = "";
		commands[14][0] = "";
		commands[15][0] = "";
		commands[16][0] = "";
		
		return commands;
	}
}