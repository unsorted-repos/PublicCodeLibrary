package autoInstallTaskwarrior;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;



public class SetEnvVar {
    public static void setEnvVar() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "echo $u");
        Map<String, String> env = pb.environment();
        // set environment variable u
        env.put("u", "util/");

        Process p = pb.start();
        String output = loadStream(p.getInputStream());
        String error = loadStream(p.getErrorStream());
        int rc = p.waitFor();
        System.out.println("Process ended with rc=" + rc);
        System.out.println("\nStandard Output:\n");
        System.out.println(output);
        System.out.println("\nStandard Error:\n");
        System.out.println(error);
        
        System.exit(0);
    }

    private static String loadStream(InputStream s) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(s));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line).append("\n");
        return sb.toString();
    }
    
}

