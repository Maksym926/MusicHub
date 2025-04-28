package json.Artist;

import json.Track.Track;

/**
 * Artist class represents a musical artist and holds detailed
 * information about the artist, their tracks, statistics, similar artists, and more.
 */
public class Artist {

    // Basic artist information
    private String name = "";

    // Additional detailed data
    private Stats stats;
    private SimilarArtist similarArtist;
    private Bio bio;
    private TopArtists topArtists;
    private Track track;

    // ----- Getters and Setters -----

    /**
     * Gets the artist's name.
     *
     * @return the artist's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the artist's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the artist's stats (e.g., number of listeners, play count).
     *
     * @return the Stats object
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Sets the artist's stats.
     *
     * @param stats the Stats object to set
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Gets information about similar artists.
     *
     * @return the SimilarArtist object
     */
    public SimilarArtist getSimilarArtist() {
        return similarArtist;
    }

    /**
     * Sets the similar artists information.
     *
     * @param similarArtist the SimilarArtist object to set
     */
    public void setSimilarArtist(SimilarArtist similarArtist) {
        this.similarArtist = similarArtist;
    }

    /**
     * Gets the artist's biography.
     *
     * @return the Bio object
     */
    public Bio getArtistBio() {
        return bio;
    }

    /**
     * Sets the artist's biography.
     *
     * @param bio the Bio object to set
     */
    public void setBio(Bio bio) {
        this.bio = bio;
    }

    /**
     * Gets the top artists (most popular artists related to this one).
     *
     * @return a string representing the top artists
     */
    public String getTopArtists() {
        return topArtists.getTopArtists();
    }

    /**
     * Sets the top artist's information.
     *
     * @param topArtists the TopArtists object to set
     */
    public void setTopArtists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

    /**
     * Sets the track information associated with this artist.
     *
     * @param track the Track object to set
     */
    public void setTrack(Track track) {
        this.track = track;
    }

    /**
     * Gets the track information associated with this artist.
     *
     * @return the Track object
     */
    public Track getTrack() {
        return track;
    }
}
