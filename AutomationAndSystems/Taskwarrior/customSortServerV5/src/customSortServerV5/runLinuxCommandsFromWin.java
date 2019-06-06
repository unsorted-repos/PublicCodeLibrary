package customSortServerV5;

public class runLinuxCommandsFromWin {
	/**
	 * Pass a command that needs to be executed in wsl ubuntu. Then it creates a
	 * powershell file that contanis wsl+that command Then it executes the command.
	 * Once the command is finished, it should delete the powershell again.
	 * 
	 * Should check if command contains sudo, if yes it should throw error. TODO:
	 * Replace return null with return the output of the command.
	 * 
	 * @param command
	 * @return
	 */
	public String runLinuxCommandFromWindows(HardCoded hardCoded, String[] commands) {
		commands = prependWSL(commands);
		CreateFiles.createTestLaunchers(hardCoded, hardCoded.getTempCommandFileName(), commands);
		return RunPowershell
				.runPowershell(generatePowershellLaunchCommand(hardCoded, hardCoded.getTempCommandFileName()), true);

	}

	private static String[] prependWSL(String[] commands) {
		for (int i = 0; i < commands.length; i++) {
			commands[i] = "wsl " + commands[i];
		}
		return commands;
	}

	/**
	 * 
	 * @param hardCoded
	 * @param launchCommandScriptName
	 * @return
	 */
	private String generatePowershellLaunchCommand(HardCoded hardCoded, String launchCommandScriptName) {
		String launcherPath = hardCoded.getWindowsPath() + "/src/" + hardCoded.getTempCommandScriptFolder() + "/";
		launcherPath = hardCoded.swapSlashes(launcherPath);
		String command1 = "powershell.exe & '" + launcherPath + launchCommandScriptName + "' "; //
		return command1;
	}
}
