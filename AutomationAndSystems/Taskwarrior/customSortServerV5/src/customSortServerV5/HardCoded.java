package customSortServerV5;
public class HardCoded {

	//Assumptions: 
	//0. Method generatePropertyList assumes the (user defined) attributes of
	//a task have the exact same name in the JSON pending.data file as they
	//have as fields of Object Task.

	private String eclipseFilePath;
	private String eclipseFileName;
	private String pendingFileName;
	private String ubuntuFilePath;
	private String nameOfCustomSortParameterLabel;
	private String customSortDataType;
	private String udaName;
	private String udaLabel;
	private String udaDataType;
	private String customReportName;
	private double urgencyThreshold;
	private String sudo;
	private String backlogFileName;
	private String testDataFolder;
	private String windowsPath;
	private String linuxPath;
	private String testWslLaunchersFolder;
//	private String relativeCompiledJarPath = 
	private String compiledJarName;
	private String wslLauncherScriptName;
	private String wslWhoamiScriptName;
//	private String linuxUsernameFromWindows;
	private String linuxUsername;
	
	
	public HardCoded() {
		 this.eclipseFilePath="input/";
		 this.eclipseFileName="pendingPublic.data";
		 this.pendingFileName="pending.data";
		 this.ubuntuFilePath="/home/"+getLinuxUsername()+"/.task/";
		 this.nameOfCustomSortParameterLabel="cSort";
		 this.customSortDataType="numeric";
		 this.udaName="estimate";
		 this.udaLabel="est";
		 this.udaDataType="duration";
		 this.customReportName="nice0";
		 this.urgencyThreshold = 11.5;
		 this.sudo = "sudo ";
		 this.backlogFileName = "backlog.data";
		 this.testDataFolder = "testData";
		 this.windowsPath = GetThisPath.getWindowsPath();
		 this.linuxPath = GetThisPath.getLinuxPath();
		 this.testWslLaunchersFolder = "wslLaunchers";
//		 this.relativeCompiledJarPath = 
		 this.compiledJarName = "JavaServerSort.jar";
		 this.wslLauncherScriptName = "wslLauncher8.ps1";
		 this.wslWhoamiScriptName = "wslWhoami.ps1";
//		 this.linuxUsernameFromWindows = getLinuxUserName();
		 this.linuxUsername = getLinuxUsername();
	}
	

	
	



	public String getLinuxUsername() {
		linuxUsername = absorbLinuxUserName();	
		return linuxUsername;
	}





	public void setLinuxUsername(String linuxUsername) {
		this.linuxUsername = linuxUsername;
	}





	public String getEclipseFilePath() {
		return eclipseFilePath;
	}




	public void setEclipseFilePath(String eclipseFilePath) {
		this.eclipseFilePath = eclipseFilePath;
	}




	public String getEclipseFileName() {
		return eclipseFileName;
	}




	public void setEclipseFileName(String eclipseFileName) {
		this.eclipseFileName = eclipseFileName;
	}




	public String getPendingFileName() {
		return pendingFileName;
	}




	public void setPendingFileName(String pendingFileName) {
		this.pendingFileName = pendingFileName;
	}




	public String getUbuntuFilePath() {
		return ubuntuFilePath;
	}




	public void setUbuntuFilePath(String ubuntuFilePath) {
		this.ubuntuFilePath = ubuntuFilePath;
	}




	public String getNameOfCustomSortParameterLabel() {
		return nameOfCustomSortParameterLabel;
	}




	public void setNameOfCustomSortParameterLabel(String nameOfCustomSortParameterLabel) {
		this.nameOfCustomSortParameterLabel = nameOfCustomSortParameterLabel;
	}




	public String getCustomSortDataType() {
		return customSortDataType;
	}




	public void setCustomSortDataType(String customSortDataType) {
		this.customSortDataType = customSortDataType;
	}




	public String getUdaName() {
		return udaName;
	}




	public void setUdaName(String udaName) {
		this.udaName = udaName;
	}




	public String getUdaLabel() {
		return udaLabel;
	}




