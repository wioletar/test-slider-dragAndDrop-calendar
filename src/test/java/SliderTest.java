import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SliderTest extends BaseTest{

//    Open https://jqueryui.com/slider/#custom-handle
//    Move slider to value 80
//    Move slider to value 50
//    Move slider to value 55
//    Move slider to again to value 55
//   1. Each step should check position with assertion
//2. Moving slider should be done with one method that will work for each value between slider range (0-100)
// and it should move it to selected number, wherever the slider is actually.


    @DataProvider(name = "Positions")
    public static Object[][] positions() {
        return new Object[][] { {80}, {50},{55},{55}, {}};
    }

    @Test(dataProvider = "Positions")
    public void testSlider(int position) throws InterruptedException {
        driver.get("https://jqueryui.com/slider/#custom-handle");
        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        int i=70;
        driver.switchTo().frame(frame);
        WebElement slider = driver.findElement(By.id("custom-handle"));
        move(position, slider);
        Thread.sleep(4000);
    }

    private void move(int position, WebElement slider) {
        slider.click();
        // parseInt -> analizuje Stringa do Inta
        if (Integer.parseInt(slider.getText())<position)
            while (Integer.parseInt(slider.getText())!=position){
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        else
            while (Integer.parseInt(slider.getText())!=position){
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        Assert.assertEquals(Integer.parseInt(slider.getText()),position);
    }


}
