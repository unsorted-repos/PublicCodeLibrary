//package test0;

import javax.swing.*;    
/**
 * 0. Quick commands are written with:
 * del ClassFive.class
 * del myjar.jar
 * javac ClassFive.java
 * jar -cvmf manifest.mf myjar.jar ClassFive.class
 * java -jar myjar.jar
 * 1. You can not have the package <projectName>; line above to make it runnable in eclipse if you want to execute it as jar.
 * 2. The path of the jar file is not returned by the System.getProperty line which returns the working directory, that's just the path
 *    where cmd currently is in.
 * @param args
 */
public class ClassFive{    
	ClassFive(){    
				// The working directory is the location in which the jar is called from.
		// E.g. if cmd is in c:/ and the .jar file is located in c:/test and called with: java -jar \test\myjar.jar
		// Then the working directory is c:/ and NOT c:/test
		System.out.println("Working Directory = " + System.getProperty("user.dir"));	
		
		// The actual location of the file that is executed is found with: 
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		System.out.println("file location = "+path);
}
	public static void main(String[] args) {
		
	    new ClassFive();    
	}    
}    
