import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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


//    public static void main(String[] args) {
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        String today=dateFormat.format(date);
//        System.out.println(today);
//
//    }

    @DataProvider(name = "inputDate")
    public static Object[][] inputDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String today=dateFormat.format(date);
        return new Object[][] {{"01/02/2019"},{today},{"27/12/2018"},{"06/07/2018"},{"20/11/2017"}};
    }




    @Test(dataProvider = "inputDate")
    public void calendarTest2(String inputDate) throws ParseException, InterruptedException { //dd/MM/yyyy
        driver.get("https://jqueryui.com/datepicker/#other-months");
        WebElement frame = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
        driver.switchTo().frame(frame);
        WebElement datePicker = driver.findElement(By.cssSelector("input[id='datepicker']"));
        datePicker.click();

        String currentMonth=driver.findElement(By.className("ui-datepicker-month")).getText(); //MMMM
        String currentYear=driver.findElement(By.className("ui-datepicker-year")).getText(); //yyyy


        // input month as int -> 01/02/2019 -> 02
        String[] inputDateValue= inputDate.split("/");
        String dayToSelect=inputDateValue[0];

        int monthToSelect = Integer.parseInt(inputDateValue[1]); //02
        int yearToSelect=Integer.parseInt(inputDateValue[2]);

        // month from website
        DateTimeFormatter formatMonth = DateTimeFormat.forPattern("MMM");
        DateTime instanceMonth        = formatMonth.withLocale(Locale.ENGLISH).parseDateTime(currentMonth);
        int month_number         = instanceMonth.getMonthOfYear();
        System.out.println(month_number);

        //year from website
        DateTimeFormatter formatYear = DateTimeFormat.forPattern("yyyy");
        DateTime instanceYear        = formatYear.withLocale(Locale.ENGLISH).parseDateTime(currentYear);
        int year_number         = instanceYear.getYear();
        System.out.println(year_number);

      int yearDifference=yearToSelect-year_number;

    if(yearDifference!=0){
        if(yearDifference>0){
            int numberOfClick=((monthToSelect+12-month_number)+(12*(yearDifference-1)));
            for(int i=0;i<numberOfClick;i++){
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            }
        }
        else if(yearDifference<0){
            int numberOfClick=month_number+(12-monthToSelect)-12*(yearDifference+1);
            for(int i=0;i<numberOfClick;i++){
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-w']")).click();
            }
        }
    }
        else {
        if (monthToSelect > month_number) {
            for (int i = 0; i < monthToSelect - month_number; i++) {
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            }
        } else if (monthToSelect < month_number) {
            for (int i = 0; i < month_number - monthToSelect; i++) {
                driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-w']")).click();
            }
        }
    }
        List<WebElement> listOfDays=driver.findElements(By.cssSelector("tbody tr td"));
        for(WebElement element:listOfDays){
            if(Integer.valueOf(element.getAttribute("data-month")).equals(monthToSelect-1)){
                WebElement dayElement=element.findElement(By.cssSelector("a"));
                String dayElementString=dayElement.getText();
                if(dayElementString.equalsIgnoreCase(dayToSelect)){
                 element.click();
                }
            }
        }
        WebElement dateInput = driver.findElement(By.cssSelector("#datepicker"));
        Assert.assertEquals(dateInput.getAttribute("value"), inputDate);


    }

}

