package autoInstallTaskwarrior;

import java.io.File;

public class GetThisPath {

	/**
	 * Source:https://stackoverflow.com/questions/15359702/get-location-of-jar-file#15359999
	 * Warning, appearently does not work in certain scenarios, the updated solution did not work in my scenario;
	 * 		calling the .jar file from a different folder in cmd (e.g. java -jar test\myjar.jar) did not work.
	 * 
	 * @return platform dependant format of location of compiled .jar of this project in [0], Linux format in [1]
	 */
	public static String[] getJarLocation() {
		String[] paths= new String[2];
		
		// get path of location of compiled .jar file of this project in windows format 
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		paths[0] = dir.toString()+"/";
		
		//generate linux path of location of compiled .jar file of this project.
		paths[1]=convertToLinuxPath(paths[0]);
		
		return paths;
	}
	
	/**
	 * This method is not necessary since the compiled .jar file
	 * automatically returns the file path in linux format when it is
	 * run in linux.
	 * @param windowsPath
	 * @return
	 */
	public static String convertToLinuxPath(String windowsPath) {
		
		// swap backwards slashes to forward slashes
		String linuxPath = windowsPath.replace("\\", "/");
		String driveLetter =linuxPath.substring(0, 1).toLowerCase();
		String remainderPath = linuxPath.substring(2,windowsPath.length());
				
		// switch to lowercase drive letter and convert X:/ to /mnt/x
		if (linuxPath.substring(0, 1).matches ("[a-zA-Z]\\.?")) {
			return "/mnt/"+driveLetter+remainderPath;
		}
		
		return null;
	}
}
