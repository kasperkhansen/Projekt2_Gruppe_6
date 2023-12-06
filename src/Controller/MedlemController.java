package Controller;
import Model.*;
import View.Input;
import View.MenuSystem.FormandSubMenu;
import View.MenuSystem.MedlemSubMenu;
import View.MenuSystem.UserMenu;

import java.util.ArrayList;

import static Controller.DatabaseController.getMedlemByMobileNumber;
import static javax.swing.UIManager.get;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = new ArrayList<>();

    // use cases ---------------------------------------------
    // 1. registrerMedlem
    // 2. skiftMedlemskab
    // 3. betalEngangsbillet

    // medlem
    public static void registrerMedlem() {
        System.out.println("Registrerer medlem...");
        int choice;
        while (true) {
            int mobilNumner = Input.getIdInput(); // Assuming this method gets the mobile number
            if (doesMemberExist(mobilNumner)) {
                System.out.println("Mobil nummer eksisterer allerede.");
                System.out.println();
                System.out.println("1. Prøv igen");
                System.out.println("2. Gå tilbage til Formand menu");
                System.out.println("3. Opstart igen");
                System.out.println("3. Afslut");
                choice = Input.intInput("Valg: ");
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        FormandSubMenu formandSubMenu = new FormandSubMenu();
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
            } else {
                tilfoejMedlem(new Medlem(Input.getNameInput("Indtast navn", "Ugyldigt navn, prøv igen"), Input.medlemskabInput(), Input.getBirthDateInput(), Input.getIdInput()));
                break;
            }
        }
    }

    public static void tilfoejMedlem(Medlem medlem) {
        if (!alleMedlemmer.contains(medlem)) {
            DatabaseController.saveMedlemAsFile(medlem);
            alleMedlemmer.add(medlem);
            DatabaseController.loadFilesToArr();
            System.out.println("Medlem tilføjet: " + medlem.getNavn());
        }
    }

    // skift
    public static void skiftMedlemskabMedInputScan() {
        skiftMedlemskab(Input.getIdInput());
    }

    public static void skiftMedlemskab(int id) {
        Medlem medlem = getMedlemMedId(id);
        if (medlem != null) {
            int nytMedlemskabNr;

            do {
                nytMedlemskabNr = Input.medlemskabInput();

                if (nytMedlemskabNr == medlem.getMedlemsskabsNr()) {
                    System.out.println("Dette er det nuværende medlemskab. Vælg et andet for at skifte.");
                }
            } while (nytMedlemskabNr == medlem.getMedlemsskabsNr());


            String valgtMedlemskab;
            switch (nytMedlemskabNr) {
                case 1:
                    medlem.setMedlemskab(1);
                    valgtMedlemskab = "Aktiv - konkurrence.";
                    System.out.println("Medlemskab for " + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);
                    break;
                case 2:
                    medlem.setMedlemskab(2);
                    valgtMedlemskab = "Aktiv - motionist.";
                    System.out.println("Medlemskab for " + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);
                    break;
                case 3:
                    medlem.setMedlemskab(3);
                    valgtMedlemskab = "Passivt.";
                    System.out.println("Medlemskab for " + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);
                    break;
                default:
                    System.out.println("Ugyldig indtastning, prøv igen");
                    return;
            }
            DatabaseController.updaterMedlemFile(medlem);
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }
    }

    // betal
    public static void betalEngangsbillet() {
        System.out.println("Betal engangsbillet i proces...");
        if (!alleMedlemmer.isEmpty()) {
            String søgtNavn = Input.getNameInput("Indtast navn", "Ugyldigt navn, prøv igen");
            boolean medlemFundet = false;

            for (Medlem med : alleMedlemmer) {
                if (med.getNavn().equals(søgtNavn)) {
                    medlemFundet = true;

                    if (med.erPassivtMedlemskab()) {
                        System.out.println("Engangsbillet til " + søgtNavn + " er købt");
                    } else {
                        System.out.println(søgtNavn + " er ikke passivt medlem.");
                    }
                    break;
                }
            }
            if (!medlemFundet) {
                System.out.println("Ingen medlem fundet med dette navn: " + søgtNavn);
            }
        } else {
            System.out.println("Ingen medlemmer i listen");
        }
    }

    // 2 medlem Methods ---------------------------------------------
    public static void fjernMedlem(Medlem medlem) {
        alleMedlemmer.remove(medlem);
        System.out.println("Medlem fjernet: " + medlem.getNavn());
    }

    public static Medlem getMedlemMedId(int id) {
        for (Medlem medlem : alleMedlemmer) {
            if (medlem.getId() == id) {
                return medlem;
            }
        }
        return null;
    }

    public static ArrayList<Medlem> getAlleMedlemmer() {
        return alleMedlemmer;
    }

    public static void opdaterMedlem(Medlem medlem) {
        fjernMedlem(medlem);
        tilfoejMedlem(medlem);
    }

    public static boolean doesMemberExist(int mobileNumber) {
        return getMedlemByMobileNumber(mobileNumber) != null;
    }


    // 3 print methods ---------------------------------------------
    public static void printAlleMedlemmer() {
        for (Medlem medlem : alleMedlemmer) {
            System.out.print(medlem.toString());
        }
        System.out.println();
    }

    public static void printAlleMedlemmerMobilNr() {
        for (Medlem medlem : alleMedlemmer) {
            System.out.println(medlem.getNavn() + " " + medlem.getId());
        }
    }

//---------------------------------------Fill metoder------------------------------------------------------------------

    public static void fillStaevneCrawlMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.crawlKonkurrence.get(0)!=null)
                TraenerController.BedsteStaevneTiderCrawl.add(m);
        }
    }

    public static void fillStaevneBrystMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.brystKonkurrence.get(0)!=null)
                TraenerController.BedsteStaevneTiderBryst.add(m);
        }
    }

    public static void fillStaevneButterflyMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.butterflyKonkurrence.get(0)!=null)
                TraenerController.BedsteStaevneTiderButterfly.add(m);
        }
    }

    public static void fillTraeningCrawlMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.crawlTraening.get(0)!=null)
                TraenerController.BedsteTraeningsTiderCrawl.add(m);
        }
    }

    public static void fillTraeningBrystMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.brystTraening.get(0)!=null)
                TraenerController.BedsteTraeningsTiderBryst.add(m);
        }
    }

    public static void fillTraeningButterflyMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.butterflyTraening.get(0)!=null)
                TraenerController.BedsteTraeningsTiderButterfly.add(m);
        }


    }





}