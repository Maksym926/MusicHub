public class MusicHub {

    // The main entry point of the program
    public static void main(String[] args){
        run(); // Calls the run method to start the application
    }

    // The run method that sets up the application components
    private static void run(){
        Model model = new Model(); // Creates an instance of the Model class
        ConsoleView view = new ConsoleView(); // Creates an instance of the ConsoleView class
        Presenter presenter = new Presenter(model, view); // Creates an instance of the Presenter class, passing the model and view
        presenter.importColors(); // Calls the importColors method on the presenter to set up color data or settings
        presenter.DisplayGameMenu(); // Calls the DisplayGameMenu method on the presenter to display the menu in the console view
    }
}
