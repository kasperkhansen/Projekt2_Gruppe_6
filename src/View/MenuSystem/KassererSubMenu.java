package View.MenuSystem;

import static Controller.MedlemController.getMedlemMedId;
import static View.Input.intInput;
import Controller.KontingentController;
import Model.Medlem;
import View.Input;

import static View.Input.booleanInput;

public class KassererSubMenu {



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
            System.out.println("3. Gå tilbage til Bruger menu");
            System.out.println("4. Afslut");

            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                registrerKontingentBetaling();
                    break;
                case 2:
                    //kontingentController.overblikOverKontingentBetalinger();
                    System.out.println("kontingentController.seOverblik();");
                    break;
                case 3:
                    UserMenu.displayMenu();
                    break;
                case 4:
                    System.out.println("Afslutter...");
                    System.exit(0);
                    break;
                // case 5:

                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }

    private void registrerKontingentBetaling() {
        int MedlemId = Input.getIdInput();
        Medlem medlem = getMedlemMedId(MedlemId);
        KontingentController.opretBetaling(medlem);
        /*boolean aktivJuniorMedlem = booleanInput("Er medlemmet aktiv junior? (Ja/Nej): ");
        boolean aktivSeniorMedlem = booleanInput("Er medlemmet Senior? (Ja/Nej): ");
        boolean pensionistRabat = booleanInput("Er medlemmet pensionist? (Ja/Nej): ");
        boolean passivMedlem = booleanInput("Er medlemmet passivt? (Ja/Nej): "); */

    }
}
