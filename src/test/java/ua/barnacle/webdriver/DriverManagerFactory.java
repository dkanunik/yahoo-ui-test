package ua.barnacle.webdriver;

public class DriverManagerFactory {

    public static DriverManager getDriverManager() {
        DriverManager driverManager;

        String browser = System.getProperty("browser", "");

        switch (browser) {
            case "chrome":
                driverManager = new ChromeDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }

        return driverManager;
    }
}

