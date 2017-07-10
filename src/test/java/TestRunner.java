import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

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

    @AfterMethod
    public void afterMethods(Method method, ITestResult testResult){
        // not env depended screenshot path getter
        String path = Paths.get("screenshot", String.format("screenshots_%s.jpg", method.getName())).toAbsolutePath().toString();
        if (!testResult.isSuccess()){
            takeScreenShot(path);
        }
    }

    @AfterClass
    public void tearDown(){
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
    }

    private static void takeScreenShot(String fileWithPath) {
        Logger logger = Logger.getLogger("ScreenShot Maker");
        TakesScreenshot scrShot =((TakesScreenshot)DriverManager.getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            logger.info(String.format("Failed to make screenshot, exception:%s", e.getMessage()));
        }
    }
}
