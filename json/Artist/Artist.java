package json.Artist;

import json.Track.Track;

public class Artist {
    private String name = "";
    private Stats stats ;
    private SimilarArtist similarArtist;
    private Bio bio;
    private TopArtists topArtists;
    private Track track;

    public String getName(){
        return name;
    }
    public void setName(String name){
          this.name = name;

    }

    public void setTopArtists(TopArtists topArtists) {
        this.topArtists = topArtists;
    }

    public String getTopArtists() {
        return topArtists.getTopArtists();
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

    public void setTrack(Track track){
        this.track = track;
    }
    public Track getTrack(){
        return track;
    }

}
