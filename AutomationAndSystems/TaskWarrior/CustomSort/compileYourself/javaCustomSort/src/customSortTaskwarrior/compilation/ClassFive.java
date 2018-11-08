//To compile as jar you need to comment out this package allocation. For testing you uncomment it:
//package customSortTaskwarrior.compilation;
//Version 6.
// Completely dynamic path

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
//import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Iterator;

//import javax.swing.*;

//import org.junit.platform.commons.util.StringUtils;    
/**
 * 0. Read in two .txt files with the arrays of ID's and arrays of projects.
 * 1. Get threshold 
 * 2. if urgency is equal to or above threshold sort on urgency
 * 		2.b Creates array sortedUrg with a list of task ID's
 * 3. if the urgency is below the threshold and if it has a project, sort on project
 * 		3.b Creates an array sortedProj with a list of task ID's
 * 4. Sort the tasks without a project, that are below the threshold on urgency in a new array sortedNoProj
 * 	 (I.e, if the id is neither in sortedUrg nor sortedProj, put the id in sortedNoProj
 * 5. Create a final array with the following task id's sequentially: sortedProj,sortedNoProj,sortedUrg
 * 6. Create the taskwarrior command that adds the custom sort order to a new custom field of those tasks
 * 7. Create a taskwarrior command that generates a custom sorting view/custom list sorted on that custom sort order
 * 8. Write both those commands to a .batch file.
 * @param args
 */
