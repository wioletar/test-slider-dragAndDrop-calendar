package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class SliderPage {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe[class='demo-frame']")
    private WebElement frame;

    @FindBy(css = ".ui-slider-handle")
    private WebElement slider;


    public void swtichToFrame(){
        driver.switchTo().frame(frame);
    }

    public void move(int position) {

        slider.click();
      //  WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement sliderAfterWait = wait.until(ExpectedConditions.elementToBeClickable(slider));
        if (Integer.parseInt(sliderAfterWait.getText())<position)
            while (Integer.parseInt(sliderAfterWait.getText())!=position){
                sliderAfterWait.sendKeys(Keys.ARROW_RIGHT);
            }
        else
            while (Integer.parseInt(sliderAfterWait.getText())!=position){
                sliderAfterWait.sendKeys(Keys.ARROW_LEFT);
            }
        Assert.assertEquals(Integer.parseInt(slider.getText()),position);
    }
}
