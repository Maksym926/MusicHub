import json.Artist.Artist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

interface IVeiw{
    void setPresenter(Presenter presenter);
    void displayMenu();
    void updateConsole(String data);
    boolean showInternetErrorMsg(String errorMessage);
    void showCashedData(Artist info);

}
public class ConsoleView implements IVeiw {
    Presenter presenter;
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static  String headingsColor = RESET;
    public static String optionColor = RESET;
    public  static String outputColor = RESET;
    public static String hashedDataColor = RESET;


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    public void displayMenu() {
        boolean isEnd = false;
        while (!isEnd){

            System.out.println(headingsColor + "+----------------------------+");
            System.out.println("|        MUSIC HUB           |");
            System.out.println("+----------------------------+" + RESET);
            System.out.println(optionColor +"Enter artist, to get more info about artist");
            System.out.println("Enter song, to get more info about song");
            System.out.println("Enter keyword topArtists, if you want to get top artists of specific country");
            System.out.println("Enter keyword topSongs, if you want to get top songs of specific country");
            System.out.println("Enter S, to go to the settings");

            System.out.println("Press Q, to leave MusicHub" + RESET);
            String userChoice = getUserInput();
            if(userChoice.equals("artist")){
                System.out.println(optionColor + "Enter the artist's name" + RESET);
                String artistName = getUserInput();

                boolean completed = false;

                while (!completed){

                    System.out.println(optionColor +"Enter keyword ArtistBasic, if you want to get basic artist info");
                    System.out.println("Enter keyword ArtistExpanded, if you want to get expanded artist info");

                    System.out.println("Press Q, to leave this option" + RESET);
                    String input = getUserInput();
                    switch (input){
                        case "ArtistBasic", "ArtistExpanded":
                            presenter.getInfoByOption(artistName, input);
                            break;
                        case "Q":
                            completed = true;
                            break;
                    }
                }


            }
            else if(userChoice.equals("song")){
                boolean completed = false;

                while (!completed){
                    System.out.println(optionColor + "Enter artist's name or Q if you want to leave this option" + RESET);

                    String artistName = getUserInput();
                    if(!artistName.equalsIgnoreCase("Q")){
                        System.out.println(optionColor + "Enter a song name");
                        String songName = getUserInput();
                        System.out.println("Enter keyword SongBasic, if you want to get basic artist's song info");
                        System.out.println("Enter keyword SongExpanded, if you want to get expanded artist's song info" + RESET);

                        String input = getUserInput();


                        presenter.getInfoByOption(artistName, songName, input);

                    }else{
                        completed = true;
                    }


                }
            }
            else if(userChoice.equals("topSongs")){
                System.out.println(optionColor + "Enter the country" + RESET);
                String country = getUserInput();
                presenter.getInfoByOption(country, userChoice);
            }
            else if(userChoice.equals("topArtists")){
                System.out.println(optionColor + "Enter the country" + RESET);
                String country = getUserInput();
                presenter.getInfoByOption(country, userChoice);
            }
            else if(userChoice.equals("S")){
                displayColorSettings();
                String option = getUserInput();
                String colorOption;
                switch(option){
                    case "1":
                        displayColorOptions();
                        colorOption = getUserInput();
                        switch (colorOption){
                            case "1":
                                headingsColor = RED;
                                setColors("1", RED);
                                break;
                            case "2":
                                headingsColor =GREEN;
                                setColors("1", GREEN);
                                break;
                            case "3":
                                headingsColor =BLUE;
                                setColors("1", BLUE);
                                break;
                            case "4":
                                headingsColor =YELLOW;
                                setColors("1", YELLOW);
                                break;
                            case "5":
                                headingsColor = RESET;
                                setColors("1", RESET);
                                break;
                        }
                        break;
                    case "2":
                        displayColorOptions();
                        colorOption= getUserInput();
                        switch (colorOption){
                            case "1":
                                optionColor = RED;
                                setColors("2", RED);
                                break;
                            case "2":
                                optionColor =GREEN;
                                setColors("2", GREEN);
                                break;
                            case "3":
                                optionColor =BLUE;
                                setColors("2", BLUE);
                                break;
                            case "4":
                                optionColor =YELLOW;
                                setColors("2", YELLOW);
                                break;
                            case "5":
                                optionColor = RESET;
                                setColors("2", RESET);
                                break;
                        }
                        break;
                    case  "3":
                        displayColorOptions();
                        colorOption= getUserInput();
                        switch (colorOption){
                            case "1":
                                outputColor = RED;
                                setColors("3", RED);
                                break;
                            case "2":
                                outputColor =GREEN;
                                setColors("3", GREEN);
                                break;
                            case "3":
                                outputColor =BLUE;
                                setColors("3", BLUE);
                                break;
                            case "4":
                                outputColor =YELLOW;
                                setColors("3", YELLOW);
                                break;
                            case "5":
                                outputColor = RESET;
                                setColors("3", RESET);
                                break;
                        }
                        break;
                    case  "4":
                        displayColorOptions();
                        colorOption= getUserInput();
                        switch (colorOption){
                            case "1":
                                hashedDataColor = RED;
                                setColors("4", RED);
                                break;
                            case "2":
                                hashedDataColor =GREEN;
                                setColors("4", GREEN);
                                break;
                            case "3":
                                hashedDataColor=BLUE;
                                setColors("4", BLUE);
                                break;
                            case "4":
                                hashedDataColor=YELLOW;
                                setColors("4", YELLOW);
                                break;
                            case "5":
                                hashedDataColor = RESET;
                                setColors("4", RESET);
                                break;
                        }
                        break;
                    case "5":
                        hashedDataColor = RESET;
                        outputColor = RESET;
                        optionColor = RESET;
                        headingsColor = RESET;
                        setColors("5", RESET);


                }
            }
            else if(userChoice.equals("Q")){
                presenter.updateJsonFile();
                isEnd = true;
            }
        }
    }
    private void displayColorSettings(){
        System.out.println(optionColor +"╔═════════════════════════════════╗");
        System.out.println("║   Customize Console Colors      ║");
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║ 1. Logo Color                   ║");
        System.out.println("║ 2. Options Color                ║");
        System.out.println("║ 3. Output Messages Color        ║");
        System.out.println("║ 4. Cached Data Display Color    ║");
        System.out.println("║ 5. Reset to Default Colors      ║");
        System.out.println("║ 6. Back to Main Menu            ║");
        System.out.println("╚═════════════════════════════════╝");
        System.out.println("Please select what you want to customize (1-7): " + RESET);
    }
    private void displayColorOptions(){


        System.out.println("        Choose a Color        ");
        System.out.println("1." +RED + " Red" + RESET);
        System.out.println("2." + GREEN + " Green" + RESET);
        System.out.println("3." + BLUE + " Blue" + RESET);
        System.out.println("4." +YELLOW + " Yellow" + RESET);


        System.out.println("Please select a color (1-5): ");

    }
    public boolean showInternetErrorMsg(String message){
        System.out.println(message);
        String input = getUserInput();
        if(input.equals("Yes")){
            return true;
        }
        return false;
    }

