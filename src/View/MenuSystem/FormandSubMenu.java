package View.MenuSystem;

import Controller.DatabaseController;
import Controller.MedlemController;

import java.time.LocalDate;

import static View.Input.*;


public class FormandSubMenu {

    public FormandSubMenu () {
        displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Formand Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Registrer Medlem");
            System.out.println("2. Gå tilbage til Bruger menu");
            System.out.println("3. Afslut");

            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    MedlemController.registrerMedlem();
                    DatabaseController.saveArrToFileDatabase();
                    break;
                case 2:
                    UserMenu.displayMenu();
                    break;
                case 3:
                    System.out.println("Afslutter...");
                    System.exit(0);
                    break;
                case 4:

                    break;
                case 5:

                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }
}
