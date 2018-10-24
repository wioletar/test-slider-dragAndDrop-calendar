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
import java.util.List;

public class CalendarPage {
    WebDriver driver;
    String yearWanted;
    String monthWanted;
    String dayWanted;


    public CalendarPage(WebDriver driver) {
        this.driver = driver;
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

    public void swtichToFrame() {
        driver.switchTo().frame(frame);
    }

    public void clickToDatepicker() {
        datePicker.click();
    }

    public void splitInputDate(String date) {
        if (date.equalsIgnoreCase("today")) {
            String todaysDate = String.valueOf(LocalDate.now());
            System.out.println(todaysDate);
            String[] dateBroken = todaysDate.split("-");
            yearWanted = dateBroken[0];
            monthWanted = dateBroken[1];
            dayWanted = dateBroken[2];
        } else {
            String[] dateBroken = date.split("\\.");
            yearWanted = dateBroken[2];
            monthWanted = dateBroken[1];
            dayWanted = dateBroken[0];

            if (monthWanted.startsWith("0")) {
                monthWanted = monthWanted.replace("0", "");
            }
            if (dayWanted.startsWith("0")) {
                dayWanted = dayWanted.replace("0", "");
            }
        }
    }

    public void getDate() {
        int year = Integer.parseInt(yearWanted);
        int month = Integer.parseInt(monthWanted);

        int yearNow = Integer.parseInt(yearElement.getText());

        while (yearNow != year) {
            if (yearNow < year) {
                nextButton.click();
                int searchedYearNext = Integer.parseInt(yearElement.getText());
                yearNow = searchedYearNext;
            } else {
                previousButton.click();
                int searchedYearPrevious = Integer.parseInt(yearElement.getText());
                yearNow = searchedYearPrevious;
            }
        }
        int monthNow = Month.valueOf(monthElement.getText().toUpperCase()).getValue();

        while (monthNow != month) {
            if (monthNow < month) {
                nextButton.click();
                int mosearchedMonthNext = Month.valueOf(monthElement.getText().toUpperCase()).getValue();
                monthNow = mosearchedMonthNext;
            } else {
                previousButton.click();
                int searchedMonthPrevious = Month.valueOf(monthElement.getText().toUpperCase()).getValue();
                monthNow = searchedMonthPrevious;
            }
        }
        for (WebElement element: daysElements) {
            if (Integer.valueOf(element.getAttribute("data-month")).equals(month - 1)){
                String dayOfMonth = innerDaysElement.getText();
                if (dayOfMonth.equalsIgnoreCase(dayWanted)){
                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    element = wait.until(ExpectedConditions.elementToBeClickable(innerDaysElement));
                    element.click();
                }
            }
        }
    }

    public void isCorrectDatePicked(String expectedDate){
       Assert.assertEquals(isDatePicked.getText(), expectedDate);
    }


}
