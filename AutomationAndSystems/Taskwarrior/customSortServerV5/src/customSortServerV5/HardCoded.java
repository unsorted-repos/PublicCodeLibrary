package customSortServerV5;
public class HardCoded {

	//Assumptions: 
	//0. Method generatePropertyList assumes the (user defined) attributes of
	//a task have the exact same name in the JSON pending.data file as they
	//have as fields of Object Task.

	private String eclipseFilePath="input/";
	private String eclipseFileName="pendingPublic.data";
	private String pendingFileName="pending.data";
	private String ubuntuFilePath="/home/"+getLinuxUserName()+"/.task/";
	private String nameOfCustomSortParameterLabel="cSort";
	private String customSortDataType="numeric";
	private String udaName="estimate";
	private String udaLabel="est";
	private String udaDataType="duration";
	private String customReportName="nice0";
	private double urgencyThreshold = 11.5;
	private String sudo = "sudo ";
	private String backlogFileName = "backlog.data";
	private String testDataFolder = "testData";
	private String windowsPath;
	private String linuxPath;
	private String testWslLaunchersFolder = "wslLaunchers";
//	private String relativeCompiledJarPath = 
	private String compiledJarName = "JavaServerSort.jar";
	private String wslLauncherScriptName = "wslLauncher8.ps1";
	private String wslWhoamiScriptName = "wslWhoami.ps1";
	private String linuxUsernameFromWindows;
	
	
	public HardCoded() {
		 this.eclipseFilePath="input/";
		 this.eclipseFileName="pendingPublic.data";
		 this.pendingFileName="pending.data";
		 this.ubuntuFilePath="/home/"+getLinuxUserName()+"/.task/";
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
		 this.linuxUsernameFromWindows = absorbLinuxUsernameFromWindows();
	}
	
	public static String getLinuxUsernameFromWindows() {
		return linuxUsernameFromWindows;
	}

	public static String getWslWhoamiScriptName() {
		return wslWhoamiScriptName;
	}

	public static void setWslWhoamiScriptName(String wslWhoamiScriptName) {
		HardCoded.wslWhoamiScriptName = wslWhoamiScriptName;
	}

	public static String getWslLauncherScriptName() {
		return wslLauncherScriptName;
	}

	public static void setWslLauncherScriptName(String wslLauncherScriptName) {
		HardCoded.wslLauncherScriptName = wslLauncherScriptName;
	}

	public static String getTestWslLaunchersFolder() {
		return testWslLaunchersFolder;
	}

	public static void setTestWslLaunchersFolder(String testWslLaunchersFolder) {
		HardCoded.testWslLaunchersFolder = testWslLaunchersFolder;
	}

	public static String getLinuxPath() {
		return linuxPath;
	}

	public static void setLinuxPath(String linuxPath) {
		HardCoded.linuxPath = linuxPath;
	}

	public static String getCompiledJarName() {
		return compiledJarName;
	}

	public static void setCompiledJarName(String compiledJarName) {
		HardCoded.compiledJarName = compiledJarName;
	}

	public static String getWindowsPath() {
		
		System.out.println("WindwsPathReturned="+windowsPath);
		return windowsPath;
	}

//	public static void setWindowsPath(String windowsPath) {
//		HardCoded.windowsPath = windowsPath;
//	}

	public static String getTestDataFolder() {
		return testDataFolder;
	}

	public static void setTestDataFolder(String testDataFolder) {
		HardCoded.testDataFolder = testDataFolder;
	}

	public static String getBacklogFileName() {
		return backlogFileName;
	}

	public static void setBacklogFileName(String backlogFileName) {
		HardCoded.backlogFileName = backlogFileName;
	}

	/**
	 * @return the udaName
	 */
	public static String getUdaName() {
		return udaName;
	}

	/**
	 * @param udaName the udaName to set
	 */
	public static void setUdaName(String udaName) {
		HardCoded.udaName = udaName;
	}

	/**
	 * @return the udaLabel
	 */
	public static String getUdaLabel() {
		return udaLabel;
	}

	/**
	 * @param udaLabel the udaLabel to set
	 */
	public static void setUdaLabel(String udaLabel) {
		HardCoded.udaLabel = udaLabel;
	}

