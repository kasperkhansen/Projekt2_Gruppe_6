import Controller.DatabaseController;
import View.MenuSystem.*;

import Controller.DatabaseController.*;


public class Main {
    public static void main(String[] args) {
        View.MenuSystem.UserMenu.displayMenu();
        DatabaseController.printDatabase();
    }
}
