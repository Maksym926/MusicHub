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
        String urlString = "https://api.apileague.com/convert-image-to-ascii-txt?url=https://pbs.twimg.com/profile_images/1484668108539416580/dTvoAd3F_400x400.jpg&width=30&height=30";
        String apiKey = "aad418d189a7441eb7e96030d747a275";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-api-key", apiKey);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print result
                // Now, process the JSON response manually
                String json = response.toString();
                System.out.println(json);
                JSONObject obj = new JSONObject(json);
                String asciiArt = obj.getString("ascii_art");

                // Now fix newlines
                asciiArt = asciiArt.replace("\\n", ""); // remove fake newlines
                asciiArt = asciiArt.replace("\\\"", "\""); // optional

                // Split into lines 30 characters wide
                int width = 30;
                for (int i = 0; i < asciiArt.length(); i += width) {
                    int endIdx = Math.min(i + width, asciiArt.length());
                    System.out.println(asciiArt.substring(i, endIdx));
                }

            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
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