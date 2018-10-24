package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderSubmenuPage {
    WebDriver driver;

    public SliderSubmenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[contains(text(),'Custom handle')]")
    private WebElement customHandle;

    public void chooseCustomHandle(){
        customHandle.click();
    }
}
