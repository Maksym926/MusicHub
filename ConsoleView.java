import json.Artist.Artist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
/**
 * Interface for Console View operations.
 */
interface IView {
    void setPresenter(Presenter presenter);
    void displayMenu();
    void updateConsole(String data);
    boolean showInternetErrorMsg(String errorMessage);
    void showCachedData(Artist info);
}
/**
 * ConsoleView class handles the user interaction part of the application
 */
public class ConsoleView implements IView {
    //create presenter object
    private Presenter presenter;
    // ANSI color codes for text styling
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    // Color settings for various parts of the UI
    public static String headingsColor = RESET;
    public static String optionColor = RESET;
    public static String outputColor = RESET;
    public static String cachedDataColor = RESET;

    // set presenter, to retrieve presenter's methods
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Displays the main menu and handles user navigation through options.
     */
    public void displayMenu() {
        boolean isEnd = false;
        while (!isEnd) {
            displayMainMenu();
            String userChoice = getUserInput();

            switch (userChoice.toLowerCase()) {
                case "artist" -> handleArtistOption();
                case "song" -> handleSongOption();
                case "topartists", "topsongs" -> handleTopOption(userChoice);
                case "s" -> handleSettings();
                case "q" -> {
                    presenter.updateJsonFile();
                    isEnd = true;
                }
                default -> System.out.println(RED + "Invalid option. Please try again." + RESET);
            }
        }
    }
    /**
     * Displays the main menu options.
     */
    private void displayMainMenu() {
        System.out.println(headingsColor + "+----------------------------+");
        System.out.println("|         MUSIC HUB          |");
        System.out.println("+----------------------------+" + RESET);
        System.out.println(optionColor + "Enter 'artist' to get information about an artist.");
        System.out.println("Enter 'song' to get information about a song.");
        System.out.println("Enter 'topArtists' to get top artists from a country.");
        System.out.println("Enter 'topSongs' to get top songs from a country.");
        System.out.println("Enter 'S' to open settings.");
        System.out.println("Enter 'Q' to quit MusicHub." + RESET);
    }
    /**
     * Handles user input related to artist information retrieval.
     */
    private void handleArtistOption() {
        System.out.println(optionColor + "Enter the artist's name:" + RESET);
        String artistName = getUserInput();
        boolean completed = false;

        while (!completed) {
            System.out.println(optionColor + "Enter 'ArtistBasic' for basic info or 'ArtistExpanded' for detailed info.");
            System.out.println("Enter 'Q' to go back." + RESET);
            String input = getUserInput();
            switch (input) {
                case "ArtistBasic", "ArtistExpanded" -> presenter.getInfoByOption(artistName, input);
                case "Q" -> completed = true;
                default -> System.out.println(RED + "Invalid option." + RESET);
            }
        }
    }
    /**
     * Handles user input related to song information retrieval.
     */
    private void handleSongOption() {
        boolean completed = false;

        while (!completed) {
            System.out.println(optionColor + "Enter the artist's name or 'Q' to go back:" + RESET);
            String artistName = getUserInput();
            if (artistName.equalsIgnoreCase("Q")) {
                completed = true;
            } else {
                System.out.println(optionColor + "Enter the song's name:" + RESET);
                String songName = getUserInput();
                System.out.println(optionColor + "Enter 'SongBasic' for basic info or 'SongExpanded' for detailed info." + RESET);
                String input = getUserInput();
                presenter.getInfoByOption(artistName, songName, input);
            }
        }
    }
    /**
     * Handles user input related to top artists or songs for a specific country.
     */
    private void handleTopOption(String type) {
        System.out.println(optionColor + "Enter the country:" + RESET);
        String country = getUserInput();
        presenter.getInfoByOption(country, type);
    }
    /**
     * Manages the color settings menu.
     */
    private void handleSettings() {
        boolean backToMenu = false;
        while (!backToMenu) {
            displayColorSettings();
            String option = getUserInput();
            switch (option) {
                case "1" -> customizeColor("headings");
                case "2" -> customizeColor("options");
                case "3" -> customizeColor("outputs");
                case "4" -> customizeColor("cachedData");
                case "5" -> resetColors();
                case "6" -> backToMenu = true;
                default -> System.out.println(RED + "Invalid choice." + RESET);
            }
        }
    }
    /**
     * Allows the user to set the color for a specific section.
     */
    private void customizeColor(String section) {
        displayColorOptions();
        String colorChoice = getUserInput();
        String color = switch (colorChoice) {
            case "1" -> RED;
            case "2" -> GREEN;
            case "3" -> BLUE;
            case "4" -> YELLOW;
            case "5" -> RESET;
            default -> RESET;
        };

        switch (section) {
            case "headings" -> headingsColor = color;
            case "options" -> optionColor = color;
            case "outputs" -> outputColor = color;
            case "cachedData" -> cachedDataColor = color;
        }
        setColorInConfig(section, color);
    }
    /**
     * Resets all colors back to default (no color).
     */
    private void resetColors() {
        headingsColor = optionColor = outputColor = cachedDataColor = RESET;
        setColorInConfig("headings", RESET);
        setColorInConfig("options", RESET);
        setColorInConfig("outputs", RESET);
        setColorInConfig("cachedData", RESET);
    }
    /**
     * Displays the color settings menu.
     */
    private void displayColorSettings() {
        System.out.println(optionColor + "\u2554\u2550 Color Settings \u2557");
        System.out.println("1. Headings Color");
        System.out.println("2. Options Color");
        System.out.println("3. Output Color");
        System.out.println("4. Cached Data Color");
        System.out.println("5. Reset All Colors");
        System.out.println("6. Back to Menu" + RESET);
    }
    /**
     * Displays the color options available to the user.
     */
    private void displayColorOptions() {
        System.out.println("Select a Color:");
        System.out.println("1." + RED + " Red" + RESET);
        System.out.println("2." + GREEN + " Green" + RESET);
        System.out.println("3." + BLUE + " Blue" + RESET);
        System.out.println("4." + YELLOW + " Yellow" + RESET);
        System.out.println("5. Default" + RESET);
    }

