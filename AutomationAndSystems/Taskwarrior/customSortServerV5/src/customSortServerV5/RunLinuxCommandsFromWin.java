package customSortServerV5;

public class RunLinuxCommandsFromWin {
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
	public static String runLinuxCommandFromWindows(HardCoded hardCoded, String[] commands) {
		commands = prependWSL(commands);
		String commandPath = hardCoded.getWindowsPath() + "src/" + hardCoded.getTempCommandScriptFolder() + "/";
		
		commandPath = hardCoded.swapSlashes(commandPath);
//		System.out.println("CommandPath="+commandPath);
		CreateFiles.managePowershellSciptCreation(hardCoded,commandPath, hardCoded.getTempCommandFileName(), commands);
//		
//		String launcherPath = hardCoded.getWindowsPath() + "src/" + hardCoded.getTempCommandScriptFolder() + "/";
//		launcherPath = hardCoded.swapSlashes(launcherPath);
		return RunPowershell
				.runPowershell(generatePowershellLaunchCommand(hardCoded, commandPath, hardCoded.getTempCommandFileName()), true);

	}

	private static String[] prependWSL(String[] commands) {
		for (int i = 0; i < commands.length; i++) {
			commands[i] = "wsl " + commands[i];
//			System.out.println(commands[i]);
		}
		return commands;
	}

	/**
	 * 
	 * @param hardCoded
	 * @param launchCommandScriptName
	 * @return
	 */
	private static String generatePowershellLaunchCommand(HardCoded hardCoded, String launchCommandScriptPath, String launchCommandScriptName) {
		
//		System.out.println("Executing"+launchCommandScriptPath+launchCommandScriptName);
		
		String command1 = "powershell.exe & '" + launchCommandScriptPath + launchCommandScriptName + "' "; //
		return command1;
	}
}
