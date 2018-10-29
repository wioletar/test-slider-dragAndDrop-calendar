import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;


public class DroppableTest extends BaseTest {

    MainMenuPage mainMenuPage;
    DroppableSubmenuPage droppableSubmenuPage;
    DroppablePage droppablePage;

    @BeforeMethod
    public void setup(){
        mainMenuPage = new MainMenuPage(driver);
        droppableSubmenuPage = new DroppableSubmenuPage(driver);
        droppablePage = new DroppablePage(driver);
        mainMenuPage.openDroppable();
        droppableSubmenuPage.chooseDefaultFunctionality();
    }

    @Test
    public void dragAndDropTest() {
        droppablePage.swtichToFrame()
                    .dragAndDrop()
                    .verifySuccess();
    }
}
