package View.MenuSystem;

import static View.Input.intInput;

/*

 */

public class MedlemSubMenu extends SubMenu {

    public MedlemSubMenu () {
        displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Medlem Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Ændring af medlemskab");
            System.out.println("2. ");
            System.out.println("3. Exit");

            int choice = intInput("Choice: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                   // medlemController.aendreMedlemskab();
                    System.out.println("medlemController.aendreMedlemskab() chosen");
                    break;
                case 2:
                    //kontingentController.koebEnGangsBillet();
                    System.out.println("kontingentController.koebEnGangsBillet()");
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input, Try Again");
            }
        }
    }

}
