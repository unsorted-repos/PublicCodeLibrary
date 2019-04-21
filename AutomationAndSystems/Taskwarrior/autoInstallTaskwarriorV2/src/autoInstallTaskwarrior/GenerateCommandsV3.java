package autoInstallTaskwarrior;

import java.io.IOException;
import java.util.Map;

/**
 * A list of the verified commands (In the comment above each command)
 * is converted into a set of commands that Java can execute (an array of strings).
 * 
 * At the second last entry of a command, the accompanying "working path"
 * is specified in method generateCommands.
 * At the last entry the environment variable is specified.
 * @author a
 *
 */
public class GenerateCommandsV3 {
	public static Command[] generateCommands(InstallData installData) {
		int nrOfCommands = 51;
		String[][] commandLines = new String[nrOfCommands+1][1];
		Command[] commands = new Command[nrOfCommands+1];
		for (int i = 0; i<nrOfCommands;i++) {
			System.out.println(commands[i].toString());
			commands[i].setGetOutput(false);
			} // initialise get output to false
		//In combination with Attempt 1:
		
			
			// first command to enable working with an environment variable that exists.
			//commandLines[0].setCommandLines(commandLines);
			commandLines[0] = new String[6];
			commandLines[0][0] = "sudo";
			commandLines[0][1] = "mkdir";
			commandLines[0][2] = "-p";
			commandLines[0][3] = "/var/taskd";
			commandLines[0][4] = installData.getLinuxPath();
			commandLines[0][5] = "TASKDDATA=/var/taskd";
			commands[0].setCommandLines(commandLines[0]);
			commands[0].setEnvVarContent("/var/task");
			commands[0].setEnvVarName("TASKDDATA");
			commands[0].setWorkingPath("");

			
			commandLines[1] = new String[5];
			// commandLines[0][0] = "yes | sudo apt update";
			commandLines[1][0] = "yes | sudo";
			commandLines[1][1] = "apt";
			commandLines[1][2] = "update";
			commandLines[1][3] = installData.getLinuxPath();
			commandLines[1][4] = "TASKDDATA=/var/taskd";
			commands[1].setCommandLines(commandLines[1]);
			commands[1].setEnvVarContent("/var/task");
			commands[1].setEnvVarName("TASKDDATA");
			commands[1].setWorkingPath("");
		
			
			// commandLines[2][0] = "sudo apt install task";	
			commandLines[2] = new String[6];
			commandLines[2][0] = "yes | sudo";
			commandLines[2][1] = "apt";
			commandLines[2][2] = "install";
			commandLines[2][3] = "task";
			commandLines[2][4] = "/home/"+installData.getLinuxUserName();
			commandLines[2][5] = "TASKDDATA=/var/taskd";
			commands[2].setCommandLines(commandLines[2]);
			commands[2].setEnvVarContent("/var/task");
			commands[2].setEnvVarName("TASKDDATA");
			commands[2].setWorkingPath("");
			
			// commandLines[3][0] = "sudo apt install taskd";
			commandLines[3] = new String[6];
			commandLines[3][0] = "yes | sudo";
			commandLines[3][1] = "apt";
			commandLines[3][2] = "install";
			commandLines[3][3] = "taskd";
			commandLines[3][4] = installData.getLinuxPath();
			commandLines[3][5] = "TASKDDATA=/var/taskd";
			commands[3].setCommandLines(commandLines[3]);
			commands[3].setEnvVarContent("/var/task");
			commands[3].setEnvVarName("TASKDDATA");
			commands[3].setWorkingPath("");
			
			// write fillers for commands that are replaced:
			// cd ..
			commandLines[4] = new String[4];
			commandLines[4][0] = "echo";
			commandLines[4][1] = "filler";
			commandLines[4][2] = installData.getLinuxPath();
			commandLines[4][3] = "TASKDDATA=/var/taskd";
			commands[4].setCommandLines(commandLines[4]);
			commands[4].setEnvVarContent("/var/task");
			commands[4].setEnvVarName("TASKDDATA");
			commands[4].setWorkingPath("");
			
			// write fillers for commands that are replaced:
			// cd ..
			commandLines[5] = new String[4];
			commandLines[5][0] = "echo";
			commandLines[5][1] = "filler";
			commandLines[5][2] = installData.getLinuxPath();
			commandLines[5][3] = "TASKDDATA=/var/taskd";
			commands[5].setCommandLines(commandLines[5]);
			commands[5].setEnvVarContent("/var/task");
			commands[5].setEnvVarName("TASKDDATA");
			commands[5].setWorkingPath("");
			// write fillers for commands that are replaced:
			// export TASKDDATA=/var/taskd
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			commandLines[6] = new String[4];
			commandLines[6][0] = "echo";
			commandLines[6][1] = "filler";
			commandLines[6][2] = installData.getLinuxPath();
			commandLines[6][3] = "TASKDDATA=/var/taskd";
			commands[6].setCommandLines(commandLines[6]);
			commands[6].setEnvVarContent("/var/task");
			commands[6].setEnvVarName("TASKDDATA");
			commands[6].setWorkingPath("");
			// commandLines[7][0] = "sudo mkdir -p $TASKDDATA";
			// Note: -p creates the parent folders up and until the final folder. 
			// Note: $TASKDDATA=/var/taskd
			// Note: the /var folder should be located in root. Hence, it should be/is an absolute path.
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			// TODO: verify that there is no /var/taskd made in the current working directory/location of the .jar file.
			commandLines[7] = new String[6];
			commandLines[7][0] = "sudo";
			commandLines[7][1] = "mkdir";
			commandLines[7][2] = "-p";
			commandLines[7][3] = "/var/taskd";
			commandLines[7][4] = installData.getLinuxPath();
			commandLines[7][5] = "TASKDDATA=/var/taskd";
			commands[7].setCommandLines(commandLines[7]);
			commands[7].setEnvVarContent("/var/task");
			commands[7].setEnvVarName("TASKDDATA");
			commands[7].setWorkingPath("");
			// commandLines[8][0] = "sudo taskd init --data $TASKDDATA";
			// working folder should be root
			// environment variable:TASKDDATA=/var/taskd
			// Verified by checking whether /var/taskd/config did not exist before execution of 
			// this command, and does exist after. Status: Both verified, hence command verified.
			commandLines[8] = new String[7];
			commandLines[8][0] = "yes | sudo";
			commandLines[8][1] = "taskd";
			commandLines[8][2] = "init";
			commandLines[8][3] = "--data";
			commandLines[8][4] = "/var/taskd";
			commandLines[8][5] = installData.getLinuxPath();
			commandLines[8][6] = "TASKDDATA=/var/taskd";
			commands[8].setCommandLines(commandLines[8]);
			commands[8].setEnvVarContent("/var/task");
			commands[8].setEnvVarName("TASKDDATA");
			commands[8].setWorkingPath("");
			

		// Verified till here: substitute: cd /usr/share/taskd/pki
		// cd /usr/share/taskd/pki
		// sudo nano vars
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[9] = new String[6];
		commandLines[9][0] = "sudo";
		commandLines[9][1] = "cp";
		commandLines[9][2] = installData.getLinuxPath()+installData.getVars(); 
		commandLines[9][3] = "/usr/share/taskd/pki/";
		commandLines[9][4] = installData.getLinuxPath();
		commandLines[9][5] = "TASKDDATA=/var/taskd";
		commands[9].setCommandLines(commandLines[9]);
		commands[9].setEnvVarContent("/var/task");
		commands[9].setEnvVarName("TASKDDATA");
		commands[9].setWorkingPath("");
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
		commandLines[10] = new String[4];
		commandLines[10][0] = "sudo";
		commandLines[10][1] = "/usr/share/taskd/pki/generate";
		commandLines[10][2] = "/usr/share/taskd/pki/";
		commandLines[10][3] = "TASKDDATA=/var/taskd";
		commands[10].setCommandLines(commandLines[10]);
		commands[10].setEnvVarContent("/var/task");
		commands[10].setEnvVarName("TASKDDATA");
		commands[10].setWorkingPath("");
		
		
		

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
		commandLines[11] = new String[6];
		commandLines[11][0] = "sudo";
		commandLines[11][1] = "cp"; 
		//commandLines[11][2] = "client.cert.pem";
		commandLines[11][2] = "/usr/share/taskd/pki/client.cert.pem"; //abs
		commandLines[11][3] = "/var/taskd";
		commandLines[11][4] = "/usr/share/taskd/pki/";
		commandLines[11][5] = "TASKDDATA=/var/taskd";
		commands[11].setCommandLines(commandLines[11]);
		commands[11].setEnvVarContent("/var/task");
		commands[11].setEnvVarName("TASKDDATA");
		commands[11].setWorkingPath("");
		//		sudo cp client.key.pem $TASKDDATA
		//		to: sudo cp client.key.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[12] = new String[6];
		commandLines[12][0] = "sudo";
		commandLines[12][1] = "cp"; 
		//commandLines[12][2] = "client.key.pem";
		commandLines[12][2] = "/usr/share/taskd/pki/client.key.pem"; //abs
		commandLines[12][3] = "/var/taskd";
		commandLines[12][4] = "/usr/share/taskd/pki/";
		commandLines[12][5] = "TASKDDATA=/var/taskd";
		commands[12].setCommandLines(commandLines[12]);
		commands[12].setEnvVarContent("/var/task");
		commands[12].setEnvVarName("TASKDDATA");
		commands[12].setWorkingPath("");
		
		//		sudo cp server.cert.pem $TASKDDATA
		//		to: sudo cp server.cert.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[13] = new String[6];
		commandLines[13][0] = "sudo";
		commandLines[13][1] = "cp"; 
		//commandLines[13][2] = "server.cert.pem";
		commandLines[13][2] = "/usr/share/taskd/pki/server.cert.pem"; //abs
		commandLines[13][3] = "/var/taskd";
		commandLines[13][4] = "/usr/share/taskd/pki/";
		commandLines[13][5] = "TASKDDATA=/var/taskd";
		commands[13].setCommandLines(commandLines[13]);
		commands[13].setEnvVarContent("/var/task");
		commands[13].setEnvVarName("TASKDDATA");
		commands[13].setWorkingPath("");
		
		//		sudo cp server.key.pem $TASKDDATA
		//		to: sudo cp server.key.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[14] = new String[6];
		commandLines[14][0] = "sudo";
		commandLines[14][1] = "cp"; 
		//commandLines[14][2] = "server.key.pem";
		commandLines[14][2] = "/usr/share/taskd/pki/server.key.pem"; //abs
		commandLines[14][3] = "/var/taskd";
		commandLines[14][4] = "/usr/share/taskd/pki/";
		commandLines[14][5] = "TASKDDATA=/var/taskd";
		commands[14].setCommandLines(commandLines[14]);
		commands[14].setEnvVarContent("/var/task");
		commands[14].setEnvVarName("TASKDDATA");
		commands[14].setWorkingPath("");
		
		//		sudo cp server.crl.pem $TASKDDATA
		//		to: sudo cp server.crl.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[15] = new String[6];
		commandLines[15][0] = "sudo";
		commandLines[15][1] = "cp"; 
		//commandLines[15][2] = "server.crl.pem";
		commandLines[15][2] = "/usr/share/taskd/pki/server.crl.pem"; //abs
		commandLines[15][3] = "/var/taskd";
		commandLines[15][4] = "/usr/share/taskd/pki/";
		commandLines[15][5] = "TASKDDATA=/var/taskd";
		commands[15].setCommandLines(commandLines[15]);
		commands[15].setEnvVarContent("/var/task");
		commands[15].setEnvVarName("TASKDDATA");
		commands[15].setWorkingPath("");
		
		//		sudo cp ca.cert.pem $TASKDDATA
		//		to: sudo cp ca.cert.pem "/var/taskd"
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[16] = new String[6];
		commandLines[16][0] = "sudo";
		commandLines[16][1] = "cp"; 
		//commandLines[16][2] = "ca.cert.pem";
		commandLines[16][2] = "/usr/share/taskd/pki/ca.cert.pem"; //abs
		commandLines[16][3] = "/var/taskd";
		commandLines[16][4] = "/usr/share/taskd/pki/";
		commandLines[16][5] = "TASKDDATA=/var/taskd";
		commands[16].setCommandLines(commandLines[16]);
		commands[16].setEnvVarContent("/var/task");
		commands[16].setEnvVarName("TASKDDATA");
		commands[16].setWorkingPath("");
		
//		sudo taskd config --force client.cert $TASKDDATA/client.cert.pem --data $TASKDDATA
//		sudo taskd config --force client.cert /var/taskd/client.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[17] = new String[10];
		commandLines[17][0] = "sudo";
		commandLines[17][1] = "taskd"; 
		commandLines[17][2] = "config";
		commandLines[17][3] = "--force";
		commandLines[17][4] = "client.cert";
		commandLines[17][5] = "/var/taskd/client.cert.pem";
		commandLines[17][6] = "--data";
		commandLines[17][7] = "/var/taskd";
		commandLines[17][8] = "/usr/share/taskd/pki/";
		commandLines[17][9] = "TASKDDATA=/var/taskd";
		commands[17].setCommandLines(commandLines[17]);
		commands[17].setEnvVarContent("/var/task");
		commands[17].setEnvVarName("TASKDDATA");
		commands[17].setWorkingPath("");
		
		
//		sudo taskd config --force client.key $TASKDDATA/client.key.pem --data $TASKDDATA
//		sudo taskd config --force client.key /var/taskd/client.key.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[18] = new String[10];
		commandLines[18][0] = "sudo";
		commandLines[18][1] = "taskd"; 
		commandLines[18][2] = "config";
		commandLines[18][3] = "--force";
		commandLines[18][4] = "client.key";
		commandLines[18][5] = "/var/taskd/client.key.pem";
		commandLines[18][6] = "--data";
		commandLines[18][7] = "/var/taskd";
		commandLines[18][8] = "/usr/share/taskd/pki/";
		commandLines[18][9] = "TASKDDATA=/var/taskd";
		commands[18].setCommandLines(commandLines[18]);
		commands[18].setEnvVarContent("/var/task");
		commands[18].setEnvVarName("TASKDDATA");
		commands[18].setWorkingPath("");
		
		
//		sudo taskd config --force server.cert $TASKDDATA/server.cert.pem --data $TASKDDATA
//		sudo taskd config --force server.cert /var/taskd/server.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[19] = new String[10];
		commandLines[19][0] = "sudo";
		commandLines[19][1] = "taskd"; 
		commandLines[19][2] = "config";
		commandLines[19][3] = "--force";
		commandLines[19][4] = "server.cert";
		commandLines[19][5] = "/var/taskd/server.cert.pem";
		commandLines[19][6] = "--data";
		commandLines[19][7] = "/var/taskd";
		commandLines[19][8] = "/usr/share/taskd/pki/";
		commandLines[19][9] = "TASKDDATA=/var/taskd";
		commands[19].setCommandLines(commandLines[19]);
		commands[19].setEnvVarContent("/var/task");
		commands[19].setEnvVarName("TASKDDATA");
		commands[19].setWorkingPath("");
//		sudo taskd config --force server.key $TASKDDATA/server.key.pem --data $TASKDDATA
//		sudo taskd config --force server.key /var/taskd/server.key.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[20] = new String[10];
		commandLines[20][0] = "sudo";
		commandLines[20][1] = "taskd"; 
		commandLines[20][2] = "config";
		commandLines[20][3] = "--force";
		commandLines[20][4] = "server.key";
		commandLines[20][5] = "/var/taskd/server.key.pem";
		commandLines[20][6] = "--data";
		commandLines[20][7] = "/var/taskd";
		commandLines[20][8] = "/usr/share/taskd/pki/";
		commandLines[20][9] = "TASKDDATA=/var/taskd";
		commands[20].setCommandLines(commandLines[20]);
		commands[20].setEnvVarContent("/var/task");
		commands[20].setEnvVarName("TASKDDATA");
		commands[20].setWorkingPath("");
		

//		sudo taskd config --force server.crl $TASKDDATA/server.crl.pem --data $TASKDDATA
//		sudo taskd config --force server.crl /var/taskd/server.crl.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[21] = new String[10];
		commandLines[21][0] = "sudo";
		commandLines[21][1] = "taskd"; 
		commandLines[21][2] = "config";
		commandLines[21][3] = "--force";
		commandLines[21][4] = "server.crl";
		commandLines[21][5] = "/var/taskd/server.crl.pem";
		commandLines[21][6] = "--data";
		commandLines[21][7] = "/var/taskd";
		commandLines[21][8] = "/usr/share/taskd/pki/";
		commandLines[21][9] = "TASKDDATA=/var/taskd";
		commands[21].setCommandLines(commandLines[21]);
		commands[21].setEnvVarContent("/var/task");
		commands[21].setEnvVarName("TASKDDATA");
		commands[21].setWorkingPath("");
		
		
		
		
		
//		sudo taskd config --force ca.cert $TASKDDATA/ca.cert.pem --data $TASKDDATA
//		sudo taskd config --force ca.cert /var/taskd/ca.cert.pem --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[22] = new String[10];
		commandLines[22][0] = "sudo";
		commandLines[22][1] = "taskd"; 
		commandLines[22][2] = "config";
		commandLines[22][3] = "--force";
		commandLines[22][4] = "ca.cert";
		commandLines[22][5] = "/var/taskd/ca.cert.pem";
		commandLines[22][6] = "--data";
		commandLines[22][7] = "/var/taskd";
		commandLines[22][8] = "/usr/share/taskd/pki/";
		commandLines[22][9] = "TASKDDATA=/var/taskd";
		commands[22].setCommandLines(commandLines[22]);
		commands[22].setEnvVarContent("/var/task");
		commands[22].setEnvVarName("TASKDDATA");
		commands[22].setWorkingPath("");
		// write fillers for commands that are replaced:
		// cd $TASKDDATA
		commandLines[23] = new String[4];
		commandLines[23][0] = "echo";
		commandLines[23][1] = "filler";
		commandLines[23][2] = installData.getLinuxPath();
		commandLines[23][3] = "TASKDDATA=/var/taskd";
		commands[23].setCommandLines(commandLines[23]);
		commands[23].setEnvVarContent("/var/task");
		commands[23].setEnvVarName("TASKDDATA");
		commands[23].setWorkingPath("");
		//TODO: find out where the value of $PWD comes from
		
//		sudo taskd config --force log $PWD/taskd.log --data $TASKDDATA
//		sudo taskd config --force log $PWD/taskd.log --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[24] = new String[10];
		commandLines[24][0] = "sudo";
		commandLines[24][1] = "taskd"; 
		commandLines[24][2] = "config";
		commandLines[24][3] = "--force";
		commandLines[24][4] = "log";
		commandLines[24][5] = "/var/taskd/taskd.log";
		commandLines[24][6] = "--data";
		commandLines[24][7] = "/var/taskd";
		commandLines[24][8] = "/var/taskd/";
		commandLines[24][9] = "TASKDDATA=/var/taskd";
		commands[24].setCommandLines(commandLines[24]);
		commands[24].setEnvVarContent("/var/task");
		commands[24].setEnvVarName("TASKDDATA");
		commands[24].setWorkingPath("");
		
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
//		sudo taskd config --force pid.file $PWD/taskd.pid --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[25] = new String[10];
		commandLines[25][0] = "sudo";
		commandLines[25][1] = "taskd"; 
		commandLines[25][2] = "config";
		commandLines[25][3] = "--force";
		commandLines[25][4] = "pid.file";
		commandLines[25][5] = "/var/taskd/taskd.pid";
		commandLines[25][6] = "--data";
		commandLines[25][7] = "/var/taskd";
		commandLines[25][8] = "/var/taskd/";
		commandLines[25][9] = "TASKDDATA=/var/taskd";
		commands[25].setCommandLines(commandLines[25]);
		commands[25].setEnvVarContent("/var/task");
		commands[25].setEnvVarName("TASKDDATA");
		commands[25].setWorkingPath("");
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
//		sudo taskd config --force server 0.0.0.0:53589 --data /var/taskd
		// working directory: /usr/share/taskd/pki
		// environment variable:TASKDDATA=/var/taskd
		commandLines[26] = new String[10];
		commandLines[26][0] = "sudo";
		commandLines[26][1] = "taskd"; 
		commandLines[26][2] = "config";
		commandLines[26][3] = "--force";
		commandLines[26][4] = "server";
		commandLines[26][5] = installData.getServerName()+":"+installData.getServerPort();
		commandLines[26][6] = "--data";
		commandLines[26][7] = "/var/taskd";
		commandLines[26][8] = "/var/taskd";
		commandLines[26][9] = "TASKDDATA=/var/taskd";
		commands[26].setCommandLines(commandLines[26]);
		commands[26].setEnvVarContent("/var/task");
		commands[26].setEnvVarName("TASKDDATA");
		commands[26].setWorkingPath("");
		
		
		//task add testtask description 1
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commandLines[27] = new String[6];
		commandLines[27][0] = "yes | task";
		commandLines[27][1] = "add";
		commandLines[27][2] = "testtask";
		commandLines[27][3] = "description";
		commandLines[27][4] = "/var/taskd";	
		commandLines[27][5] = "TASKDDATA=/var/taskd";
		commands[27].setCommandLines(commandLines[27]);
		commands[27].setEnvVarContent("/var/task");
		commands[27].setEnvVarName("TASKDDATA");
		commands[27].setWorkingPath("");
		
		
//		sudo taskd add org Public --data $TASKDDATA
//		sudo taskd add org Public --data /var/taskd
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commandLines[28] = new String[9];
		commandLines[28][0] = "sudo";
		commandLines[28][1] = "taskd"; 
		commandLines[28][2] = "add";
		commandLines[28][3] = "org";
		commandLines[28][4] = installData.getTwOrganisation();
		commandLines[28][5] = "--data";
		commandLines[28][6] = "/var/taskd";
		commandLines[28][7] = "/var/taskd";
		commandLines[28][8] = "TASKDDATA=/var/taskd";
		commands[28].setCommandLines(commandLines[28]);
		commands[28].setEnvVarContent("/var/task");
		commands[28].setEnvVarName("TASKDDATA");
		commands[28].setWorkingPath("");
		
//		commands[29].setCommandLines(commandLines[29]);
//		commands[29].setEnvVarContent("/var/task");
//		commands[29].setEnvVarName("TASKDDATA");
//		commands[29].setWorkingPath("");
		
		
		
//		sudo taskd add user 'Public' 'First' --data $TASKDDATA
//		sudo taskd add user 'Public' 'First' --data $TASKDDATA
		// working directory: /var/taskd
		// environment variable:TASKDDATA=/var/taskd
		commandLines[30] = new String[10];
		commandLines[30][0] = "sudo";
		commandLines[30][1] = "taskd"; 
		commandLines[30][2] = "add";
		commandLines[30][3] = "user";
		commandLines[30][4] = installData.getTwOrganisation();
		commandLines[30][5] = installData.getTwUserName();
		commandLines[30][6] = "--data";
		commandLines[30][7] = "/var/taskd";
		commandLines[30][8] = "/var/taskd/";
		commandLines[30][9] = "TASKDDATA=/var/taskd";
		commands[30].setCommandLines(commandLines[30]);
		commands[30].setEnvVarContent("/var/task");
		commands[30].setEnvVarName("TASKDDATA");
		commands[30].setWorkingPath("");
		
		//return commands;
	//}
	
	/**
	 * Generate commands of 18 (pdf) on.
	 *  Add working directory at the second last end of the command array.
	 *  Last entry contains the environment variable.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 * @param storeUserInput
	 * @return
	 */
	//public static String[][] generateSecondCommands(InstallData installData, boolean testRun,String linuxPath,String vars,String[] storeUserInput,String serverName,String serverPort) {
		//String[][] commands = new String[50][1];
		String directoryPath = new String("/var/taskd/orgs/"+installData.getTwOrganisation()+"/users/");
		
		if (!installData.isTestrun()) {
			// get taskwarrior uuid
			//getTwUuid.findFoldersInDirectory(directoryPath).get(0));
			
			System.out.println("The TW uuid = " + installData.getTwUuid());
			
			// write fillers for commands that are replaced:
			// cd /usr/share/taskd/pki
//			commandLines[31] = new String[3];
//			commandLines[31][0] = "echo";
//			commandLines[31][1] = "filler";
//			commandLines[31][2] = linuxPath;
//			commands[31].setCommandLines(commandLines[31]);
//			commands[31].setEnvVarContent("/var/task");
//			commands[31].setEnvVarName("TASKDDATA");
//			commands[31].setWorkingPath("");

			
			
			// sudo ./generate.client First
			// to: sudo  ./generate.client <taskwarrior username> 
			// with working directory: /usr/share/taskd/pki
			// environment variable TASKDDATA=/var/taskd
			commandLines[32] = new String[5];
			commandLines[32][0] = "sudo";
			commandLines[32][1] = "/usr/share/taskd/pki/generate.client"; 
			commandLines[32][2] = installData.getTwUserName();
			commandLines[32][3] = "/usr/share/taskd/pki/";
			commandLines[32][4]= "TASKDDATA=/var/taskd";
			commands[32].setCommandLines(commandLines[32]);
			commands[32].setEnvVarContent("/var/task");
			commands[32].setEnvVarName("TASKDDATA");
			commands[32].setWorkingPath("");
			
			
			// write fillers for commands that are replaced:
			// cd /usr/share/taskd/pki
//			commandLines[33] = new String[3];
//			commandLines[33][0] = "echo";
//			commandLines[33][1] = "filler";
//			commandLines[33][2] = linuxPath;
//			commands[33].setCommandLines(commandLines[33]);
//			commands[33].setEnvVarContent("/var/task");
//			commands[33].setEnvVarName("TASKDDATA");
//			commands[33].setWorkingPath("");
			
			
			// sudo cp First.cert.pem /home/a/.task
			// to: sudo cp <taskwarrior username>.cert.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// with working directory: /usr/share/taskd/pki
			// environment variable TASKDDATA=/var/taskd
			// TODO: verify the First.cert.pem file is copied to the /.task/folder
			commandLines[35] = new String[6];
			commandLines[35][0] = "sudo";
			commandLines[35][1] = "cp"; 
			//commandLines[35][2] = installData.getTwUserName()+".cert.pem";
			commandLines[35][2] = "/usr/share/taskd/pki/"+installData.getTwUserName()+".cert.pem"; //abs
			commandLines[35][3] = "/home/"+installData.getLinuxUserName()+"/.task/";
			commandLines[35][4] = "/usr/share/taskd/pki/";
			commandLines[35][5] = "TASKDDATA=/var/taskd";
			commands[35].setCommandLines(commandLines[35]);
			commands[35].setEnvVarContent("/var/task");
			commands[35].setEnvVarName("TASKDDATA");
			commands[35].setWorkingPath("");
			
			
			//sudo cp First.key.pem /home/a/.task
			// to: sudo cp <taskwarrior username>.key.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// TODO: verify the First.key.pem file is copied to the /.task/folder
			commandLines[36] = new String[6];
			commandLines[36][0] = "sudo";
			commandLines[36][1] = "cp"; 
			//commandLines[35][2] = installData.getTwUserName()+".key.pem";
			commandLines[36][2] = "/usr/share/taskd/pki/"+installData.getTwUserName()+".key.pem"; //abs
			commandLines[36][3] = "/home/"+installData.getLinuxUserName()+"/.task/";
			commandLines[36][4] = "/usr/share/taskd/pki/";
			commandLines[36][5] = "TASKDDATA=/var/taskd";
			commands[36].setCommandLines(commandLines[35]);
			commands[36].setEnvVarContent("/var/task");
			commands[36].setEnvVarName("TASKDDATA");
			commands[36].setWorkingPath("");
			
			//sudo cp ca.cert.pem /home/a/.task
			// to: sudo cp ca.cert.pem /home/<Ubuntu username>/.task
			//working directory: /usr/share/taskd/pki/
			// TODO: verify the ca.cert.pem file is copied to the /.task/folder
			commandLines[37] = new String[6];
			commandLines[37][0] = "sudo";
			commandLines[37][1] = "cp"; 
			//commandLines[37][2] = "ca.cert.pem";
			commandLines[37][2] = "/usr/share/taskd/pki/"+"ca.cert.pem"; //abs
			commandLines[37][3] = "/home/"+installData.getLinuxUserName()+"/.task/";
			commandLines[37][4] = "/usr/share/taskd/pki/";
			commandLines[37][5] = "TASKDDATA=/var/taskd";
			commands[37].setCommandLines(commandLines[35]);
			commands[37].setEnvVarContent("/var/task");
			commands[37].setEnvVarName("TASKDDATA");
			commands[37].setWorkingPath("");
			
			//sudo task config taskd.certificate -- /home/a/.task/First.cert.pem
			// to: sudo task config taskd.certificate -- /home/<Ubuntu username>/.task/<taskwarrior username>.cert.pem
			//working directory: /usr/share/taskd/pki/
			commandLines[38] = new String[8];
			commandLines[38][0] = "yes | sudo";
			commandLines[38][1] = "task"; 
			commandLines[38][2] = "config";
			commandLines[38][3] = "taskd.certificate";
			commandLines[38][4] = "--";
			commandLines[38][5] = "/home/"+installData.getLinuxUserName()+"/.task/"+installData.getTwUserName()+".cert.pem";
			commandLines[38][6] = "/usr/share/taskd/pki/";
			commandLines[38][7] = "TASKDDATA=/var/taskd";
			commands[38].setCommandLines(commandLines[35]);
			commands[38].setEnvVarContent("/var/task");
			commands[38].setEnvVarName("TASKDDATA");
			commands[38].setWorkingPath("");
			
			
			// sudo task config taskd.key -- /home/a/.task/First.key.pem
			// to: sudo task config taskd.key -- /home/<Ubuntu username>/.task/<taskwarrior username>.key.pem
			//working directory: /usr/share/taskd/pki/
			commandLines[39] = new String[8];
			commandLines[39][0] = "yes | sudo";
			commandLines[39][1] = "task"; 
			commandLines[39][2] = "config";
			commandLines[39][3] = "taskd.key";
			commandLines[39][4] = "--";
			commandLines[39][5] = "/home/"+installData.getLinuxUserName()+"/.task/"+installData.getTwUserName()+".key.pem";
			commandLines[39][6] = "/usr/share/taskd/pki/";
			commandLines[39][7] = "TASKDDATA=/var/taskd";
			commands[39].setCommandLines(commandLines[36]);
			commands[39].setEnvVarContent("/var/task");
			commands[39].setEnvVarName("TASKDDATA");
			commands[39].setWorkingPath("");
			
			// sudo task config taskd.ca -- /home/a/.task/ca.cert.pem
			// to: sudo task config taskd.ca -- /home/<Ubuntu username>/.task/ca.cert.pem
			//working directory: /usr/share/taskd/pki/
			commandLines[40] = new String[8];
			commandLines[40][0] = "yes | sudo";
			commandLines[40][1] = "task"; 
			commandLines[40][2] = "config";
			commandLines[40][3] = "taskd.ca";
			commandLines[40][4] = "--";
			commandLines[40][5] = "/home/"+installData.getLinuxUserName()+"/.task/ca.cert.pem";
			commandLines[40][6] = "/usr/share/taskd/pki/";
			commandLines[40][7] = "TASKDDATA=/var/taskd";
			commands[40].setCommandLines(commandLines[40]);
			commands[40].setEnvVarContent("/var/task");
			commands[40].setEnvVarName("TASKDDATA");
			commands[40].setWorkingPath("");
			
			// sudo task config taskd.server -- 0.0.0.0:53589
			// to: sudo task config taskd.server -- 0.0.0.0:53589
			//working directory: /usr/share/taskd/pki/
			commandLines[41] = new String[8];
			commandLines[41][0] = "yes | sudo";
			commandLines[41][1] = "task"; 
			commandLines[41][2] = "config";
			commandLines[41][3] = "taskd.server";
			commandLines[41][4] = "--";
			commandLines[41][5] = installData.getServerName()+":"+installData.getServerPort();
			commandLines[41][6] = "/usr/share/taskd/pki/";
			commandLines[41][7] = "TASKDDATA=/var/taskd";
			commands[41].setCommandLines(commandLines[41]);
			commands[41].setEnvVarContent("/var/task");
			commands[41].setEnvVarName("TASKDDATA");
			commands[41].setWorkingPath("");
			
			// sudo task config taskd.credentials -- Public/First/b6634b72-04a3-4220-8d49-6d56as4tefb
			// to: sudo task config taskd.credentials -- <taskwarrior organisation>/<taskwarrior username>/<taskwarrior uuid>
			//working directory: /usr/share/taskd/pki/
			commandLines[42] = new String[8];
			commandLines[42][0] = "yes | sudo";
			commandLines[42][1] = "task"; 
			commandLines[42][2] = "config";
			commandLines[42][3] = "taskd.credentials";
			commandLines[42][4] = "--";
			commandLines[42][5] = installData.getTwOrganisation()+"/"+installData.getTwUserName()+"/"+installData.getTwUuid();
			commandLines[42][6] = "/usr/share/taskd/pki/";
			commandLines[42][7] = "TASKDDATA=/var/taskd";
			commands[42].setCommandLines(commandLines[42]);
			commands[42].setEnvVarContent("/var/task");
			commands[42].setEnvVarName("TASKDDATA");
			commands[42].setWorkingPath("");
		}
//		return commands;
//	}
//	
	/**
	 * The second last entry contains the working path
	 * the last entry contains the environment variable
	 * Set the environment path with the command.
	 * @param testRun
	 * @param linuxPath
	 * @param vars
	 * @param storeUserInput
	 * @param serverName
	 * @param serverPort
	 * @return
	 */
	//public static String[][] generateThirdCommands(InstallData installData, boolean testRun,String linuxPath,String vars,String[] storeUserInput,String serverName,String serverPort) {
		//String[][] commands = new String[50][1];
		String twUuid;
		//String directoryPath = new String("/var/taskd/orgs/"+installData.getTwOrganisation()+"/users/");
		//String directoryPath = new String("/var/taskd/orgs/"+installData.getTwUuid()+"/users/");
		
		if (!installData.isTestrun()) {
			// write fillers for command 0:
			commandLines[43] = new String[4];
			commandLines[43][0] = "echo";
			commandLines[43][1] = "filler";
			commandLines[43][2] = installData.getLinuxPath();
			commandLines[43][3] = "TASKDDATA=/var/taskd";
			commands[43].setCommandLines(commandLines[43]);
			commands[43].setEnvVarContent("/var/task");
			commands[43].setEnvVarName("TASKDDATA");
			commands[43].setWorkingPath("");
			
			// taskdctl start
			// to: 
			//working directory: /usr/share/taskd/pki/
			//Environment variable: TASKDDATA=/var/taskd
			commandLines[44] = new String[4];
			commandLines[44][0] = "taskdctl";
			commandLines[44][1] = "start"; 
			commandLines[44][2] = "/usr/share/taskd/pki/";
			commandLines[44][3] = "TASKDDATA=/var/taskd";
			commands[44].setCommandLines(commandLines[44]);
			commands[44].setEnvVarContent("/var/task");
			commands[44].setEnvVarName("TASKDDATA");
			commands[44].setWorkingPath("");
			
			// sudo task sync init 
			// to: 
			//working directory: /usr/share/taskd/pki/
			commandLines[45] = new String[6];
			commandLines[45][0] = "yes | sudo";
			commandLines[45][1] = "task"; 
			commandLines[45][2] = "sync";
			commandLines[45][3] = "init";
			commandLines[45][4] = "/usr/share/taskd/pki/";
			commandLines[45][5] = "TASKDDATA=/var/taskd";
			commands[45].setCommandLines(commandLines[45]);
			commands[45].setEnvVarContent("/var/task");
			commands[45].setEnvVarName("TASKDDATA");
			commands[45].setWorkingPath("");
			
			// sudo task config taskd.trust -- ignore hostname
			// to: 
			//working directory: /usr/share/taskd/pki/
			commandLines[46] = new String[9];
			commandLines[46][0] = "sudo";
			commandLines[46][1] = "task"; 
			commandLines[46][2] = "config";
			commandLines[46][3] = "taskd.trust";
			commandLines[46][4] = "--";
			commandLines[46][5] = "ignore";
			commandLines[46][6] = "hostname";
			commandLines[46][7] = "/usr/share/taskd/pki/";
			commandLines[46][8] = "TASKDDATA=/var/taskd";
			commands[46].setCommandLines(commandLines[46]);
			commands[46].setEnvVarContent("/var/task");
			commands[46].setEnvVarName("TASKDDATA");
			commands[46].setWorkingPath("");
			
			// taskdctl stop
			// to: 
			//working directory: /usr/share/taskd/pki/
			commandLines[47] = new String[4];
			commandLines[47][0] = "taskdctl";
			commandLines[47][1] = "stop"; 
			commandLines[47][2] = "/usr/share/taskd/pki/";
			commandLines[47][3] = "TASKDDATA=/var/taskd";
			commands[47].setCommandLines(commandLines[47]);
			commands[47].setEnvVarContent("/var/task");
			commands[47].setEnvVarName("TASKDDATA");
			commands[47].setWorkingPath("");
			
			//sudo taskd config --data $TASKDDATA
			// to: sudo taskd config --data /var/taskd 
			//working directory: /var/taskd/
			commandLines[48] = new String[7];
			commandLines[48][0] = "sudo";
			commandLines[48][1] = "taskd"; 
			commandLines[48][2] = "config";
			commandLines[48][3] = "--data";
			commandLines[48][4] = "/var/taskd/";
			commandLines[48][5] = "/var/taskd/";
			commandLines[48][6] = "TASKDDATA=/var/taskd";
			commands[48].setCommandLines(commandLines[48]);
			commands[48].setEnvVarContent("/var/task");
			commands[48].setEnvVarName("TASKDDATA");
			commands[48].setWorkingPath("");
			
			// sudo -s
			// export TASKDDATA=/var/taskd
			// taskdctl start
			// to: 
			//working directory: /var/taskd/
			commandLines[49] = new String[4];
			commandLines[49][0] = "sudo";
			commandLines[49][1] = "-s"; 
			commandLines[49][2] = "/var/taskd";
			commandLines[49][3] = "TASKDDATA=/var/taskd";
			commands[49].setCommandLines(commandLines[49]);
			commands[49].setEnvVarContent("/var/task");
			commands[49].setEnvVarName("TASKDDATA");
			commands[49].setWorkingPath("");
			
			commandLines[50] = new String[4];
			commandLines[50][0] = "taskdctl";
			commandLines[50][1] = "start";
			commandLines[50][2] = "/usr/share/taskd/pki/";
			commandLines[50][3] = "TASKDDATA=/var/taskd";
			commands[50].setCommandLines(commandLines[50]);
			commands[50].setEnvVarContent("/var/task");
			commands[50].setEnvVarName("TASKDDATA");
			commands[50].setWorkingPath("");
			// sudo task sync init
			// to: 
			//working directory: /var/taskd/
			commandLines[51] = new String[6];
			commandLines[51][0] = "yes | sudo";
			commandLines[51][1] = "task"; 
			commandLines[51][2] = "sync";
			commandLines[51][3] = "init";
			commandLines[51][4] = "/var/taskd/";
			commandLines[51][5] = "TASKDDATA=/var/taskd";
			commands[51].setCommandLines(commandLines[51]);
			commands[51].setEnvVarContent("/var/task");
			commands[51].setEnvVarName("TASKDDATA");
			commands[51].setWorkingPath("");
		}
		return commands;
	}
}
