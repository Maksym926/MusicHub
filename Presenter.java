import java.io.IOException;

public class Presenter {
    Model model;
    ConsoleView view;
    Presenter(Model model, ConsoleView view){
        this.model = model;
        this.view = view;
        view.setPresenter(this);
    }
    public void DisplayGameMenu(){
        if(model.isApiAvailable()){
            view.displayMenu();
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' to exit MusicHub" )){
                try{
                    view.showCashedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }
            }
        }

    }
    public void getInfoByOption(String input, String option){
        if(model.isApiAvailable()){
            String result = model.findInfoByOption(input, option);
            view.updateConsole(result);
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' if you want to come back to the main menu" )){
                try{
                    view.showCashedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }
            }
        }

    }
    public void getInfoByOption(String artistName, String songName, String option){
        if(model.isApiAvailable()){
            String result = model.findInfoByOption(artistName, songName, option);
            view.updateConsole(result);
        }else{
            if(view.showInternetErrorMsg("You were disconnected from the internet. To retrieve the data from your last API call, enter 'yes' or click 'no' if you want to come back to the main menu" )){
                try{
                    view.showCashedData(DataHandler.readCashedData());
                }catch (IOException e){
                    e.getMessage();
                }

            }
        }

    }


    public void updateJsonFile(){
        DataHandler.updateLastAPICallCache(model.newArtist, model.stats, model.similarArtist, model.bio, model.topArtists, model.track, model.tags, model.wiki, model.topSongs);
    }
    public void importColors(){
        view.importColorsFromFile();
    }


}
