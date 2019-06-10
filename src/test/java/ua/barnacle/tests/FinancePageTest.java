package ua.barnacle.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ua.barnacle.pages.FinanceIndexPage;
import ua.barnacle.pages.quote.QuotePage;
import ua.barnacle.pages.quote.dataobjects.FilterData;
import ua.barnacle.pages.quote.dataobjects.HistoricalData;
import ua.barnacle.reports.Steps;

import java.util.Arrays;

@Feature("Finance")
public class FinancePageTest extends AbstractTest {

    @Test(enabled = false)
    public void testIfRightEmptyResultIsShownForHistoricalDataTable() {
        FinanceIndexPage financeIndexPage = FinanceIndexPage.open(this.driver);
        QuotePage quotePage = financeIndexPage.searchByQuery("AMZN");
        quotePage.openHistoricalDataTab();

        FilterData filterData = new FilterData("Max", "Dividends Only", "Weekly");
        HistoricalData historicalData = quotePage.filterHistoricalData(filterData);

        Steps.print("Assert that Historical Data table doesn't contain any data");
        this.softAssert.assertEquals(historicalData.getRowCount(), 0,
                "Historical Data table shouldn't contain any data");

        Steps.print("Assert that Historical Data table contains \"No Dividends\" message");
        this.softAssert.assertEquals(historicalData.getEmptyResultMessage(), "No Dividends",
                "Historical Data table should contain \"No Dividends\" message");
        this.softAssert.assertAll();
    }

    @Test()
    public void testIfHistoricalDataIsRelevantToFilterSettings() {
        FinanceIndexPage financeIndexPage = FinanceIndexPage.open(this.driver);
        QuotePage quotePage = financeIndexPage.searchByQuery("AMZN");
        quotePage.openHistoricalDataTab();

        FilterData filterData = new FilterData("6/1/2019", "6/3/2019", "Historical Prices", "Daily");
        HistoricalData historicalData = quotePage.filterHistoricalData(filterData);

        // This verification should be performed with the more advanced approach.
        // There will be only one row verification at this stage

        String[][] expectedData = new String[][]{
                {"May 31, 2019", "1,790.01", "1,795.59", "1,772.70", "1,775.07", "1,775.07", "4,618,800"}
        };

        String[][] actualData = historicalData.getData();

        Steps.print("Assert that Historical Data table doens't contain more that 1 row");
        assertEquals(historicalData.getRowCount(), 1,
                "Historical Data table contains invalid rows count");

        for (int i = 0; i < actualData.length; i++) {
            Steps.print("Assert that Historical Data table contains " + Arrays.toString(expectedData[i]) + " value");
            this.softAssert.assertEquals(actualData[i], expectedData[i]);
        }

        this.softAssert.assertAll();
    }

    @Test()
    @Story("Test if Historical Data table continuous loading works")
    public void testIfHistoricalDataTableContinuousLoadingWorks() {
        FinanceIndexPage financeIndexPage = FinanceIndexPage.open(this.driver);
        QuotePage quotePage = financeIndexPage.searchByQuery("AMZN");
        quotePage.scrollHistoricalDataTable();

        Steps.print("Assert that Historical Data table contains more than 90 rows");
        assertTrue(quotePage.getHistoricalDataTableRowsCount() > 90,
                "Historical data table doesn't contain required rows count");

    }
}
