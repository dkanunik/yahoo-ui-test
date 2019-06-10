package ua.barnacle.pages.quote.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.barnacle.commons.BaseUIElement;
import ua.barnacle.commons.Waiter;
import ua.barnacle.pages.quote.dataobjects.FilterData;

public class HistoricalDataFilterComponent extends BaseUIElement {

    @FindBy(css = "input[data-test='date-picker-full-range']")
    private WebElement datePicker;

    @FindBy(css = "input[data-test='date-picker-menu']")
    private WebElement datePickerMenu;

    @FindBy(css = "span[data-test='historicalFilter-selected']")
    private WebElement showPicker;

    @FindBy(css = "div[data-test='historicalFilter-menu']")
    private WebElement showMenu;

    @FindBy(css = "span[data-test='historicalFrequency-selected']")
    private WebElement frequencyPicker;

    @FindBy(css = "div[data-test='historicalFrequency-menu']")
    private WebElement frequencyMenu;

    @FindBy(xpath = "//span[.='Done']")
    private WebElement doneButton;

    @FindBy(css = "drop-down-selector")
    private WebElement filterContainer;

    @FindBy(css = "input[name ='startDate']")
    private WebElement startDateInput;

    @FindBy(css = "input[name ='endDate']")
    private WebElement endDateInput;

    @FindBy(xpath = "//span[.='Apply']")
    private WebElement applyButton;

    public HistoricalDataFilterComponent(WebDriver driver) {
        super(driver);
    }

    public void defineFilterWithDataObject(FilterData filterData) {
        if (filterData.getTimePeriod() != null) {
            setTimePeriodFilter(filterData);
        }

        if (filterData.getShow() != null) {
            setShowFilter(filterData);
        }

        if (filterData.getShow() != null) {
            setFrequencyFilter(filterData);
        }

        if (filterData.getStartDate() != null) {
            setStartDate(filterData);
        }

        if (filterData.getEndDate() != null) {
            setEndDate(filterData);
        }
    }

    public void performFiltering() {
        this.applyButton.click();
        Waiter.sleep(1); //todo: replace the pause by a relevant waiter
    }

    private void setFrequencyFilter(FilterData filterData) {
        Waiter.waitForVisibilityOfElement(this.frequencyPicker, 3);
        this.frequencyPicker.click();
        Waiter.waitForVisibilityOfElement(this.frequencyMenu, 1);
        WebElement frequencyPickerOptionElement = this.driver.findElement(By.xpath("//span[.='" + filterData.getFrequency() +"']"));
        frequencyPickerOptionElement.click();
    }

    private void setShowFilter(FilterData filterData) {
        Waiter.waitForVisibilityOfElement(this.showPicker, 3);
        this.showPicker.click();
        Waiter.waitForVisibilityOfElement(this.showMenu, 1);
        WebElement showPickerOptionElement = this.driver.findElement(By.xpath("//span[.='" + filterData.getShow() +"']"));
        showPickerOptionElement.click();
    }

    private void setTimePeriodFilter(FilterData filterData) {
        Waiter.waitForVisibilityOfElement(this.datePicker, 3);
        this.datePicker.click();
        Waiter.waitForVisibilityOfElement(this.datePickerMenu, 2);
        WebElement datePickerOptionElement = this.driver.findElement(By.xpath("//span[.='" + filterData.getTimePeriod() +"']"));
        datePickerOptionElement.click();
        this.doneButton.click();
    }

    private void setStartDate(FilterData filterData) {
        Waiter.waitForVisibilityOfElement(this.datePicker, 3);
        this.datePicker.click();
        Waiter.waitForVisibilityOfElement(this.startDateInput, 1);
        this.startDateInput.clear();
        this.startDateInput.sendKeys(filterData.getStartDate());
        this.doneButton.click();
    }

    private void setEndDate(FilterData filterData) {
        Waiter.waitForVisibilityOfElement(this.datePicker, 3);
        this.datePicker.click();
        Waiter.waitForVisibilityOfElement(this.endDateInput, 1);
        this.endDateInput.clear();
        this.endDateInput.sendKeys(filterData.getEndDate());
        this.doneButton.click();
    }
}
