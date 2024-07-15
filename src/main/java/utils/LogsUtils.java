package utils;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogsUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogsUtils.class);

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
                logger.info("Folder logs was created successfully.");
            } else {
                logger.error("Failed to create folder logs.");
            }
        }
    }

}
