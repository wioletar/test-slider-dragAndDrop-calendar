package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderSubmenuPage extends BasePage {

    public SliderSubmenuPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Custom handle')]")
    private WebElement customHandle;

    public void chooseCustomHandle(){
        customHandle.click();
    }
}
