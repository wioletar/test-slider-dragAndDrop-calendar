import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainMenuPage;
import pages.SliderPage;
import pages.SliderSubmenuPage;

import java.util.concurrent.TimeUnit;

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
        return new Object[][] {{80}, {50},{55},{55}};
    }

    @Test(dataProvider = "Positions")
    public void testSlider(int position) throws InterruptedException {

        MainMenuPage mainMenuPage = new MainMenuPage(driver);
        mainMenuPage.openSlider();

        SliderSubmenuPage sliderSubmenuPage = new SliderSubmenuPage(driver);
        sliderSubmenuPage.chooseCustomHandle();

        SliderPage sliderPage = new SliderPage(driver);
        sliderPage.swtichToFrame();
        sliderPage.move(position);
    }
}
