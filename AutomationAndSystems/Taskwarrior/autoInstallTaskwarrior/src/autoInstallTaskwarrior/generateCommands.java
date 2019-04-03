package learnToSayYesToLinux;

public class generateCommands {
	public static String[] generateCommands(boolean testRun) {
		String[] commands = new String[25];
		char vd = (char)124; //vertical dash: |
		char bs = (char)92; //backslash: \		
		char and = (char)38; // and $
		char quotation = (char)34; // quotation mark "
		
		//In combination with Attempt 1:
		if (!testRun) {
			commands[0] = "yes | sudo apt update "+and+and+" sudo apt upgrade";
			commands[1] = "sudo apt install task";
			commands[2] = "sudo apt install taskd";
			commands[3] = "cd ..";
			commands[4] = "cd ..";
			commands[5] = "export TASKDDATA=/var/taskd";
			commands[6] = "sudo mkdir -p $TASKDDATA";
			commands[7] = "sudo taskd init --data $TASKDDATA";
		}else {
				System.out.println("Skipped some lines for testing");
		}
		
		commands[8] = "sudo nano /usr/share/taskd/pki/vars";
		commands[9] = "echo hi";
		commands[10] = "";
		commands[11] = "";
		commands[12] = "";
		commands[13] = "";
		commands[14] = "";
		commands[15] = "";
		commands[16] = "";
		
		return commands;
	}
}
