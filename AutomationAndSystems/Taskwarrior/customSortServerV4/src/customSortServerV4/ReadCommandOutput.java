/**
 * 
 */
package customSortServerV4;

import java.util.ArrayList;

/**
 * @author a
 *
 */
public class ReadCommandOutput {


	public static double splitOutput(ArrayList<ArrayList<String>> returned,boolean getUrgency) {
		readErrorCommandExecutionOutput(returned.get(1));
		return readGoodCommandExecutionOutput(returned.get(0),getUrgency);
	}
	public static double readGoodCommandExecutionOutput(ArrayList<String> goodCommandOutput,boolean getUrgency) {
		String line =null;
		if (getUrgency) {
			for (int i = 0;i<goodCommandOutput.size();i++) {
				line = goodCommandOutput.get(i);
				if (line.length()>7) {
					//System.out.println(outputLines.substring(0,7));
					if (line.substring(0, 7).equals("Urgency")) {
						System.out.println("THIS IS THE URGENCY LINE!"+line);
						return getUrgency(line);
					}
				}
			}
		}
		return -1;
	}
	public static void readErrorCommandExecutionOutput(ArrayList<String> errorCommandOutput) {
		for (int i = 0;i<errorCommandOutput.size();i++) {
			System.out.println(errorCommandOutput.get(i));
		}
	}

	public static double getUrgency(String line) {
		String urgency=null;
		if (line.length()>7) {
			//System.out.println(outputLines.substring(0,7));
			if (line.substring(0, 7).equals("Urgency")) {
				
				
				//Get index of first nr.
				urgency = line.substring(numberIndex(line),line.length());
				System.out.println("Urgency"+line);
				System.out.println("Urgency"+line);
				return Double.parseDouble(urgency);
			}
		}	
		return -1;
	}
	
	public static int numberIndex(String s) {
	    int len = s.length();
	    char temp;
	    for (int i = 0; i < len; ++i) {
	    	temp=s.charAt(i);
	    	if(Character.isDigit(s.charAt(i))) {
	            return i;
	        }
	    }
	    return -1;
	}
}
