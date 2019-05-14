package autoInstallTaskwarrior;

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
		int nrOfCertificatesToImport = 3;
		boolean[] checkAllFiles = new boolean[3];
		// check if certificates exist in certificatesInput folder
		while (areAllTrue(checkAllFiles)) {
			for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
				checkAllFiles[i] = CreateFiles.checkIfFileExist(installData.getCertificateInputPath(),
						installData.getSyncCertificateNames()[i]);
			}
			if (!areAllTrue(checkAllFiles)) {
				requestCertificatesInput(installData);
			}
		}
	}
	
	public static void requestCertificatesInput(InstallData installData) {
		StringBuilder sb = new StringBuilder();
		sb.append("In your server-device, you can find the required certificates in:"+'\n');
		sb.append(installData.getCertificateOutputPath()+'\n');
		sb.append("Please copy those 3 certificate files:"+'\n'+'\n');
		for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
			sb.append(installData.getSyncCertificateNames()[i]+'\n');
		}
		sb.append("to path:"+'\n');
		sb.append(installData.getCertificateInputPath()+'\n');
		sb.append("and confirm with y if you have done so.");
		String question = sb.toString();
	}
	
	public static boolean areAllTrue(boolean[] array)
	{
	    for(boolean b : array) if(!b) return false;
	    return true;
	}
	
	public static void importCertificates(InstallData installData) {
		
	}
}