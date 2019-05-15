package autoInstallTaskwarrior;

import java.io.File;

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
		boolean[] checkAllFiles = new boolean[installData.getSyncCertificateNames().length+1];
		// check if certificates exist in certificatesInput folder
//		System.out.println("alltrue="+areAllTrue(checkAllFiles));
		while (!areAllTrue(checkAllFiles)) {
			for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
				checkAllFiles[i] = CreateFiles.checkIfFileExist(installData.getCertificateInputPath(),
						installData.getSyncCertificateNames()[i]);
			}
			System.out.println("Checking: whether twUuid.txt exits="+installData.getTwUuidFileName());
			checkAllFiles[installData.getSyncCertificateNames().length] = CreateFiles.checkIfFileExist(installData.getCertificateInputPath(),
					installData.getTwUuidFileName());
			if (!areAllTrue(checkAllFiles)) {
				requestCertificatesInput(installData);
			}
		}
	}

	public static void requestCertificatesInput(InstallData installData) {
		String desiredAnswer = "y";
		StringBuilder sb = new StringBuilder();
		sb.append('\n'+"In your server-device, you can find the required certificates in:" + '\n');
		sb.append(installData.getCertificateOutputPath() + '\n');
		sb.append("Please copy those 3 certificate files and the txt file with the taskwarrior server uuid:" + '\n' + '\n');
		for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
			sb.append(installData.getSyncCertificateNames()[i] + '\n');
			sb.append(installData.getServerTwUuid()+'\n');
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
				CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName, destinationPath,
						destinationFileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
