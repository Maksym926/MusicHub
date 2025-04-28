package json.Artist; // Package declaration, indicating this class belongs to the json.Artist package

public class Bio {
    // Private member variables to store the published date and summary of the Bio
    private String publisedDate = "";  // Initially set to an empty string
    private String summary = "";       // Initially set to an empty string

    // Getter method for publisedDate
    public String getPublisedDate(){
        return publisedDate;  // Returns the value of publisedDate
    }

    // Setter method for publisedDate
    public void setPublisedDate(String publisedDate){
        this.publisedDate = publisedDate;  // Sets the value of publisedDate to the provided argument
    }

    // Getter method for summary
    public String getSummary(){
        return summary;  // Returns the value of summary
    }

    // Setter method for summary
    public void setSummary(String summary){
        this.summary = summary;  // Sets the value of summary to the provided argument
    }
}
