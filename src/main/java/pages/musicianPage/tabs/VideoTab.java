package pages.musicianPage.tabs;

import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.*;
import java.util.stream.Collectors;

public class VideoTab extends BasePage {
    private String sortButton = "//button[contains(@class, 'sort-menu')]";
    private String videosLinks = ".//ul[contains(@id, 'channels-browse-content')]//a[@href]";

    public VideoTab sortBy(SortBy by){
        click(sortButton);
        click(by.getLocator());
        return this;
    }

    public List<String> getVideoLinks(){
        sleep(2); // PAGE LOAD TO FAST, elements changed during loading, without sleep fail with stail webelement exception, page load to  //TODO: fix it in future
        Set<WebElement> elements = new LinkedHashSet<>(findElements(videosLinks));
        List<String> links = new ArrayList<>(elements.stream().map(element -> element.getAttribute("href")).collect(Collectors.toCollection(LinkedHashSet::new)));
        return links;
    }

    public List<String> getVideoLinks(int quantity) {
        return getVideoLinks().subList(0, quantity);
    }
}
