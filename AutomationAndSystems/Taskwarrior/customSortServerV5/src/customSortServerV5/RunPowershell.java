package customSortServerV5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RunPowershell {
	

	 public static String runPowershell(String command, boolean getOutput){
		 StringBuilder sb = new StringBuilder();
		 
	  // Executing the command
	  Process powerShellProcess;
		try {
			powerShellProcess = Runtime.getRuntime().exec(command);
		
		  // Getting the results
		  powerShellProcess.getOutputStream().close();
		  String line;
//		  System.out.println("Standard Output:");
		  BufferedReader stdout = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getInputStream()));
		  while ((line = stdout.readLine()) != null) {
//		   System.out.println(line);
		   if (getOutput) {
			   sb.append(line);
		   }
		  }
		  stdout.close();
//		  System.out.println("Standard Error:");
		  BufferedReader stderr = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getErrorStream()));
		  while ((line = stderr.readLine()) != null) {
//		   System.out.println(line);
		  }
		  stderr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		  System.out.println("Done");
		
		return sb.toString();

	}
	 
	public static String powershellCommand(HardCoded hardCoded ) {
		String launcherPath = hardCoded.getWindowsPath()+"/src/"+ hardCoded.getTestDataFolder()+"/"+hardCoded.getTestWslLaunchersFolder()+"/";
		launcherPath = hardCoded.swapSlashes(launcherPath);
		String launcherName = hardCoded.getWslLauncherScriptName();		
		String command1 = "powershell.exe & '"+launcherPath+launcherName+"' "; //
//		System.out.println("Launcher command ="+command1);
		return command1;
	}
	
	
	
	
}
