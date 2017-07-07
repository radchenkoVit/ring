package pages;

import org.openqa.selenium.WebDriver;
import utils.DriverExtension;
import utils.DriverManager;

public class BasePage implements DriverExtension {
    protected WebDriver driver = DriverManager.getDriver();

    public BasePage(){
        waitPageToLoad();
    }
}
