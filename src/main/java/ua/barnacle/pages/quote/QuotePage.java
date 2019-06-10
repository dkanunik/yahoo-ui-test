package ua.barnacle.pages.quote;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.barnacle.commons.BaseUIElement;
import ua.barnacle.commons.Waiter;
import ua.barnacle.pages.quote.components.HistoricalDataFilterComponent;
import ua.barnacle.pages.quote.components.HistoricalDataTableComponent;
import ua.barnacle.pages.quote.dataobjects.FilterData;
import ua.barnacle.pages.quote.dataobjects.HistoricalData;
import ua.barnacle.types.TabLabel;

public class QuotePage extends BaseUIElement {

    private static final String XPATH_TABMENU_CONTAITER = "//div[@id='quote-nav']";

    private static final int ATTEMPTS_TO_SCROLL = 10;

    @FindBy(xpath = "//span[.='*Close price adjusted for splits.']")
    private WebElement historicalDataTableFooter;

    private HistoricalDataFilterComponent historicalDataFilterComponent;

    private HistoricalDataTableComponent historicalDataTableComponent;

    public QuotePage(WebDriver driver) {
        super(driver);
        this.historicalDataFilterComponent = new HistoricalDataFilterComponent(this.driver);
        this.historicalDataTableComponent =  new HistoricalDataTableComponent(this.driver);
    }

    @Step("Scroll Historical Data table")
    public void scrollHistoricalDataTable() {
        followByTab(TabLabel.HISTORICAL_DATA);
        waitForTableData();
    }

    @Step("Follow to Historical Data tab")
    public void openHistoricalDataTab() {
        followByTab(TabLabel.HISTORICAL_DATA);
    }

    public int getHistoricalDataTableRowsCount() {
        return driver.findElements(By.xpath("//div[@id='Col1-1-HistoricalDataTable-Proxy']//tr")).size();
    }

    @Step("Filter data by values {0}")
    public HistoricalData filterHistoricalData(FilterData filterData) {
        this.historicalDataFilterComponent.defineFilterWithDataObject(filterData);
        this.historicalDataFilterComponent.performFiltering();
        return this.historicalDataTableComponent.getHistoricalData();
    }

    private void followByTab(TabLabel tabLabel) {
        Waiter.waitForVisibilityOfElement(By.xpath(XPATH_TABMENU_CONTAITER), 5);
        WebElement tabElement = this.driver.findElement(By.linkText(tabLabel.getText()));
        tabElement.click();
    }

    private void waitForTableData() {
        int historicalDataTableFooterYPoint = 0;

        Waiter.waitForVisibilityOfElement(this.historicalDataTableFooter, 3);

        for (int i = 0; i <= ATTEMPTS_TO_SCROLL; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
            if (historicalDataTableFooterYPoint == this.historicalDataTableFooter.getLocation().getY()) {
                break;
            } else {
                historicalDataTableFooterYPoint = this.historicalDataTableFooter.getLocation().getY();
                Waiter.sleep(1);
            }
        }
    }
}
