package pages.musicianPage;

import pages.YouTubePage;
import pages.musicianPage.tabs.Tabs;
import pages.musicianPage.tabs.VideoTab;

public class MusicianPage extends YouTubePage {
    private VideoTab videoTab;

    public MusicianPage(){
        videoTab = new VideoTab();
    }

    public MusicianPage switchToTab(Tabs tab){
        click(tab.getLocator()); return this;
    }

    public VideoTab getVideoTab(){return videoTab;}
}
