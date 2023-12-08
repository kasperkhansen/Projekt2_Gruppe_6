package View.MenuSystem;


import Controller.DatabaseController;

import Controller.TraenerController;


import static View.Input.intInput;

public class TraenerSubMenu {

    public TraenerSubMenu () {
        displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Traener Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("Hvad vil De gerne?:");
            System.out.println(" 1. Registrering af svoemmernes traenings resultater");
            System.out.println(" 2. Se alle med resultater");
            System.out.println(" 3. Gå tilbage til Bruger menu");
            System.out.println(" 4. Afslut");

            int choice = intInput("Valg: ");



            switch (choice) {
                case 1:
                    TraenerController.registrerResultat();
                    DatabaseController.saveArrToFileDatabase();
                    break;

                case 2:
                    TraenerController.seAlleMedResultater();
                    DatabaseController.saveArrToFileDatabase();
                    break;
                case 3:
                    UserMenu.displayMenu();
                    break;
                case 4:
                    System.out.println("Afslutter...");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }

        }
    }

}
