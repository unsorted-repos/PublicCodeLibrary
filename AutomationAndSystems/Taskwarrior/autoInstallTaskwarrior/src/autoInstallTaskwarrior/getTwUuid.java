package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class getTwUuid {
	public static List<String> findFoldersInDirectory(String directoryPath) {
	    List<String> foldersInDirectory = null; 
		File directory = new File(directoryPath);
		
	    System.out.println("Incoming file = "+directory.getAbsolutePath());
	    
	    FileFilter directoryFileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
			
	    File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
	    if (directoryListAsFile != null) {
		    System.out.println("file = "+directoryListAsFile.toString());
		    foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
		    for (File directoryAsFile : directoryListAsFile) {
		        foldersInDirectory.add(directoryAsFile.getName());
		    }
	    }
	    return foldersInDirectory;
	}
}
