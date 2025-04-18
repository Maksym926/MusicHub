package json.Artist;

public class Stats {
    String numberOfListeners;
    String playCount;
    String tags;

    public void setNumberOfListeners(String numberOfListeners){
        this.numberOfListeners = numberOfListeners;
    }

    public void setPlayCount(String playCount){
        this.playCount = playCount;
    }

    public void setTags(String tags){
        this.tags = tags;
    }
    public String getNumberOfListeners(){
        return numberOfListeners;
    }
    public String getPlayCount(){
        return playCount;
    }

    public String getTags(){
        return tags;
    }
}
