import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DatepickerTest extends BaseTest {

//    Create paremeterized test (one test that will be run each time for different parameter), as parameter pass such dates:
//            1.02.2019
//            27.12.2018
//            today
//            06.07.2018
//            20.11.2017
//    All this moving to date should be done with one method

    MainMenuPage mainMenuPage;
    DatepickerSubmenuPage datepickerSubmenuPage;
    DatepickerPage datepickerPage;

    @BeforeMethod
    public void setup(){
        mainMenuPage = new MainMenuPage(driver);
        datepickerSubmenuPage = new DatepickerSubmenuPage(driver);
        datepickerPage = new DatepickerPage(driver);
        mainMenuPage.openDatepicker();
        datepickerSubmenuPage.chooseDatesInOtherMonths();
    }

    @DataProvider(name = "inputDate")
    public static Object[][] inputDate() {
        return new Object[][]{{"01.02.2019", "02/01/2019"},
                {"06.07.2018", "07/06/2018"},
                {"20.11.2017", "11/20/2017"},
                {"today", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) },
                {"27.12.2018", "12/27/2018"}};
    }

    @Test(dataProvider = "inputDate")
    public void calendarTest(String date, String expectedDate) {
        datepickerPage.swtichToFrame()
                    .clickToDatepicker()
                    .splitInputDate(date)
                    .getDate()
                    .isCorrectDatePicked(expectedDate);
    }
}

