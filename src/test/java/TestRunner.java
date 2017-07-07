import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;
import utils.DriverManager;

import java.util.concurrent.TimeUnit;

public class TestRunner {
    protected WebDriver driver;

    @BeforeClass
    public void setUp(){
        ChromeDriverManager.getInstance().setup(); // download bin for chrome, set path to system
        driver = DriverFactory.getChrome();
        DriverManager.setWebDriver(driver); // add driver to have opportunity to get it from any place

        //set default settings for driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown(){
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }
}
