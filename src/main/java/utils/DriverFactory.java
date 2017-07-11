package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static String pathToPlugin = Paths.get("adBlock", "AdBlock_v.crx").toAbsolutePath().toString(); //make env independent

    public static WebDriver getChrome(){
        return new ChromeDriver();
    }

    public static WebDriver getChromeWithAdBlock(){
        return getChrome(pathToPlugin);
    }

    public static WebDriver getChrome(String pathToPlugin){
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(pathToPlugin));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        ChromeDriver driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try { //waiting for instaling plugin
            Thread.sleep(15000);
        } catch (InterruptedException ignore) { //TODO: to improve, add for element, close extra tab
        }
        return driver;
    }

}
