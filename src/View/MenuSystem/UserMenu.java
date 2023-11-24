package View.MenuSystem;

import View.Input;

public class UserMenu {
    int choice;
    String userName;

    public UserMenu() {
        userName = Input.getNameInput("user name");
    }

    public void displayMenu() {
        System.out.println("\nWelcome " + userName + "!");
        System.out.println("--------------------");
        while (true) {
            System.out.println("\nHvilken bruger type er de?:");
            System.out.println("1. Formand");
            System.out.println("2. Kasserer");
            System.out.println("3. Traener");
            System.out.println("4. Medlem");
            System.out.println("5. Exit");
            int choice = Input.intInput("Choice: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    FormandSubMenu formandSubMenu = new FormandSubMenu();
                    break;
                case 2:
                    KassererSubMenu kassererSubMenu = new KassererSubMenu();
                    break;
                case 3:
                    TraenerSubMenu traenerSubMenu = new TraenerSubMenu();
                    break;
                case 4:
                    MedlemSubMenu medlemSubMenu = new MedlemSubMenu();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input, Try Again");
            }
        }
    }

}
