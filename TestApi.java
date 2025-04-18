import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class TestApi {
    private static final String API_KEY = "b129759fc85be1b4eca753741e115b2c";  // Replace with your Last.fm API key

    public static void main(String[] args) {
        String artistName = "Queen";  // Replace with the artist you're interested in
        try {
            // Retrieve artist information
            String artistInfo = getArtistInfo(artistName);
            System.out.println("Artist Info: " + artistInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to retrieve artist information from Last.fm API using HttpURLConnection
    public static String getArtistInfo(String artistName) throws Exception {
        // Build the Last.fm API URL with the artist name and API key
        String urlString = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=" +"Ukraine" +  "&api_key=" +  API_KEY +  "&format=json";


        // Create a URL object
        URL url = new URL(urlString);

        // Open an HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get the response code and check if it was successful (200 OK)
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("HTTP request failed with code " + responseCode);
        }

        // Read the response from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println("Response: " + response.toString());

        in.close();

        ArrayList<String> topSongs = new ArrayList<>();

        // Parse the JSON response using JSONObject
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray songs = jsonResponse.getJSONObject("tracks").getJSONArray("track");
        for(int i = 0; i < songs.length(); i++){
            JSONObject artist = songs.getJSONObject(i);
            topSongs.add(artist.getString("name"));
        }
        System.out.println(String.join("\n", topSongs) );






        return "Number of listeners: " ;  // Return the artist's bio or any other data you'd like
    }

}