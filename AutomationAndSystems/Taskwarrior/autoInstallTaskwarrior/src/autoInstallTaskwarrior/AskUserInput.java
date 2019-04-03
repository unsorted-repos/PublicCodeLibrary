package autoInstallTaskwarrior;
import java.util.Scanner;

public class AskUserInput {
	public static String[] getUserInput() { 
		String[] userInput = new String[5];
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Please re-enter your Unix username: ");
		userInput[0] = reader.next(); // Scans the next token of the input as an int.
		System.out.println("Please re-enter your Unix password: ");
		userInput[1] = reader.next(); // Scans the next token of the input as an int.
		
		//once finished
		reader.close();
		return userInput;
	}
}
