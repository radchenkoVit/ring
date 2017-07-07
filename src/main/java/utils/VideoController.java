package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class VideoController implements DriverExtension {
    public WebElement videoPlayer = find("//video");
    private JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();

    public VideoController(){
        waitPageToLoad();
        waitUntil("//video", WaitCondition.visible);
    }

    public String getSource(){
        return ((String) jsExecutor.executeScript("return arguments[0].currentSrc;", videoPlayer)).replaceAll("blob:", "").trim();
    }

    public VideoController play(){
        jsExecutor.executeScript("return arguments[0].play()",
                videoPlayer); return this;
    }

    public VideoController pause(){
        jsExecutor.executeScript("return arguments[0].pause()",
                videoPlayer); return this;
    }

    public int getDuration(){
        return (int) jsExecutor.executeScript("return arguments[0].duration",
                videoPlayer);
    }
}
