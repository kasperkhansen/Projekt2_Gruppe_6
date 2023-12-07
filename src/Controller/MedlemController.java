package Controller;
import Model.*;
import View.Input;
import View.MenuSystem.FormandSubMenu;
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

                while (true) {
                    choice = Input.intInput("Valg: ");
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Ugyldigt input, prøv igen");
                        System.out.println();
                        System.out.println("1. Gå tilbage og indtast nyt mobil nummer");
                        System.out.println("2. Gå tilbage til Formand menu");
                        System.out.println("3. Opstart igen");
                        System.out.println("4. Afslut");
                    }
                }


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
        }
    }

    // skift
    public static void skiftMedlemskabMedInputScan() {
        System.out.println("Skift medlemskab for medlem:");
        printAlleMedlemmerMobilNr();
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

            System.out.println("Test: "+ medlem.getId()+ " " + medlem.getNavn() + " " + medlem.getMedlemsskabsNr());

            String valgtMedlemskab;
            switch (nytMedlemskabNr) {
                case 1:
                    medlem.setMedlemskab(1);
                    valgtMedlemskab = "Aktiv - konkurrence.";
                    System.out.println("Medlemskab for medlem med ID " + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);
                    break;
                case 2:
                    medlem.setMedlemskab(2);
                    valgtMedlemskab = "Aktiv - motionist.";
                    System.out.println("Medlemskab for medlem med ID " + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);
                    break;
                case 3:
                    medlem.setMedlemskab(3);
                    valgtMedlemskab = "Passivt.";
                    System.out.println("Medlemskab for medlem med ID" + getMedlemMedId(id).getId() + " er ændret til " + valgtMedlemskab);

                    break;
                default:
                    System.out.println("Ugyldig indtastning, prøv igen");
                    return;
            }
            // opdater alleMedlemmer med opdateret medlem
            System.out.println("Test: "+ medlem.getId()+ " " + medlem.getNavn() + " " + medlem.getMedlemsskabsNr());
            System.out.println(medlem.getMedlemsskabsNr());
            opdaterMedlem(medlem);

            DatabaseController.setMedlemskabsNr(medlem.getId(), nytMedlemskabNr);
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }
    }

    // betal
    public static void betalEngangsbillet() {
        System.out.println("Betal engangsbillet i proces...");

        if (!alleMedlemmer.isEmpty()) {
            int søgtId = Input.getIdInput();
            boolean medlemFundet = false;

            for (Medlem med : alleMedlemmer) {
                if (med.getId() == søgtId) {
                    medlemFundet = true;

                    if (med.erPassivtMedlemskab()) {
                        System.out.println("Engangsbillet til medlem med ID " + søgtId + " er købt.");
                    } else {
                        System.out.println("Medlem med ID" + søgtId + " er ikke passivt medlem.");
                    }
                    break;
                }
            }
            if (!medlemFundet) {
                System.out.println("Ingen medlem fundet med dette navn: " + søgtId);
            }
        } else {
            System.out.println("Ingen medlemmer i listen");
        }
    }

    // 2 medlem Methods ---------------------------------------------
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

    public static void opdaterMedlem(Medlem newMedlem) {

        // compare newMedlem and medlem in alleMedlemmer

        alleMedlemmer.add(mergeMedlemmer(getMedlemMedId(newMedlem.getId()), newMedlem));

    }

    public static Medlem mergeMedlemmer (Medlem oldMedlem, Medlem newMedlem) {
        if (oldMedlem != null) {
            // check values: navn, medlemskab, fødselsdato, træningsresultater, konkurrenceresultater
            if (!oldMedlem.getNavn().equals(newMedlem.getNavn())) {
                oldMedlem.setNavn(newMedlem.getNavn());
            }
            if (oldMedlem.getMedlemsskabsNr() != newMedlem.getMedlemsskabsNr()) {
                oldMedlem.setMedlemskab(newMedlem.getMedlemsskabsNr());
            }
            if (!oldMedlem.getFoedselsdato().equals(newMedlem.getFoedselsdato())) {
                oldMedlem.setFoedselsdato(newMedlem.getFoedselsdato());
            }
            mergeTraeningResults(oldMedlem.getCrawlTraening(), newMedlem.getCrawlTraening());
            mergeKonkurrenceResults(oldMedlem.getCrawlKonkurrence(), newMedlem.getCrawlKonkurrence());
            mergeTraeningResults(oldMedlem.getBrystTraening(), newMedlem.getBrystTraening());
            mergeKonkurrenceResults(oldMedlem.getBrystKonkurrence(), newMedlem.getBrystKonkurrence());
            mergeTraeningResults(oldMedlem.getButterflyTraening(), newMedlem.getButterflyTraening());
            mergeKonkurrenceResults(oldMedlem.getButterflyKonkurrence(), newMedlem.getButterflyKonkurrence());

            return oldMedlem;
        } else {
            return newMedlem;
        }
    }

    private static void mergeTraeningResults(ArrayList<Traeningsresultat> oldResults, ArrayList<Traeningsresultat> newResults) {

        for (Traeningsresultat result : newResults) {
            if (!oldResults.contains(result)) {
                oldResults.add(result);
            }
        }
    }
    private static void mergeKonkurrenceResults(ArrayList<Konkurrenceresultat> oldResults, ArrayList<Konkurrenceresultat> newResults) {
        for (Konkurrenceresultat result : newResults) {
            if (!oldResults.contains(result)) {
                oldResults.add(result);
            }
        }
    }


    public static void fjernMedlem(Medlem medlem) {
        for (Medlem m : alleMedlemmer) {
            if (m.getId() == medlem.getId()) {
                alleMedlemmer.remove(m);
                break;
            }
        }
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
        int i = 1;
        for (Medlem medlem : alleMedlemmer) {
            System.out.println(" "+ i +". " + medlem.getNavn() + " (" + medlem.getId() + ")");
            i++;
        }
    }

//---------------------------------------Fill metoder------------------------------------------------------------------

    public static void fillStaevneCrawlMedMedlemmerMedTider(){
        for (Medlem m : alleMedlemmer) {
            if (m.crawlKonkurrence.isEmpty())
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