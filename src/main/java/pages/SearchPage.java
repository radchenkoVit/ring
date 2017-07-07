package pages;

import org.openqa.selenium.WebElement;
import pages.musicianPage.MusicianPage;

import static utils.WaitCondition.clickable;
import static utils.WaitCondition.visible;

public class SearchPage extends YouTubePage {
    private String channelButton = "//li/span[contains(text(), 'Channel')][contains(@class,'yt-badge')]";

    public SearchPage(){
       waitPageToLoad();
    }

    public MusicianPage goToChannel(){
        WebElement channel = find(channelButton);
        waitUntil(channelButton, visible);
        click(channelButton, clickable);
        if (!waitUntilDisappear(channel)) throw new RuntimeException("Channel button was not clicked");
        return new MusicianPage();
    }
}
