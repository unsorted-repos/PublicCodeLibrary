/**
 * 
 */
package autoInstallTaskwarrior;

/**
 * @author a
 *
 */
public class HardCoded {
	public static InstallData hardCoded() {
		InstallData installData = new InstallData();
		
		//hardcoded
//		String[] storeUserInput =;
		installData.setUserInput(AskUserInput.getUserInput());
		installData.setTestrun(false);
		installData.setVars("vars");
		
		installData.setServerName(installData.getUserInput()[4]);
		installData.setServerPort("53589");
		
		installData.setBackupScriptName("autoBackup.sh");
		installData.setInternalBackupScriptPath("resource/");
		installData.setInternalBackupScriptName("autoBackup.sh");
		installData.setCustomSortScriptName("JavaServerSort.jar");
		
		//get the path of this compiled .jar file
		installData.setLinuxPath(GetThisPath.getJarLocation()[0]);
		
		
		
//		installData.getUserInput()[2]="Public";
//		installData.getUserInput()[3]="First";
		
		//when it's run in linux it automatically returns linux path. (No need for conversion)
		//String linuxPath = getThisPath.getJarLocation()[1]; 
		
		// TODO: Set to false before you publish so that users don't lose their other tw users!
		// TODO: Add question at start asking if they want to do a clean install/delete other tw users.
		installData.setDeleteOtherTwUsers(true);
		
		
		System.out.println("Path ="+installData.getWindowsPath());
		System.out.println("Path ="+installData.getLinuxPath());

		return installData;
	}
}
