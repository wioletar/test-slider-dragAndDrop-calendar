import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DroppablePage;
import pages.DroppableSubmenuPage;
import pages.MainMenuPage;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DropableTest extends BaseTest {

//    open https://jqueryui.com/droppable/
//    drag one object and drop on another
//    check with assertion if it was successful
//    Use Action class to move objects https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html


    @Test
    public void dragAndDropTest() {

        MainMenuPage mainMenuPage= new MainMenuPage(driver);
        mainMenuPage.openDroppable();

        DroppableSubmenuPage droppableSubmenuPage = new DroppableSubmenuPage(driver);
        droppableSubmenuPage.chooseDefaultFunctionality();

        DroppablePage droppablePage = new DroppablePage(driver);
        droppablePage.swtichToFrame();
        droppablePage.dragAndDrop();
        droppablePage.verifySuccess();


//        driver.get("https://jqueryui.com/droppable/");
//        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
//        driver.switchTo().frame(frame);
//
//        WebElement dragBox = driver.findElement(By.cssSelector("div[id='draggable']"));
//        WebElement dropBox = driver.findElement(By.cssSelector("div[id='droppable']"));
//
//        Actions builder = new Actions(driver);
//        builder.dragAndDrop(dragBox, dropBox).build().perform();
//        builder.release(dropBox).build().perform();
//
//        Assert.assertEquals("Dropped!", dropBox.getText());
    }

}
