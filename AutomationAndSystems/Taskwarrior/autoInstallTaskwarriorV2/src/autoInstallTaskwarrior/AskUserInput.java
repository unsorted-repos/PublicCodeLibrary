package autoInstallTaskwarrior;
import java.util.Scanner;

public class AskUserInput {
	public static String[] getUserInput(InstallData installData) { 
		String[] userInput = new String[7];
		
		if (!installData.isDevelopeMode()) {
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Please re-enter your Unix username: ");
			userInput[0] = reader.next(); // Scans the next token of the input as an int.
			
			
			System.out.println("Please enter the name of your taskwarrior organization (it can be named:Public if you don't have any): ");
			userInput[2] = reader.next(); // Scans the next token of the input as an int.
			System.out.println("Please enter the name of this taskwarrior user: (it can be:First if you don't know any");
			userInput[3] = reader.next(); // Scans the next token of the input as an int.
			System.out.println("Please enter the server name: eg. something.something.com or: 0.0.0.0");
			userInput[4] = reader.next(); // Scans the next token of the input as an int.
			System.out.println("Is thisyour server (pc that is on most of the time and doing a few sorting computations) or client?(server/client)");
			userInput[5] = reader.next();
			System.out.println("By default your taskwarrior backup is put into:"+"/mnt/c/task backup"+"\n if you want to change that path, then please enter it now:(to skip type:n)");
			userInput[6] = reader.next();
			//once finished
			reader.close();
		} else {
			userInput[0] = "a";
			
			userInput[2] = "Public";
			userInput[3] = "First";
			userInput[4] = "0.0.0.0";
			userInput[5] =  "server";
			userInput[6] = "";
		}
		return userInput;
	}
	
	public static String requestDriveLetter() {
		String driveLetter=null;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("I would like to make a folder to copy your certificates and backups to, so that you can easily copy them to your other devices."+'\n'+'\n'+"Can you please enter the letter of the harddrive in which you want this folder?");
		driveLetter = reader.next(); // Scans the next token of the input as an int.
		return driveLetter;
	}
}
