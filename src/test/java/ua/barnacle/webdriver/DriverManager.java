package ua.barnacle.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverManager {

    protected ChromeDriver driver;

    public abstract WebDriver createWebDriver();

    public abstract void quitWebDriver();
}
