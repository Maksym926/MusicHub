package json.Artist; // Package declaration, indicating this class belongs to the json.Artist package

public class TopArtists {
    // Private member variable to store the top artists' information
    String topArtists = ""; // Initially set to an empty string

    // Setter method for topArtists
    public void setTopArtists(String topArtists) {
        this.topArtists = topArtists; // Sets the topArtists to the provided argument
    }

    // Getter method for topArtists
    public String getTopArtists() {
        return topArtists; // Returns the value of topArtists
    }
}
