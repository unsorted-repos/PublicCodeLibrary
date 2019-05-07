package autoInstallTaskwarrior;

public class InstallData {
	private String linuxUserName;
	private String linuxPw;
	private String serverName;
	private String serverPort;
	private String twOrganisation;
	private String twUserName;
	private String windowsPath;
	private String linuxPath;
	private String vars;
	private String[] copyVerifications19 = new String[3];
	private String[] userInput;
	private boolean testrun;
	private String twUuid;
	private boolean deleteOtherTwUsers;
	private boolean isServer;
	private String backupDestination;
	private String backupScriptName;
	private String internalBackupScriptName;
	private String internalBackupScriptPath;
	private boolean developeMode;
	private String basrcFileName;
	private String bashrcPath;
	private String visudoFileName;
	private String visudoPath;
	private String sudoersFileName;
	private CronJob[] cronJobs;
	private String maintenanceFolder;
	private String sortScriptName;
	
	/**
	 * @return the maintenanceFolder
	 */
	public String getMaintenanceFolder() {
		return maintenanceFolder;
	}

	/**
	 * @param maintenanceFolder the maintenanceFolder to set
	 */
	public void setMaintenanceFolder(String maintenanceFolder) {
		this.maintenanceFolder = maintenanceFolder;
	}

	/**
	 * @return the sortScriptName
	 */
	public String getSortScriptName() {
		return sortScriptName;
	}

	/**
	 * @param sortScriptName the sortScriptName to set
	 */
	public void setSortScriptName(String sortScriptName) {
		this.sortScriptName = sortScriptName;
	}

	/**
	 * @return the cronJob
	 */
	public CronJob[] getCronJobs() {
		return cronJobs;
	}

	/**
	 * @param cronJob the cronJob to set
	 */
	public void setCronJobs(CronJob[] cronJobs) {
		this.cronJobs = cronJobs;
	}

	/**
	 * @return the sudoers
	 */
	public String getSudoersFileName() {
		return sudoersFileName;
	}

	/**
	 * @param sudoers the sudoers to set
	 */
	public void setSudoersFileName(String sudoersFileName) {
		this.sudoersFileName = sudoersFileName;
	}

	/**
	 * @return the visudoFileName
	 */
	public String getVisudoFileName() {
		return visudoFileName;
	}

	/**
	 * @param visudoFileName the visudoFileName to set
	 */
	public void setVisudoFileName(String visudoFileName) {
		this.visudoFileName = visudoFileName;
	}

	/**
	 * @return the visudoPath
	 */
	public String getVisudoPath() {
		return visudoPath;
	}

	/**
	 * @param visudoPath the visudoPath to set
	 */
	public void setVisudoPath(String visudoPath) {
		this.visudoPath = visudoPath;
	}

	/**
	 * @return the basrcFileName
	 */
	public String getBasrcFileName() {
		return basrcFileName;
	}

	/**
	 * @param basrcFileName the basrcFileName to set
	 */
	public void setBasrcFileName(String basrcFileName) {
		this.basrcFileName = basrcFileName;
	}

	/**
	 * @return the bashrcPath
	 */
	public String getBashrcPath() {
		return bashrcPath;
	}

	/**
	 * @param bashrcPath the bashrcPath to set
	 */
	public void setBashrcPath(String bashrcPath) {
		this.bashrcPath = bashrcPath;
	}

	/**
	 * @return the internalBackupScriptName
	 */
	public String getInternalBackupScriptName() {
		return internalBackupScriptName;
	}

	/**
	 * @param internalBackupScriptName the internalBackupScriptName to set
	 */
	public void setInternalBackupScriptName(String internalBackupScriptName) {
		this.internalBackupScriptName = internalBackupScriptName;
	}

	/**
	 * @return the internalBackupScriptPath
	 */
	public String getInternalBackupScriptPath() {
		return internalBackupScriptPath;
	}

	/**
	 * @param internalBackupScriptPath the internalBackupScriptPath to set
	 */
	public void setInternalBackupScriptPath(String internalBackupScriptPath) {
		this.internalBackupScriptPath = internalBackupScriptPath;
	}

	private String customSortScriptName;
	
	
//	String linuxPw = storeUserInput[1];
//	String serverName = "0.0.0.0";
//	String serverPort = "53589";
//	storeUserInput[2]="Public";
//	storeUserInput[3]="First";
//	//get the path of this file
//	String windowsPath = GetThisPath.getJarLocation()[0];
//	//when it's run in linux it automatically returns linux path. (No need for conversion)
//	//String linuxPath = getThisPath.getJarLocation()[1]; 
//	String linuxPath = windowsPath;
//	
//	//hardcoded copy verifications file names
//	String[] copyVerification19 = new String[3]; 		
//	copyVerification19[0] = storeUserInput[3]+".cert.pem";
//	copyVerification19[1] = storeUserInput[3]+".key.pem";
//	copyVerification19[2] = "ca.cert.pem";

	/**
	 * @return the backupScriptName
	 */
	public String getBackupScriptName() {
		return backupScriptName;
	}

	/**
	 * @param backupScriptName the backupScriptName to set
	 */
	public void setBackupScriptName(String backupScriptName) {
		this.backupScriptName = backupScriptName;
	}

