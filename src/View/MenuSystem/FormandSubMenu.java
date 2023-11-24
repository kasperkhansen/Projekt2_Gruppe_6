package View.MenuSystem;

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
            System.out.println("3. Exit");

            int choice = intInput("Choice: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
           //         medlemController.registrerMedlem();
                    System.out.println("test: registrer medlem method chosen");
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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
