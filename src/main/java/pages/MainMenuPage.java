package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class MainMenuPage extends BasePage{


    public MainMenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        waitUntilElementsVisible();
    }

    @FindBy(xpath = "//a[contains(text(),'Datepicker')]")
    private WebElement datapicker;

    @FindBy(xpath = "//a[contains(text(),'Droppable')]")
    private WebElement droppable;

    @FindBy(xpath = "//a[contains(text(),'Slider')]")
    private WebElement slider;

    public void waitUntilElementsVisible(){
        waitForElements(Arrays.asList(datapicker,droppable,slider));
    }
    public void openDatepicker() {
        datapicker.click();
    }

    public void openDroppable() {
        droppable.click();
    }

    public void openSlider() {
        slider.click();
    }
}