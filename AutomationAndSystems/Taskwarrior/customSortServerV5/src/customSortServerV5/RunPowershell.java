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
//		char AND = (char) 38; // and &
//		char quotation = (char) 34; // quotation mark "
//		String command = "powershell.exe  \"C:\\wslLauncher2.ps1\" "; // Verified
//		String command0 = "powershell.exe  \"C:/wslLauncher2.ps1\" "; // Verified
//		String command2 = "powershell.exe  \"E:/wslLauncher2.ps1\" "; // Verified
//		String command3 = "powershell.exe  \"E:/testFolder/wslLauncher2.ps1\" "; // Verified
//		String command4 = "powershell.exe  \"E:/test Folder/wslLauncher2.ps1\" "; // Verified
		String launcherPath = HardCoded.getWindowsPath()+"/src/"+ HardCoded.getTestDataFolder()+"/"+HardCoded.getTestWslLaunchersFolder()+"/";
		launcherPath = swapSlashes(launcherPath);
		String launcherName = HardCoded.getWslLauncherScriptName();
		
//		String command1 = "powershell.exe  \""+launcherPath+launcherName+"\""; //
		String command1 = "powershell.exe & '"+launcherPath+launcherName+"' "; //
		
		
		System.out.println("Running:"+command1);
//		System.out.println("1:"+command1);
//		System.out.println("2:"+command2);
//		System.out.println("3:"+command3);
//		System.out.println("4:"+command4);
		return command1;
	}
	
	public static String swapSlashes(String line) {
		// swap backwards slashes to forward slashes
		line = line.replace("\\", "/");
		return line;
	}
}
