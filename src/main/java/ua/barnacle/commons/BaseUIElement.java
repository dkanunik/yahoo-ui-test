package ua.barnacle.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseUIElement {

    protected WebDriver driver;

    public BaseUIElement(WebDriver driver) {
        this.driver = driver;
        Waiter.init(driver);
        PageFactory.initElements(driver, this);
    }
}
