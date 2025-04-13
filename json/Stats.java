package json;

public class Stats {
    String numberOfListeners;
    String playCount;

    public void setNumberOfListeners(String numberOfListeners){
        this.numberOfListeners = numberOfListeners;

    }
    public void setPlayCount(String playCount){
        this.playCount = playCount;
    }
    public String getNumberOfListeners(){
        return numberOfListeners;
    }
    public String getPlayCount(){
        return playCount;
    }
}
