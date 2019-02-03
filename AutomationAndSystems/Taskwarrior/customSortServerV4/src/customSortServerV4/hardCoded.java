package customSortServerV4;

public class hardCoded {
	private static String eclipseFilePath="input/";
	private static String eclipseFileName="pendingPublic.data";
	private static String ubuntuFileName="pending.data";
	
	public static String getUbuntuFileName() {
		return ubuntuFileName;
	}

	public static void setUbuntuFileName(String ubuntuFileName) {
		hardCoded.ubuntuFileName = ubuntuFileName;
	}

	public static String getEclipseFilePath() {
		return eclipseFilePath;
	}

	public static void setEclipseFilePath(String eclipseFilePath) {
		hardCoded.eclipseFilePath = eclipseFilePath;
	}

	public static String getEclipseFileName() {
		return eclipseFileName;
	}

	public static void setEclipseFileName(String eclipseFileName) {
		hardCoded.eclipseFileName = eclipseFileName;
	}
}
