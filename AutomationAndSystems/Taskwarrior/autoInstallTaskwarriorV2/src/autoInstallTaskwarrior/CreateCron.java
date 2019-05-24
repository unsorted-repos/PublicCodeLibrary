package autoInstallTaskwarrior;


import java.io.*;
import java.util.*;

/**
 * Source: https://community.oracle.com/thread/1830946
 */
public class CreateCron {
    ArrayList jobs;
    Runtime rt;

    CreateCron() {
     rt = Runtime.getRuntime();
     jobs = new ArrayList();
    }

    void readCron() {
     //String[] list = { "crontab", "-l" };
     //String[] list = { "sudo", "crontab", "-l" };
     String[] list = { "crontab", "-l" };
     jobs = new ArrayList();
     try {
         // Stick a job into crontab
         Process child = rt.exec(list);
         BufferedReader cronout = new BufferedReader(new InputStreamReader(child.getInputStream()));
         String cronjob = cronout.readLine();
         while (cronjob != null) {
          jobs.add(cronjob);
          cronjob = cronout.readLine();
         }
         child.waitFor();
     }
     catch(IOException e) {
         System.err.println("IOException starting process!");
     }
     catch(InterruptedException e) {
         System.err.println("Interrupted waiting for process!");
     }
    }

    void listJobs() {
     Iterator iter = jobs.iterator();
     while (iter.hasNext()) {
         System.out.println("job="+(String)iter.next());
     }
    }

    void checkJobs(String description,ArrayList listOfJobs) {
        Iterator iter = listOfJobs.iterator();
        while (iter.hasNext()) {
            System.out.println(description+(String)iter.next());
        }
       }
    
    void writeJobs() {
     String[] edit = { "crontab"};
     //String[] edit = { "sudo", "crontab"};
     try {
         // Stick a job into crontab
         Process child = rt.exec(edit);
         PrintWriter cronIn = new PrintWriter(child.getOutputStream());
         Iterator iter = jobs.iterator();
         while (iter.hasNext()) {
          cronIn.println((String)iter.next());
         }
         cronIn.close();
         child.waitFor();
     }
     catch(IOException e) {
         System.err.println("IOException starting process!");
     }
     catch(InterruptedException e) {
         System.err.println("Interrupted waiting for process!");
     }
    }

    /**
     * TODO: remove hardcoded cronjob paths
     * TODO: uncomment
     */
    void doStuff(InstallData installData) {
     readCron();
     listJobs();
     String tempJobString;
     
     //before adding the jobs, check whether they are in it.
     checkJobs("already present=",jobs);
     
     for (int i = 0; i < installData.getCronJobs().length; i++) {
    	 tempJobString=installData.getCronJobs()[i].getCompleteCommand();
    	 if (!jobs.contains(tempJobString)) {
    		 if (checkIfCronShouldBeInstalled(installData,i)) {
    			 jobs.add(tempJobString);
            	 System.out.println("Adding="+tempJobString);	 
    		 }
    	 }else {
    		 System.out.println("The list already contained:"+tempJobString);
    	 }
     }
     
     writeJobs();
     readCron();
     listJobs();
    }

    /**
     * This method checks whether the cronjob should be installed or not. 
     * @param installData
     * @param cronjob
     */
    private boolean checkIfCronShouldBeInstalled(InstallData installData, int cronJobIndex) {
    	CronJob tempCronJob = installData.getCronJobs()[cronJobIndex];
    	if (!installData.isServer() && !tempCronJob.isUseInClient()) {
    		System.out.println("It is client, and this cron should not be used in client");
    		return false;
    	}else {
    		return true;
    	}
    }
}