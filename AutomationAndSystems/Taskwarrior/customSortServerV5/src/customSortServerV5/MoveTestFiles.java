package customSortServerV5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;


public class MoveTestFiles {
	private String internalOriginalBacklogFilePath;
	private String internalOriginalPendingFilePath;
	private String internalTestBacklogFilePath;
	private String internalTestPendingFilePath;
	private File backlogOriginal;
	private File pendingOriginal;
	private File tempInternalBacklogOriginal;
	
	
	public File returnTempBacklogFile(HardCoded hardCoded) {
		String beforeDot = splitFilenameOnDot(hardCoded.getBacklogFileName())[0];
		String dotAndExtension = splitFilenameOnDot(hardCoded.getBacklogFileName())[1];
		File tempFile;
		try {
			tempFile = File.createTempFile(beforeDot,dotAndExtension);
			return tempFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//File tempFile = File.createTempFile("MyAppName-", ".tmp");
//		tempFile.deleteOnExit();
		return null;
	}
	
	public String[] splitFilenameOnDot(String filename) {
		String[] splittedFilename = new String[2];
		splittedFilename[0]=filename.substring(0, filename.indexOf("."));
		splittedFilename[1]=filename.substring(filename.indexOf("."),filename.length());
		return splittedFilename;
	}
	
	/**
	 * Constructor to be able to pass the MoveTestFiles object with it's
	 * accompanying files.
	 */
	MoveTestFiles(HardCoded hardCoded) {
		importOriginalBacklog(hardCoded);
		importOriginalPending(hardCoded);
	}
	
	public void importOriginalBacklog(HardCoded hardCoded) {
		this.backlogOriginal = new File(hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
		importOriginalDataFiles(hardCoded,hardCoded.getBacklogFileName());
	}

	/**
	 * TODO: change like backlog to win in linux commmand.
	 * Imports the original/current pending.data file of taskwarrior and stores it
	 * internally.
	 */
	public void importOriginalPending(HardCoded hardCoded) {
		this.pendingOriginal = new File(hardCoded.getUbuntuFilePath() + hardCoded.getPendingFileName());
		importOriginalDataFiles(hardCoded,hardCoded.getPendingFileName());
	}
		
	/**
	 * TODO: change backlog importation from linux path without 
	 * Imports the original/current backlog.data file of taskwarrior and stores it
	 * internally.
	 */
	public void importOriginalDataFiles(HardCoded hardCoded, String dataFilename) {
		System.out.println("absorbing:"+hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
//		this.backlogOriginal = getResourceAsFile(hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
		this.backlogOriginal = new File(hardCoded.getUbuntuFilePath() + dataFilename);
		
		String sourcePath = hardCoded.getUbuntuFilePath();
		String sourceFilename = dataFilename;
		String destinationPath = hardCoded.getLinuxPath()+"src/"+hardCoded.getTestDataFolder()+"/"+hardCoded.getTestOriginalDataFolderName()+"/";
		String destinationFilename = dataFilename;
		
		// create originalDataFiles
		CreateFolders.createFolderWithEclipse(hardCoded.getWindowsPath()+"src/"+hardCoded.getTestDataFolder()+"/"+hardCoded.getTestOriginalDataFolderName()+"/");
		
		// copy backlog from wsl to this project in win using powershell
		copyFilesFromWinInLinux(hardCoded,sourcePath,sourceFilename, destinationPath, destinationFilename, false);
		System.out.println("IMPORTED BACKLOG!-");
	}



	/*
	 * restores the original backlog.data once testing is completed by exporting it
	 * to ~/.task/backlog.data
	 */
	public void restoreOriginalBacklog(HardCoded hardCoded) {
		String destinationPath =hardCoded.getUbuntuFilePath() ;
		String destinationFileName = hardCoded.getBacklogFileName();
		manageCopyFilesFromWinInLinux(hardCoded, this.backlogOriginal,destinationPath,destinationFileName,false);
	}

	/*
	 * restores the original pending.data once testing is completed by exporting it
	 * to ~/.task/pending.data
	 */
	public void restoreOriginalPending(HardCoded hardCoded) {
		String destinationPath =hardCoded.getUbuntuFilePath() ;
		String destinationFileName = hardCoded.getPendingFileName();
		manageCopyFilesFromWinInLinux(hardCoded,this.pendingOriginal,destinationPath,destinationFileName,false);
	}

	/*
	 * exports the custom created test file.
	 */
	public void exportTestFile(HardCoded hardCoded, File mockTestFile, String destinationFileName) {
		String destinationPath = hardCoded.getUbuntuFilePath() ;
		manageCopyFilesFromWinInLinux(hardCoded,this.pendingOriginal,destinationPath,destinationFileName,false);
	}

	/*
	 * exports the custom created test file named pending.data to
	 * ~/.task/pending.data
	 */
	public void exportTestPending(String internalTestFileName) {

	}

//	public static void makeDestinationFolder(String destinationPath) {
//		Command command = new Command();
//		String[] commandLines = new String[4];
//		commandLines[0] = "sudo";
//		commandLines[1] = "mkdir";
//		commandLines[2] = "-p";
//		commandLines[3] = destinationPath;
//		command.setCommandLines(commandLines);
//		command.setEnvVarContent("/var/taskd");
//		command.setEnvVarName("TASKDDATA");
//		command.setWorkingPath("");
//		command.setSetWorkingPath(false);
//
//		// execute command to create destination folder
//		RunCommandsV3.executeCommands(command, false);
//	}

	
	/**
	 * First it creates the destination folder if it doesn't exist yet. Then it
	 * copies the file from resource to the destination path.
	 * 
	 * @param installData
	 * @param sourcePath
	 * @param sourceFileName
	 * @param destinationPath
	 * @param destinationFileName
	 * @throws Exception
	 */
	public static void copyFileWithSudo(String sourcePath, String sourceFileName, String destinationPath,
			String destinationFileName) {

		// create copy command
		Command command = new Command();
		String[] commandLines = new String[4];
		commandLines[0] = "sudo";
		commandLines[1] = "cp";
		commandLines[2] = sourcePath + sourceFileName;
		commandLines[3] = destinationPath + destinationFileName;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("/usr/share/taskd/pki");

		// execute command to copy file
		RunCommandsV3.executeCommands(command, false);
	}
	
	/**
	 * TODO: at internalFile replace path with path dependent on fileName with
	 * switchstatement This method orchestrates exporting a file from resources to
	 * an external folder. After copying the file to the destination path, the file
	 * is made runnable with chmod.
	 * 
	 * @param installData
	 * @param fileName
	 * @throws Exception
	 */
	public static void manageCopyFilesFromWinInLinux(HardCoded hardCoded,File internalFile, String destinationPath, String destinationFilename, boolean runnable){
		char quotation = (char) 34; // quotation mark "
		// declare copy and paste locations
		String sourceFilename = internalFile.getName();
		String sourcePath = internalFile.getPath().substring(0,
				internalFile.getPath().length() - sourceFilename.length());
		copyFilesFromWinInLinux(hardCoded,sourcePath,sourceFilename,destinationPath,destinationFilename, runnable);
		}
	
	public static void copyFilesFromWinInLinux(HardCoded hardCoded,String sourcePath,String sourceFilename, String destinationPath, String destinationFileName, boolean runnable){
		char quotation = (char) 34; // quotation mark "
		// first remove target file before copying
		removeTargetFile(hardCoded,destinationPath+destinationFileName);
		
		//TODO: add nullcheck
		String[] copyCommand = new String[1];

//		copyCommand[0] = "cp "+quotation+sourcePath+sourceFilename+quotation+" "+destinationPath+destinationFileName;
		copyCommand[0] = "cp "+quotation+sourcePath+sourceFilename+quotation+" "+quotation+destinationPath+destinationFileName+quotation;
		copyCommand[0] = hardCoded.slashDirToRight(copyCommand[0]);
		RunLinuxCommandsFromWin.runLinuxCommandFromWindows(hardCoded,copyCommand);
		
		System.out.println("RAN:"+copyCommand[0]);
		}
	
	public static void removeTargetFile(HardCoded hardCoded,String targetPathWithFileName) {
		char quotation = (char) 34; // quotation mark "
		String[] copyCommand = new String[1];
		copyCommand[0] = "rm "+quotation+targetPathWithFileName+quotation;
		copyCommand[0] = hardCoded.slashDirToRight(copyCommand[0]);
		RunLinuxCommandsFromWin.runLinuxCommandFromWindows(hardCoded,copyCommand);
		System.out.println("RAN:"+copyCommand[0]);
	}
	public static void importResource(HardCoded hardCoded,String sourcePath,String sourceFileName, String destinationPath, String destinationFileName, boolean runnable){
		char quotation = (char) 34; // quotation mark "
		//TODO: add nullcheck
		
		// first remove target file before copying
		removeTargetFile(hardCoded,destinationPath+destinationFileName);
		String[] copyCommand = new String[1];
		copyCommand[0] = "cp "+quotation+sourcePath+sourceFileName+quotation+" "+quotation+destinationPath+destinationFileName+quotation;
		RunLinuxCommandsFromWin.runLinuxCommandFromWindows(hardCoded,copyCommand);
		System.out.println(copyCommand[0]);
//		copyFileWithSudo(sourcePath, sourceFileName, destinationPath, destinationFileName);
	}
	
	public static void startWSL() {
		ProcessBuilder pb = new ProcessBuilder("wsl");
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			Thread.sleep(10000);
			OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
			osw.write("ls\n");
			osw.write("echo hi\n");
			osw.flush();	
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}