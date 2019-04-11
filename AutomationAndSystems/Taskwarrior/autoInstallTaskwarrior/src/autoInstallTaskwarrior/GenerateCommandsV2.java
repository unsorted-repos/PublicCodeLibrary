package autoInstallTaskwarrior;

/**
 * This rewrites the commands so that they capture the intent 
 * without using environment variables but just the direct
 * commands.
 * 
 * At the last entry of a command, the accompanying "working path"
 * is specified.
 * @author a
 *
 */
public class GenerateCommandsV2 {
	public static String[][] generateCommands(boolean testRun,String linuxPath,String vars) {
		String[][] commands = new String[50][1];
//		char vd = (char)124; //vertical dash: |
//		char bs = (char)92; //backslash: \		
//		char and = (char)38; // and $
//		char quotation = (char)34; // quotation mark "
		
		//In combination with Attempt 1:
		if (!testRun) {
			commands[0] = new String[4];
			//commands[0][0] = "yes | sudo apt update "+and+and+" sudo apt upgrade";
			commands[0][0] = "yes | sudo";
			commands[0][1] = "apt";
			commands[0][2] = "update";
			commands[0][3] = linuxPath;
					
			
			commands[1] = new String[4];
			commands[1][0] = "yes | sudo";
			commands[1][1] = "apt";
			commands[1][2] = "upgrade";
			commands[1][3] = linuxPath;
			
			//			commands[2][0] = "sudo apt install task";
			commands[2] = new String[5];
			commands[2][0] = "yes | sudo";
			commands[2][1] = "apt";
			commands[2][2] = "install";
			commands[2][3] = "task";
			commands[2][4] = linuxPath;
			
			//			commands[3][0] = "sudo apt install taskd";
			commands[3] = new String[5];
			commands[3][0] = "yes | sudo";
			commands[3][1] = "apt";
			commands[3][2] = "install";
			commands[3][3] = "taskd";
			commands[3][4] = linuxPath;
			
			// write fillers for commands that are replaced:
			commands[4] = new String[3];
			commands[4][0] = "echo";
			commands[4][1] = "command";
			commands[4][2] = linuxPath;
			
			commands[5] = new String[3];
			commands[5][0] = "echo";
			commands[5][1] = "command";
			commands[5][2] = linuxPath;
			
			commands[6] = new String[3];
			commands[6][0] = "echo";
			commands[6][1] = "command";
			commands[6][2] = linuxPath;
			
			//			commands[7][0] = "sudo mkdir -p $TASKDDATA";
			commands[7] = new String[5];
			commands[7][0] = "sudo";
			commands[7][1] = "mkdir";
			commands[7][2] = "-p";
			commands[7][3] = "/var/taskd";
			commands[7][4] = linuxPath;
			
			// verify by checking whether /var/taskd/config did not exist before execution of 
			// this command, and does exist after. Status: Both verified, hence command verified.
			//			commands[8][0] = "sudo taskd init --data $TASKDDATA";
			commands[8] = new String[6];
			commands[8][0] = "sudo";
			commands[8][1] = "taskd";
			commands[8][2] = "init";
			commands[8][3] = "--data";
			commands[8][4] = "/var/taskd";
			commands[8][5] = linuxPath;
				
		}else {
				System.out.println("Skipped some lines for testing");
		}
		
		// Verified till here: substitute: cd /usr/share/taskd/pki
		// sudo nano vars
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
		//		sudo cp client.cert.pem $TASKDDATA
		//		to: sudo cp client.cert.pem "/var/taskd"
		commands[11] = new String[5];
		commands[11][0] = "sudo";
		commands[11][1] = "cp"; 
		commands[11][2] = "client.cert.pem";
		commands[11][3] = "/var/taskd";
		commands[11][4] = "/usr/share/taskd/pki/";
			
		//		sudo cp client.key.pem $TASKDDATA
		//		to: sudo cp client.key.pem "/var/taskd"
		commands[12] = new String[5];
		commands[12][0] = "sudo";
		commands[12][1] = "cp"; 
		commands[12][2] = "client.key.pem";
		commands[12][3] = "/var/taskd";
		commands[12][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.cert.pem $TASKDDATA
		//		to: sudo cp server.cert.pem "/var/taskd"
		commands[13] = new String[5];
		commands[13][0] = "sudo";
		commands[13][1] = "cp"; 
		commands[13][2] = "server.cert.pem";
		commands[13][3] = "/var/taskd";
		commands[13][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.key.pem $TASKDDATA
		//		to: sudo cp server.key.pem "/var/taskd"
		commands[14] = new String[5];
		commands[14][0] = "sudo";
		commands[14][1] = "cp"; 
		commands[14][2] = "server.key.pem";
		commands[14][3] = "/var/taskd";
		commands[14][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp server.crl.pem $TASKDDATA
		//		to: sudo cp server.crl.pem "/var/taskd"
		commands[15] = new String[5];
		commands[15][0] = "sudo";
		commands[15][1] = "cp"; 
		commands[15][2] = "server.crl.pem";
		commands[15][3] = "/var/taskd";
		commands[15][4] = "/usr/share/taskd/pki/";
		
		//		sudo cp ca.cert.pem $TASKDDATA
		//		to: sudo cp ca.cert.pem "/var/taskd"
		commands[16] = new String[5];
		commands[16][0] = "sudo";
		commands[16][1] = "cp"; 
		commands[16][2] = "ca.cert.pem";
		commands[16][3] = "/var/taskd";
		commands[16][4] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force client.cert $TASKDDATA/client.cert.pem --data $TASKDDATA
//		sudo taskd config --force client.cert /var/taskd/client.cert.pem --data /var/taskd		
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
		commands[18] = new String[9];
		commands[18][0] = "sudo";
		commands[18][1] = "taskd"; 
		commands[18][2] = "config";
		commands[18][3] = "--force";
		commands[18][4] = "client.cert";
		commands[18][5] = "/var/taskd/client.key.pem";
		commands[18][6] = "--data";
		commands[18][7] = "/var/taskd";
		commands[18][8] = "/usr/share/taskd/pki/";
		
		
//		sudo taskd config --force server.cert $TASKDDATA/server.cert.pem --data $TASKDDATA
//		sudo taskd config --force server.cert /var/taskd/server.cert.pem --data /var/taskd		
		commands[19] = new String[9];
		commands[19][0] = "sudo";
		commands[19][1] = "taskd"; 
		commands[19][2] = "config";
		commands[19][3] = "--force";
		commands[19][4] = "client.cert";
		commands[19][5] = "/var/taskd/server.cert.pem";
		commands[19][6] = "--data";
		commands[19][7] = "/var/taskd";
		commands[19][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force server.key $TASKDDATA/server.key.pem --data $TASKDDATA
//		sudo taskd config --force server.key /var/taskd/server.key.pem --data /var/taskd		
		commands[20] = new String[9];
		commands[20][0] = "sudo";
		commands[20][1] = "taskd"; 
		commands[20][2] = "config";
		commands[20][3] = "--force";
		commands[20][4] = "client.cert";
		commands[20][5] = "/var/taskd/server.key.pem";
		commands[20][6] = "--data";
		commands[20][7] = "/var/taskd";
		commands[20][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force server.crl $TASKDDATA/server.crl.pem --data $TASKDDATA
//		sudo taskd config --force server.crl /var/taskd/server.crl.pem --data /var/taskd		
		commands[21] = new String[9];
		commands[21][0] = "sudo";
		commands[21][1] = "taskd"; 
		commands[21][2] = "config";
		commands[21][3] = "--force";
		commands[21][4] = "client.cert";
		commands[21][5] = "/var/taskd/server.crl.pem";
		commands[21][6] = "--data";
		commands[21][7] = "/var/taskd";
		commands[21][8] = "/usr/share/taskd/pki/";
		
//		sudo taskd config --force ca.cert $TASKDDATA/ca.cert.pem --data $TASKDDATA
//		sudo taskd config --force ca.cert /var/taskd/ca.cert.pem --data /var/taskd		
		commands[22] = new String[9];
		commands[22][0] = "sudo";
		commands[22][1] = "taskd"; 
		commands[22][2] = "config";
		commands[22][3] = "--force";
		commands[22][4] = "client.cert";
		commands[22][5] = "/var/taskd/ca.cert.pem";
		commands[22][6] = "--data";
		commands[22][7] = "/var/taskd";
		commands[22][8] = "/usr/share/taskd/pki/";
		
		
		
//		sudo taskd config --force log $PWD/taskd.log --data $TASKDDATA
//		sudo taskd config --force log $PWD/taskd.log --data /var/taskd
		commands[23] = new String[9];
		commands[23][0] = "sudo";
		commands[23][1] = "taskd"; 
		commands[23][2] = "config";
		commands[23][3] = "--force";
		commands[23][4] = "log";
		commands[23][5] = "$PWD/taskd.log";
		commands[23][6] = "--data";
		commands[23][7] = "/var/taskd";
		commands[23][8] = "/var/taskd/";
		
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
		commands[24] = new String[9];
		commands[24][0] = "sudo";
		commands[24][1] = "taskd"; 
		commands[24][2] = "config";
		commands[24][3] = "--force";
		commands[24][4] = "pid.file";
		commands[24][5] = "$PWD/taskd.pid";
		commands[24][6] = "--data";
		commands[24][7] = "/var/taskd";
		commands[24][8] = "/var/taskd/";
		
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
		commands[25] = new String[9];
		commands[25][0] = "sudo";
		commands[25][1] = "taskd"; 
		commands[25][2] = "config";
		commands[25][3] = "--force";
		commands[25][4] = "server";
		commands[25][5] = "0.0.0.0:53589";
		commands[25][6] = "--data";
		commands[25][7] = "/var/taskd";
		commands[25][8] = "/var/taskd";
		
		//task add testtask description 1
		commands[26] = new String[5];
		commands[26][0] = "yes | task";
		commands[26][1] = "add";
		commands[26][2] = "testtask";
		commands[26][3] = "description";
		commands[26][4] = "/var/taskd";
		
		return commands;
	}
}