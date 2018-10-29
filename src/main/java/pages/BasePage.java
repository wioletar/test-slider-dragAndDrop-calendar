package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForClickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
