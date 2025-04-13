public class Presenter {
    Model model;
    ConsoleView view;
    Presenter(Model model, ConsoleView view){
        this.model = model;
        this.view = view;
        view.setPresenter(this);
    }
    public void DisplayGameMenu(){

        view.displayMenu();
    }
    public void getInfoByOption(String input, String option){
        if(model.isApiAvailable()){
            String result = model.findInfoByOption(input, option);
            view.updateConsole(result);
        }else{
            if(view.showInternetErrorMsg()){
                DataHandler.readCashedData();
            }
        }

    }
    public void updateJsonFile(){
        DataHandler.updateLastAPICallCache(model.stats);
    }


}
