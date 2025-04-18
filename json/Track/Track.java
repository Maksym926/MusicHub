package json.Track;



public class Track {
    private String trackName;
    private String artistName;
    private String numberOfListeners;
    private String playCount;
    private String duration;
    private Wiki wiki;
    private TopTags topTags;
    private TopSongs topSongs;

    public String getTrackName(){
        return trackName;
    }
    public void setTrackName(String trackName){
        this.trackName = trackName;

    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setPlayCount(String playCount){
        this.playCount = playCount;
    }
    public void setNumberOfListeners(String numberOfListeners){
        this.numberOfListeners = numberOfListeners;
    }
    public void setWiki(Wiki wiki){
        this.wiki = wiki;
    }

    public void setTopSongs(TopSongs topSongs) {
        this.topSongs = topSongs;
    }

    public void setTopTags(TopTags topTags) {
        this.topTags = topTags;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getDuration(){
        return duration;
    }
    public String getNumberOfListeners(){
        return numberOfListeners;
    }
    public String getPlayCount(){
        return playCount;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public TopTags getTopTags() {
        return topTags;
    }

    public TopSongs getTopSongs() {
        return topSongs;
    }
}
