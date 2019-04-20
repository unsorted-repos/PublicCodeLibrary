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
		
		installData.setServerName("0.0.0.0");
		installData.setServerPort("53589");
		
		//get the path of this compiled .jar file
		installData.setLinuxPath(GetThisPath.getJarLocation()[0]);
		
		String serverPort = "53589";
//		installData.getUserInput()[2]="Public";
//		installData.getUserInput()[3]="First";
		
		//when it's run in linux it automatically returns linux path. (No need for conversion)
		//String linuxPath = getThisPath.getJarLocation()[1]; 
		
		//hardcoded copy verifications file names
		String[] copyVerification19 = new String[3]; 		
		copyVerification19[0] = installData.getUserInput()[3]+".cert.pem";
		copyVerification19[1] = installData.getUserInput()[3]+".key.pem";
		copyVerification19[2] = "ca.cert.pem";
		installData.setCopyVerifications19(copyVerification19);
		
		
		System.out.println("Path ="+installData.getWindowsPath());
		System.out.println("Path ="+installData.getLinuxPath());

		return installData;
	}
}
