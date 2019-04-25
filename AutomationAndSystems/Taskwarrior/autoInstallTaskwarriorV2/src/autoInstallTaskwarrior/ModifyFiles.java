package autoInstallTaskwarrior;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ModifyFiles {
	/**
	 * TODO: Change prepend to "modifyText" and
	 *     pass a string indicating prepend or append, and merge 
	 *     prepend and append as just a writing method based on fileName.
	 * TODO: Generalize method to switch based on filename. That entails
	 *     renaming methods "prependLines" and "appendLines" based on filenames.
	 * @param installData
	 * @param fileName
	 */
	public static void prependText(InstallData installData, File fileName) {
	    FileOutputStream fileOutputStream =null;

	    BufferedReader br = null;
	    FileReader fr = null;
	    String newFileName = fileName.getAbsolutePath() + "@";

	    try {
	        fileOutputStream = new FileOutputStream(newFileName);
	        fileOutputStream = writeBashrc(installData,fileOutputStream);

	        fr = new FileReader(fileName);
	        br = new BufferedReader(fr);

	        String sCurrentLine;

	        while ((sCurrentLine = br.readLine()) != null) {
	            fileOutputStream.write(("\n"+sCurrentLine).getBytes());
	        }
	        fileOutputStream.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            fileOutputStream.close();
	            if (br != null)
	                br.close();

	            if (fr != null)
	                fr.close();

	            new File(newFileName).renameTo(new File(newFileName.replace("@", "")));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public static void appendText(InstallData installData, File fileName) {
	    FileOutputStream fileOutputStream =null;

	    BufferedReader br = null;
	    FileReader fr = null;
	    String newFileName = fileName.getAbsolutePath() + "@";

	    try {
	        fileOutputStream = new FileOutputStream(newFileName);
	        
	        fr = new FileReader(fileName);
	        br = new BufferedReader(fr);

	        String sCurrentLine;

	        while ((sCurrentLine = br.readLine()) != null) {
	            fileOutputStream.write((sCurrentLine+"\n").getBytes());

	        }
	        
	        fileOutputStream = writeVisudo(installData,fileOutputStream);
	        
	        fileOutputStream.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            fileOutputStream.close();
	            if (br != null)
	                br.close();

	            if (fr != null)
	                fr.close();

	            new File(newFileName).renameTo(new File(newFileName.replace("@", "")));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	/**
	 * Write the lines you wish to prepend to the text file.  
	 * A line/string ending with \n starts a new line.
	 * The last line does not need a \n to make the original text start at a new line.
	 * @param fileOutputStream
	 * @return
	 * @throws IOException
	 */
	private static FileOutputStream writeBashrc(InstallData installData,FileOutputStream fileOutputStream) throws IOException {
		char quotation = (char)34; // quotation mark "
		fileOutputStream.write(("#get root"+"\n").getBytes());
		fileOutputStream.write(("if [ ! -f /home/"+installData.getLinuxUserName()+"/maintenance/getRootBool ]; then"+"\n").getBytes());
		fileOutputStream.write(("   echo "+quotation+"Getting sudo rights now."+quotation+"\n").getBytes());
		fileOutputStream.write(("   sudo touch /home/"+installData.getLinuxUserName()+"/maintenance/getRootBool"+"\n").getBytes());
		fileOutputStream.write(("   sudo -s"+"\n").getBytes());
		fileOutputStream.write(("fi"+"\n").getBytes());
		fileOutputStream.write(("\n").getBytes());

		fileOutputStream.write(("# remove got root boolean for next time you boot up Unix"+"\n").getBytes());
		fileOutputStream.write(("sudo rm /home/" + installData.getLinuxUserName()+"/maintenance/getRootBool"+"\n").getBytes());
		fileOutputStream.write(("\n").getBytes());
		
		fileOutputStream.write(("#Start cron service"+"\n").getBytes());
		fileOutputStream.write(("sudo -i service cron start"+"\n").getBytes());
		fileOutputStream.write(("\n").getBytes());
		
		fileOutputStream.write(("#Startup taskwarrior"+"\n").getBytes());
		fileOutputStream.write(("export TASKDDATA=/var/taskd"+"\n").getBytes());
		fileOutputStream.write(("cd $TASKDDATA"+"\n").getBytes());
		fileOutputStream.write(("sudo taskd config --data $TASKDDATA"+"\n").getBytes());
		fileOutputStream.write(("\n").getBytes());
		
		fileOutputStream.write(("taskdctl start"+"\n").getBytes());
		fileOutputStream.write(("task sync"+"\n").getBytes());
		fileOutputStream.write(("\n").getBytes());
        
		return fileOutputStream;
	}
	
	private static FileOutputStream writeVisudo(InstallData installData, FileOutputStream fileOutputStream) throws IOException {
		//fileOutputStream.write(("\n").getBytes());
		fileOutputStream.write(("zq ALL=(ALL) NOPASSWD: ALL"+"\n").getBytes());
		return fileOutputStream;
	}
}

