package ua.barnacle.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    private static String driverFilePath;

    static {
        driverFilePath = "driver/chromedriver_";
        driverFilePath += System.getProperty("os.name").toLowerCase().replaceAll(" ", "");
    }

    @Override
    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", driverFilePath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--kiosk");
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().setSize(new org.openqa.selenium.Dimension(1500, 2048));
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        return this.driver;
    }

    @Override
    public void quitWebDriver() {
        this.driver.quit();
    }
}
