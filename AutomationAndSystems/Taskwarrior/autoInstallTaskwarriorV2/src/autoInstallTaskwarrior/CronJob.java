/**
 * 
 */
package autoInstallTaskwarrior;

/**
 * 
 *
 */
public class CronJob {
	private String cronTiming;
	private String cronCommand;
	private String cronPath;
	private String cronFileName;	
	private String completeCommand;
	private boolean useInMultiDevice;
	private boolean useThisCronInClientScenario;
	
	
	public boolean isUseThisCronInClientScenario() {
		return useThisCronInClientScenario;
	}

	public void setUseThisCronInClientScenario(boolean useThisCronInClientScenario) {
		this.useThisCronInClientScenario = useThisCronInClientScenario;
	}

	public String getCronTiming() {
		return cronTiming;
	}

	public void setCronTiming(String cronTiming) {
		this.cronTiming = cronTiming;
	}

	public String getCronCommand() {
		return cronCommand;
	}

	public void setCronCommand(String cronCommand) {
		this.cronCommand = cronCommand;
	}

	public String getCronFileName() {
		return cronFileName;
	}

	public void setCronFileName(String cronFileName) {
		this.cronFileName = cronFileName;
	}

	public boolean isUseInMultiDevice() {
		return useInMultiDevice;
	}

	public void setUseInMultiDevice(boolean useInMultiDevice) {
		this.useInMultiDevice = useInMultiDevice;
	}

	public boolean isUseInClient() {
		return useInClient;
	}

	public void setUseInClient(boolean useInClient) {
		this.useInClient = useInClient;
	}

	public String getCronPath() {
		return cronPath;
	}

	public void setCompleteCommand(String completeCommand) {
		this.completeCommand = completeCommand;
	}
	private boolean useInClient;
	
	/**
     * Question: is calling setCronPath in the constructor proper etiquette?
	 * @param cronTiming
	 * @param cronCommand
	 * @param cronPath
	 * @param cronFileName
	 */
	CronJob(String cronTiming, String cronCommand, String cronPath, String cronFileName, boolean useThisCronInClientScenario) {
		this.cronTiming = cronTiming;
		this.cronCommand = cronCommand;	
		// ensure path is correct
		this.setCronPath(cronPath);
		this.cronFileName = cronFileName;
		this.useThisCronInClientScenario = useThisCronInClientScenario;
	}
	
	/**
	 * @param cronPath the cronPath to set
	 */
	public void setCronPath(String cronPath) {
		if (cronPath.charAt(cronPath.length()-1) != '/') {
			cronPath = cronPath + "/";
		}
		this.cronPath = cronPath;
	}
	
	/**
	 * @return the completeCommand
	 */
	public String getCompleteCommand() {
		this.setCompleteCommand();
		return completeCommand;
	}
	/**
	 * @param completeCommand the completeCommand to set
	 */
	public void setCompleteCommand() {
		if (this.cronTiming != null && this.cronCommand != null && this.cronPath != null && this.cronFileName != null) {
			this.completeCommand = this.cronTiming+" " + this.cronCommand + " ";
			this.completeCommand = this.completeCommand + (char)34 + this.cronPath;
			this.completeCommand = this.completeCommand + this.cronFileName + (char)34;
		}else {
			throw new IllegalStateException("There is a property of the cronjob specification equal to null. This leads to an incorrect cronjob.");
		}
	}
}
