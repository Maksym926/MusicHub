import java.util.Scanner;

interface IVeiw{
    void setPresenter(Presenter presenter);
    void displayMenu();
    void updateConsole(String data);
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
            System.out.println("Press 1, to get number of song listeners ");
            System.out.println("Press 2, to get number of song play count ");
            System.out.println("Press Q, to leave MusicHub");
            String option = getUserInput();
            if(option.equals("1")){
                System.out.println("Enter the name of the artist to get listeners");
                String songName = getUserInput();
                presenter.getInfoByOption(songName, option);

            }
            else if(option.equals("2")){
                System.out.println("Enter the artist name or the name of the song, to get play count ");
                String input = getUserInput();
                presenter.getInfoByOption(input, option);
            }
            else if(option.equals("Q")){
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
