package customSortServerV5;

public class OSValidator {

    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * Get the OS.
     * TODO: Throw error at incorrect os.
     * @return
     */
    public static String returnOS() {

//        System.out.println(OS);

        if (isWindows()) {
//            System.out.println("This is Windows");
            return "windows";
        } else if (isMac()) {
//            System.out.println("This is Mac");
            return null;
        } else if (isUnix()) {
//            System.out.println("This is Unix or Linux");
            return "ubuntu";
        } else if (isSolaris()) {
//            System.out.println("This is Solaris");
        } else {
//            System.out.println("Your OS is not support!!");
            return null;
        }
        return null;
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return OS.contains("sunos");
    }
    public static String getOS(){
        if (isWindows()) {
            return "win";
        } else if (isMac()) {
            return "osx";
        } else if (isUnix()) {
            return "uni";
        } else if (isSolaris()) {
            return "sol";
        } else {
            return "err";
        }
    }

}