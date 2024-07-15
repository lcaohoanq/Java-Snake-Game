package utils;

import java.io.File;

public class LogsUtils {


    /**
     * Checks if a "logs" folder exists in the project's root directory. If it does not exist, the
     * method creates it.
     */
    public static void ensureLogsFolderExists() {
        // Assuming the project's root directory is the current working directory
        String projectRootPath = System.getProperty("user.dir");
        File logsFolder = new File(projectRootPath, "logs");

        if (!logsFolder.exists()) {
            boolean wasCreated = logsFolder.mkdir();
            if (wasCreated) {
                System.out.println("Logs folder was successfully created.");
            } else {
                System.out.println("Failed to create logs folder.");
            }
        } else {
            System.out.println("Logs folder already exists.");
        }
    }

}
