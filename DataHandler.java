import java.io.*;

import com.google.gson.Gson;
import json.Artist.*;
import json.Track.*;

public class DataHandler {
    public static void  updateLastAPICallCache(Artist artist, Stats stats, SimilarArtist similarArtist, Bio bio, TopArtists topArtists, Track track, TopTags topTags, Wiki wiki, TopSongs topSongs){

//        File last_api_call_cache = new File("last_api_call_cache.json");
//        if(!last_api_call_cache.exists()){
//            createFile(last_api_call_cache);
//        }
        artist.setStats(stats);
        artist.setSimilarArtist(similarArtist);
        artist.setBio(bio);
        artist.setTopArtists(topArtists);

        track.setTopTags(topTags);
        track.setWiki(wiki);
        track.setTopSongs(topSongs);

        try{
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("last_api_call_cache.json");
            gson.toJson(artist, writer);
            gson.toJson(track , writer);
            writer.close();

        }catch (IOException e){
            e.getMessage();
        }



    }
    public static void readCashedData(){
        try{
            Gson gson = new Gson();
            FileReader reader = new FileReader("last_api_call_cache.json");

            Artist artistInfo = gson.fromJson(reader, Artist.class);
            System.out.println(artistInfo.getName());
            System.out.println(artistInfo.getStats().getNumberOfListeners());
            System.out.println(artistInfo.getStats().getPlayCount());
            System.out.println(artistInfo.getStats().getTags());
            System.out.println(artistInfo.getSimilarArtist().getSimilarArtistName());
            System.out.println(artistInfo.getArtistBio().getPublisedDate());
            System.out.println(artistInfo.getArtistBio().getSummary());
        }catch (IOException e){
            e.getMessage();
        }

    }


//    private void createFile(File last_api_call_cache){
//        try{
//            last_api_call_cache.createNewFile();
//        }catch (IOException e){
//            e.getMessage();
//        }
//
//
//    }

}
