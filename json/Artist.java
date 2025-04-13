package json;

public class Artist {
    private String name;
    private Stats stats;
    private SimilarArtist similarArtist;
    private Bio bio;
    public Artist (String name, Stats stats, SimilarArtist similarArtist, Bio bio){
        this.name = name;
        this.stats = stats;
        this.similarArtist = similarArtist;
        this.bio = bio;


    }
    public String getName(){
        return name;
    }
    public Stats getStats(){
        return stats;
    }
    public SimilarArtist getSimilarArtist(){
        return similarArtist;
    }
    public Bio getArtistBio(){
        return bio;
    }
}