	/**
	 * @return the udaDataType
	 */
	public static String getUdaDataType() {
		return udaDataType;
	}

	/**
	 * @param udaDataType the udaDataType to set
	 */
	public static void setUdaDataType(String udaDataType) {
		HardCoded.udaDataType = udaDataType;
	}

	private static String nameOfCustomSortParameter="customSort";
	
	/**
	 * @return the nameOfCustomSortParameter
	 */
	public static String getNameOfCustomSortParameter() {
		return nameOfCustomSortParameter;
	}

	/**
	 * @param nameOfCustomSortParameter the nameOfCustomSortParameter to set
	 */
	public static void setNameOfCustomSortParameter(String nameOfCustomSortParameter) {
		HardCoded.nameOfCustomSortParameter = nameOfCustomSortParameter;
	}

	/**
	 * @return the nameOfCustomSortParameterLabel
	 */
	public static String getNameOfCustomSortParameterLabel() {
		return nameOfCustomSortParameterLabel;
	}

	/**
	 * @param nameOfCustomSortParameterLabel the nameOfCustomSortParameterLabel to set
	 */
	public static void setNameOfCustomSortParameterLabel(String nameOfCustomSortParameterLabel) {
		HardCoded.nameOfCustomSortParameterLabel = nameOfCustomSortParameterLabel;
	}

	/**
	 * @return the customSortDataType
	 */
	public static String getCustomSortDataType() {
		return customSortDataType;
	}

	/**
	 * @param customSortDataType the customSortDataType to set
	 */
	public static void setCustomSortDataType(String customSortDataType) {
		HardCoded.customSortDataType = customSortDataType;
	}

	/**
	 * @return the customReportName
	 */
	public static String getCustomReportName() {
		return customReportName;
	}

	
	/**
	 * @param customReportName the customReportName to set
	 */
	public static void setCustomReportName(String customReportName) {
		HardCoded.customReportName = customReportName;
	}
	
	/**
	 * @return the ubuntuFilePath
	 */
	public static String getUbuntuFilePath() {
		return ubuntuFilePath;
	}

	/**
	 * @param ubuntuFilePath the ubuntuFilePath to set
	 */
	public static void setUbuntuFilePath(String ubuntuFilePath) {
		HardCoded.ubuntuFilePath = ubuntuFilePath;
	}

	/**
	 * @return the sudo
	 */
	public static String getSudo() {
		return sudo;
	}

	/**
	 * @param sudo the sudo to set
	 */
	public static void setSudo(String sudo) {
		HardCoded.sudo = sudo;
	}

	/**
	 * @return the urgencyThreshold
	 */
	public static double getUrgencyThreshold() {
		return urgencyThreshold;
	}

	/**
	 * @param urgencyThreshold the urgencyThreshold to set
	 */
	public static void setUrgencyThreshold(double urgencyThreshold) {
		HardCoded.urgencyThreshold = urgencyThreshold;
	}

	public static String getPendingFileName() {
		return pendingFileName;
	}

	public static void setPendingFileName(String ubuntuFileName) {
		HardCoded.pendingFileName = ubuntuFileName;
	}

	public static String getEclipseFilePath() {
		return eclipseFilePath;
	}

	public static void setEclipseFilePath(String eclipseFilePath) {
		HardCoded.eclipseFilePath = eclipseFilePath;
	}

	public static String getEclipseFileName() {
		return eclipseFileName;
	}

	public static void setEclipseFileName(String eclipseFileName) {
		HardCoded.eclipseFileName = eclipseFileName;
	}
	
	public static String getLinuxUserName() {
		String linuxUserName = null;
		if (OSValidator.returnOS().equals("windows")) {
			System.out.println("RUNNING IN WINDOWS");
			return setLinuxUsernameFromWindows();
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
	
	private static String absorbLinuxUsernameFromWindows() {
		String[] lines = new String[1];
		lines[0] = "wsl whoami";
		CreateFiles.createTestLaunchers(HardCoded.getWslWhoamiScriptName(),lines);
		String linuxUsername = RunPowershell.runPowershell(RunPowershell.storeWhoami(),false);
		System.out.println("username="+linuxUsername);
		return linuxUsername;
	}
}
