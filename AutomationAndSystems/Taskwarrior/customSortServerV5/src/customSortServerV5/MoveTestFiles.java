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

	/**
	 * Constructor to be able to pass the MoveTestFiles object with it's
	 * accompanying files.
	 */
	MoveTestFiles(HardCoded hardCoded) {
		importOriginalBacklog(hardCoded);
		importOriginalPending(hardCoded);
	}

	/**
	 * Imports the original/current backlog.data file of taskwarrior and stores it
	 * internally.
	 */
	public void importOriginalBacklog(HardCoded hardCoded) {
		System.out.println("absorbing:"+hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
//		this.backlogOriginal = getResourceAsFile(hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
		this.backlogOriginal = new File(hardCoded.getUbuntuFilePath() + hardCoded.getBacklogFileName());
		System.out.println("originalBacklogImported="+this.backlogOriginal);

	}

	/**
	 * Imports the original/current pending.data file of taskwarrior and stores it
	 * internally.
	 */
	public void importOriginalPending(HardCoded hardCoded) {
		this.pendingOriginal = new File(hardCoded.getUbuntuFilePath() + hardCoded.getPendingFileName());
	}

	/*
	 * restores the original backlog.data once testing is completed by exporting it
	 * to ~/.task/backlog.data
	 */
	public void restoreOriginalBacklog(HardCoded hardCoded) {
		String destinationPath =hardCoded.getUbuntuFilePath() ;
		String destinationFileName = hardCoded.getBacklogFileName();
		exportResource(this.backlogOriginal,destinationPath,destinationFileName,false);
	}

	/*
	 * restores the original pending.data once testing is completed by exporting it
	 * to ~/.task/pending.data
	 */
	public void restoreOriginalPending(HardCoded hardCoded) {
		String destinationPath =hardCoded.getUbuntuFilePath() ;
		String destinationFileName = hardCoded.getPendingFileName();
		exportResource(this.pendingOriginal,destinationPath,destinationFileName,false);
	}

	/*
	 * exports the custom created test file.
	 */
	public void exportTestFile(HardCoded hardCoded, File mockTestFile, String destinationFileName) {
		String destinationPath = hardCoded.getUbuntuFilePath() ;
		exportResource(this.pendingOriginal,destinationPath,destinationFileName,false);
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
	public static void exportResource(File internalFile, String destinationPath, String destinationFileName, boolean runnable){
		// declare copy and paste locations
		System.out.println("Exporting file=" + destinationFileName);
		System.out.println("internalFile"+internalFile);
		String sourceFileName = internalFile.getName();
		String sourcePath = internalFile.getPath().substring(0,
				internalFile.getPath().length() - sourceFileName.length());
		
		//TODO: add nullcheck
		copyFileWithSudo(sourcePath, sourceFileName, destinationPath, destinationFileName);
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
