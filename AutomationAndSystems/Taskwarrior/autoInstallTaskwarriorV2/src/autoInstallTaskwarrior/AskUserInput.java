package autoInstallTaskwarrior;
import java.util.Scanner;

public class AskUserInput {
	public static void getUserInput(InstallData installData) { 
		UserInput userInput = new UserInput();
		
		if (!installData.isDevelopeMode()) {
			initialiseQuestionsPreInstall(installData,userInput);
		} else {
			userInput.getAnswer().add("a");
			userInput.getAnswer().add("Public");
			userInput.getAnswer().add("First");
			userInput.getAnswer().add("n");
			userInput.getAnswer().add("server");
			userInput.getAnswer().add("0.0.0.0");
		}
	}
	
	/**
	 * QuestionSetV2
	 * @param userInput
	 */
	private static void initialiseQuestionsPreInstall(InstallData installData,UserInput userInput) {
		userInput.appendQuestion("Please re-enter your Unix username: ");
		userInput.appendQuestion("Please enter the name of your taskwarrior organization (Standard organisation is called:Public):");
		userInput.appendQuestion("Please enter the name of this taskwarrior user: (Standard username is called:First");
		userInput.appendQuestion("Will you use taskwarrior on multiple devices (eg. a pc and android phone)?(y/n)");
		userInput.appendQuestion("Is this your server or client pc?(server/client)");
		userInput.appendQuestion("Please enter the ddns of the website that you host with this pc. (e.g. duckduckgo.life becomes:duckduckgo.ddns.net)");
		//userInput[6] = reader.next();
		
		askQuestions(installData, userInput);
	}
	
	/**
	 * assumption: maintains question order and content of QuestionSetV2.
	 * @param userInput
	 */
	private static void askQuestions(InstallData installData, UserInput userInput) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		String answer =null;
		for (int i = 0; i <userInput.getQuestions().size();i++) {
			boolean correctFormat = false;
			while (!correctFormat) {
				System.out.println(userInput.getQuestions().get(i));
				answer = reader.next();
				correctFormat=checkUserFormat(i,answer);
			}
			userInput.appendAnswer(answer);
			if (i==3 && answer.equals("n")) { //if use 1 device, always server so skip last 2 questions.
				userInput.appendAnswer("server");
				userInput.appendAnswer("0.0.0.0");
				System.out.println("Answers="+String.join(",", userInput.getAnswer()));
				i = userInput.getQuestions().size()+1; 
			}
			
		}
		reader.close();
		installData.setUserInput(userInput);
	}
	
	/**
	 * TODO: Test method
	 * TODO: add in code tests(not unit tests) in cases that return true by default.
	 * @param i
	 * @param answer
	 * @return
	 */
	private static boolean checkUserFormat(int i, String answer) {
		switch (i) {
			case 0: {
				return true;
			}
			case 1: {
				return true;
			}
			case 2: {
				return true;
			}
			case 3: {
				if (answer.equals("y")) {
					return true;
				}else if (answer.equals("n")) {
					return true;
				}else {
					return false;
				}
			}
			case 4: {
				if (answer.equals("server")) {
					return true;
				}else if (answer.equals("client")) {
					return true;
				}else {
					return false;
				}
			}
			case 5:{
				return true;
			}
		}
		return false;
	}
	
	public static String requestDriveLetter() {
		String driveLetter=null;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("I would like to make a folder to copy your certificates and backups to, so that you can easily copy them to your other devices."+'\n'+'\n'+"Can you please enter the letter of the harddrive in which you want this folder?");
		driveLetter = reader.next(); // Scans the next token of the input as an int.
		return driveLetter;
	}
	
	public static void promptReboot() {
		String response=null;
		
		boolean confirmedReboot = false;
		while (!confirmedReboot) {
			System.out.println("Installation is completed. Before you continue you need to restart WSL Ubuntu (start>ubuntu). Please confirm with y.");
			//System.exit(0);
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			response = reader.next();
			if (response.equals("y")) {
				confirmedReboot = true;
				Main.exitUbuntu();
			}
		}
	}
}
