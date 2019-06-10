package ua.barnacle.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Waiter {

    private static WebDriver driver;

    public static void init(WebDriver driver) {
        Waiter.driver = driver;
    }

    public static WebElement waitForVisibilityOfElement(By locator, long sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForVisibilityOfElement(WebElement element, long sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        return wait.until(ExpectedConditions.visibilityOfAllElements(Collections.singletonList(element)));
    }

    public static WebElement waitForVisibilityOfElement(By locator, long timeOutSec, long pollingMs) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeOutSec, SECONDS)
                .pollingEvery(pollingMs, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
