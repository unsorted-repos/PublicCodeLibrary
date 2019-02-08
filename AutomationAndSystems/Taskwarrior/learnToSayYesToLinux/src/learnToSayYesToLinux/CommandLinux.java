package learnToSayYesToLinux;

import java.io.*;
import java.util.ArrayList;


public class CommandLinux {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test create a custom UDA
		createUDA("testSortA","tSortA","numeric");

		System.exit(0);
	}

	/**
	 * Method creates a taskwarrior user defined Attribute if the data type is correct
	 * Thows error datatype is not correct.
	 * TODO: write proper exception
	 * @param udaName
	 * @param label
	 * @param type
	 */
	private static void createUDA(String udaName, String label,String type) {
		char vd = (char)124; //vertical dash: |
		char bs = (char)92; //backslash: \		
		String[] commands = new String[2];

		//Check if the datatype is correct for taskwarrior:
		if (type.equals("numeric") || type.equals("string") || type.equals("date") || type.equals("duration")){
			//Does not work:
//			commands[0]="printf 'y"+bs+"n' "+vd+" sudo task config uda."+udaName+".type "+type;
//			commands[1]="printf 'y"+bs+"n' "+vd+" sudo task config uda."+udaName+".label "+ label;			

			//Does not work:
//			commands[0]="printf 'yes yes "+bs+"n' "+vd+" sudo task config uda."+udaName+".type "+type;
//			commands[1]="printf 'yes yes "+bs+"n' "+vd+" sudo task config uda."+udaName+".label "+ label;			

			//Yields an infinite stream of Adding:yes | sudo task config uda.testSortA.type numeric
			//Runs fine with the ignoreOutput boolean on, but it does not actually create the UDA
//			commands[0]="yes yes "+vd+" sudo task config uda."+udaName+".type "+type;
//			commands[1]="yes yes "+vd+" sudo task config uda."+udaName+".label "+ label;			
			
			//Source: https://stackoverflow.com/questions/18866381/how-can-i-run-multiple-commands-in-just-one-cmd-windows-in-java
//			commands[0]="sudo task config uda."+udaName+".type "+type+" & yes";
//			commands[1]="sudo task config uda."+udaName+".label "+ label+ " & yes";
			
			
//			commands[0]="sudo task config uda."+udaName+".type "+type+" & sudo yes";
//			commands[1]="sudo task config uda."+udaName+".label "+ label+ " & sudo yes";
			
			
//			commands[0]="task config uda."+udaName+".type "+type+" & sudo yes";
//			commands[1]="task config uda."+udaName+".label "+ label+ " & sudo yes";
			
			commands[0]="task config uda."+udaName+".type "+type+" & yes";
			commands[1]="task config uda."+udaName+".label "+ label+ " & yes";
			
			
			
//			runCommands(commands[0],false);
//			runCommands(commands[1], false);
			
			runCommands(commands[0],true);
			runCommands(commands[1], true);

			System.out.println("Ran:"+commands[0]);
			System.out.println("Ran:"+commands[1]);
			//Trow exception if the datatype is not correct.
		}else {
			try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public static ArrayList<ArrayList<String>> runCommands(String command,boolean ignoreOutput) {

		String s = null;
		String outputLines=null;
		ArrayList<String> goodExecutionOutput=new ArrayList<String>();
		ArrayList<String> errorExecutionOutput=new ArrayList<String>();
		ArrayList<ArrayList<String>> returnLists = new ArrayList<ArrayList<String>>();

		try {
			// run the Unix "task nice0" command
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader brGood = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// get output
			if (!ignoreOutput) {
				while ((s = brGood.readLine()) != null) {
					System.out.println("Adding:"+s);
					goodExecutionOutput.add(s);
				}

				// get the error message
				while ((s = brError.readLine()) != null) {
					errorExecutionOutput.add(s);
				}	
			}

		}
		catch (IOException e) {
			System.out.println("Error: ");
			e.printStackTrace();
			System.exit(-1);
		}

		//Merge outputLists and return
		returnLists.add(goodExecutionOutput);
		returnLists.add(errorExecutionOutput);
		return returnLists;
	}	
}


//Verify that the commands do actually work when you enter them manually in WSL Ubuntu 16.04:
//printf 'y\n' | sudo task config uda.newTestSort.type numeric
//printf 'y\n' | sudo task config uda.newTestSort.label nTSort
//sudo task 2 modify newTestSort:29
//inspect task 2 whether the modify command changed the description or the new uda newTestSort:
//sudo task 2
//Indeed Lists a new UDA named nTSort with value 29.



