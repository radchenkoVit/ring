package pages.musicianPage.tabs;

public enum SortBy {
    Newest("dd"), MostPopular("p"), Oldest("da");

    private String key;

    SortBy(String key){
        this.key = key;
    }

    private String pattern = ".//span[contains(@href, 'sort=%s')]";
    public String getLocator(){
        return String.format(pattern, this.key);
    }
}
