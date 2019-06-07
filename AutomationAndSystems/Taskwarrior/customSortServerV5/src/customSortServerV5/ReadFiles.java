package customSortServerV5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFiles {
	
	/**
	 * Checks if the file filename in folder path exists.
	 * 
	 * @param path
	 * @param filename
	 * @return
	 */
	public static boolean checkIfFileExist(String path, String filename) {

		// merge file path and file name to file object
		File f = new File(path + filename);

		// check if file exists
		if (f.exists() && !f.isDirectory()) {
//			System.out.println("File:" + path + filename + " exists");
			return true;
		} else {
//			System.out.println("ERROR!! The file:" + path + filename + " does NOT exist");
			return false;
		}
	}
	
	public static ArrayList<String> readFiles(String filePathAndName) {
		ArrayList<String> lines = new ArrayList<String>();
		
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePathAndName))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                //sb.append(line).append("\n");
//            	System.out.println("Adding line:"+line);
                lines.add(line);
                System.out.println("Adding line ="+line);
            }
            System.out.println("Test");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // System.out.println(sb);
        
        return lines;
    }
	
	
}
