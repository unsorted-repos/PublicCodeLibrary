package autoInstallTaskwarrior;

public class InstallData {
	private String linuxUserName;
	private String serverName;
	private String serverPort;
	private String twOrganisation;
	private String twUserName;
	private String windowsPath;
	private String linuxPath;
	private String vars;
	private String[] copyVerifications19 = new String[3];
	//private String[] userInput;
	private boolean testrun;
	private String twUuid;
	private boolean deleteOtherTwUsers;
	private boolean isServer;
	private String backupScriptDestination;
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
	private String outputFolderDriveLetter;
	private String outputPath;
	private String certificateOutputPath;
	private String certificateInputPath;
	private String BackupOutputPath;
	private String BackupInputPath;
	
	
	/**
	 * @return the backupScriptDestination
	 */
	public String getBackupScriptDestination() {
		return backupScriptDestination;
	}

	/**
	 * @param backupScriptDestination the backupScriptDestination to set
	 */
	public void setBackupScriptDestination(String backupScriptDestination) {
		this.backupScriptDestination = backupScriptDestination;
	}

	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	
	/**
	 * @return the outputFolderDriveLetter
	 */
	public String getOutputFolderDriveLetter() {
		return outputFolderDriveLetter;
	}

	/**
	 * TODO: check if it is bad style to update the values of the relative paths like this.
	 * @param outputFolderDriveLetter the outputFolderDriveLetter to set
	 */
	public void setOutputFolderDriveLetter(String outputFolderDriveLetter) {
		this.outputFolderDriveLetter = outputFolderDriveLetter;
		HardCoded.initializeOutputFolder(this);
	}

	/**
	 * @return the certificateOutputPath
	 */
	public String getCertificateOutputPath() {
		return certificateOutputPath;
	}

	/**
	 * @param certificateOutputPath the certificateOutputPath to set
	 */
	public void setCertificateOutputPath(String certificateOutputPath) {
		this.certificateOutputPath = certificateOutputPath;
	}

	/**
	 * @return the certificateInputPath
	 */
	public String getCertificateInputPath() {
		return certificateInputPath;
	}

	/**
	 * @param certificateInputPath the certificateInputPath to set
	 */
	public void setCertificateInputPath(String certificateInputPath) {
		this.certificateInputPath = certificateInputPath;
	}

	/**
	 * @return the backupOutputPath
	 */
	public String getBackupOutputPath() {
		return BackupOutputPath;
	}

	/**
	 * @param backupOutputPath the backupOutputPath to set
	 */
	public void setBackupOutputPath(String backupOutputPath) {
		BackupOutputPath = backupOutputPath;
	}

	/**
	 * @return the backupInputPath
	 */
	public String getBackupInputPath() {
		return BackupInputPath;
	}

	/**
	 * @param backupInputPath the backupInputPath to set
	 */
	public void setBackupInputPath(String backupInputPath) {
		BackupInputPath = backupInputPath;
	}
	
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
		return backupScriptDestination;
	}

	/**
	 * @param backupDestination the backupDestination to set
	 */
	public void setBackupDestination(String backupDestination) {
		this.backupScriptDestination = backupDestination;
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
	
	/**
	 * assume input has been filtered
	 * TODO: remove hardcoded tw org and tw username
	 * TODO: disable reading userInput
	 * @param userInput the userInput to set
	 */
	public void setUserInput(UserInput userInput) {
		this.linuxUserName = userInput.getAnswer().get(0);
		System.out.println("linuxUserName="+this.linuxUserName);
		this.twOrganisation = userInput.getAnswer().get(1);
		this.twUserName = userInput.getAnswer().get(2);
		if (userInput.getAnswer().get(3).equals("y")) { //uses on multiple devices
			if (userInput.getAnswer().get(4).equals("server")) {
				this.isServer = true;
			} else { // pc is client
				this.isServer = false;
			}
		} else { // uses on single device so this does the sorting/is server
			this.isServer = true;
		}
		if (this.isServer) {
			System.out.println("Setting server="+userInput.getAnswer().get(5));
			this.serverName = userInput.getAnswer().get(5);
		} else {
			this.serverName = "0.0.0.0";
		}
		
		// TODO: Put in correct location
		this.backupScriptDestination = "/home/"+this.linuxUserName+"/maintenance/";
		System.out.println("backupScriptDestination="+this.backupScriptDestination);
		
	}
}
