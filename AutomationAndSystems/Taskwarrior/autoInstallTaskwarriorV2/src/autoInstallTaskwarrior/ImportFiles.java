package autoInstallTaskwarrior;

import java.io.File;
import java.util.ArrayList;

public class ImportFiles {

	/**
	 * This method first checks if the input folder for certificates exists Then
	 * checks whether the certificates are located in the input folder if not, get
	 * in loop prompting the user to put them in the certificatesInput if found,
	 * they are copy pasted to the ./task folder at the end of installation
	 * 
	 */
	public static void checkImportCertificates(InstallData installData) {
		// check if certificatesInput folder exists, create it if it does not exist.
		System.out.println("The certificateInputPath =" + installData.getCertificateInputPath());
		if (!CreateFolders.checkIfFolderExists(installData.getCertificateInputPath())) {
			CreateFolders.createOutputFolder(installData, installData.getCertificateInputPath());
		}
		checkIfCertificatesExist(installData);
	}

	/**
	 * Checks whether the certificates exist in the folder certificatesInput of the
	 * taskwarrior working directory
	 * 
	 * @param installData
	 * @return
	 */
	private static void checkIfCertificatesExist(InstallData installData) {
		boolean[] checkAllFiles = new boolean[installData.getSyncCertificateNames().length + 1];
		// check if certificates exist in certificatesInput folder
//		System.out.println("alltrue="+areAllTrue(checkAllFiles));
		while (!areAllTrue(checkAllFiles)) {
			for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
				checkAllFiles[i] = CreateFiles.checkIfFileExist(installData.getCertificateInputPath(),
						installData.getSyncCertificateNames()[i]);
			}
			System.out.println("Checking: whether twUuid.txt exits=" + installData.getTwUuidFileName());
			checkAllFiles[installData.getSyncCertificateNames().length] = CreateFiles
					.checkIfFileExist(installData.getCertificateInputPath(), installData.getTwUuidFileName());
			if (!areAllTrue(checkAllFiles)) {
				requestCertificatesInput(installData);
			}
		}
	}

	public static void requestCertificatesInput(InstallData installData) {
		String desiredAnswer = "y";
		StringBuilder sb = new StringBuilder();
		sb.append('\n' + "In your server-device, you can find the required certificates in:" + '\n');
		sb.append(installData.getCertificateOutputPath() + '\n');
		sb.append("Please copy those 3 certificate files and the txt file with the taskwarrior server uuid:" + '\n'
				+ '\n');
		for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
			sb.append(installData.getSyncCertificateNames()[i] + '\n');
			sb.append(installData.getServerTwUuid() + '\n');
		}
		sb.append("to path:" + '\n');
		sb.append(installData.getCertificateInputPath() + '\n');
		sb.append("and confirm with y if you have done so.");
		String question = sb.toString();

		AskUserInput.loopQuestion(question, desiredAnswer);

	}

	public static boolean areAllTrue(boolean[] array) {
		for (boolean b : array)
			if (!b)
				return false;
		return true;
	}

	/**
	 * Checks if you use multiple devices and whether this is a client pc. If both
	 * true, it imports the certificates from <your
	 * hdd>/taskwarrior/certificatesInput to /home/<your linux username>/.task/
	 * 
	 * @param installData
	 */
	public static void importCertificates(InstallData installData) {
		String sourcePath = installData.getCertificateInputPath();
		String destinationPath = "/home/" + installData.getLinuxUserName() + "/.task/";
		for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
			String sourceFileName = installData.getSyncCertificateNames()[i];
			String destinationFileName = sourceFileName;
			try {
				System.out.println("Copying as import:" + sourcePath + sourceFileName + "to:" + destinationPath
						+ destinationFileName);
				CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName, destinationPath,
						destinationFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void checkImportBackups(InstallData installData) {
		// check if certificatesInput folder exists, create it if it does not exist.
		System.out.println("The backupInputPath =" + installData.getBackupInputPath());
		if (!CreateFolders.checkIfFolderExists(installData.getBackupInputPath())) {
			CreateFolders.createOutputFolder(installData, installData.getBackupInputPath());
		}
		checkIfBackupsExist(installData);
	}

	/**
	 * Checks whether the certificates exist in the folder certificatesInput of the
	 * taskwarrior working directory
	 * 
	 * @param installData
	 * @return
	 */
	private static void checkIfBackupsExist(InstallData installData) {
		boolean[] checkAllFiles = new boolean[installData.getRestoreBackupNames().length];
		// check if certificates exist in certificatesInput folder
//		System.out.println("alltrue="+areAllTrue(checkAllFiles));
		while (!areAllTrue(checkAllFiles)) {
			for (int i = 0; i < installData.getRestoreBackupNames().length; i++) {
				checkAllFiles[i] = CreateFiles.checkIfFileExist(installData.getBackupInputPath(),
						installData.getRestoreBackupNames()[i]);
			}
			if (!areAllTrue(checkAllFiles)) {
				requestBackupInput(installData);
			}
		}
	}

	public static void requestBackupInput(InstallData installData) {
		String desiredAnswer = "y";
		StringBuilder sb = new StringBuilder();
		sb.append('\n' + "Please put the following files:" + '\n');

		for (int i = 0; i < installData.getRestoreBackupNames().length; i++) {
			sb.append(installData.getRestoreBackupNames()[i] + '\n');
		}
		sb.append("(undo.data) is not necessary but will be restored if you include it" + '\n');
		sb.append("to path:" + '\n');
		sb.append(installData.getBackupInputPath() + '\n');
		sb.append("and confirm with y if you have done so." + '\n');
		String question = sb.toString();

		AskUserInput.loopQuestion(question, desiredAnswer);

	}

	/**
	 * Imports the .data files from <you hdd>/taskwarrior/backupsInput to
	 * /home/<your linux username>/.task/
	 * 
	 * @param installData
	 */
	public static void importBackups(InstallData installData) {
		String sourcePath = installData.getBackupInputPath();
		String destinationPath = "/home/" + installData.getLinuxUserName() + "/.task/";

		// first import backlog.data (installData.getRestoreBackupNames()[0]
		overWriteOldTwUuidImportedBacklog(installData);

		for (int i = 1; i < installData.getRestoreBackupNames().length; i++) {
			String sourceFileName = installData.getRestoreBackupNames()[i];
			String destinationFileName = sourceFileName;
			try {
				System.out.println("Copying as import:" + sourcePath + sourceFileName + "to:" + destinationPath
						+ destinationFileName);
				CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName, destinationPath,
						destinationFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * If backup data is restored, the backlog.data file contains the tw uuid of the
	 * previous installation. To be able sync with the backed up data, the old tw
	 * uuid needs to be replaced with the new tw uuid in the top line of
	 * backlog.data.
	 * 
	 */
	private static void overWriteOldTwUuidImportedBacklog(InstallData installData) {
		// convert tw uuid to ArrayList
		ArrayList<String> twUuid = new ArrayList<String>();
		twUuid.add(installData.getTwUuid());

		// create temporary backlog.data copy to modify tw uuid before it is imported.
		createTempBacklogCopy(installData);

		// Locate copy of backlog.data file
		String fileName = installData.getRestoreBackupNames()[0] + installData.getCopyText();
		String filePath = installData.getBackupInputPath();
		String destinationPath = "/home/" + installData.getLinuxUserName() + "/" + installData.getTwDataFolderName()
				+ "/";
		String destinationFileName = installData.getRestoreBackupNames()[0];

		ModifyFiles.enforceWriteAccess(filePath, fileName);

		// Check if file exists and substitute old tw uuid with new tw uuid.
		if (CreateFiles.checkIfFileExist(filePath, fileName)) {
			File backlog = new File(filePath + fileName);
			ModifyFiles.removeFirstNLines(filePath, fileName, 1); // remove 1 line from top
			ModifyFiles.prependText(installData, backlog, twUuid);
		}

		// copy the modified file to the new location.
		CopyFiles.copyFileWithSudo(installData, filePath, fileName, destinationPath, destinationFileName);
		System.out.println("Copied:" + filePath + fileName + " to:" + destinationPath + destinationFileName);
	}

	private static void createTempBacklogCopy(InstallData installData) {
		// copy to backlog1.data
		String sourcePath = installData.getBackupInputPath();
		String sourceFileName = installData.getRestoreBackupNames()[0];
		String destinationPath = sourcePath;
		String destinationFileName = sourceFileName + installData.getCopyText();
		CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName, destinationPath, destinationFileName);
		System.out.println("Copied:" + sourcePath + sourceFileName + " to:" + destinationPath + destinationFileName);
	}
}
