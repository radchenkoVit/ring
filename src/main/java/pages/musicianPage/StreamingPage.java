package pages.musicianPage;

import pages.YouTubePage;
import utils.VideoController;

public class StreamingPage extends YouTubePage {
    private VideoController videoController;

    public StreamingPage open(String url){
        driver.get(url);
        waitPageToLoad();
        videoController = new VideoController();
        return this;
    }

    public VideoController getVideoController(){
        return videoController;
    }
}
