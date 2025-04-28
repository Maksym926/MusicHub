import java.io.IOException;
/**
 * Presenter class acts as a bridge between the Model and the View.
 * It handles the application logic and communication.
 */
public class Presenter {
    // Model and View references
    Model model;
    ConsoleView view;

    /**
     * Constructor: links the Presenter with a Model and a View.
     */
    Presenter(Model model, ConsoleView view){
        this.model = model;
        this.view = view;
        view.setPresenter(this);
    }
    /**
     * Starts the main menu if the API is available.
     * If there is no internet connection, prompts user to view cached data instead.
     */
    public void DisplayGameMenu(){
        if(model.isApiAvailable()){
            view.displayMenu();
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' to exit MusicHub" )){
                try{
                    view.showCachedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }
            }
        }

    }
    /**
     * Retrieves artist/country information based on user input and option,
     * and updates the console with the result.
     * If offline, shows cached data instead.
     */
    public void getInfoByOption(String input, String option){
        if(model.isApiAvailable()){
            String result = model.findInfoByOption(input, option);
            view.updateConsole(result);
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' if you want to come back to the main menu" )){
                try{
                    view.showCachedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }
            }
        }

    }

    /**
     * Retrieves song information based on artist name, song name, and the user's option.
     * If offline, shows cached data instead.
     */
    public void getInfoByOption(String artistName, String songName, String option){
        if(model.isApiAvailable()){
            String result = model.findInfoByOption(artistName, songName, option);
            view.updateConsole(result);
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' if you want to come back to the main menu" )){
                try{
                    view.showCachedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }

            }
        }

    }

    /**
     * Updates the local cache with the latest API data
     * by calling the DataHandler.
     */
    public void updateJsonFile(){
        DataHandler.updateLastAPICallCache(model.newArtist, model.stats, model.similarArtist, model.bio, model.topArtists, model.track, model.tags, model.wiki, model.topSongs);
    }
    /**
     * Imports previously saved color settings from a configuration file.
     */
    public void importColors(){
        view.importColorsFromFile();
    }


}
