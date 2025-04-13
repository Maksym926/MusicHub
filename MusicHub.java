public class MusicHub {

    public static void main(String[] args){
        run();
    }
    private static void run(){
        Model model = new Model();
        ConsoleView view = new ConsoleView();
        Presenter presenter = new Presenter(model, view);
        presenter.DisplayGameMenu();
    }
}
