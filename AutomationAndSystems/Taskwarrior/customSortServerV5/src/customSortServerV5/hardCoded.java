package customSortServerV5;

public class hardCoded {

	//Assumptions: 
	//0. Method generatePropertyList assumes the (user defined) attributes of
	//a task have the exact same name in the JSON pending.data file as they
	//have as fields of Object Task.

	private static String eclipseFilePath="input/";
	private static String eclipseFileName="pendingPublic.data";
	private static String ubuntuFileName="pending.data";
	private static String ubuntuFilePath="/home/"+getLinuxUserName()+"/.task/";
	private static String nameOfCustomSortParameterLabel="cSort";
	private static String customSortDataType="numeric";
	private static String udaName="estimate";
	private static String udaLabel="est";
	private static String udaDataType="duration";
	private static String customReportName="nice0";
	private static double urgencyThreshold = 11.5;
	private static String sudo = "sudo ";
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
		hardCoded.udaName = udaName;
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
		hardCoded.udaLabel = udaLabel;
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
		hardCoded.udaDataType = udaDataType;
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
		hardCoded.nameOfCustomSortParameter = nameOfCustomSortParameter;
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
		hardCoded.nameOfCustomSortParameterLabel = nameOfCustomSortParameterLabel;
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
		hardCoded.customSortDataType = customSortDataType;
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
		hardCoded.customReportName = customReportName;
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
		hardCoded.ubuntuFilePath = ubuntuFilePath;
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
		hardCoded.sudo = sudo;
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
		hardCoded.urgencyThreshold = urgencyThreshold;
	}

	public static String getUbuntuFileName() {
		return ubuntuFileName;
	}

	public static void setUbuntuFileName(String ubuntuFileName) {
		hardCoded.ubuntuFileName = ubuntuFileName;
	}

	public static String getEclipseFilePath() {
		return eclipseFilePath;
	}

	public static void setEclipseFilePath(String eclipseFilePath) {
		hardCoded.eclipseFilePath = eclipseFilePath;
	}

	public static String getEclipseFileName() {
		return eclipseFileName;
	}

	public static void setEclipseFileName(String eclipseFileName) {
		hardCoded.eclipseFileName = eclipseFileName;
	}
	
	public static String getLinuxUserName() {
		if (checkForNonRoot() != null && checkForNonRoot() != "root") {
			System.out.println("Returning nonRoot username ="+checkForNonRoot());
			return checkForNonRoot();
		}
		String linuxUserName = null;
		Command command = new Command();
		String[] commandLines = new String[1];
		commandLines[0] = "dir";
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("/home/");
		command.setSetWorkingPath(true);
		command.setGetOutput(true);

		
		// execute command to create destination folder
		try {
			linuxUserName = RunCommandsV3.executeCommands(command, false);
			System.out.println("Found username = "+linuxUserName);
			return linuxUserName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
}
