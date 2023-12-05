package View.MenuSystem;


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
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Registrering af svoemmernes traenings resultater");
            System.out.println("2. Se top 5 svoemmere indenfor discipline crawl");
            System.out.println("2. Se top 5 svoemmere indenfor disciplinen bryst");
            System.out.println("2. Se top 5 svoemmere indenfor disciplinen butterfly");
            System.out.println("3. Registrer konkurrence svømmeres resultat");
            System.out.println("4. Gå tilbage til Bruger menu");
            System.out.println("5. Afslut");

            int choice = intInput("Valg: ");
            System.out.println("--------------------\n");


            switch (choice) {
                case 1:
                    TraenerController.registrerResultat();
                    //System.out.println("traenerController.registrerTraeningsResultater();");
                    break;
                case 2:
                    TraenerController.seTopFemSvoemmereCrawl();
                    //System.out.println("traenerController.seTopFemSvoemmere();");
                    break;
                case 3:
                    TraenerController.seTopFemSvoemmereBryst();
                    break;
                case 4:
                    TraenerController.seTopFemSvoemmereButterfly();
                    break;

                case 5:
                    //traenerController.registrerKonkurrenceSvoemmeresRsultat();
                    System.out.println("traenerController.registrerKonkurrenceSvoemmeresRsultat();");
                    break;
                case 6:
                    UserMenu.displayMenu();

                    break;
                case 7:
                    System.out.println("Afslutter...");
                    System.exit(0);
                default:
                    System.out.println("Ugyldigt input, prøv igen");
            }
        }
    }

}