    public void updateConsole(String data) {
        System.out.println(outputColor + data + RESET);
    }

    public void showCashedData(Artist info){
        System.out.println("===========================");
        System.out.println("  Information about artist  ");
        System.out.println("===========================");

        System.out.println("Artist's name: " + info.getName());
        System.out.println("The number of listeners: " +info.getStats().getNumberOfListeners());
        System.out.println("PlauCount" + info.getStats().getPlayCount());
        System.out.println("Tags: " + info.getStats().getTags());
        System.out.println("Similar Artists: " + info.getSimilarArtist().getSimilarArtistName());
        System.out.println("Artist's biography: " + info.getArtistBio().getSummary());
        System.out.println("Top Artists " + info.getTopArtists());
        System.out.println("Published date: " + info.getArtistBio().getPublisedDate());

        System.out.println("===========================");
        System.out.println("  Information about song  ");
        System.out.println("===========================");

        System.out.println("Track name: " + info.getTrack().getTrackName());
        System.out.println("Artist: " + info.getTrack().getArtistName());
        System.out.println("Duration of a song: " +info.getTrack().getDuration());
        System.out.println("PlayCount: " + info.getTrack().getPlayCount());
        System.out.println("The number of listeners: " + info.getTrack().getNumberOfListeners());
        System.out.println("Wiki: " + info.getTrack().getWiki().getSummary());
        System.out.println("Tags: " + info.getTrack().getTopTags());
        System.out.println("Top Songs " + info.getTrack().getTopSongs());
        System.out.println("Published date: " + info.getTrack().getWiki().getPublisedDate());


    }

    private String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return  input;
    }

    private void setColors(String option, String color){
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            if(option.equalsIgnoreCase("1")){
                properties.setProperty("headings", color);
                updateConfigFile(properties);
            }else if(option.equalsIgnoreCase("2")){
                properties.setProperty("options", color);
                updateConfigFile(properties);
            }else if(option.equalsIgnoreCase("3")){
                properties.setProperty("outputs", color);
                updateConfigFile(properties);
            }else if(option.equalsIgnoreCase("4")){
                properties.setProperty("hashedData", color);
                updateConfigFile(properties);
            }else if(option.equalsIgnoreCase("5")){
                properties.setProperty("headings", color);
                updateConfigFile(properties);
                properties.setProperty("options", color);
                updateConfigFile(properties);
                properties.setProperty("outputs", color);
                updateConfigFile(properties);
                properties.setProperty("hashedData", color);
                updateConfigFile(properties);

            }
        }catch (IOException e){
            e.getMessage();
        }
    }
    private void updateConfigFile(Properties properties){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("config.properties");

            // store() method is used to write the properties into properties file
            properties.store(fileOutputStream, "File is modified");

            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void importColorsFromFile(){
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            headingsColor = properties.getProperty("headings");
            optionColor = properties.getProperty("options");
            outputColor = properties.getProperty("outputs");
            hashedDataColor = properties.getProperty("hashedData");

        }
        catch (IOException e){
            e.getMessage();
        }
    }



}
