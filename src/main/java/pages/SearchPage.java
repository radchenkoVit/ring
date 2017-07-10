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
        for (int i = 0; i < 3; i++) {
            click(channelButton, clickable);
            if (!isPresent(channelButton)){click(channelButton);}
        }
        if (waitUntilDisappear(channel)) throw new RuntimeException("Channel button was not clicked"); // TODO: make better, quick fix
        return new MusicianPage();
    }
}
