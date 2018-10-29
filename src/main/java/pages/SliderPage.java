package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



public class SliderPage extends BasePage {

    Actions builder;

    public SliderPage(WebDriver driver) {
       super(driver);
       PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe[class='demo-frame']")
    private WebElement frame;

    @FindBy(css = ".ui-slider-handle")
    private WebElement slider;


    public SliderPage swtichToFrame(){
        driver.switchTo().frame(frame);
        return this;
    }

    public SliderPage clickSlider(){
        builder = new Actions(driver);
        builder.moveToElement( slider ).click( slider );
        builder.perform();
        return this;
    }

    public SliderPage move(int position) {

        if (Integer.parseInt(slider.getText())<position)
            while (Integer.parseInt(slider.getText())!=position){
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        else
            while (Integer.parseInt(slider.getText())!=position){
                slider.sendKeys(Keys.ARROW_LEFT);
            }
            return this;
    }
    public SliderPage positionAssertion(int position){
        Assert.assertEquals(Integer.parseInt(slider.getText()),position);
        return this;
    }

}
