package json.Artist;

public class Artist {
    private String name;
    private Stats stats;
    private SimilarArtist similarArtist;
    private Bio bio;
    private TopArtists topArtists;

    public String getName(){
        return name;
    }
    public void setName(String name){
          this.name = name;

    }

    public void setTopArtists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

    public TopArtists getTopArtists() {
        return topArtists;
    }

    public Stats getStats(){
        return stats;
    }
    public void setStats(Stats stats){
        this.stats = stats;
    }
    public SimilarArtist getSimilarArtist(){
        return similarArtist;
    }
    public void setSimilarArtist(SimilarArtist similarArtist){
        this.similarArtist = similarArtist;
    }
    public Bio getArtistBio(){
        return bio;
    }
    public void setBio(Bio bio){
        this.bio = bio;
    }

}
