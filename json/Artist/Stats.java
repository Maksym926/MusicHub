package json.Artist; // Package declaration, indicating this class belongs to the json.Artist package

public class Stats {
    // Private member variables to store the number of listeners, play count, and tags
    String numberOfListeners = ""; // Initially set to an empty string
    String playCount = "";         // Initially set to an empty string
    String tags = "";              // Initially set to an empty string

    // Setter method for numberOfListeners
    public void setNumberOfListeners(String numberOfListeners){
        this.numberOfListeners = numberOfListeners; // Sets the numberOfListeners to the provided argument
    }

    // Setter method for playCount
    public void setPlayCount(String playCount){
        this.playCount = playCount; // Sets the playCount to the provided argument
    }

    // Getter method for numberOfListeners
    public String getNumberOfListeners(){
        return numberOfListeners; // Returns the value of numberOfListeners
    }

    // Getter method for playCount
    public String getPlayCount(){
        return playCount; // Returns the value of playCount
    }

    // Getter method for tags
    public String getTags(){
        return tags; // Returns the value of tags
    }
}
