package utils;

import java.io.File;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                log.info("Folder logs was created successfully.");
            } else {
                log.error("Failed to create folder logs.");
            }
        }
    }

}
