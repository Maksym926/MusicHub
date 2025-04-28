package json.Track;

/**
 * TopSongs class represents a list of top songs
 * associated with an artist or a country.
 */
public class TopSongs {

    // A string containing the top songs
    private String topSongs = "";

    /**
     * Sets the top songs.
     *
     * @param topSongs a string representing the top songs
     */
    public void setTopSongs(String topSongs) {
        this.topSongs = topSongs;
    }

    /**
     * Gets the top songs.
     *
     * @return a string containing the top songs
     */
    public String getTopSongs() {
        return topSongs;
    }
}
