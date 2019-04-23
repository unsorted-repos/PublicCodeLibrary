package autoInstallTaskwarrior;

/**
 * Gets the directory path based on the taskwarrior organisation
 * and the taskwarrior user name that the user entered. 
 * Then verifies the path exists, then gets the name of the
 * first folder in that path. (That first folder is the tw uuid).
 * @author a
 *
 */
public class getTwUuid {
//	public static List<String> findFoldersInDirectory(String directoryPath) {
//	    List<String> foldersInDirectory = null; 
//		File directory = new File(directoryPath);
//		
//	    System.out.println("Incoming file = "+directory.getAbsolutePath());
//	    
//	    FileFilter directoryFileFilter = new FileFilter() {
//	        public boolean accept(File file) {
//	            return file.isDirectory();
//	        }
//	    };
//			
//	    File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
//	    if (directoryListAsFile != null) {
//		    System.out.println("file = "+directoryListAsFile.toString());
//		    foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
//		    for (File directoryAsFile : directoryListAsFile) {
//		        foldersInDirectory.add(directoryAsFile.getName());
//		    }
//	    }
//	    System.out.println("the found directory = "+foldersInDirectory);
//	    return foldersInDirectory;
//	}
	
	public static String getUuid() {
		return null;
		
	}
}
