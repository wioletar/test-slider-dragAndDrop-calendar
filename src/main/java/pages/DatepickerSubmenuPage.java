package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatepickerSubmenuPage {
    WebDriver driver;

    public DatepickerSubmenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[contains(text(),'Dates in other months')]")
    private WebElement datesInOtherMonths;

    public void chooseDatesInOtherMonths(){
    datesInOtherMonths.click();
    }
}
