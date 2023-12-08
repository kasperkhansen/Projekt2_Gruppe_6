import Controller.DatabaseController;
import View.MenuSystem.*;


public class Main {
    public static void main(String[] args) {
        DatabaseController.load();
        UserMenu.displayMenu();
    }
    
}