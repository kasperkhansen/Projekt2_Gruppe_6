package View.MenuSystem;

import static View.Input.intInput;


public class KassererSubMenu extends SubMenu {

    public KassererSubMenu () {
        displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Kasserer Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Registrer kontingentbetalinger");
            System.out.println("2. Overblik over kontingentbetalinger.");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. Exit");

            int choice = intInput("Choice: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    //kontingentController.registrerKontingentBetaling();
                    System.out.println("kontingentController.registrerKontingentBetaling();");
                    break;
                case 2:
                    //kontingentController.overblikOverKontingentBetalinger();
                    System.out.println("kontingentController.overblikOverKontingentBetalinger();");
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
