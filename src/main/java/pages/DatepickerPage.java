package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DatepickerPage extends BasePage {

    String expectedYear;
    String expectedMonth;
    String expectedDay;


    public DatepickerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/iframe")
    private WebElement frame;

    @FindBy(xpath = "//*[@id=\"datepicker\"]")
    private WebElement datePicker;

    @FindBy(css = "input[class='hasDatepicker']")
    private WebElement isDatePicked;

    @FindBy(css = "span[class='ui-datepicker-year']")
    private  WebElement yearElement;

    @FindBy(css = "span[class='ui-datepicker-month']")
    private WebElement monthElement;

    @FindBy(css = "tbody tr td[data-handler='selectDay']")
    private List<WebElement> daysElements;

    @FindBy(css = "a[class='ui-state-default']")
    private WebElement innerDaysElement;

    @FindBy(css ="div .ui-datepicker-next" )
    private WebElement nextButton;

    @FindBy(css = "div .ui-datepicker-prev")
    private WebElement previousButton;

    public DatepickerPage swtichToFrame() {
        driver.switchTo().frame(frame);
        return this;
    }

    public DatepickerPage clickToDatepicker() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.elementToBeClickable(datePicker));
        datePicker.click();
        return this;
    }

    public DatepickerPage splitInputDate(String inputDate) {
        if (inputDate.equalsIgnoreCase("today")) {
            String todaysDate = String.valueOf(LocalDate.now());
            System.out.println(todaysDate);
            String[] splitInputDate = todaysDate.split("-");
            expectedYear = splitInputDate[0];
            expectedMonth = splitInputDate[1];
            expectedDay = splitInputDate[2];
        } else {
            String[] splitInputDate = inputDate.split("\\.");
            expectedYear = splitInputDate[2];
            expectedMonth = splitInputDate[1];
            expectedDay = splitInputDate[0];
            if (expectedMonth.startsWith("0")) {
                expectedMonth = expectedMonth.replace("0", "");
            }
            if (expectedDay.startsWith("0")) {
                expectedDay = expectedDay.replace("0", "");
            }
        }
        return this;
    }

    public DatepickerPage getDate() {
        goToCorrectYear();
        int month = goToCorrectMonth();
        for (WebElement element: daysElements) {
            if (Integer.valueOf(element.getAttribute("data-month")).equals(month - 1)){
                String dayOfMonth = innerDaysElement.getText();
                if (dayOfMonth.equalsIgnoreCase(expectedDay)){
                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    WebElement newElement = wait.until(ExpectedConditions.elementToBeClickable(innerDaysElement));
                    newElement.click();
                }
            }
        }
        return this;
    }

    private int goToCorrectMonth() {
        int month = Integer.parseInt(expectedMonth);
        int monthInCalendar = Month.valueOf(monthElement.getText().toUpperCase()).getValue();

        while (monthInCalendar != month) {
            if (monthInCalendar < month) {
                nextButton.click();
                int searchedMonthNext = Month.valueOf(monthElement.getText().toUpperCase()).getValue();
                monthInCalendar = searchedMonthNext;
            } else {
                previousButton.click();
                int searchedMonthPrevious = Month.valueOf(monthElement.getText().toUpperCase()).getValue();
                monthInCalendar = searchedMonthPrevious;
            }
        }
        return month;
    }

    private void goToCorrectYear() {
        int year = Integer.parseInt(expectedYear);
        int yearInCalendar = Integer.parseInt(yearElement.getText());

        while (yearInCalendar != year) {
            if (yearInCalendar < year) {
               waitForClickElement(nextButton);
                nextButton.click();
                int searchedYearNext = Integer.parseInt(yearElement.getText());
                yearInCalendar = searchedYearNext;
            } else {
 //               waitForClickElement(previousButton);
                previousButton.click();
                int searchedYearPrevious = Integer.parseInt(yearElement.getText());
                yearInCalendar = searchedYearPrevious;
            }
        }
    }

    public void isCorrectDatePicked(String expectedDate){
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOfAllElements(isDatePicked));
        Assert.assertEquals(isDatePicked.getText(), expectedDate);
    }
}
