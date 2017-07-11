package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public interface DriverExtension {
    Logger log = Logger.getLogger(DriverExtension.class.getName());

    // click with condition and without - two different approach, need test to check which is better
    default void click(String locator){
        log.info(String.format("Click on element with locator: %s", locator));
        int attemptingTime = 0;
        while (attemptingTime < 11) {
            try {
                find(locator).click();
                break;// we exit from cycle if click was done
            } catch (StaleElementReferenceException ignore) {
                log.warning(String.format("Failed to click on element be locator: %s", locator));
                if (attemptingTime == 10) throw new RuntimeException(ignore.getMessage()); // use runtime cause don't want to throw exception higher
                attemptingTime++;
            }
        }
    }

    // click with condition and without - two different approach, need test to check which is better
    default void click(String locator, WaitCondition condition){
        waitUntil(locator, condition).click();
    }

    default void type(String locator, String text){
        log.info(String.format("Type into element with locator: %s, text: %s", locator, text));
        find(locator).sendKeys(text);
    }

     default WebElement find(String locator){
        WebDriver driver = DriverManager.getDriver();
        return driver.findElement(by(locator));
    }

    default List<WebElement> findElements(String locator){
        WebDriver driver = DriverManager.getDriver();
        return driver.findElements(by(locator));
    }

    static By by(String locator){
        if (locator.contains("//")) return By.xpath(locator);
        return By.cssSelector(locator);
    }

    default boolean isPresent(String locator){
        return isPresent(locator, 10);
    }

    default boolean isPresent(String locator, int timeInSeconds){
        try {
            waitUntil(locator, WaitCondition.presence, timeInSeconds);
            return true;
        } catch (NotFoundException e){
            return false;
        }
    }

    default WebElement waitUntil(String locator, WaitCondition condition){
        return waitUntil(locator, condition, 10);
    }

    default WebElement waitUntil(String locator, WaitCondition condition, int timeInSeconds){
        FluentWait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(timeInSeconds, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class) //say by to StaleReference exception
                .pollingEvery(500, TimeUnit.MILLISECONDS);
        return wait.until(condition.get().apply(by(locator)));
    }

    default boolean waitUntilDisappear(String locator){
        return waitUntilDisappear(find(locator));
    }

    default boolean waitUntilDisappear(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);
            return wait.until(ExpectedConditions.stalenessOf(element));
        } catch (TimeoutException e){
            return false;
        }
    }

    default boolean waitPageToLoad(){
        return new WebDriverWait(DriverManager.getDriver(), 30).until(wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    default void sleep(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException ignore) {
        }
    }
}
