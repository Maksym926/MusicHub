import json.Artist.*;
import json.Track.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Model {

    private static final String API_KEY = "b129759fc85be1b4eca753741e115b2c";

    Stats stats = new Stats();
    Artist newArtist = new Artist();
    SimilarArtist similarArtist = new SimilarArtist();
    Bio bio = new Bio();
    TopArtists topArtists = new TopArtists();

    Track track = new Track();
    TopTags tags = new TopTags();
    Wiki wiki = new Wiki();
    TopSongs topSongs = new TopSongs();

    public String findInfoByOption(String input, String option){

        try {
            String urlString = "";
            switch (option) {
                case "artist":
                        newArtist.setName(input);
                        urlString ="http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="
                        + newArtist.getName().replace(" ", "+")
                        + "&api_key=" + API_KEY + "&format=json";

                        break;

                case "topSongs":
                        urlString = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=" +input + "&api_key=" + API_KEY + "&format=json";
                        break;
                case "topArtists":
                        urlString ="http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=" +input + "&api_key=" + API_KEY + "&format=json";
                        break;
            }

            return getInfo(urlString, option);

        }catch (JSONException e){
            System.out.println("Artist or song have not been found");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public String findInfoByOption(String artistName, String songName, String option){
        track.setArtistName(artistName);
        track.setTrackName(songName);

        try {
            String urlString;



            urlString = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=" +  API_KEY +"&artist=" + artistName.replace(" ", "+") + "&track=" + songName.replace(" ", "+") + "&format=json";

            return getInfo(urlString, option);

        }catch (JSONException e){
            System.out.println("Artist or song have not been found");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getInfo(String urlString, String option) throws Exception{
        URL url = new URL(urlString);

        // Open an HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get the response code and check if it was successful (200 OK)
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {

        }

        // Read the response from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
//        System.out.println("Response: " + response.toString());

        in.close();

        // Parse the JSON response using JSONObject
        JSONObject jsonResponse = new JSONObject(response.toString());
        System.out.println(jsonResponse);

            if(option.equals("ArtistBasic")) {
                JSONObject artist = jsonResponse.getJSONObject("artist");
                String listeners = artist.getJSONObject("stats").getString("listeners");
                String playCount = artist.getJSONObject("stats").getString("playcount");

                ArrayList<String> similarArtistName = new ArrayList<>();

                JSONArray similarArtists = artist
                        .getJSONObject("similar")
                        .getJSONArray("artist");

                System.out.println("Similar artists to " + newArtist.getName() + ":");
                for (int i = 0; i < similarArtists.length(); i++) {
                    JSONObject similar = similarArtists.getJSONObject(i);
                    similarArtistName.add(similar.getString("name"));
                }
                if (similarArtistName.isEmpty()) {
                    similarArtistName.add("similar artists have not been found");
                }

                updateArtistObject(listeners, playCount, similarArtistName.get(0));
                return "Artist's name: " + newArtist.getName() + "\n" + "Number of listeners: " + listeners + "\n" + "Play count: " + playCount + "\n" + "Similar artist: " + similarArtistName.get(0);
            }
            else if (option.equals("ArtistExpanded")){
                ArrayList<String> similarArtistName = new ArrayList<>();


                JSONObject artist = jsonResponse.getJSONObject("artist");
                String listeners = artist.getJSONObject("stats").getString("listeners");
                String playCount = artist.getJSONObject("stats").getString("playcount");


                JSONArray similarArtists = artist
                        .getJSONObject("similar")
                        .getJSONArray("artist");


                for (int i = 0; i < similarArtists.length(); i++) {
                    JSONObject similar = similarArtists.getJSONObject(i);
                    similarArtistName.add(similar.getString("name"));
                }
                if(similarArtistName.isEmpty()){
                    similarArtistName.add("similar artists have not been found");
                }

                String artistBio = artist.getJSONObject("bio").getString("summary");
                String publishedDate = artist.getJSONObject("bio").getString("published");
                updateArtistObject(listeners, playCount ,String.join(", ", similarArtistName), artistBio, publishedDate);
                return  "Artist's name: " + newArtist.getName() + "\n" + "Number of listeners: " + listeners + "\n" + "Play count: " + playCount + "\n" + "Similar artists: " + String.join(", ", similarArtistName) + "\n" + "Bio: " + artistBio + "\n" + "Date: " + publishedDate;

            }
            else if(option.equals("SongBasic")){

                ArrayList<String> tagsList = new ArrayList<>();
                JSONObject track = jsonResponse.getJSONObject("track");
                String trackName = track.getString("name");
                String songDuration = track.getString("duration");
                String listeners = track.getString("listeners");
                String playCount = track.getString("playcount");
                JSONArray tags = track.getJSONObject("toptags").getJSONArray("tag");

                for (int i = 0; i < tags.length(); i++){
                    JSONObject tag = tags.getJSONObject(i);
                    tagsList.add(tag.getString("name"));

                }
                if(tagsList.isEmpty()){
                    tagsList.add("No tags have been found");
                }


                updateTrackObject(songDuration, listeners, playCount, tagsList.get(0));
                return"Artist's name: " + newArtist.getName() + "\n" + "Track name: " + trackName + "\n" + "Number of listeners: " + listeners + "\n" + "Play count: " + playCount + "\n" + "Song duration: " + songDuration + "\n" + "Tag: " + tagsList.get(0) + "\n";

            }
            else if(option.equals("SongExpanded")){
                ArrayList<String> tagsList = new ArrayList<>();
                JSONObject track = jsonResponse.getJSONObject("track");
                System.out.println(track);
                String trackName = track.getString("name");
                String songDuration = track.getString("duration");
                String listeners = track.getString("listeners");
                String playCount = track.getString("playcount");
                JSONArray tags = track.getJSONObject("toptags").getJSONArray("tag");
                for (int i = 0; i < tags.length(); i++){
                    JSONObject tag = tags.getJSONObject(i);
                    tagsList.add(tag.getString("name"));
                }
                String summary = track.getJSONObject("wiki").getString("summary");
                String publishedDate = track.getJSONObject("wiki").getString("published");
                if(tags.isEmpty()){
                    tagsList.add("No tags have been found");
                }
                updateTrackObject(songDuration, listeners, playCount, String.join(", ", tagsList), summary ,publishedDate);
                return"Artist's name " + newArtist.getName() + "\n" + "Track name: " + trackName + "\n" + "Number of listeners: " + listeners + "\n" + "Play count: " + playCount + "\n" + "Song duration: " + songDuration + "\n" + "Tag: " + String.join(", ", tagsList) + "\n" + "Summary: " + summary + "\n" + "Publish date: " + publishedDate;

            }
            if(option.equals("topArtists")){
                ArrayList<String> topArtistsList = new ArrayList<>();

                // Parse the JSON response using JSONObject
                JSONArray artists = jsonResponse.getJSONObject("topartists").getJSONArray("artist");
                for(int i = 0; i < artists.length(); i++){
                    JSONObject artist = artists.getJSONObject(i);
                    topArtistsList.add(artist.getString("name"));
                }
                if(topArtistsList.isEmpty()){
                    topArtistsList.add("The top artists have not been found");
                }
                updateArtistObject(String.join(", ", topArtistsList));
                return "The most popular artists: " + String.join("\n", topArtistsList);
            }
            if(option.equals("topSongs")){
                ArrayList<String> topSongsList = new ArrayList<>();

                // Parse the JSON response using JSONObject
                JSONArray songs = jsonResponse.getJSONObject("tracks").getJSONArray("track");
                for(int i = 0; i < songs.length(); i++){
                    JSONObject artist = songs.getJSONObject(i);
                    topSongsList.add(artist.getString("name"));
                }
                if(topSongsList.isEmpty()){
                    topSongsList.add("The top songs have not been found");
                }
                updateTrackObject(String.join(", ", topSongsList));
                return "The most popular songs: " + String.join("\n", topSongsList);
            }
        return "";  // Return the artist's bio or any other data you'd like
    }
    private void updateArtistObject(String newTopArtists){
        topArtists.setTopArtists(newTopArtists);
    }
    private void updateArtistObject(String listener, String playCount, String similarArtistName){
        stats.setNumberOfListeners(listener);
        stats.setPlayCount(playCount);
        similarArtist.setSimilarArtistName(similarArtistName);
    }
    private void updateArtistObject(String listener, String playCount, String similarArtistName, String artistBio, String publishedDate){
        stats.setNumberOfListeners(listener);
        stats.setPlayCount(playCount);
        similarArtist.setSimilarArtistName(similarArtistName);
        bio.setSummary(artistBio);
        bio.setPublisedDate(publishedDate);
    }
    private void updateTrackObject(String newTopSongs){
        topSongs.setTopSongs(newTopSongs);
    }
    private void updateTrackObject( String trackDuration, String listener, String playCount, String tag){
        track.setDuration(trackDuration);
        track.setNumberOfListeners(listener);
        track.setPlayCount(playCount);
        tags.setTags(tag);
    }
    private void updateTrackObject(String trackDuration, String listener, String playCount, String newTags , String summary, String publishedDate){
        track.setDuration(trackDuration);
        track.setNumberOfListeners(listener);
        track.setPlayCount(playCount);
        tags.setTags(newTags);
        wiki.setSummary(summary);
        wiki.setPublisedDate(publishedDate);

    }


    public boolean isApiAvailable() {
        try {
            URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="
                    + "&api_key=" + API_KEY + "&format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            return statusCode == HttpURLConnection.HTTP_OK; // 200
        } catch (IOException e) {

            return false;
        }
    }

}