	/**
	 * @return the customSortScriptName
	 */
	public String getCustomSortScriptName() {
		return customSortScriptName;
	}

	/**
	 * @param customSortScriptName the customSortScriptName to set
	 */
	public void setCustomSortScriptName(String customSortScriptName) {
		this.customSortScriptName = customSortScriptName;
	}

	InstallData(){
	}

	/**
	 * @return the linuxUserName
	 */
	public String getLinuxUserName() {
		return linuxUserName;
	}

	/**
	 * @param linuxUserName the linuxUserName to set
	 */
	public void setLinuxUserName(String linuxUserName) {
		this.linuxUserName = linuxUserName;
	}

	/**
	 * @return the linuxPw
	 */
	public String getLinuxPw() {
		return linuxPw;
	}

	/**
	 * @param linuxPw the linuxPw to set
	 */
	public void setLinuxPw(String linuxPw) {
		this.linuxPw = linuxPw;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort the serverPort to set
	 */
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the twOrganisation
	 */
	public String getTwOrganisation() {
		return twOrganisation;
	}

	/**
	 * @param twOrganisation the twOrganisation to set
	 */
	public void setTwOrganisation(String twOrganisation) {
		this.twOrganisation = twOrganisation;
	}

	/**
	 * @return the twUserName
	 */
	public String getTwUserName() {
		return twUserName;
	}

	/**
	 * @param twUserName the twUserName to set
	 */
	public void setTwUserName(String twUserName) {
		this.twUserName = twUserName;
	}

	/**
	 * @return the windowsPath
	 */
	public String getWindowsPath() {
		return windowsPath;
	}

	/**
	 * @param windowsPath the windowsPath to set
	 */
	public void setWindowsPath(String windowsPath) {
		this.windowsPath = windowsPath;
	}

	/**
	 * @return the linuxPath
	 */
	public String getLinuxPath() {
		return linuxPath;
	}

	/**
	 * @param linuxPath the linuxPath to set
	 */
	public void setLinuxPath(String linuxPath) {
		this.linuxPath = linuxPath;
	}

	/**
	 * @return the copyVerifications19
	 */
	public String[] getCopyVerifications19() {
		return copyVerifications19;
	}

	/**
	 * @param copyVerifications19 the copyVerifications19 to set
	 */
	public void setCopyVerifications19(String[] copyVerifications19) {
		this.copyVerifications19 = copyVerifications19;
	}
	
	/**
	 * @return the vars
	 */
	public String getVars() {
		return vars;
	}

	/**
	 * @param vars the vars to set
	 */
	public void setVars(String vars) {
		this.vars = vars;
	}

	/**
	 * @return the testrun
	 */
	public boolean isTestrun() {
		return testrun;
	}

	/**
	 * @param testrun the testrun to set
	 */
	public void setTestrun(boolean testrun) {
		this.testrun = testrun;
	}

	/**
	 * TODO: remove hardcoded tw org and tw username
	 * TODO: disable reading userInput
	 * @param userInput the userInput to set
	 */
	public void setUserInput(String[] userInput) {
		this.userInput = userInput;
		this.linuxUserName = userInput[0];
		this.linuxPw = userInput[1];
		this.twOrganisation = userInput[2];
		this.twUserName = userInput[3];
		this.serverName = userInput[4];
		if (userInput[5].equals("y")) {
			this.isServer = true;
		} else {
			this.isServer = false;
		}
		// TODO: change from if NOT n then userInput[6]
		if (userInput[6].equals("nnnnn")) {
			this.backupDestination = userInput[6];
		}else {
			this.backupDestination = "/home/"+this.linuxUserName+"/maintenance/";
		}
		
	}
	
	public String[] getUserInput() {
		return this.userInput;
	}

	/**
	 * @return the twUuid
	 */
	public String getTwUuid() {
		return twUuid;
	}

	/**
	 * @param twUuid the twUuid to set
	 */
	public void setTwUuid(String twUuid) {
		this.twUuid = twUuid;
	}

	/**
	 * @return the deleteOtherTwUsers
	 */
	public boolean isDeleteOtherTwUsers() {
		return deleteOtherTwUsers;
	}

	/**
	 * @param deleteOtherTwUsers the deleteOtherTwUsers to set
	 */
	public void setDeleteOtherTwUsers(boolean deleteOtherTwUsers) {
		this.deleteOtherTwUsers = deleteOtherTwUsers;
	}

	/**
	 * @return the isServer
	 */
	public boolean isServer() {
		return isServer;
	}

	/**
	 * @param isServer the isServer to set
	 */
	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}

	/**
	 * @return the backupDestination
	 */
	public String getBackupDestination() {
		return backupDestination;
	}

	/**
	 * @param backupDestination the backupDestination to set
	 */
	public void setBackupDestination(String backupDestination) {
		this.backupDestination = backupDestination;
	}

	/**
	 * @return the developeMode
	 */
	public boolean isDevelopeMode() {
		return developeMode;
	}

	/**
	 * @param developeMode the developeMode to set
	 */
	public void setDevelopeMode(boolean developeMode) {
		this.developeMode = developeMode;
	}
	
}
