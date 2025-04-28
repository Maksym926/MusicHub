package json.Track;

/**
 * Wiki class stores summary and published date
 * information about a music track.
 */
public class Wiki {

    // Published date of the track information
    private String publisedDate = "";

    // Summary description of the track
    private String summary = "";

    /**
     * Gets the published date of the track's wiki information.
     * @return the published date as a String
     */
    public String getPublisedDate() {
        return publisedDate;
    }

    /**
     * Sets the published date of the track's wiki information.
     * @param publisedDate the date to set
     */
    public void setPublisedDate(String publisedDate) {
        this.publisedDate = publisedDate;
    }

    /**
     * Gets the summary description of the track.
     * @return the summary as a String
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary description of the track.
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