    /**
     * Shows an internet error message and prompts for retry.
     */
    public boolean showInternetErrorMsg(String message) {
        System.out.println(message);
        String input = getUserInput();
        return input.equalsIgnoreCase("Yes");
    }

    /**
     * Updates the console with the given data.
     */
    public void updateConsole(String data) {
        System.out.println(outputColor + data + RESET);
    }

    /**
     * Displays cached artist and song information in the console.
     */
    public void showCachedData(Artist info) {
        System.out.println(cachedDataColor + "===========================" + RESET);
        System.out.println(cachedDataColor + "  Artist Information" + RESET);
        System.out.println(cachedDataColor + "===========================" + RESET);

        System.out.println("Name: " + info.getName());
        System.out.println("Listeners: " + info.getStats().getNumberOfListeners());
        System.out.println("PlayCount: " + info.getStats().getPlayCount());
        System.out.println("Tags: " + info.getStats().getTags());
        System.out.println("Similar Artists: " + info.getSimilarArtist().getSimilarArtistName());
        System.out.println("Biography: " + info.getArtistBio().getSummary());
        System.out.println("Top Artists: " + info.getTopArtists());
        System.out.println("Published Date: " + info.getArtistBio().getPublisedDate());

        System.out.println(cachedDataColor + "===========================" + RESET);
        System.out.println(cachedDataColor + "  Song Information" + RESET);
        System.out.println(cachedDataColor + "===========================" + RESET);

        System.out.println("Track Name: " + info.getTrack().getTrackName());
        System.out.println("Artist: " + info.getTrack().getArtistName());
        System.out.println("Duration: " + info.getTrack().getDuration());
        System.out.println("PlayCount: " + info.getTrack().getPlayCount());
        System.out.println("Listeners: " + info.getTrack().getNumberOfListeners());
        System.out.println("Wiki: " + info.getTrack().getWiki().getSummary());
        System.out.println("Tags: " + info.getTrack().getTopTags());
        System.out.println("Top Songs: " + info.getTrack().getTopSongs());
        System.out.println("Published Date: " + info.getTrack().getWiki().getPublisedDate());
    }
    /**
     * Reads a single line of user input.
     */
    private String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    /**
     * Saves a color setting into the config properties file.
     */
    private void setColorInConfig(String key, String value) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }

        properties.setProperty(key, value);

        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            properties.store(fos, "Updated colors");
        } catch (IOException e) {
            System.err.println("Error saving properties file: " + e.getMessage());
        }
    }
    /**
     * Loads previously saved color settings from the config file.
     */
    public void importColorsFromFile() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            headingsColor = properties.getProperty("headings", RESET);
            optionColor = properties.getProperty("options", RESET);
            outputColor = properties.getProperty("outputs", RESET);
            cachedDataColor = properties.getProperty("cachedData", RESET);
        } catch (IOException e) {
            System.err.println("Error loading colors from config file: " + e.getMessage());
        }
    }
}
