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
	
	/**
     * Question: is calling setCronPath in the constructor proper etiquette?
	 * @param cronTiming
	 * @param cronCommand
	 * @param cronPath
	 * @param cronFileName
	 */
	CronJob(String cronTiming, String cronCommand, String cronPath, String cronFileName) {
		this.cronTiming = cronTiming;
		this.cronCommand = cronCommand;	
		// ensure path is correct
		this.setCronPath(cronPath);
		this.cronFileName = cronFileName;
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
