/**
 * 
 */
package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {

	/**
     * Create a file instance to copy a temporary file.
     * @param resourcePath
     * @return
     */
    public static File getResourceAsFile(String resourcePath) {
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
            if (in == null) {
            	System.out.println("In=null");
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
            return null;
        }
    }
    
    /**
     * copy the temporary file that is read from recourses
     * @param args
     */
    public static void copyFile(File file,String filePath, String fileName) {	
    	InputStream inStream = null;
    	OutputStream outStream = null;
    	try{
 
    	   
    	    File file2 =new File(filePath+fileName);
 
    	    inStream = new FileInputStream(file);
    	    outStream = new FileOutputStream(file2); // for override file content
    	    //outStream = new FileOutputStream(file2,<strong>true</strong>); // for append file content
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
 
    	    if (inStream != null)inStream.close();
    	    if (outStream != null)outStream.close();
 
    	    System.out.println("File Copied to:"+filePath);
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public static void copyFileWithSudo(InstallData installData,String sourcePath,String sourceFileName, String destinationPath, String destinationFileName) throws Exception {
    	
    	// create copy command
    	Command command = new Command();
    	String[] commandLines = new String[4];
		commandLines[0] = "sudo";
		commandLines[1] = "cp";
		commandLines[2] = sourcePath+sourceFileName;
		commandLines[3] = destinationPath+destinationFileName;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("/usr/share/taskd/pki");
		
		// copy file
		RunCommandsV3.executeCommands(command,false);
    }
}
