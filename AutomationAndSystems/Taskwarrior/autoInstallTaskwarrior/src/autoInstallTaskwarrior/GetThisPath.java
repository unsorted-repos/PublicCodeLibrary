package learnToSayYesToLinux;

import java.io.File;

public class getThisPath {

	/**
	 * Source:https://stackoverflow.com/questions/15359702/get-location-of-jar-file#15359999
	 * Warning, appearently does not work in certain scenarios, the updated solution did not work in my scenario;
	 * 		calling the .jar file from a different folder in cmd (e.g. java -jar test\myjar.jar) did not work.
	 * @return actual location of the compiled runnable .jar file that is executed is returned from this method
	 */
	public static String getJarLocation() {
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		return path;
	}
}
