import java.io.*;

import com.google.gson.Gson;
import json.Artist.*;
import json.Track.*;

public class DataHandler {
    public static void  updateLastAPICallCache(Artist artist, Stats stats, SimilarArtist similarArtist, Bio bio, TopArtists topArtists, Track track, TopTags topTags, Wiki wiki, TopSongs topSongs){

        artist.setStats(stats);
        artist.setSimilarArtist(similarArtist);
        artist.setBio(bio);
        artist.setTopArtists(topArtists);

        track.setTopTags(topTags);
        track.setWiki(wiki);
        track.setTopSongs(topSongs);

        artist.setTrack(track);

        try{
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("last_api_call_cache.json");
            gson.toJson(artist, writer);
            writer.close();

        }catch (IOException e){
            e.getMessage();
        }



    }
    public static Artist readCashedData() throws IOException{
            Gson gson = new Gson();
            FileReader reader = new FileReader("last_api_call_cache.json");
            Artist info = gson.fromJson(reader, Artist.class);
            return info;
    }


}
