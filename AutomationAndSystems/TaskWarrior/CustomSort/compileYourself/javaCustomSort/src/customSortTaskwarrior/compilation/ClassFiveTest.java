package customSortTaskwarrior.compilation;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ClassFiveTest {

	@Test
	void separarateStringAtSemicolonTest() {	
		String inputString="a;;baa ;";
		String[] outputString=new String[4];
		String[] returnString =ClassFive.separarateStringAtSemicolon(inputString,";");
		outputString[0] = "a";
		outputString[1] = "";
		outputString[2] = "baa ";
		outputString[3] = "";
		
		
		for (int i = 0;i<outputString.length;i++) {
			assertTrue("This must be equal",returnString[i].equals(outputString[i]));	
		}		
	}

	@Test
	void separarateStringAtSemicolonTestSkipped() {	
		String inputString="80;18.4";
		String[] outputString=new String[2];
		String[] returnString =ClassFive.separarateStringAtSemicolon(inputString,";");
		outputString[0] = "80";
		outputString[1] = "18.4";
		
		for (int i = 0;i<outputString.length;i++) {
			assertTrue("This must be equal",returnString[i].equals(outputString[i]));	
		}		
	}
	

	@Test
	void separarateStringAtSemicolonZeroTest() {	
		String inputString="asdkjhasdf s";
		String[] outputString=new String[1];
		String[] returnString =ClassFive.separarateStringAtSemicolon(inputString,";");
		outputString[0] = "asdkjhasdf s";		
		for (int i = 0;i<outputString.length;i++) {
			assertTrue("This must be equal",returnString[i].equals(outputString[i]));	
		}		
	}
	
	/**
	 * Tests if it detects the correct amount of substring detections
	 */
	@Test void getNrOfSubstringOccurencesTest() {
		String inputString = "a;b;c";
		String subString = ";";
		assertEquals(ClassFive.getNrOfSubStringOccurences(inputString, subString),2);
	}
	
	/**
	 * Tests if it detects the correct amount of substring detections in case 0 occurences
	 */
	@Test void getNrOfSubstringOccurencesZeroTest() {
		String inputString = "abc sd";
		String subString = ";";
		assertEquals(ClassFive.getNrOfSubStringOccurences(inputString, subString),0);
	}
	
	/**
	 * Tests whether the input arrayList is indeed separated into the correct substrings
	 */
	@Test
	void convertTxtToArrayTest() {		
		ArrayList inputList = new ArrayList();
		inputList.add("a;b");
		inputList.add("c;d");
		inputList.add("e;f");
		String[][] outputString= new String[2][3];
		outputString[0][0]="a";
		outputString[1][0]="b";
		outputString[0][1]="c";
		outputString[1][1]="d";
		outputString[0][2]="e";
		outputString[1][2]="f";
		
		String[][] returnString = ClassFive.convertTxtToArray(inputList);
		for (int i = 0;i<outputString.length;i++) {
			for (int j = 0;j<outputString[0].length;j++) {
			assertTrue("This must be equal",returnString[i][j].equals(outputString[i][j]));	
			}
		}
	}
}
