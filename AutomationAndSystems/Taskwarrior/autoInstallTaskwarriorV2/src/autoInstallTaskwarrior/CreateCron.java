package autoInstallTaskwarrior;


import java.lang.*;
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
     String[] list = { "sudo", "crontab", "-l" };
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
         System.out.println((String)iter.next());
     }
    }

    void writeJobs() {
     //String[] edit = { "crontab"};
     String[] edit = { "sudo", "crontab"};
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
    void doStuff() {
     readCron();
     listJobs();
//     jobs.add("*/1 * * * * touch /home/a/maintenance/cronTest");
//     jobs.add("*/1 * * * * sudo touch /home/a/maintenance/cronTestSudo");
//     jobs.add("*/1 * * * * root touch /home/a/maintenance/cronTestSudo");
     writeJobs();
     readCron();
     listJobs();
    }

//    public static void main(String[] args) {
//     CreateCron c = new CreateCron();
//     c.doStuff();
//     c.writeJobs();
//    }
}