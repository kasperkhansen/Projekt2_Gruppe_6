package View.MenuSystem;

import Controller.MedlemController;

import java.time.LocalDate;

import static View.Input.*;

/*


Registrere medlemmer.

 */

public class FormandSubMenu extends SubMenu {

    public FormandSubMenu () {
        displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Formand Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Registrerer Medlem");
            System.out.println("2. ");
            System.out.println("3. Afslut");

            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    MedlemController.registrerMedlem();
                    System.out.println("test: registrer medlem method chosen");
                    break;
                case 2:


                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Afslutter...");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }

    public static void main(String[] args) {
        new FormandSubMenu();
    }
}
