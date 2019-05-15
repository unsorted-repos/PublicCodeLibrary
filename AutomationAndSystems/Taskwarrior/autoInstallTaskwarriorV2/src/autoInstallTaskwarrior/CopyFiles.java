/**
 * 
 */
package autoInstallTaskwarrior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {

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
	public static void exportResource(InstallData installData, String fileName, boolean runnable) throws Exception {
		// declare copy and paste locations
		File internalFile = CopyFiles.getResourceAsFile(installData.getInternalBackupScriptPath() + fileName);
		System.out.println("Finding file=" + fileName);
		String sourceFileName = internalFile.getName();
		String sourcePath = internalFile.getPath().substring(0,
				internalFile.getPath().length() - sourceFileName.length());
		String destinationPath = installData.getBackupDestination();
		String destinationFileName = installData.getBackupScriptName();

		if (CopyFiles.getResourceAsFile("resource/" + fileName) != null) {
			System.out.println("find the exported file one in:"
					+ CopyFiles.getResourceAsFile("resource/" + fileName).getAbsolutePath());

			// copy internal file to external folder
			System.out.println("And copied it to:" + destinationPath + fileName);
			CopyFiles.copyFileWithSudo(installData, sourcePath, sourceFileName, destinationPath, fileName);

			// make .sh runnable
			if (runnable) {
				CreateFiles.makeScriptRunnable(destinationPath, destinationFileName);
			}
		}
	}

	/**
	 * Create a file instance to copy a temporary file. In human words: The file
	 * that is contained in the resource folder of this eclipse project is put into
	 * a java "File" object. That means it gets a different name and the absolute
	 * path/directory of that file becomes something like: /tm/234234.tmp Since you
	 * can apply the copy command to that file you can copy it to an actual existing
	 * destination folder.
	 * 
	 * @param resourcePath
	 * @return
	 */
	public static File getResourceAsFile(String resourcePath) {
		try {
			InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
			if (in == null) {
				System.out.println("In=null");
				return null;
			}

			File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
			tempFile.deleteOnExit();

			try (FileOutputStream out = new FileOutputStream(tempFile)) {
				// copy stream
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
			}
			return tempFile;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR");
			return null;
		}
	}

	/**
	 * copy the temporary file that is read from recourses
	 * 
	 * @param args
	 */
	public static void copyFile(File file, String filePath, String fileName) {
		InputStream inStream = null;
		OutputStream outStream = null;
		try {

			File file2 = new File(filePath + fileName);

			inStream = new FileInputStream(file);
			outStream = new FileOutputStream(file2); // for override file content
			// outStream = new FileOutputStream(file2,<strong>true</strong>); // for append
			// file content

			byte[] buffer = new byte[1024];

			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();

			System.out.println("File Copied to:" + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	public static void copyFileWithSudo(InstallData installData, String sourcePath, String sourceFileName,
			String destinationPath, String destinationFileName) throws Exception {

		// create destination folder if it does not exist
		makeDestinationFolder(destinationPath);

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
	 * The destination path is only made if it does not yet exist. If it does exist
	 * and contains files, those files are preserved.
	 * 
	 * @param destinationPath
	 * @throws Exception
	 */
	public static void makeDestinationFolder(String destinationPath) throws Exception {
		Command command = new Command();
		String[] commandLines = new String[4];
		commandLines[0] = "sudo";
		commandLines[1] = "mkdir";
		commandLines[2] = "-p";
		commandLines[3] = destinationPath;
		command.setCommandLines(commandLines);
		command.setEnvVarContent("/var/taskd");
		command.setEnvVarName("TASKDDATA");
		command.setWorkingPath("");
		command.setSetWorkingPath(false);

		// execute command to create destination folder
		RunCommandsV3.executeCommands(command, false);
	}

	/**
	 * Copies the files cp -a /mnt/c/twUpload/ca.cert.pem cp -a
	 * /mnt/c/twUpload/First.cert.pem cp -a /mnt/c/twUpload/First.key.pem to the
	 * folder <your detected/selected harddrive>:/taskwarrior/certificatesOutput/
	 * 
	 * cp -a /mnt/c/twUpload/ca.cert.pem ~/.task/ cp -a
	 * /mnt/c/twUpload/First.cert.pem ~/.task/ cp -a /mnt/c/twUpload/First.key.pem
	 * ~/.task/
	 * 
	 * @param installData
	 */
	public static void exportServerCertificates(InstallData installData) {
		System.out.println("your output folder is located in=" + installData.getOutputPath());
		
		String sourcePath = "/home/" + installData.getLinuxUserName() + "/.task/";
		String destinationPath = installData.getCertificateOutputPath();

		for (int i = 0; i < installData.getSyncCertificateNames().length; i++) {
			try {
				CopyFiles.copyFileWithSudo(installData, sourcePath, installData.getSyncCertificateNames()[i],
						destinationPath, installData.getSyncCertificateNames()[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CreateFiles.exportTwUuid(installData);
	}
}
