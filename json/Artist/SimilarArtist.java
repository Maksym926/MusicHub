package json.Artist; // Package declaration, indicating this class belongs to the json.Artist package

public class SimilarArtist {
    // Private member variable to store the name of a similar artist
    String name = "";  // Initially set to an empty string

    // Getter method for name
    public String getSimilarArtistName(){
        return name;  // Returns the value of the name variable
    }

    // Setter method for name
    public void setSimilarArtistName(String similarArtistName){
        this.name = similarArtistName;  // Sets the value of name to the provided argument
    }
}
