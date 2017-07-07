package pages;

public class YouTubePage extends BasePage {
    private static String url = "http://youtube.com";
    private String searchInput = "//input[@id='masthead-search-term']";
    private String searchButton = "//button[@id='search-btn']";

    public YouTubePage open(){
        return open(url);
    }

    public YouTubePage open(final String url){
        driver.get(url);
        waitPageToLoad();
        return this;
    }

    public SearchPage search(String searchQuery){
        type(searchInput, searchQuery);
        click(searchButton);
        return new SearchPage();
    }
}
