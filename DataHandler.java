import java.io.*;

import com.google.gson.Gson;
import json.Artist.*;
import json.Track.*;
/**
 * DataHandler class manages caching and retrieving the last API call data.
 * It saves artist and track information to a JSON file and can reload it when needed.
 */
public class DataHandler {
    /**
     * Updates the local cache (JSON file) with the latest API data.
     *
     * @param artist         Artist object to save
     * @param stats          Stats object to attach
     * @param similarArtist  Similar artist information to attach
     * @param bio            Artist biography to attach
     * @param topArtists     Top artists to attach
     * @param track          Track information to attach
     * @param topTags        Top tags for the track
     * @param wiki           Wiki information for the track
     * @param topSongs       Top songs information
     */
    public static void  updateLastAPICallCache(Artist artist, Stats stats, SimilarArtist similarArtist, Bio bio, TopArtists topArtists, Track track, TopTags topTags, Wiki wiki, TopSongs topSongs){
        // Set linked data into the Artist object
        artist.setStats(stats);
        artist.setSimilarArtist(similarArtist);
        artist.setBio(bio);
        artist.setTopArtists(topArtists);

        // Set linked data into the Track object
        track.setTopTags(topTags);
        track.setWiki(wiki);
        track.setTopSongs(topSongs);

        artist.setTrack(track);// Link track back into artist

        // Serialize the Artist object into JSON and save it to a file
        try{
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("last_api_call_cache.json");
            gson.toJson(artist, writer);
            writer.close();

        }catch (IOException e){
            e.getMessage();
        }



    }
    /**
     * Reads cached data from the last API call from the JSON file.
     *
     * @return Artist object populated with the cached data
     * @throws IOException if the cache file could not be read
     */
    public static Artist readCashedData() throws IOException{
            Gson gson = new Gson();
            FileReader reader = new FileReader("last_api_call_cache.json");
            // Deserialize JSON content into an Artist object
            Artist info = gson.fromJson(reader, Artist.class);
            return info;
    }


}
