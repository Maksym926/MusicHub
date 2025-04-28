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

    public Stats stats = new Stats();
    public final Artist newArtist = new Artist();
    public final SimilarArtist similarArtist = new SimilarArtist();
    public final Bio bio = new Bio();
    public final TopArtists topArtists = new TopArtists();
    public final Track track = new Track();
    public final TopTags tags = new TopTags();
    public final Wiki wiki = new Wiki();
    public final TopSongs topSongs = new TopSongs();

    public String findInfoByOption(String input, String option) {
        try {
            String urlString = buildUrl(input, option);
            if (urlString.isEmpty()) return "";

            return getInfo(urlString, option);
        } catch (JSONException e) {
            System.out.println("Artist or song could not be found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String findInfoByOption(String artistName, String songName, String option) {
        track.setArtistName(artistName);
        track.setTrackName(songName);

        try {
            String urlString = String.format(
                    "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=%s&artist=%s&track=%s&format=json",
                    API_KEY,
                    artistName.replace(" ", "+"),
                    songName.replace(" ", "+")
            );

            return getInfo(urlString, option);
        } catch (JSONException e) {
            System.out.println("Artist or song could not be found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String buildUrl(String input, String option) {
        switch (option) {
            case "ArtistExpanded":
            case "ArtistBasic":
                newArtist.setName(input);
                return String.format(
                        "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=%s&api_key=%s&format=json",
                        newArtist.getName().replace(" ", "+"), API_KEY
                );
            case "topSongs":
                return String.format(
                        "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=%s&api_key=%s&format=json",
                        input, API_KEY
                );
            case "topArtists":
                return String.format(
                        "http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=%s&api_key=%s&format=json",
                        input, API_KEY
                );
            default:
                return "";
        }
    }

    private String getInfo(String urlString, String option) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to fetch data: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        switch (option) {
            case "ArtistBasic":
                return processArtistBasic(jsonResponse);
            case "ArtistExpanded":
                return processArtistExpanded(jsonResponse);
            case "SongBasic":
                return processSongBasic(jsonResponse);
            case "SongExpanded":
                return processSongExpanded(jsonResponse);
            case "topArtists":
                return processTopArtists(jsonResponse);
            case "topSongs":
                return processTopSongs(jsonResponse);
            default:
                return "";
        }
    }

    private String processArtistBasic(JSONObject jsonResponse) throws JSONException {
        JSONObject artist = jsonResponse.getJSONObject("artist");
        String listeners = artist.getJSONObject("stats").getString("listeners");
        String playCount = artist.getJSONObject("stats").getString("playcount");

        ArrayList<String> similarArtists = getSimilarArtists(artist);

        updateArtistObject(listeners, playCount, similarArtists.get(0));

        return String.format(
                "Artist's Name: %s%nNumber of Listeners: %s%nPlay Count: %s%nSimilar Artist: %s",
                newArtist.getName(), listeners, playCount, similarArtists.get(0)
        );
    }

    private String processArtistExpanded(JSONObject jsonResponse) throws JSONException {
        JSONObject artist = jsonResponse.getJSONObject("artist");
        String listeners = artist.getJSONObject("stats").getString("listeners");
        String playCount = artist.getJSONObject("stats").getString("playcount");
        String artistBio = artist.getJSONObject("bio").getString("summary");
        String publishedDate = artist.getJSONObject("bio").getString("published");

        ArrayList<String> similarArtists = getSimilarArtists(artist);

        updateArtistObject(listeners, playCount, String.join(", ", similarArtists), artistBio, publishedDate);

        return String.format(
                "Artist's Name: %s%nNumber of Listeners: %s%nPlay Count: %s%nSimilar Artists: %s%nBio: %s%nDate: %s",
                newArtist.getName(), listeners, playCount, String.join(", ", similarArtists), artistBio, publishedDate
        );
    }

    private String processSongBasic(JSONObject jsonResponse) throws JSONException {
        JSONObject track = jsonResponse.getJSONObject("track");
        String trackName = track.getString("name");
        String duration = track.getString("duration");
        String listeners = track.getString("listeners");
        String playCount = track.getString("playcount");

        ArrayList<String> tagList = getTags(track);

        updateTrackObject(duration, listeners, playCount, tagList.get(0));

        return String.format(
                "Artist's Name: %s%nTrack Name: %s%nNumber of Listeners: %s%nPlay Count: %s%nSong Duration: %s%nTag: %s",
                newArtist.getName(), trackName, listeners, playCount, duration, tagList.get(0)
        );
    }

    private String processSongExpanded(JSONObject jsonResponse) throws JSONException {
        JSONObject track = jsonResponse.getJSONObject("track");
        String trackName = track.getString("name");
        String duration = track.getString("duration");
        String listeners = track.getString("listeners");
        String playCount = track.getString("playcount");
        String summary = track.getJSONObject("wiki").getString("summary");
        String publishedDate = track.getJSONObject("wiki").getString("published");

        ArrayList<String> tagList = getTags(track);

        updateTrackObject(duration, listeners, playCount, String.join(", ", tagList), summary, publishedDate);

        return String.format(
                "Artist's Name: %s%nTrack Name: %s%nNumber of Listeners: %s%nPlay Count: %s%nSong Duration: %s%nTags: %s%nSummary: %s%nPublished Date: %s",
                newArtist.getName(), trackName, listeners, playCount, duration, String.join(", ", tagList), summary, publishedDate
        );
    }

    private String processTopArtists(JSONObject jsonResponse) throws JSONException {
        JSONArray artists = jsonResponse.getJSONObject("topartists").getJSONArray("artist");
        ArrayList<String> artistNames = new ArrayList<>();

        for (int i = 0; i < artists.length(); i++) {
            artistNames.add(artists.getJSONObject(i).getString("name"));
        }

        updateArtistObject(String.join(", ", artistNames));

        return "Top Artists:\n" + String.join("\n", artistNames);
    }

    private String processTopSongs(JSONObject jsonResponse) throws JSONException {
        JSONArray tracks = jsonResponse.getJSONObject("tracks").getJSONArray("track");
        ArrayList<String> trackNames = new ArrayList<>();

        for (int i = 0; i < tracks.length(); i++) {
            trackNames.add(tracks.getJSONObject(i).getString("name"));
        }

        updateTrackObject(String.join(", ", trackNames));

        return "Top Songs:\n" + String.join("\n", trackNames);
    }

    private ArrayList<String> getSimilarArtists(JSONObject artist) throws JSONException {
        JSONArray similar = artist.getJSONObject("similar").getJSONArray("artist");
        ArrayList<String> similarArtists = new ArrayList<>();

        for (int i = 0; i < similar.length(); i++) {
            similarArtists.add(similar.getJSONObject(i).getString("name"));
        }

        if (similarArtists.isEmpty()) {
            similarArtists.add("No similar artists were found");
        }
        return similarArtists;
    }

    private ArrayList<String> getTags(JSONObject track) throws JSONException {
        JSONArray tagsArray = track.getJSONObject("toptags").getJSONArray("tag");
        ArrayList<String> tagList = new ArrayList<>();

        for (int i = 0; i < tagsArray.length(); i++) {
            tagList.add(tagsArray.getJSONObject(i).getString("name"));
        }

        if (tagList.isEmpty()) {
            tagList.add("No tags were found");
        }
        return tagList;
    }

    private void updateArtistObject(String topArtistsString) {
        topArtists.setTopArtists(topArtistsString);
    }

    private void updateArtistObject(String listeners, String playCount, String similarArtistName) {
        stats.setNumberOfListeners(listeners);
        stats.setPlayCount(playCount);
        similarArtist.setSimilarArtistName(similarArtistName);
    }

    private void updateArtistObject(String listeners, String playCount, String similarArtistName, String bioSummary, String publishedDate) {
        stats.setNumberOfListeners(listeners);
        stats.setPlayCount(playCount);
        similarArtist.setSimilarArtistName(similarArtistName);
        bio.setSummary(bioSummary);
        bio.setPublisedDate(publishedDate);
    }

    private void updateTrackObject(String topSongsString) {
        topSongs.setTopSongs(topSongsString);
    }

    private void updateTrackObject(String duration, String listeners, String playCount, String tag) {
        track.setDuration(duration);
        track.setNumberOfListeners(listeners);
        track.setPlayCount(playCount);
        tags.setTags(tag);
    }

    private void updateTrackObject(String duration, String listeners, String playCount, String tags, String summary, String publishedDate) {
        track.setDuration(duration);
        track.setNumberOfListeners(listeners);
        track.setPlayCount(playCount);
        this.tags.setTags(tags);
        wiki.setSummary(summary);
        wiki.setPublisedDate(publishedDate);
    }

    public boolean isApiAvailable() {
        try {
            URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=&api_key=" + API_KEY + "&format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }
}