	public void setUdaLabel(String udaLabel) {
		this.udaLabel = udaLabel;
	}




	public String getUdaDataType() {
		return udaDataType;
	}




	public void setUdaDataType(String udaDataType) {
		this.udaDataType = udaDataType;
	}




	public String getCustomReportName() {
		return customReportName;
	}




	public void setCustomReportName(String customReportName) {
		this.customReportName = customReportName;
	}




	public double getUrgencyThreshold() {
		return urgencyThreshold;
	}




	public void setUrgencyThreshold(double urgencyThreshold) {
		this.urgencyThreshold = urgencyThreshold;
	}




	public String getSudo() {
		return sudo;
	}




	public void setSudo(String sudo) {
		this.sudo = sudo;
	}




	public String getBacklogFileName() {
		return backlogFileName;
	}




	public void setBacklogFileName(String backlogFileName) {
		this.backlogFileName = backlogFileName;
	}




	public String getTestDataFolder() {
		return testDataFolder;
	}




	public void setTestDataFolder(String testDataFolder) {
		this.testDataFolder = testDataFolder;
	}




	public String getWindowsPath() {
		return windowsPath;
	}




	public void setWindowsPath(String windowsPath) {
		this.windowsPath = windowsPath;
	}




	public String getLinuxPath() {
		return linuxPath;
	}




	public void setLinuxPath(String linuxPath) {
		this.linuxPath = linuxPath;
	}




	public String getTestWslLaunchersFolder() {
		return testWslLaunchersFolder;
	}




	public void setTestWslLaunchersFolder(String testWslLaunchersFolder) {
		this.testWslLaunchersFolder = testWslLaunchersFolder;
	}




	public String getCompiledJarName() {
		return compiledJarName;
	}




	public void setCompiledJarName(String compiledJarName) {
		this.compiledJarName = compiledJarName;
	}




	public String getWslLauncherScriptName() {
		return wslLauncherScriptName;
	}




	public void setWslLauncherScriptName(String wslLauncherScriptName) {
		this.wslLauncherScriptName = wslLauncherScriptName;
	}




	public String getWslWhoamiScriptName() {
		return wslWhoamiScriptName;
	}




	public void setWslWhoamiScriptName(String wslWhoamiScriptName) {
		this.wslWhoamiScriptName = wslWhoamiScriptName;
	}

	private String absorbLinuxUserName() {
		String linuxUserName = null;
		if (OSValidator.returnOS().equals("windows")) {
			System.out.println("RUNNING IN WINDOWS");
			return absorbLinuxUsernameFromWindows();
		}
		
		System.out.println("Incoming nonRoot username ="+checkForNonRoot());
		if (checkForNonRoot() != null && !checkForNonRoot().equals("root")) {
//			System.out.println("Returning nonRoot username ="+checkForNonRoot());
			return checkForNonRoot();
		}
		
		Command command = buildGetLinuxUserNameCommand();
		
		// execute command to create destination folder
		try {
			linuxUserName = RunCommandsV3.executeCommands(command, false);
//			System.out.println("Found username = "+linuxUserName);
			return linuxUserName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static Command buildGetLinuxUserNameCommand() {
		Command command = new Command();
		String[] commandLines = new String[1];
		commandLines[0] = "dir";
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("/home/");
		command.setSetWorkingPath(true);
		command.setGetOutput(true);
		return command;
	}
	
	public static String checkForNonRoot() {
		String linuxUserName = null;
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
		try {
			linuxUserName = RunCommandsV3.executeCommands(command, false);
			return linuxUserName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String absorbLinuxUsernameFromWindows() {
		String[] lines = new String[1];
		lines[0] = "wsl whoami";
		CreateFiles.createTestLaunchers(this, this.getWslWhoamiScriptName(),lines);
		//TODO: INVESTIGATE WHETHER THIS RECURSION "THIS" causes nulls
		String linuxUsername = RunPowershell.runPowershell(RunPowershell.storeWhoami(this),false);
		System.out.println("username="+linuxUsername);
		return linuxUsername;
	}
}
