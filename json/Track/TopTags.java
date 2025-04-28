package json.Track;

/**
 * TopTags class represents the tags (genres, labels, etc.)
 * associated with a music track.
 */
public class TopTags {

    // A string containing the top tags for the track
    private String tags = "";

    /**
     * Sets the tags for the track.
     *
     * @param tags a string containing the tags (e.g., genres or keywords)
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * Gets the tags associated with the track.
     *
     * @return a string containing the track's tags
     */
    public String getTags() {
        return tags;
    }
}
