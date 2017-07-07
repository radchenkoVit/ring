package pages.musicianPage.tabs;

public enum Tabs {
    Home, Videos, Playlists, Channels, Discussion, About;

    private String patternTest = ".//div[@id='channel-subheader']//span[contains(text(), '%s')]";
    public String getLocator(){
        return String.format(patternTest, this.name());
    }
}
