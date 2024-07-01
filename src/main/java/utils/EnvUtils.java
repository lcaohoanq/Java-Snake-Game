package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
    private static Dotenv dotenv;

    static {
        try {
            dotenv = Dotenv.configure().load();
        } catch (Exception e) {
            System.err.println("Error initializing EnvUtils: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        try {
            return dotenv.get(key);
        } catch (Exception e) {
            System.err.println("Error retrieving environment variable: " + e.getMessage());
            e.printStackTrace();
            return null; // Return null or a default value in case of error
        }
    }

}
