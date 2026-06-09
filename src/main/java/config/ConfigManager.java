package config;

public class ConfigManager {

    public static final String BASE_URL =
            System.getProperty("env", "https://www.automationexercise.com/");

    public static final String BROWSER =
            System.getProperty("browser", "chromium");

    public static final boolean HEADLESS =
            Boolean.parseBoolean(
                    System.getProperty("headless", "true")
            );
}

