package ua.barnacle.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import ua.barnacle.webdriver.DriverManager;
import ua.barnacle.webdriver.DriverManagerFactory;

public class AbstractTest extends Assertion {

    protected SoftAssert softAssert;

    protected WebDriver driver;

    private DriverManager driverManager;

    @BeforeTest
    public void beforeTest() {
        this.driverManager = DriverManagerFactory.getDriverManager();
    }

    @BeforeMethod
    public void setup() {
        this.driver = this.driverManager.createWebDriver();

        this.softAssert = new SoftAssert();
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitWebDriver();
    }
}