public class ClassFive{    
	ClassFive(){    
	//parameters
	String[] completeArray;
	String[][] tempAddingArray;
	int nrOfFirstDataSeries = 2;
	int nrOfDataSeries=5;
	double threshold = 13.5;
	ArrayList sortedProj = new ArrayList();
	ArrayList sortedProj_Urg = new ArrayList();
	ArrayList sortedProj_Proj = new ArrayList();
	ArrayList sortedUrg= new ArrayList();
	ArrayList sortedUrg_Urg= new ArrayList();
	ArrayList sortedUrg_Proj= new ArrayList();
	ArrayList sortedNoProj = new ArrayList();
	ArrayList sortedNoProj_Urg = new ArrayList();
	ArrayList sortedNoProj_Proj = new ArrayList();
	int[] finalTaskIdList;
	int[] secretSort;
	int nrOfTasks;
	
	//0. read two .txt files into arrays
	tempAddingArray=readTxtArray("idUrg.txt");
	//1. Create an object that contains all the arrays
	Object[][] arr = new Object[nrOfDataSeries][tempAddingArray[0].length];
	
	//1. Absorb the array id in arr[0] and urgency in arr[1]
	for (int i = 0;i<tempAddingArray[0].length;i++) {
		arr[0][i] = Integer.parseInt(tempAddingArray[0][i]);//store id
		arr[1][i] = Double.parseDouble(tempAddingArray[1][i]);//store id
	}
	
	//arr[1] = new Integer(1);//store urgency
	//arr[2] = new String("Second Pair"); //store project

	//tempAddingArray = new String[completeArray.size];
	tempAddingArray=readTxtArray("idProj.txt");
	for (int i = 0;i<tempAddingArray[0].length;i++) {
		arr[2][i] = Integer.parseInt(tempAddingArray[0][i]);//store id
		arr[3][i] = tempAddingArray[1][i];//store id
	}
	
	//Indexed sort on urgency:
	Pair[] sortedUrgArr= indexedDoubleSort(arr[0],arr[1]);
	
	//Indexed sort on alphabet using
	StringPair[] sortedProjArr = indexedAlphabetSort(arr[2],arr[3]);
	
	// Add the index to a list
//	for (int i = 0;i<sortedUrgArr.length;i++) {
//		if (sortedUrgArr[i].value<threshold) {
//			sortedProj.add(sortedUrgArr[i].index);
//		}
//	}

	
	//Retry add the index to a list
	for (int i = 0;i<sortedProjArr.length;i++) {
		//if (sortedProjArr[i].value<threshold) {
			
			//Check if it is in list with project
			for (int j=0;j<sortedUrgArr.length;j++) {
				if (sortedUrgArr[j].index==sortedProjArr[i].index) {
					if (sortedUrgArr[j].value<threshold) {
						sortedProj.add(sortedUrgArr[j].index);
						sortedProj_Urg.add(sortedUrgArr[j].value);
						sortedProj_Proj.add(sortedProjArr[i].value);
						System.out.println("id="+sortedUrgArr[j].index+" urg ="+sortedUrgArr[j].value+" proj= "+sortedProjArr[i].value);
					}
				}					
			}		
		}
	
//	System.out.println(Arrays.toString(sortedProj.toArray()));
//	System.out.println(Arrays.toString(sortedProj_Urg.toArray()));
//	System.out.println(Arrays.toString(sortedProj_Proj.toArray()));

	// Add the index to a list for no project
	for (int i = 0;i<sortedUrgArr.length;i++) {
		boolean inList=false;
		if (sortedUrgArr[i].value<threshold) {
			
			//Check if it is in list with project
			for (int j=0;j<sortedProjArr.length;j++) {
				if (sortedUrgArr[i].index==sortedProjArr[j].index) {
					inList=true;
				}					
			}	
			if (!inList) {
				sortedNoProj.add(sortedUrgArr[i].index);
				sortedNoProj_Urg.add(sortedUrgArr[i].value);
				System.out.println("id="+sortedUrgArr[i].index+" urg ="+sortedUrgArr[i].value);
				
			}
		}
	}
//	System.out.println(Arrays.toString(sortedNoProj.toArray()));
//	System.out.println(Arrays.toString(sortedNoProj_Urg.toArray()));

	// Add the index to a list for urg>treshold
	for (int i = 0;i<sortedUrgArr.length;i++) {
		boolean inList=false;
		if (sortedUrgArr[i].value>=threshold) {
			
			//Check if it is in list with project
			//for (int j=0;j<sortedProjArr.length;j++) {
				//if (sortedUrgArr[i].index==sortedProjArr[j].index) {
					sortedUrg.add(sortedUrgArr[i].index);
					sortedUrg_Urg.add(sortedUrgArr[i].value);
					//sortedUrg_Proj.add(sortedProjArr[j].value);
					//System.out.println("id="+sortedUrgArr[i].index+" urg ="+sortedUrgArr[i].value+" proj= "+sortedProjArr[i].value);
				//}					
			//}	
		}
	}
//	System.out.println(Arrays.toString(sortedUrg.toArray()));
//	System.out.println(Arrays.toString(sortedUrg_Urg.toArray()));
//	System.out.println(Arrays.toString(sortedUrg_Proj.toArray()));
	
	//merge final list
	nrOfTasks=sortedUrg.size()+sortedNoProj.size()+sortedProj.size();
	System.out.println("nr of tasks="+nrOfTasks+"sortedurg="+sortedUrg.size()+"sortedNoProj="+sortedNoProj.size()+"sortedProj="+sortedProj.size());
	//initialize finalTaskIdList
	finalTaskIdList= new int[nrOfTasks];
	secretSort= new int[nrOfTasks];
			
	Iterator ite = sortedProj.iterator();
	int counter=0;
	while(ite.hasNext()) {		
		finalTaskIdList[counter]=(int) ite.next();
		counter++;
	}
	ite=null;
	ite = sortedNoProj.iterator();
	while(ite.hasNext()) {		
		finalTaskIdList[counter]=(int) ite.next();
		counter++;
	}
	ite=null;
	ite = sortedUrg.iterator();
	while(ite.hasNext()) {		
		finalTaskIdList[counter]=(int) ite.next();
		counter++;
	}	
	System.out.println("final list = "+Arrays.toString(finalTaskIdList));
	//Create a UDA named HiddenSort 
	//task config uda.secretSort.type numeric
	//task config uda.secretSort.label sSort
	//Modify Secretsort: task 15 modify secretSort:14
	
	for (int i =0;i<=finalTaskIdList.length-1;i++) {
		secretSort[i]=i;
	}
	
	//write to .txt file
	printCommands(finalTaskIdList,secretSort);
}

