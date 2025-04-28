package json.Track;

/**
 * Track class represents a music track (song) and holds
 * details like track name, artist, play count, listeners, etc.
 */
public class Track {

    // Track details
    private String trackName = "";
    private String artistName = "";
    private String numberOfListeners = "";
    private String playCount = "";
    private String duration = "";

    // Extra details (nested JSON objects)
    private Wiki wiki;
    private TopTags topTags;
    private TopSongs topSongs;

    // ----- Getters and Setters -----

    /**
     * Gets the track name.
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * Sets the track name.
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * Gets the artist name for this track.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets the artist name.
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Gets the track duration (in seconds).
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the track duration.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the total number of listeners.
     */
    public String getNumberOfListeners() {
        return numberOfListeners;
    }

    /**
     * Sets the number of listeners.
     */
    public void setNumberOfListeners(String numberOfListeners) {
        this.numberOfListeners = numberOfListeners;
    }

    /**
     * Gets the play count (how many times the track was played).
     */
    public String getPlayCount() {
        return playCount;
    }

    /**
     * Sets the play count.
     */
    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    // ----- Nested Objects -----

    /**
     * Gets the Wiki (track summary) information.
     */
    public Wiki getWiki() {
        return wiki;
    }

    /**
     * Sets the Wiki information for the track.
     */
    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    /**
     * Gets the top tags associated with the track.
     */
    public String getTopTags() {
        return topTags.getTags();
    }

    /**
     * Sets the top tags object.
     */
    public void setTopTags(TopTags topTags) {
        this.topTags = topTags;
    }

    /**
     * Gets the list of top songs (if any).
     */
    public String getTopSongs() {
        return topSongs.getTopSongs();
    }

    /**
     * Sets the top songs object.
     */
    public void setTopSongs(TopSongs topSongs) {
        this.topSongs = topSongs;
    }
}
