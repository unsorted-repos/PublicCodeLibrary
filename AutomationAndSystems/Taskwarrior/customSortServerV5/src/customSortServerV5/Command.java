package customSortServerV5;

public class Command {
	private String[] commandLines;
	private String workingPath;
	private String envVarName;
	private String envVarContent;
	private boolean getOutput;
	private boolean setWorkingPath;
	private boolean setEnvVar;
	
	public Command() {}
	/**
	 * @return the commandLines
	 */
	public String[] getCommandLines() {
		return commandLines;
	}
	/**
	 * @param commandLines the commandLines to set
	 */
	public void setCommandLines(String[] commandLines) {
		this.commandLines = commandLines;
	}
	/**
	 * @return the workingDirectory
	 */
	public String getWorkingDirectory() {
		return workingPath;
	}
	/**
	 * @param workingDirectory the workingDirectory to set
	 */
	public void setWorkingPath(String workingDirectory) {
		this.workingPath = workingDirectory;
	}
	/**
	 * @return the envVarName
	 */
	public String getEnvVarName() {
		return envVarName;
	}
	/**
	 * @param envVarName the envVarName to set
	 */
	public void setEnvVarName(String envVarName) {
		this.envVarName = envVarName;
	}
	/**
	 * @return the envVarContent
	 */
	public String getEnvVarContent() {
		return envVarContent;
	}
	/**
	 * @param envVarContent the envVarContent to set
	 */
	public void setEnvVarContent(String envVarContent) {
		this.envVarContent = envVarContent;
	}
	/**
	 * @return the getOutput
	 */
	public boolean isGetOutput() {
		return getOutput;
	}
	/**
	 * @param getOutput the getOutput to set
	 */
	public void setGetOutput(boolean getOutput) {
		this.getOutput = getOutput;
	}
	/**
	 * @return the setWorkingPath
	 */
	public boolean isSetWorkingPath() {
		return setWorkingPath;
	}
	/**
	 * @param setWorkingPath the setWorkingPath to set
	 */
	public void setSetWorkingPath(boolean setWorkingPath) {
		this.setWorkingPath = setWorkingPath;
	}
	/**
	 * @return the setEnvVar
	 */
	public boolean isSetEnvVar() {
		return setEnvVar;
	}
	/**
	 * @param setEnvVar the setEnvVar to set
	 */
	public void setSetEnvVar(boolean setEnvVar) {
		this.setEnvVar = setEnvVar;
	}
	
	
}
