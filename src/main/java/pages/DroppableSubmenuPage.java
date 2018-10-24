package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppableSubmenuPage {
    WebDriver driver;

    public DroppableSubmenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Default functionality')]")
    private WebElement defaultFunctionality;

    public void chooseDefaultFunctionality(){
        defaultFunctionality.click();
    }


}
