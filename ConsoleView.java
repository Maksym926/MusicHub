import java.util.Scanner;

interface IVeiw{
    void setPresenter(Presenter presenter);
    void displayMenu();
    void updateConsole(String data);
    boolean showInternetErrorMsg();

}
public class ConsoleView implements IVeiw {
    Presenter presenter;


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }


    public void displayMenu() {
        boolean isEnd = false;
        while (!isEnd){

            System.out.println("MusicHub menu");
            System.out.println("Enter artist, to get more info about artist");
            System.out.println("Enter song, to get more info about song");
            System.out.println("Enter keyword topArtists, if you want to get top artists of specific country");
            System.out.println("Enter keyword topSongs, if you want to get top songs of specific country");
            System.out.println("Enter S, to go to the settings");

            System.out.println("Press Q, to leave MusicHub");
            String userChoice = getUserInput();
            if(userChoice.equals("artist")){
                System.out.println("Enter the artist's name");
                String artistName = getUserInput();

                boolean completed = false;

                while (!completed){

                    System.out.println("Enter keyword ArtistBasic, if you want to get basic artist info");
                    System.out.println("Enter keyword ArtistExpanded, if you want to get expanded artist info");

                    System.out.println("Press Q, to leave this option");
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
                    System.out.println("Enter artist's name");
                    String artistName = getUserInput();
                    System.out.println("Enter a song name");
                    String songName = getUserInput();
                    System.out.println("Enter keyword SongBasic, if you want to get basic artist's song info");
                    System.out.println("Enter keyword SongExpanded, if you want to get expanded artist's song info");

                    String input = getUserInput();

                    switch (input){
                        case "SongBasic", "SongExpanded":
                            presenter.getInfoByOption(artistName, songName, input);
                            break;
                        case "Q":
                            completed = true;
                            break;
                    }
                }
            }
            else if(userChoice.equals("topSongs")){
                System.out.println("Enter the country");
                String country = getUserInput();
                presenter.getInfoByOption(country, userChoice);
            }
            else if(userChoice.equals("topArtists")){
                System.out.println("Enter the country");
                String country = getUserInput();
                presenter.getInfoByOption(country, userChoice);
            }
            else if(userChoice.equals("Q")){
                presenter.updateJsonFile();
                isEnd = true;
            }
        }
    }

    public boolean showInternetErrorMsg(){
        System.out.println("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' if you want to come back to the main menu" );
        String input = getUserInput();
        if(input.equals("Yes")){
            return true;
        }
        return false;
    }

    public void updateConsole(String data) {
        System.out.println(data);
    }

    private String getUserInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return  input;
    }



}
