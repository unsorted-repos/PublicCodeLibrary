package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFiles {
	
	/**
	 * This creates the Vars file required in command 8
	 * @param serverName
	 * @throws IOException 
	 */
	public static void createVars(String linuxPath, String fileName, String serverName, String serverPort) throws IOException {
		char quotation = (char)34; // quotation mark "
		System.out.println("Incoming path inc. filename:"+linuxPath+fileName);
		
		deleteFile(fileName);
		
		// create a file called vars with content "content"
		createFile2(linuxPath,fileName);
		createFile1(linuxPath,fileName);
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(linuxPath+fileName, "UTF-8");
			writer.println("BITS=4096");
			writer.println("EXPIRATION_DAYS=365");
			writer.println("ORGANIZATION="+quotation+"Göteborg Bit Factory"+quotation);
			writer.println("CN="+serverName+":"+serverPort);
			writer.println("COUNTRY=SE");
			writer.println("STATE="+quotation+"Västra Götaland"+quotation);
			writer.println("LOCALITY="+quotation+"Göteborg"+quotation);
			writer.close();
			System.out.println("JUST WROTE CONTENT VARS FILE!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing vars"+e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Just failed at printing vars"+e);
		}
	}
	
	/**
	 * Delete a file that is located in the same folder as the src folder of this project
	 * is located.
	 * @param fileName
	 */
	public static  void deleteFile(String fileName) {
		File file = new File(fileName);
		try {
			boolean result = Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //surround it in try catch block
	}
	
	/**
	 * Try creating a file in c:/
	 * @param data
	 * @throws IOException
	 */
	public static void saveDataInFile(String data) throws IOException {
	    Path path = Paths.get("vars");
	    byte[] strToBytes = data.getBytes();

	    Files.write(path, strToBytes);
	}
	
	/**
	 * create a file in c.
	 * @param content
	 */
	public static void createFile2(String linuxPath, String fileName) {
	    {	
	    	try {
	    		 
		      //File file = new File("c:\\vars.txt");
	    		System.out.println("Creating new file0:"+linuxPath+fileName);
	    	  File file = new File(linuxPath+fileName);
		      
		      if (file.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }
	    	} catch (IOException e) {
		      e.printStackTrace();
	    	}
	    }
	}
	
	public static void createFile1(String linuxPath, String fileName) {
		try {
			System.out.println("Creating new file1:"+linuxPath+fileName);
            File file = new File(linuxPath+fileName);
            file.createNewFile();
            //file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