	public void printCommands(int[] finalTaskIdList,int[] secretSort) {
		BufferedWriter writer = null;
        try {
            //create a temporary file

            File logFile = new File("output/output.sh");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("#!/bin/bash"+'\n');
            writer.write("yes | task config uda.secretSort.type numeric"+'\n');            
            writer.write("yes | task config uda.secretSort.label sSort"+'\n');
            for (int i =0;i<secretSort.length;i++) {
            	writer.write("task "+finalTaskIdList[i]+" modify secretSort:"+secretSort[i]+'\n');
        		System.out.println("Printed sSort="+i+"="+secretSort[i]+"finalTaskId="+finalTaskIdList[i]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	}
	
	
	/**
     * Actual sort keeping track of indices!
     * @param args
     */
    public static Pair[] indexedDoubleSort(Object[] arrOne,Object[] arrTwo) {
        Pair[] yourArray = new Pair[arrOne.length];
        
        //fill the array
        for (int i = 0;i<arrOne.length;i++) {
        	System.out.println("To double:"+(double) arrTwo[i]);
        	yourArray[i] = new Pair((int) arrOne[i],((double) arrTwo[i]));
        }
        
        Arrays.sort(yourArray);
        for (int i = 0;i<yourArray.length;i++) {
        	//System.out.println("i="+yourArray[i].index+" and urg = "+yourArray[i].value);
        }
        return yourArray;
    }

	/**
     * Actual sort keeping track of indices!
     * @param args
     */
    public static StringPair[] indexedAlphabetSort(Object[] arrOne,Object[] arrTwo) {
    	//parameters
    	int indexLastFilledElement = 0;
    	// get nr of filled elements:
    	 for (int i = 0;i<arrOne.length;i++) {if (arrTwo[i]==null) {indexLastFilledElement=i-1;i = arrOne.length+2;}}
    	StringPair[] yourArray = new StringPair[indexLastFilledElement+1];
    	
    	
        //fill the array
        for (int i = 0;i<arrOne.length;i++) {
        	System.out.println("i="+i+" and str = "+(String) arrTwo[i]);
        	if (arrTwo[i]==null) {indexLastFilledElement=i-1;} else {
        		yourArray[i] = new StringPair((int) arrOne[i],((String) arrTwo[i]));
        	}
        }
        
        System.out.println("Len of yourArray ="+yourArray.length);
        Arrays.sort(yourArray);
        for (int i = 0;i<yourArray.length;i++) {
        	//System.out.println("i="+yourArray[i].index+" and urg = "+yourArray[i].value);
        }
        return yourArray;
    }    
    
//	public static void sortOnUrgency(Object[][] arrIdUrg){
//		//final String[] strArr = {"France", "Spain", "France"};
//		final String[] arrUrg =(String[]) arrIdUrg[2];		
//		for (int i =0;i<sortedIndices.length;i++) {
//			System.out.println("i="+i+" and urg = " +arrIdUrg[3][sortedIndices[i]]);
//		}
//	}
	
	public static void main(String[] args) {
		
	    new ClassFive();    
	}    
	
	/**
	 * 
	 * @return actual location of the compiled runnable .jar file that is executed is returned from this method
	 */
	public String getJarLocation() {
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		String path = dir.toString();
		return path;
	}
	
	/**
	 * This reads a .txt file and puts it in an arraylist of type String.
	 * @param fileName
	 * @return
	 */
	public String[][] readTxtArray(String fileName){
		ArrayList someList = new ArrayList();
		Scanner sc = null;
		System.out.println("Reading:"+getJarLocation()+"/output/"+fileName);
		try {
			sc = new Scanner(new File(getJarLocation()+"/output/"+fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Burn first line containing header
		sc.nextLine();
		
		while (sc.hasNext()) {
			someList.add(sc.next());
	          //System.out.println(sc.nextLine());
	    }
	     
	    return convertTxtToArray(someList);
	}
	
	/**
	 * Loops through arrayList of strings and:
	 *  stores the id in 1st dimension index 0
	 * stores the value in 1st dimension index 1
	 * Uses the ; as a separator and then removes spaces of the values
	 * @param inputString
	 * @return
	 */
	public static String[][] convertTxtToArray(ArrayList inputString){
		//parameters		
		int nrOfSubStrings = 2;
		String[][] returnString=new String[nrOfSubStrings][inputString.size()];
					
		for (int i=0;i<nrOfSubStrings;i++) {
			for (int j = 0; j<inputString.size();j++) {	
				System.out.println("Line="+j+"Input="+inputString.get(j).toString());
				returnString[i][j]=separarateStringAtSemicolon(inputString.get(j).toString(),";")[i];
				System.out.println("Returning i,j="+i+","+j+" = "+returnString[i][j]);
			}					
		}
		return returnString;
	}
	
	/**
	 * Cuts a string into pieces at the separator and puts them in a String array of substrings.
	 * @param singleLine
	 * @return
	 */
	public static String[] separarateStringAtSemicolon(String singleLine,String separator) {			
		int nrOfSeparators=getNrOfSubStringOccurences(singleLine, separator);
		String returnString[] = new String[nrOfSeparators+1];
		for (int i = 0; i <nrOfSeparators;i++) {
			returnString[i]=singleLine.substring(0,singleLine.indexOf(separator));
			singleLine=singleLine.substring(singleLine.indexOf(separator)+1,singleLine.length());			
		}
		returnString[nrOfSeparators]=singleLine;
		return returnString;
	}
	
	/**
	 * Gets the nr of of occurences of a substring
	 * @param inputString
	 * @param subString
	 */
	public static int getNrOfSubStringOccurences(String inputString, String subString) {
		String str = inputString;
		String findStr = subString;
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		return count;
	}
	
	public static int getIndexFirstSubStringOccurence(String inputString,String subString) {
		String str = inputString;
		String findStr = subString;
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = str.indexOf(findStr,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		    }
		}
		return count;		
	}
	
}    
