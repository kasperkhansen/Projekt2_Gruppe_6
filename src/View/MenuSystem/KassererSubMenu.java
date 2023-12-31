//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package View.MenuSystem;

import static Controller.MedlemController.getMedlemMedId;
import static View.Input.intInput;

import Controller.DatabaseController;
import Controller.KontingentController;
import Controller.MedlemController;
import Model.Medlem;
import View.Input;

import static View.Input.booleanInput;

public class KassererSubMenu {
    private KontingentController kontingentController = new KontingentController();

    public KassererSubMenu() {
        this.displaySubMenu();
    }

    private void displaySubMenu() {
        System.out.println("Kasserer Menu");
        System.out.println("--------------------");
        System.out.println();

        while (true) {
            System.out.println("Hvad vil De gerne?:");
            System.out.println(" 1. Registrer kontingentbetalinger");
            System.out.println(" 2. Overblik over kontingentbetalinger.");
            System.out.println(" 3. Gå tilbage til Bruger menu");
            System.out.println(" 4. Afslut");

            int choice = intInput("Valg: ");



            switch (choice) {
                case 1:
                    KontingentController.registrerKontingentBetaling();
                    DatabaseController.saveArrToFileDatabase();
                    break;
                case 2:
                    //kontingentController.overblikOverKontingentBetalinger();
                    kontingentController.overblikOverKontingentBetalinger(MedlemController.getAlleMedlemmer());
                    break;
                case 3:
                    UserMenu.displayMenu();
                    break;
                case 4:
                    System.out.println("Afslutter...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }


}
