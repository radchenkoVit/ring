package pages;

import org.openqa.selenium.WebElement;
import pages.musicianPage.MusicianPage;

import static utils.WaitCondition.clickable;
import static utils.WaitCondition.visible;

public class SearchPage extends YouTubePage {
    private String channelTitle = "//h3[@id='channel-title']/span";
    private String channelButton = "//li/span[contains(text(), 'Channel')][contains(@class,'yt-badge')]";

    public SearchPage(){
        waitPageToLoad();
    }

    public MusicianPage goToChannel(){
        String channelLocator = isPresent(channelTitle, 3) ? channelTitle : channelButton;
        WebElement channel = find(channelLocator);
        waitUntil(channelLocator, visible);
        for (int i = 0; i < 3; i++) {
            click(channelLocator, clickable);
            if (!isPresent(channelLocator)){click(channelLocator);}
        }
        if(!waitUntilDisappear(channel)) throw new RuntimeException("Not switch to artist channel");
        return new MusicianPage();
    }
}
