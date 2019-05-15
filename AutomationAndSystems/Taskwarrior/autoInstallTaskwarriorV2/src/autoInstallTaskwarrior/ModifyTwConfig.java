package autoInstallTaskwarrior;

public class ModifyTwConfig {
	public static void setTwServerUuid(InstallData installData) {
		System.out.println("Not null ="+installData.getServerTwUuid());
		if (installData.getServerTwUuid() != null) {
			setTwServerAdress(installData);
			setTwServerUuidCommand(installData);
		}
	}


	/**
	 * C:\Taskwarrior\certificatesInput
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/ca.cert.pem ~/.task
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/First.cert.pem ~/.task
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/First.key.pem ~/.task
	 * sudo task config taskd.server -- <yourOwnWebsite>.ddns.net:53589
	 * task config taskd.credentials -- Public/First/<the user UUID of your Main tw server>
	 * 
	 * create outputFolder if the path does not exist
	 * @param installData
	 * @param folderPath
	 */
	private static void setTwServerAdress(InstallData installData) {
		int nrOfCommands = 1;
		String[][] commandLines = new String[nrOfCommands][1];
		Command[] commands = new Command[nrOfCommands];
		commands[0] = new Command();
		commandLines[0] = new String[6];
		commandLines[0][0] = "sudo";
		commandLines[0][1] = "task";
		commandLines[0][2] = "config";
		commandLines[0][3] = "taskd.server";
		commandLines[0][4] = "--";
		//TODO: make dynamic
		commandLines[0][5] = "eai.ddns.net:"+installData.getServerPort();
		
		commands[0].setCommandLines(commandLines[0]);
		commands[0].setEnvVarContent("/var/taskd");
		commands[0].setEnvVarName("TASKDDATA");
		commands[0].setWorkingPath("");
		commands[0].setSetWorkingPath(false);
		Main.printCommand(0,commandLines[0]);
		try {
			RunCommandsV3.executeCommands(commands[0],true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * C:\Taskwarrior\certificatesInput
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/ca.cert.pem ~/.task
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/First.cert.pem ~/.task
	 * cp -a /mnt/c/Taskwarrior/certificatesInput/First.key.pem ~/.task
	 * sudo task config taskd.server -- <yourOwnWebsite>.ddns.net:53589
	 * task config taskd.credentials -- Public/First/<the user UUID of your Main tw server>
	 * 
	 * create outputFolder if the path does not exist
	 * @param installData
	 * @param folderPath
	 */
	private static void setTwServerUuidCommand(InstallData installData) {
		int nrOfCommands = 1;
		String[][] commandLines = new String[nrOfCommands][1];
		Command[] commands = new Command[nrOfCommands];
		commands[0] = new Command();
		commandLines[0] = new String[6];
		commandLines[0][0] = "sudo";
		commandLines[0][1] = "task";
		commandLines[0][2] = "config";
		commandLines[0][3] = "taskd.credentials";
		commandLines[0][4] = "--";
		commandLines[0][5] = installData.getTwOrganisation()+"/"+installData.getTwUserName()+"/"+installData.getServerTwUuid();
		
		commands[0].setCommandLines(commandLines[0]);
		commands[0].setEnvVarContent("/var/taskd");
		commands[0].setEnvVarName("TASKDDATA");
		commands[0].setWorkingPath("");
		commands[0].setSetWorkingPath(false);
		
		Main.printCommand(0,commandLines[0]);
		try {
			RunCommandsV3.executeCommands(commands[0],true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
