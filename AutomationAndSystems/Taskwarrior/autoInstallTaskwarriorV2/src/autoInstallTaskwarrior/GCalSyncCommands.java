package autoInstallTaskwarrior;

public class GCalSyncCommands {
	public static Command[] generateCommands(InstallData installData) throws Exception {
		char quotation = (char) 34; // quotation mark "
		int nrOfCommands = 59;
		String[][] commandLines = new String[nrOfCommands][1];
		Command[] commands = new Command[nrOfCommands];
		for (int i = 0; i < nrOfCommands; i++) {
			commands[i] = new Command();

			// preset commands to not get the output,
			// set the environment variable and working path at every command.
			commands[i].setGetOutput(false);
			commands[i].setSetEnvVar(false);
			commands[i].setSetWorkingPath(false);
		}

		// pdf:0
		// first command to enable working with an environment variable that exists.
		// commandLines[0].setCommandLines(commandLines);
		commandLines[0] = new String[3];
		commandLines[0][0] = "yes | sudo";
		commandLines[0][1] = "add-apt-repository";
		commandLines[0][2] = "ppa:jonathonf/python-3.6";
		commands[0].setCommandLines(commandLines[0]);
		commands[0].setEnvVarContent("");
		commands[0].setEnvVarName("");
		commands[0].setWorkingPath("");
		commands[0].setSetWorkingPath(false);
		
		// pdf:1
		commandLines[1] = new String[3];
		// commandLines[0][0] = "yes | sudo apt update";
		commandLines[1][0] = "yes | sudo";
		commandLines[1][1] = "apt";
		commandLines[1][2] = "update";
		commands[1].setCommandLines(commandLines[1]);
		commands[1].setEnvVarContent("");
		commands[1].setEnvVarName("");
		commands[1].setWorkingPath(commands[1].getEnvVarContent());
		commands[1].setSetEnvVar(false);
		commands[1].setSetWorkingPath(false);
		
		commandLines[2] = new String[5];
		commandLines[2][0] = "yes | sudo";
		commandLines[2][1] = "apt";
		commandLines[2][2] = "install";
		commandLines[2][3] = "python3.6";
		commandLines[2][4] = "python3-pip";
		commands[2].setCommandLines(commandLines[2]);
		commands[2].setEnvVarContent("");
		commands[2].setEnvVarName("");
		commands[2].setWorkingPath(commands[2].getEnvVarContent());
		commands[2].setSetEnvVar(false);
		commands[2].setSetWorkingPath(false);
		
		commandLines[3] = new String[3];
		commandLines[3][0] = "yes | sudo";
		commandLines[3][1] = "apt";
		commandLines[3][2] = "update";
		commands[3].setCommandLines(commandLines[3]);
		commands[3].setEnvVarContent("");
		commands[3].setEnvVarName("");
		commands[3].setWorkingPath(commands[3].getEnvVarContent());
		commands[3].setSetEnvVar(false);
		commands[3].setSetWorkingPath(false);
		
		commandLines[4] = new String[3];
		commandLines[4][0] = "yes | sudo";
		commandLines[4][1] = "apt";
		commandLines[4][2] = "update";
		commands[4].setCommandLines(commandLines[4]);
		commands[4].setEnvVarContent("");
		commands[4].setEnvVarName("");
		commands[4].setWorkingPath(commands[4].getEnvVarContent());
		commands[4].setSetEnvVar(false);
		commands[4].setSetWorkingPath(false);
		
		commandLines[5] = new String[3];
		commandLines[5][0] = "yes | sudo";
		commandLines[5][1] = "apt";
		commandLines[5][2] = "update";
		commands[5].setCommandLines(commandLines[5]);
		commands[5].setEnvVarContent("");
		commands[5].setEnvVarName("");
		commands[5].setWorkingPath(commands[5].getEnvVarContent());
		commands[5].setSetEnvVar(false);
		commands[5].setSetWorkingPath(false);

		commandLines[6] = new String[3];
		commandLines[6][0] = "yes | sudo";
		commandLines[6][1] = "apt";
		commandLines[6][2] = "update";
		commands[6].setCommandLines(commandLines[6]);
		commands[6].setEnvVarContent("");
		commands[6].setEnvVarName("");
		commands[6].setWorkingPath(commands[6].getEnvVarContent());
		commands[6].setSetEnvVar(false);
		commands[6].setSetWorkingPath(false);
		
		commandLines[7] = new String[3];
		commandLines[7][0] = "yes | sudo";
		commandLines[7][1] = "apt";
		commandLines[7][2] = "update";
		commands[7].setCommandLines(commandLines[7]);
		commands[7].setEnvVarContent("");
		commands[7].setEnvVarName("");
		commands[7].setWorkingPath(commands[7].getEnvVarContent());
		commands[7].setSetEnvVar(false);
		commands[7].setSetWorkingPath(false);
		
		commandLines[8] = new String[3];
		commandLines[8][0] = "yes | sudo";
		commandLines[8][1] = "apt";
		commandLines[8][2] = "update";
		commands[8].setCommandLines(commandLines[8]);
		commands[8].setEnvVarContent("");
		commands[8].setEnvVarName("");
		commands[8].setWorkingPath(commands[8].getEnvVarContent());
		commands[8].setSetEnvVar(false);
		commands[8].setSetWorkingPath(false);
		
		commandLines[9] = new String[3];
		commandLines[9][0] = "yes | sudo";
		commandLines[9][1] = "apt";
		commandLines[9][2] = "update";
		commands[9].setCommandLines(commandLines[9]);
		commands[9].setEnvVarContent("");
		commands[9].setEnvVarName("");
		commands[9].setWorkingPath(commands[9].getEnvVarContent());
		commands[9].setSetEnvVar(false);
		commands[9].setSetWorkingPath(false);

		commandLines[10] = new String[3];
		commandLines[10][0] = "yes | sudo";
		commandLines[10][1] = "add-apt-repository";
		commandLines[10][2] = "ppa:jonathonf/python-3.6";
		commands[10].setCommandLines(commandLines[10]);
		commands[10].setEnvVarContent("");
		commands[10].setEnvVarName("");
		commands[10].setWorkingPath("");
		commands[10].setSetWorkingPath(false);
		
		// pdf:1
		commandLines[11] = new String[3];
		// commandLines[10][0] = "yes | sudo apt update";
		commandLines[11][0] = "yes | sudo";
		commandLines[11][1] = "apt";
		commandLines[11][2] = "update";
		commands[11].setCommandLines(commandLines[11]);
		commands[11].setEnvVarContent("");
		commands[11].setEnvVarName("");
		commands[11].setWorkingPath(commands[11].getEnvVarContent());
		commands[11].setSetEnvVar(false);
		commands[11].setSetWorkingPath(false);
		
		commandLines[12] = new String[3];
		commandLines[12][0] = "yes | sudo";
		commandLines[12][1] = "apt";
		commandLines[12][2] = "update";
		commands[12].setCommandLines(commandLines[11]);
		commands[12].setEnvVarContent("");
		commands[12].setEnvVarName("");
		commands[12].setWorkingPath(commands[12].getEnvVarContent());
		commands[12].setSetEnvVar(false);
		commands[12].setSetWorkingPath(false);
		
		commandLines[13] = new String[3];
		commandLines[13][0] = "yes | sudo";
		commandLines[13][1] = "apt";
		commandLines[13][2] = "update";
		commands[13].setCommandLines(commandLines[11]);
		commands[13].setEnvVarContent("");
		commands[13].setEnvVarName("");
		commands[13].setWorkingPath(commands[13].getEnvVarContent());
		commands[13].setSetEnvVar(false);
		commands[13].setSetWorkingPath(false);
		
		commandLines[14] = new String[3];
		commandLines[14][0] = "yes | sudo";
		commandLines[14][1] = "apt";
		commandLines[14][2] = "update";
		commands[14].setCommandLines(commandLines[11]);
		commands[14].setEnvVarContent("");
		commands[14].setEnvVarName("");
		commands[14].setWorkingPath(commands[14].getEnvVarContent());
		commands[14].setSetEnvVar(false);
		commands[14].setSetWorkingPath(false);
		
		commandLines[15] = new String[3];
		commandLines[15][0] = "yes | sudo";
		commandLines[15][1] = "apt";
		commandLines[15][2] = "update";
		commands[15].setCommandLines(commandLines[11]);
		commands[15].setEnvVarContent("");
		commands[15].setEnvVarName("");
		commands[15].setWorkingPath(commands[15].getEnvVarContent());
		commands[15].setSetEnvVar(false);
		commands[15].setSetWorkingPath(false);

		commandLines[16] = new String[3];
		commandLines[16][0] = "yes | sudo";
		commandLines[16][1] = "apt";
		commandLines[16][2] = "update";
		commands[16].setCommandLines(commandLines[11]);
		commands[16].setEnvVarContent("");
		commands[16].setEnvVarName("");
		commands[16].setWorkingPath(commands[16].getEnvVarContent());
		commands[16].setSetEnvVar(false);
		commands[16].setSetWorkingPath(false);
		
		commandLines[17] = new String[3];
		commandLines[17][0] = "yes | sudo";
		commandLines[17][1] = "apt";
		commandLines[17][2] = "update";
		commands[17].setCommandLines(commandLines[11]);
		commands[17].setEnvVarContent("");
		commands[17].setEnvVarName("");
		commands[17].setWorkingPath(commands[17].getEnvVarContent());
		commands[17].setSetEnvVar(false);
		commands[17].setSetWorkingPath(false);
		
		commandLines[18] = new String[3];
		commandLines[18][0] = "yes | sudo";
		commandLines[18][1] = "apt";
		commandLines[18][2] = "update";
		commands[18].setCommandLines(commandLines[11]);
		commands[18].setEnvVarContent("");
		commands[18].setEnvVarName("");
		commands[18].setWorkingPath(commands[18].getEnvVarContent());
		commands[18].setSetEnvVar(false);
		commands[18].setSetWorkingPath(false);
		
		commandLines[19] = new String[3];
		commandLines[19][0] = "yes | sudo";
		commandLines[19][1] = "apt";
		commandLines[19][2] = "update";
		commands[19].setCommandLines(commandLines[11]);
		commands[19].setEnvVarContent("");
		commands[19].setEnvVarName("");
		commands[19].setWorkingPath(commands[19].getEnvVarContent());
		commands[19].setSetEnvVar(false);
		commands[19].setSetWorkingPath(false);
		return commands;
	}
}
	