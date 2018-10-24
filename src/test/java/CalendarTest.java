import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CalendarPage;
import pages.DatepickerSubmenuPage;
import pages.MainMenuPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


public class CalendarTest extends BaseTest {
 //   private Calendar calendar;
//    Open https://jqueryui.com/datepicker/#other-months
//    Select date
//    Create paremeterized test (one test that will be run each time for different parameter), as parameter pass such dates:
//            1.02.2019
//            27.12.2018
//            today
//            06.07.2018
//            20.11.2017
//    All this moving to date should be done with one method


//    @DataProvider(name = "inputDate")
//    public static Object[][] inputDate() {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        String today=dateFormat.format(date);
//        return new Object[][] {{"01/02/2019", "01/02/2019"},{today, LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))},{"27/12/2018","27/12/2018"},{"06/07/2018", "06/07/2018"},{"20/11/2017", "20/11/2017"}};
//    }


    @DataProvider(name = "inputDate")
    public static Object[][] inputDate() {
        return new Object[][]{{"01.02.2019", "02/01/2019"}, {"06.07.2018", "07/06/2018"},
                {"20.11.2017", "11/20/2017"},
                {"today", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) },
                {"27.12.2018", "12/27/2018"}
        };
    }

    @Test(dataProvider = "inputDate")
    public void calendarTest(String date, String expectedDate) throws InterruptedException {

        MainMenuPage mainMenuPage= new MainMenuPage(driver);
        mainMenuPage.openDatepicker();

        DatepickerSubmenuPage datepickerSubmenuPage = new DatepickerSubmenuPage(driver);
        datepickerSubmenuPage.chooseDatesInOtherMonths();

        CalendarPage calendarPage = new CalendarPage(driver);
        calendarPage.swtichToFrame();
        calendarPage.clickToDatepicker();
        calendarPage.splitInputDate(date);
        calendarPage.getDate();
        calendarPage.isCorrectDatePicked(expectedDate);

    }
}

