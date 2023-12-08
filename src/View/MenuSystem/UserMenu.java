package View.MenuSystem;

import Controller.DatabaseController;
import Controller.MedlemController;
import View.Input;

public class UserMenu {

    public static void displayMenu() {

        System.out.println("\nVelkommen til Delfinen!");
        System.out.println(" - din lokale svømmeklub");

        System.out.println("----------------------------------");
        System.out.println();
        while (true) {
            System.out.println("Hvilken bruger type er De?:");
            System.out.println(" 1. Formand");
            System.out.println(" 2. Kasserer");
            System.out.println(" 3. Traener");
            System.out.println(" 4. Medlem");
            System.out.println(" 5. Afslut");
            int choice = Input.intInput("Valg: ");

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
                    System.out.println("Afslutter...");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }
}
