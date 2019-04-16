package autoInstallTaskwarrior;

import java.io.IOException;
import java.util.Map;

/**
 * A list of the verified commands (In the comment above each command)
 * is converted into a set of commands that Java can execute (an array of strings).
 * 
 * At the last entry of a command, the accompanying "working path"
 * is specified in method generateCommands.
 * @author a
 *
 */
public class GenerateCommandsV2 {
	public static String[][] generateCommands(boolean testRun,String linuxPath,String vars,String[] storeUserInput,String serverName,String serverPort) {
		String[][] commands = new String[50][1];

		//In combination with Attempt 1:
		if (!testRun) {
			commands[0] = new String[4];
			// commands[0][0] = "yes | sudo apt update";
			commands[0][0] = "yes | sudo";
			commands[0][1] = "apt";
			commands[0][2] = "update";
			commands[0][3] = linuxPath;
					
			// write fillers for commands that are replaced:
			// sudo apt upgrade
			commands[1] = new String[3];
			commands[1][0] = "echo";
			commands[1][1] = "filler";
			commands[1][2] = linuxPath;
			
			// commands[2][0] = "sudo apt install task";	
			commands[2] = new String[5];
			commands[2][0] = "yes | sudo";
			commands[2][1] = "apt";
			commands[2][2] = "install";
			commands[2][3] = "task";
			commands[2][4] = "/home/"+storeUserInput[0];
			
			// commands[3][0] = "sudo apt install taskd";
			commands[3] = new String[5];
			commands[3][0] = "yes | sudo";
			commands[3][1] = "apt";
			commands[3][2] = "install";
			commands[3][3] = "taskd";
			commands[3][4] = linuxPath;
			
			// write fillers for commands that are replaced:
			// cd ..
			commands[4] = new String[3];
			commands[4][0] = "echo";
			commands[4][1] = "filler";
			commands[4][2] = linuxPath;
			
			
			// write fillers for commands that are replaced:
			// cd ..
			commands[5] = new String[3];
			commands[5][0] = "echo";
			commands[5][1] = "filler";
			commands[5][2] = linuxPath;
			
			// write fillers for commands that are replaced:
			// export TASKDDATA=/var/taskd
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			commands[6] = new String[3];
			commands[6][0] = "echo";
			commands[6][1] = "filler";
			commands[6][2] = linuxPath;
			
			// commands[7][0] = "sudo mkdir -p $TASKDDATA";
			// Note: -p creates the parent folders up and until the final folder. 
			// Note: $TASKDDATA=/var/taskd
			// Note: the /var folder should be located in root. Hence, it should be/is an absolute path.
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			// TODO: verify that there is no /var/taskd made in the current working directory/location of the .jar file.
			commands[7] = new String[5];
			commands[7][0] = "sudo";
			commands[7][1] = "mkdir";
			commands[7][2] = "-p";
			commands[7][3] = "/var/taskd";
			commands[7][4] = linuxPath;
			
			// commands[8][0] = "sudo taskd init --data $TASKDDATA";
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			// Verified by checking whether /var/taskd/config did not exist before execution of 
			// this command, and does exist after. Status: Both verified, hence command verified.
			commands[8] = new String[6];
			commands[8][0] = "yes | sudo";
			commands[8][1] = "taskd";
			commands[8][2] = "init";
			commands[8][3] = "--data";
			commands[8][4] = "/var/taskd";
			commands[8][5] = linuxPath;
		}else {
				System.out.println("Skipped some lines for testing");
		}
		
		// Verified till here: substitute: cd /usr/share/taskd/pki
		// cd /usr/share/taskd/pki
		// sudo nano vars
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[9] = new String[4];
		commands[9][0] = "cp";
		commands[9][1] = linuxPath+vars; 
		commands[9][2] = "/usr/share/taskd/pki/";
		commands[9][3] = linuxPath;
		
		/**
		 * dir contains:
		 * /usr/share/taskd/pki$ dir
		 * generate  generate.ca  generate.client  generate.crl  generate.server  vars
		 *
		 * After command ./generate:
		 * usr/share/taskd/pki$ dir
		 * generate  generate.ca  generate.client  generate.crl  generate.server  vars 
		 */
		
		/**
		* substitute sudo ./generate
		* to: sudo /usr/share/taskd/pki//generate
		* The ./generate command means execute "generate" in the current path 
		* source: https://unix.stackexchange.com/questions/249039/what-means-the-dots-on-a-path	 
		* Source1: https://askubuntu.com/questions/74780/how-to-execute-script-in-different-directory
		* The dot does not mean execute, it means current directory 
		* Since the current directory is where this .jar file is located, it does
		* not contain a /usr/share.. path (that only exists in root{unless you specifically made it somewhere else}).
		*/
		// sudo ./generate
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[10] = new String[3];
		commands[10][0] = "sudo";
		commands[10][1] = "/usr/share/taskd/pki/generate";
		commands[10][2] = "/usr/share/taskd/pki/";
		
		/**
		 * TODO: derive where the client.cert.pem is actually generated/located
		 * Or where the installation directory currently would be
		 * and copy the client.cert.pem from that absolute path with for example:
		 * sudo cp /user/someactualdirectory/client.cert.pem "/var/task/"
		 *
		 */
		// sudo cp client.cert.pem $TASKDDATA
		// to: sudo cp client.cert.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[11] = new String[5];
		commands[11][0] = "sudo";
		commands[11][1] = "cp"; 
		//commands[11][2] = "client.cert.pem";
		commands[11][2] = "/usr/share/taskd/pki/client.cert.pem"; //abs
		commands[11][3] = "/var/taskd";
		commands[11][4] = "/usr/share/taskd/pki/";
			
		//		sudo cp client.key.pem $TASKDDATA
		//		to: sudo cp client.key.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[12] = new String[5];
		commands[12][0] = "sudo";
		commands[12][1] = "cp"; 
		//commands[12][2] = "client.key.pem";
		commands[12][2] = "/usr/share/taskd/pki/client.key.pem"; //abs
		commands[12][3] = "/var/taskd";
		commands[12][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.cert.pem $TASKDDATA
		//		to: sudo cp server.cert.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[13] = new String[5];
		commands[13][0] = "sudo";
		commands[13][1] = "cp"; 
		//commands[13][2] = "server.cert.pem";
		commands[13][2] = "/usr/share/taskd/pki/server.cert.pem"; //abs
		commands[13][3] = "/var/taskd";
		commands[13][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.key.pem $TASKDDATA
		//		to: sudo cp server.key.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[14] = new String[5];
		commands[14][0] = "sudo";
		commands[14][1] = "cp"; 
		//commands[14][2] = "server.key.pem";
		commands[14][2] = "/usr/share/taskd/pki/server.key.pem"; //abs
		commands[14][3] = "/var/taskd";
		commands[14][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.crl.pem $TASKDDATA
		//		to: sudo cp server.crl.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[15] = new String[5];
		commands[15][0] = "sudo";
		commands[15][1] = "cp"; 
		//commands[15][2] = "server.crl.pem";
		commands[15][2] = "/usr/share/taskd/pki/server.crl.pem"; //abs
		commands[15][3] = "/var/taskd";
		commands[15][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp ca.cert.pem $TASKDDATA
		//		to: sudo cp ca.cert.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[16] = new String[5];
		commands[16][0] = "sudo";
		commands[16][1] = "cp"; 
		//commands[16][2] = "ca.cert.pem";
		commands[16][2] = "/usr/share/taskd/pki/ca.cert.pem"; //abs
		commands[16][3] = "/var/taskd";
		commands[16][4] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force client.cert $TASKDDATA/client.cert.pem --data $TASKDDATA
//		sudo taskd config --force client.cert /var/taskd/client.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[17] = new String[9];
		commands[17][0] = "sudo";
		commands[17][1] = "taskd"; 
		commands[17][2] = "config";
		commands[17][3] = "--force";
		commands[17][4] = "client.cert";
		commands[17][5] = "/var/taskd/client.cert.pem";
		commands[17][6] = "--data";
		commands[17][7] = "/var/taskd";
		commands[17][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force client.key $TASKDDATA/client.key.pem --data $TASKDDATA
//		sudo taskd config --force client.key /var/taskd/client.key.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[18] = new String[9];
		commands[18][0] = "sudo";
		commands[18][1] = "taskd"; 
		commands[18][2] = "config";
		commands[18][3] = "--force";
		commands[18][4] = "client.key";
		commands[18][5] = "/var/taskd/client.key.pem";
		commands[18][6] = "--data";
		commands[18][7] = "/var/taskd";
		commands[18][8] = "/usr/share/taskd/pki/";
		
		
//		sudo taskd config --force server.cert $TASKDDATA/server.cert.pem --data $TASKDDATA
//		sudo taskd config --force server.cert /var/taskd/server.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[19] = new String[9];
		commands[19][0] = "sudo";
		commands[19][1] = "taskd"; 
		commands[19][2] = "config";
		commands[19][3] = "--force";
		commands[19][4] = "server.cert";
		commands[19][5] = "/var/taskd/server.cert.pem";
		commands[19][6] = "--data";
		commands[19][7] = "/var/taskd";
		commands[19][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force server.key $TASKDDATA/server.key.pem --data $TASKDDATA
//		sudo taskd config --force server.key /var/taskd/server.key.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[20] = new String[9];
		commands[20][0] = "sudo";
		commands[20][1] = "taskd"; 
		commands[20][2] = "config";
		commands[20][3] = "--force";
		commands[20][4] = "server.key";
		commands[20][5] = "/var/taskd/server.key.pem";
		commands[20][6] = "--data";
		commands[20][7] = "/var/taskd";
		commands[20][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force server.crl $TASKDDATA/server.crl.pem --data $TASKDDATA
//		sudo taskd config --force server.crl /var/taskd/server.crl.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[21] = new String[9];
		commands[21][0] = "sudo";
		commands[21][1] = "taskd"; 
		commands[21][2] = "config";
		commands[21][3] = "--force";
		commands[21][4] = "server.crl";
		commands[21][5] = "/var/taskd/server.crl.pem";
		commands[21][6] = "--data";
		commands[21][7] = "/var/taskd";
		commands[21][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force ca.cert $TASKDDATA/ca.cert.pem --data $TASKDDATA
//		sudo taskd config --force ca.cert /var/taskd/ca.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[22] = new String[9];
		commands[22][0] = "sudo";
		commands[22][1] = "taskd"; 
		commands[22][2] = "config";
		commands[22][3] = "--force";
		commands[22][4] = "ca.cert";
		commands[22][5] = "/var/taskd/ca.cert.pem";
		commands[22][6] = "--data";
		commands[22][7] = "/var/taskd";
		commands[22][8] = "/usr/share/taskd/pki/";
		
		// write fillers for commands that are replaced:
		// cd $TASKDDATA
		commands[23] = new String[3];
		commands[23][0] = "echo";
		commands[23][1] = "filler";
		commands[23][2] = linuxPath;
		
		//TODO: find out where the value of $PWD comes from
		
//		sudo taskd config --force log $PWD/taskd.log --data $TASKDDATA
//		sudo taskd config --force log $PWD/taskd.log --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[24] = new String[9];
		commands[24][0] = "sudo";
		commands[24][1] = "taskd"; 
		commands[24][2] = "config";
		commands[24][3] = "--force";
		commands[24][4] = "log";
		commands[24][5] = "/var/taskd/taskd.log";
		commands[24][6] = "--data";
		commands[24][7] = "/var/taskd";
		commands[24][8] = "/var/taskd/";
		
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[25] = new String[9];
		commands[25][0] = "sudo";
		commands[25][1] = "taskd"; 
		commands[25][2] = "config";
		commands[25][3] = "--force";
		commands[25][4] = "pid.file";
		commands[25][5] = "/var/taskd/taskd.pid";
		commands[25][6] = "--data";
		commands[25][7] = "/var/taskd";
		commands[25][8] = "/var/taskd/";
		
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commands[26] = new String[9];
		commands[26][0] = "sudo";
		commands[26][1] = "taskd"; 
		commands[26][2] = "config";
		commands[26][3] = "--force";
		commands[26][4] = "server";
		commands[26][5] = serverName+":"+serverPort;
		commands[26][6] = "--data";
		commands[26][7] = "/var/taskd";
		commands[26][8] = "/var/taskd";
		
		//task add testtask description 1
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commands[27] = new String[5];
		commands[27][0] = "yes | task";
		commands[27][1] = "add";
		commands[27][2] = "testtask";
		commands[27][3] = "description";
		commands[27][4] = "/var/taskd";	
		
//		sudo taskd add org Public --data $TASKDDATA
//		sudo taskd add org Public --data /var/taskd
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commands[28] = new String[8];
		commands[28][0] = "sudo";
		commands[28][1] = "taskd"; 
		commands[28][2] = "add";
		commands[28][3] = "org";
		commands[28][4] = storeUserInput[2];
		commands[28][5] = "--data";
		commands[28][6] = "/var/taskd";
		commands[28][7] = "/var/taskd";
		
//		sudo taskd add user 'Public' 'First' --data $TASKDDATA
//		sudo taskd add user 'Public' 'First' --data $TASKDDATA
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commands[30] = new String[9];
		commands[30][0] = "sudo";
		commands[30][1] = "taskd"; 
		commands[30][2] = "add";
		commands[30][3] = "user";
		commands[30][4] = storeUserInput[2];
		commands[30][5] = storeUserInput[3];
		commands[30][6] = "--data";
		commands[30][7] = "/var/taskd";
		commands[30][8] = "/var/taskd/";
		//commands[30][8] = "/var/taskd/orgs/";
		
		return commands;
	}
	
	/**
	 * Generate commands of 18 on. Add working directory at the end of the command array.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 * @param storeUserInput
	 * @return
	 */
	public static String[][] generateSecondCommands(boolean testRun,String linuxPath,String vars,String[] storeUserInput,String serverName,String serverPort) {
		String[][] commands = new String[50][1];
		String twUuid;
		String directoryPath = new String("/var/taskd/orgs/"+storeUserInput[2]+"/users/");
		
		if (!testRun) {
			// get taskwarrior uuid	
			twUuid=getTwUuid.findFoldersInDirectory(directoryPath).get(0);
			System.out.println("The TW uuid = "+getTwUuid.findFoldersInDirectory(directoryPath).get(0));
			
			// write fillers for commands that are replaced:
			// cd /usr/share/taskd/pki
//			commands[1] = new String[3];
//			commands[1][0] = "echo";
//			commands[1][1] = "filler";
//			commands[1][2] = linuxPath;
			
			// sudo ./generate.client First
			// to: sudo  ./generate.client <taskwarrior username> 
			// with working directory: /usr/share/taskd/pki
			// environment variable TASKDDATA=/var/taskd
			commands[0] = new String[4];
			commands[0][0] = "sudo";
			commands[0][1] = "/usr/share/taskd/pki/generate.client"; 
			commands[0][2] = storeUserInput[3];
			commands[0][3] = "/usr/share/taskd/pki/";
			
			// write fillers for commands that are replaced:
			// cd /usr/share/taskd/pki
//			commands[1] = new String[3];
//			commands[1][0] = "echo";
//			commands[1][1] = "filler";
//			commands[1][2] = linuxPath;
			
			// sudo cp First.cert.pem /home/a/.task
			// to: sudo cp <taskwarrior username>.cert.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// with working directory: /usr/share/taskd/pki
			// environment variable TASKDDATA=/var/taskd
			// TODO: verify the First.cert.pem file is copied to the /.task/folder
			commands[1] = new String[5];
			commands[1][0] = "sudo";
			commands[1][1] = "cp"; 
			//commands[1][2] = storeUserInput[3]+".cert.pem";
			commands[1][2] = "/usr/share/taskd/pki/"+storeUserInput[3]+".cert.pem"; //abs
			commands[1][3] = "/home/"+storeUserInput[0]+"/.task/";
			commands[1][4] = "/usr/share/taskd/pki/";
			
			//sudo cp First.key.pem /home/a/.task
			// to: sudo cp <taskwarrior username>.key.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// TODO: verify the First.key.pem file is copied to the /.task/folder
			commands[2] = new String[5];
			commands[2][0] = "sudo";
			commands[2][1] = "cp"; 
			//commands[2][2] = storeUserInput[3]+".key.pem";
			commands[2][2] = "/usr/share/taskd/pki/"+storeUserInput[3]+".key.pem"; //abs
			commands[2][3] = "/home/"+storeUserInput[0]+"/.task/";
			commands[2][4] = "/usr/share/taskd/pki/";
			
			//sudo cp ca.cert.pem /home/a/.task
			// to: sudo cp ca.cert.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// TODO: verify the ca.cert.pem file is copied to the /.task/folder
			commands[3] = new String[5];
			commands[3][0] = "sudo";
			commands[3][1] = "cp"; 
			//commands[3][2] = "ca.cert.pem";
			commands[3][2] = "/usr/share/taskd/pki/"+"ca.cert.pem"; //abs
			commands[3][3] = "/home/"+storeUserInput[0]+"/.task/";
			commands[3][4] = "/usr/share/taskd/pki/";
			
			//sudo task config taskd.certificate -- /home/a/.task/First.cert.pem
			// to: sudo task config taskd.certificate -- /home/<Ubuntu username>/.task/<taskwarrior username>.cert.pem
			//working directory: /usr/share/taskd/pki/
			commands[4] = new String[7];
			commands[4][0] = "yes | sudo";
			commands[4][1] = "task"; 
			commands[4][2] = "config";
			commands[4][3] = "taskd.certificate";
			commands[4][4] = "--";
			commands[4][5] = "/home/"+storeUserInput[0]+"/.task/"+storeUserInput[3]+".cert.pem";
			commands[4][6] = "/usr/share/taskd/pki/";
			
			// sudo task config taskd.key -- /home/a/.task/First.key.pem
			// to: sudo task config taskd.key -- /home/<Ubuntu username>/.task/<taskwarrior username>.key.pem
			//working directory: /usr/share/taskd/pki/
			commands[5] = new String[7];
			commands[5][0] = "yes | sudo";
			commands[5][1] = "task"; 
			commands[5][2] = "config";
			commands[5][3] = "taskd.key";
			commands[5][4] = "--";
			commands[5][5] = "/home/"+storeUserInput[0]+"/.task/"+storeUserInput[3]+".key.pem";
			commands[5][6] = "/usr/share/taskd/pki/";
			
			// sudo task config taskd.ca -- /home/a/.task/ca.cert.pem
			// to: sudo task config taskd.ca -- /home/<Ubuntu username>/.task/ca.cert.pem
			//working directory: /usr/share/taskd/pki/
			commands[6] = new String[7];
			commands[6][0] = "yes | sudo";
			commands[6][1] = "task"; 
			commands[6][2] = "config";
			commands[6][3] = "taskd.ca";
			commands[6][4] = "--";
			commands[6][5] = "/home/"+storeUserInput[0]+"/.task/ca.cert.pem";
			commands[6][6] = "/usr/share/taskd/pki/";
			
			// sudo task config taskd.server -- 0.0.0.0:53589
			// to: sudo task config taskd.server -- 0.0.0.0:53589
			//working directory: /usr/share/taskd/pki/
			commands[7] = new String[7];
			commands[7][0] = "yes | sudo";
			commands[7][1] = "task"; 
			commands[7][2] = "config";
			commands[7][3] = "taskd.server";
			commands[7][4] = "--";
			commands[7][5] = serverName+":"+serverPort;
			commands[7][6] = "/usr/share/taskd/pki/";
			
			// sudo task config taskd.credentials -- Public/First/b6634b72-04a3-4220-8d49-6d56as4tefb
			// to: sudo task config taskd.credentials -- <taskwarrior organisation>/<taskwarrior username>/<taskwarrior uuid>
			//working directory: /usr/share/taskd/pki/
			commands[8] = new String[7];
			commands[8][0] = "yes | sudo";
			commands[8][1] = "task"; 
			commands[8][2] = "config";
			commands[8][3] = "taskd.credentials";
			commands[8][4] = "--";
			commands[8][5] = storeUserInput[2]+"/"+storeUserInput[3]+"/"+twUuid;
			commands[8][6] = "/usr/share/taskd/pki/";
			
//			// 
//			// to: 
//			//working directory: /usr/share/taskd/pki/
//			commands[] = new String[5];
//			commands[][0] = "";
//			commands[][1] = ""; 
//			commands[][2] = "";
//			commands[][3] = "";
//			commands[][4] = "/usr/share/taskd/pki/";
		}
		return commands;
	}
	
	/**
	 * Set the environment path with the command.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 * @param storeUserInput
	 * @param serverName
	 * @param serverPort
	 * @return
	 */
	public static String[][] generateThirdCommands(boolean testRun,String linuxPath,String vars,String[] storeUserInput,String serverName,String serverPort) {
		String[][] commands = new String[50][1];
		String twUuid;
		String directoryPath = new String("/var/taskd/orgs/"+storeUserInput[2]+"/users/");
		
		if (!testRun) {
			// taskdctl start
			// to: 
			//working directory: /usr/share/taskd/pki/
			//Environment variable: TASKDDATA=/var/taskd
			commands[1] = new String[4];
			commands[1][0] = "taskdctl";
			commands[1][1] = "start"; 
			commands[1][2] = "/usr/share/taskd/pki/";
			commands[1][3] = "/var/taskd";
			
			// sudo task sync init 
			// to: 
			//working directory: /usr/share/taskd/pki/
			commands[2] = new String[6];
			commands[2][0] = "yes | sudo";
			commands[2][1] = "task"; 
			commands[2][2] = "sync";
			commands[2][3] = "init";
			commands[2][4] = "/usr/share/taskd/pki/";
			commands[2][5] = "/var/taskd";
			
			// sudo task config taskd.trust -- ignore hostname
			// to: 
			//working directory: /usr/share/taskd/pki/
			commands[3] = new String[9];
			commands[3][0] = "sudo";
			commands[3][1] = "task"; 
			commands[3][2] = "config";
			commands[3][3] = "taskd.trust";
			commands[3][4] = "--";
			commands[3][5] = "ignore";
			commands[3][6] = "hostname";
			commands[3][7] = "/usr/share/taskd/pki/";
			commands[3][8] = "/var/taskd";
			
			// taskdctl stop
			// to: 
			//working directory: /usr/share/taskd/pki/
			commands[4] = new String[4];
			commands[4][0] = "taskdctl";
			commands[4][1] = "stop"; 
			commands[4][2] = "/usr/share/taskd/pki/";
			commands[4][3] = "/var/taskd";

			//sudo taskd config --data $TASKDDATA
			// to: sudo taskd config --data /var/taskd 
			//working directory: /var/taskd/
			commands[5] = new String[7];
			commands[5][0] = "sudo";
			commands[5][1] = "taskd"; 
			commands[5][2] = "config";
			commands[5][3] = "--data";
			commands[5][4] = "/var/taskd/";
			commands[5][5] = "/var/taskd/";
			commands[5][6] = "/var/taskd";
						
			// sudo -s
			// export TASKDDATA=/var/taskd
			// taskdctl start
			// to: 
			//working directory: /var/taskd/
			commands[6] = new String[4];
			commands[6][0] = "sudo";
			commands[6][1] = "-s"; 
			commands[6][2] = "/var/taskd";
			commands[6][3] = "/var/taskd";
			
			commands[7] = new String[4];
			commands[7][0] = "taskdctl";
			commands[7][1] = "start";
			commands[7][2] = "/usr/share/taskd/pki/";
			commands[7][3] = "/var/taskd";
			
			// sudo task sync init
			// to: 
			//working directory: /var/taskd/
			commands[8] = new String[6];
			commands[8][0] = "yes | sudo";
			commands[8][1] = "task"; 
			commands[8][2] = "sync";
			commands[8][3] = "init";
			commands[8][4] = "/var/taskd/";
			commands[8][5] = "/var/taskd";
		}
		return commands;
	}
}
