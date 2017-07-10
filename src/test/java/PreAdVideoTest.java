import org.testng.annotations.Test;
import pages.SearchPage;
import pages.YouTubePage;
import pages.musicianPage.MusicianPage;
import pages.musicianPage.StreamingPage;
import pages.musicianPage.tabs.SortBy;
import pages.musicianPage.tabs.Tabs;
import pages.musicianPage.tabs.VideoTab;
import utils.DriverFactory;
import utils.DriverManager;
import utils.VideoController;

import java.util.List;
import java.util.logging.Logger;

public class PreAdVideoTest extends TestRunner {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String musician = "Taylor Swift";

    @Test
    public void test(){
        YouTubePage youTube = new YouTubePage();
        SearchPage searchPage = youTube
                .open("https://www.youtube.com")
                .search(musician);
        MusicianPage musicianPage = searchPage.goToChannel();

        VideoTab videoTab = musicianPage
                .switchToTab(Tabs.Videos)
                .getVideoTab()
                .sortBy(SortBy.MostPopular);

        List<String> videoLinks = videoTab.getVideoLinks(3);

        for (String videoLink: videoLinks){
            StreamingPage page = new StreamingPage();
            page.open(videoLink);
            VideoController controller = page.getVideoController(); //video starts playing when page opens
            sleep(3);//waiting to clip playing
            logger.info(String.format("Url: %s without adBlock", videoLink));
            controller.pause();
        }

        DriverManager.getDriver().quit();
        DriverManager.setWebDriver(DriverFactory.getChromeWithAdBlock()); // set browser with add block

        // do the same action as a int prev "Step"// TODO: make common method
    }

    private static void sleep(int timeInSeconds){
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException ignore) {
        }
    }

}
