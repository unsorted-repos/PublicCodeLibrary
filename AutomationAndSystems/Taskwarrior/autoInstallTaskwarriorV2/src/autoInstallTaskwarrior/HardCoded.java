/**
 * 
 */
package autoInstallTaskwarrior;

/**
 * @author a
 *
 */
public class HardCoded {

	public static InstallData hardCoded() {
		String getWslOsOutput;
		InstallData installData = new InstallData();
		// TODO: Turn off developer mode before publishing!
		installData.setDevelopeMode(false);

		// hardcoded
//		String[] storeUserInput =;
		AskUserInput.getUserInput(installData);
		installData.setTestrun(false);
		installData.setVars("vars");
		installData.setServerPort("53589");
		installData.setMaintenanceFolder("maintenance");
		installData.setSortScriptName("JavaServerSort.jar");

		installData.setInternalBackupScriptPath("resource/");
		installData.setInternalBackupScriptName("autoBackup.sh");
		installData.setBackupScriptName("autoBackup.sh");

		installData.setBackupDestination("/home/" + installData.getLinuxUserName() + "/maintenance/");
		installData.setCustomSortScriptName("JavaServerSort.jar");
		installData.setBasrcFileName(".bashrc");
		installData.setBashrcPath("/home/" + installData.getLinuxUserName());
		installData.setVisudoFileName("sudoers.sh");
		installData.setVisudoPath("/home/" + installData.getLinuxUserName() + "/maintenance/");
		installData.setSudoersFileName("sudoers.sh");
		installData.setgCalSyncInstallScriptName("gCalSyncInstaller.sh");

		// get the path of this compiled .jar file
		installData.setLinuxPath(GetThisPath.getJarLocation()[0]);

		String[] syncCertificateNames = new String[3];
		syncCertificateNames[0] = "ca.cert.pem";
		syncCertificateNames[1] = installData.getTwUserName() + ".cert.pem";
		syncCertificateNames[2] = installData.getTwUserName() + ".key.pem";
		installData.setSyncCertificateNames(syncCertificateNames);
		installData.setTwUuidFileName("twUuid.txt");
		installData.setUuidLength(36);
		
		String[] restoreBackupNames = new String[3];
		restoreBackupNames[0] = "backlog.data";
		restoreBackupNames[1] = "completed.data";
		restoreBackupNames[2] = "pending.data";
		installData.setRestoreBackupNames(restoreBackupNames);	
		installData.setTwDataFolderName(".task");
		
//		installData.getUserInput()[2]="Public";
//		installData.getUserInput()[3]="First";

		// when it's run in linux it automatically returns linux path. (No need for
		// conversion)
		// String linuxPath = getThisPath.getJarLocation()[1];

		// TODO: Set to false before you publish so that users don't lose their other tw
		// users!
		// TODO: Add question at start asking if they want to do a clean install/delete
		// other tw users.
		installData.setDeleteOtherTwUsers(true);

		// TODO: Ask if user wants to use customSorting
		// set cronjobs
		int nrOfCronJobs = 2;
		CronJob[] cronJobs = new CronJob[nrOfCronJobs];
		String[] cronTiming = new String[nrOfCronJobs];
		String[] cronCommand = new String[nrOfCronJobs];
		String[] cronPath = new String[nrOfCronJobs];
		String[] cronFileName = new String[nrOfCronJobs];
		boolean[] useThisCronInClientScenario = new boolean[nrOfCronJobs];

		// set 1st cronjob:*/1 * * * * root sh -v /home/a/maintenance/autoBackup.sh
		cronTiming[0] = "*/1 * * * *";
		cronCommand[0] = "sudo sh -v";
		cronPath[0] = "/home/a/maintenance/";
		cronFileName[0] = "autoBackup.sh";
		useThisCronInClientScenario[0] = true;

		// set 2nd cronjob:*/10 * * * * root sh -v /home/a/maintenance/customSort.sh
		cronTiming[1] = "*/10 * * * *";
		cronCommand[1] = "sudo java -jar";
		cronPath[1] = "/home/a/maintenance/";
		cronFileName[1] = "JavaServerSort.jar";
		useThisCronInClientScenario[1] = false;

		for (int i = 0; i < nrOfCronJobs; i++) {
			cronJobs[i] = new CronJob(cronTiming[i], cronCommand[i], cronPath[i], cronFileName[i], useThisCronInClientScenario[i]);
		}

		installData.setCronJobs(cronJobs);

		// create the installation folders for taskwarrior
		CreateFolders.findHardDrive(installData);
		
//		System.out.println("isImportCertificates = "+installData.isImportCertificates());
//		if (installData.isImportCertificates()) {ImportFiles.importCertificates(installData);}
		if (!installData.isUseSingleDevice() && !installData.isServer()) {
			ImportFiles.checkImportCertificates(installData);
			installData = AskUserInput.importTaskUuid(installData);
		}
		if (installData.isRestoreBackup()) {
			ImportFiles.checkImportBackups(installData);
		}
		
		// store OS:
		getWslOsOutput = getWslOs();
		if (getWslOs().contains("18.04") && getWslOs().contains("bionic")) {
			installData.setWslOs("Ubuntu 18.04");
		}else if (getWslOs().contains("16.04") && getWslOs().contains("xenial")) {
			installData.setWslOs("Ubuntu 16.04");
		}
		
		installData.setgCalSyncCloneFolder("taskw_gcal_sync");
		
		return installData;
	}

	public static void initializeOutputFolder(InstallData installData) {
		installData.setOutputPath("/mnt/" + installData.getOutputFolderDriveLetter() + "/" + "Taskwarrior" + "/");
		// TODO: check whether setBackupDestination is used as a reference to the
		// autoBacckup.sh file, or as the actual destination of the backups.
		// in case of the latter, it is conflicting with the setBackupOutputFolderName
		installData.setBackupInputPath(installData.getOutputPath() + "backupsInput" + "/");
		installData.setBackupOutputPath(installData.getOutputPath() + "backupsOutput" + "/");
		installData.setCertificateOutputPath(installData.getOutputPath() + "certificatesOutput" + "/");
		installData.setCertificateInputPath(installData.getOutputPath() + "certificatesInput" + "/");
	}

	/**
	 * Checks what the operating system is and returns it as a string.
	 * @return
	 * @throws Exception
	 */
	public static String getWslOs() {
		Command command = new Command();
		String[] commandLines = new String[2];
		commandLines[0] = "lsb_release";
		commandLines[1] = "-irc";
		command.setCommandLines(commandLines);
//		command.setEnvVarContent("/var/taskd");
//		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("");
		command.setSetEnvVar(false);
		command.setSetWorkingPath(false);	
		command.setGetOutput(true);

		// execute command to create destination folder
		try {
			return RunCommandsV3.executeCommands(command, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
