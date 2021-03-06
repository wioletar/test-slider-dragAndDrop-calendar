package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;


public class DroppablePage extends BasePage {


    Actions builder;

    public DroppablePage(WebDriver driver){
        super(driver);
        builder = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe[class='demo-frame']")
    private WebElement frame;

    @FindBy(css = "div[id='draggable']")
    private WebElement dragBox;

    @FindBy(css = "div[id='droppable']")
    private WebElement dropBox;

    @FindBy(css = ".ui-state-highlight")
    private WebElement droppedBox;

    public DroppablePage swtichToFrame(){
        driver.switchTo().frame(frame);
        return this;
    }

    public DroppablePage dragAndDrop(){

        builder.dragAndDrop(dragBox,dropBox).build().perform();
        builder.release(dropBox).build().perform();
        return this;
    }

    public void verifySuccess(){
        waitForElements(Arrays.asList(droppedBox));
        Assert.assertEquals(droppedBox.getText(), "Dropped!");
    }

}
