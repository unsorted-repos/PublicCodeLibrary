package customSortServerV5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RunPowershell {
	

	 public static void runPowershell(){

	  //String command = "powershell.exe  your command";
	  //Getting the version
//	  String command = "powershell.exe  $PSVersionTable.PSVersion";
		 String command = powershellCommand();
	  // Executing the command
	  Process powerShellProcess;
		try {
			powerShellProcess = Runtime.getRuntime().exec(command);
		
		  // Getting the results
		  powerShellProcess.getOutputStream().close();
		  String line;
		  System.out.println("Standard Output:");
		  BufferedReader stdout = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getInputStream()));
		  while ((line = stdout.readLine()) != null) {
		   System.out.println(line);
		  }
		  stdout.close();
		  System.out.println("Standard Error:");
		  BufferedReader stderr = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getErrorStream()));
		  while ((line = stderr.readLine()) != null) {
		   System.out.println(line);
		  }
		  stderr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println("Done");


	}
	 
	public static String powershellCommand() {
		String launcherPath = HardCoded.getWindowsPath()+"/src/"+ HardCoded.getTestDataFolder()+"/"+HardCoded.getTestWslLaunchersFolder()+"/";
		launcherPath = swapSlashes(launcherPath);
		String launcherName = HardCoded.getWslLauncherScriptName();		
		String command1 = "powershell.exe & '"+launcherPath+launcherName+"' "; //
		return command1;
	}
	
	public static String swapSlashes(String line) {
		// swap backwards slashes to forward slashes
		line = line.replace("\\", "/");
		return line;
	}
}
