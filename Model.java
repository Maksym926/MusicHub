import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import json.*;

public class Model {

    private static final String API_KEY = "b129759fc85be1b4eca753741e115b2c";

    Stats stats = new Stats();

    public String findInfoByOption(String input, String option ){
        try {
            String urlString;

            urlString = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="
                    + input.replace(" ", "+")
                    + "&api_key=" + API_KEY + "&format=json";

            String info = getInfo(urlString, option);
            return info;

        }catch (JSONException e){
            System.out.println("Artist or song not found");
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
        JSONObject jsonObject = new JSONObject(response.toString());
        if(option.equals("1")){
            JSONObject artist = jsonObject.getJSONObject("artist");
            String listeners = artist.getJSONObject("stats").getString("listeners");
            updateObject(listeners, "1");
            return " Number of listeners: " + listeners;

        }
        if(option.equals("2")){
            JSONObject artist = jsonObject.getJSONObject("artist");
            String playCount = artist.getJSONObject("stats").getString("playcount");
            return playCount;
        }



        return "";  // Return the artist's bio or any other data you'd like
    }
    private void updateObject(String newData, String option){
        if(option.equals("1")){
           stats.setNumberOfListeners(newData);


        }
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
