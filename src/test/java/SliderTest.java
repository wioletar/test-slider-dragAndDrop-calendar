
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainMenuPage;
import pages.SliderPage;
import pages.SliderSubmenuPage;


public class SliderTest extends BaseTest{

    MainMenuPage mainMenuPage;
    SliderSubmenuPage sliderSubmenuPage;
    SliderPage sliderPage;

    @BeforeMethod
    public void setup(){
        mainMenuPage = new MainMenuPage(driver);
        sliderSubmenuPage = new SliderSubmenuPage(driver);
        sliderPage = new SliderPage(driver);
        mainMenuPage.openSlider();
        sliderSubmenuPage.chooseCustomHandle();
    }

    @DataProvider(name = "Positions")
    public static Object[][] positions() {
        return new Object[][] {{80}, {50},{55},{55}};
    }

    @Test(dataProvider = "Positions")
    public void testSlider(int position) {
        sliderPage.swtichToFrame()
                .clickSlider()
                .move(position)
                .positionAssertion(position);
    }
}
