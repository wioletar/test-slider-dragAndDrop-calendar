package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuPage {
    WebDriver driver;

    public MainMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Datepicker')]")
    private WebElement datapicker;

    @FindBy(xpath = "//a[contains(text(),'Droppable')]")
    private WebElement droppable;

    @FindBy(xpath = "//a[contains(text(),'Slider')]")
    private WebElement slider;


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