package ua.barnacle.pages.quote.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.barnacle.commons.BaseUIElement;
import ua.barnacle.pages.quote.dataobjects.HistoricalData;

import java.util.List;

public class HistoricalDataTableComponent extends BaseUIElement {

    public HistoricalDataTableComponent(WebDriver driver) {
        super(driver);
    }

    public HistoricalData getHistoricalData() {
        HistoricalData historicalData = new HistoricalData();

        List<WebElement> tableRows = getTableRows();
        historicalData.setRowCount(tableRows.size());

        historicalData.setEmptyResultMessage(getEmptyTableMessage());

        historicalData.setData(deriveDataFromHtmlTable());

        return historicalData;
    }

    private List<WebElement> getTableRows() {
        By locator = By.xpath("//table[@data-test='historical-prices']/tbody//tr[count(td) > 1]");
        return this.driver.findElements(locator);
    }

    private String getEmptyTableMessage() {
        By locator = By.xpath("//table[@data-test='historical-prices']/tbody/tr/td/strong/span");
        List<WebElement> list = this.driver.findElements(locator);
        if (list.size() > 0) {
            return list.get(0).getText();
        } else {
            return null;
        }
    }

    private String[][] deriveDataFromHtmlTable() {
        By trListLocator = By.xpath("//table[@data-test='historical-prices']/tbody//tr");
        List<WebElement> trElementList = this.driver.findElements(trListLocator);

        String[][] tableData = new String[trElementList.size()][];

        for (int i = 1; i <= trElementList.size(); i++) {
            By tdListLocator = By.xpath("//table[@data-test='historical-prices']/tbody/tr[" + i + "]//td");
            List<WebElement> tdElementList = this.driver.findElements(tdListLocator);

            String[] tdArr = new String[tdElementList.size()];

            for (int j = 1; j <= tdElementList.size(); j++) {
                By tdElementLocator = By.xpath("//table[@data-test='historical-prices']/tbody/tr[" + i + "]/td[" + j + "]");
                WebElement tdElement = this.driver.findElement(tdElementLocator);
                tdArr[j - 1] = tdElement.getText();
            }

            tableData[i - 1] = tdArr;
        }
        return tableData;
    }
}
