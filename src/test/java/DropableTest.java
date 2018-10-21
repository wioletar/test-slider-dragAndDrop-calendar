import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class DropableTest extends BaseTest {

//    open https://jqueryui.com/droppable/
//    drag one object and drop on another
//    check with assertion if it was successful
//    Use Action class to move objects https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html

    @Test
    public void dragAndDropTest() throws AWTException {
        driver.get("https://jqueryui.com/droppable/");
        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(frame);

        WebElement dragBox = driver.findElement(By.cssSelector("div[id='draggable']"));
        WebElement dropBox=driver.findElement(By. cssSelector("div[id='droppable']"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(dragBox);
        Action action = builder.build();
        action.perform();

        // Robot to point mouse to droppable zone
        Point coordinates = driver.findElement(By.cssSelector("div[id='droppable']")).getLocation();
        Robot robot = new Robot();

        // Find location for droppable element
        int x = driver.findElement(By.cssSelector("div[id='droppable']")).getLocation().getX();
        int y = driver.findElement(By.cssSelector("div[id='droppable']")).getLocation().getY();

        // Move dragble to droppable
        // dlaczego tutaj inicjujemy nową akcję
        builder = new Actions(driver);
        builder.moveByOffset(x,y).perform();
        builder.build();
        builder.release();
        robot.mouseMove(coordinates.getX(),coordinates.getY());
        builder.release(dropBox).perform();

        Assert.assertEquals("Dropped!",dropBox.getText());
    }

    @Test
    public void dragAndDropTest2() {
        driver.get("https://jqueryui.com/droppable/");
        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(frame);

        WebElement dragBox = driver.findElement(By.cssSelector("div[id='draggable']"));
        WebElement dropBox = driver.findElement(By.cssSelector("div[id='droppable']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(dragBox,dropBox).build().perform();

       builder.release(dropBox).build().perform();
        Assert.assertEquals("Dropped!",dropBox.getText());
    }
}
