package autoInstallTaskwarrior;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;

public class ReadFiles {
	
    public static StringBuilder readFiles(String fileName) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        //System.out.println(sb);
        
        return sb;
    }

    public static boolean firstLinesMatch(String[] original, int nrOfLines,String[] comparisonLines) {
    	for (int i = 0; i < nrOfLines; i++) {
    		if (!original[i].equals(comparisonLines[i])) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public static boolean lastLinesMatch(String[] original, int nrOfLines,String[] comparisonLines) {
    	if (nrOfLines < 1) {
    		throw new IllegalArgumentException("Method lastLinesMatch must have more than 1 nr of lines to compare. But you requested:"+nrOfLines+" lines.");
    	}else {
	    	for (int i = original.length-1; i > original.length-1-nrOfLines ; i--) {
	    		
	    		if (!original[i].equals(comparisonLines[i-(original.length - comparisonLines.length)])) {
	    			return false;
	    		}
	    	}
    	}
    	return true;
    }
}

