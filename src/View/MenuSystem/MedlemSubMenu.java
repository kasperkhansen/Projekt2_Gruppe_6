package View.MenuSystem;

import Controller.KontingentController;
import Controller.MedlemController;

import static View.Input.intInput;

/*

 */

public class MedlemSubMenu {

    public MedlemSubMenu () {
        displaySubMenu();
    }

    private static void displaySubMenu() {
        System.out.println("Medlem Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("\nVælg en mulighed:");
            System.out.println("1. Ændring af medlemskab");
            System.out.println("2. Køb enganges billet");
            System.out.println("3. Gå tilbage til Bruger menu");
            System.out.println("4. Afslut");

            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    MedlemController.skiftMedlemskabMedInputScan();
                    System.out.println("medlemController.aendreMedlemskab() chosen");
                    break;
                case 2:
                    MedlemController.betalEngangsbillet();
                    System.out.println("kontingentController.koebEnGangsBillet()");
                    break;
                case 3:
                    UserMenu.displayMenu();

                case 4:
                    System.out.println("Afslutter...");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }
}
