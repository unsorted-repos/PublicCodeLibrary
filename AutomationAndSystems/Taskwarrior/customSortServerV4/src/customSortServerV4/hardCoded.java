package customSortServerV4;

public class hardCoded {

	//Assumptions: 
	//0. Method generatePropertyList assumes the (user defined) attributes of
	//a task have the exact same name in the JSON pending.data file as they
	//have as fields of Object Task.

	private static String eclipseFilePath="input/";
	private static String eclipseFileName="pendingPublic.data";
	private static String ubuntuFileName="pending.data";
	private static String ubuntuFilePath="/home/a/.task/";
	private static String nameOfCustomSortParameterLabel="cSort";
	private static String customSortDataType="numeric";
	private static String customReportName="nice0";
	private static double urgencyThreshold = 11.2;
	private static String sudo = "sudo ";
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
}
