package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return webDrivers.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDrivers.set(driver);
    }
}
