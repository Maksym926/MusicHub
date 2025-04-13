package json;

public class Bio {
    private String publisedDate;
    private String summary;
    public Bio (String publisedDate, String summary){
        this.publisedDate = publisedDate;
        this.summary = summary;
    }
    public String getPublisedDate(){
        return publisedDate;
    }
    public String getSummary(){
        return summary;
    }

}
