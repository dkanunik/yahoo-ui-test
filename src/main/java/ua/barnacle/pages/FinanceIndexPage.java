package ua.barnacle.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ua.barnacle.commons.BaseUIElement;
import ua.barnacle.commons.Waiter;
import ua.barnacle.pages.quote.QuotePage;

public class FinanceIndexPage extends BaseUIElement {

    private static final String URL_FINANCE_PAGE = "https://finance.yahoo.com/";

    private static final String XPATH_OPTION_IN_ASSIST = "//div[@data-test='search-assist-input-sugglst']//div[@title=''{0}'']";

    @FindBy(id = "yfin-usr-qry")
    private WebElement searchField;

    public FinanceIndexPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open page: " + URL_FINANCE_PAGE)
    public static FinanceIndexPage open(WebDriver driver) {
        driver.get(URL_FINANCE_PAGE);
        FinanceIndexPage financeIndexPage = new FinanceIndexPage(driver);
        financeIndexPage.waitForReadyState();
        return financeIndexPage;
    }

    private void waitForReadyState() {
        Waiter.waitForVisibilityOfElement(this.searchField, 3);
    }

    @Step("Search by query {0}")
    public QuotePage searchByQuery(String query) {
        setSearchFieldQuery(query);
        selectOptionInSearchAssist(query);

        return new QuotePage(this.driver);
    }

    private void setSearchFieldQuery(String query) {
        Actions actions = new Actions(this.driver);
        actions.moveToElement(this.searchField);
        this.searchField.sendKeys(query);
    }

    private void selectOptionInSearchAssist(String query) {
        By optionLocator = By.xpath(XPATH_OPTION_IN_ASSIST.replace("'{0}'", query));
        WebElement optionElement = Waiter.waitForVisibilityOfElement(optionLocator, 5, 100);
        optionElement.click();
    }
}
