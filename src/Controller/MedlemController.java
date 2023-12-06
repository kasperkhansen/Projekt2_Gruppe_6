package Controller;
import Model.*;
import View.Input;
import java.time.LocalDate;
import java.util.ArrayList;

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

        tilfoejMedlem(new Medlem(Input.getNameInput("Indtast navn"), Input.medlemskabInput(), Input.getBirthDateInput(), Input.getIdInput()));
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
            String søgtNavn = Input.getNameInput("Indtast navn");
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

    public void fillStaevneCrawl(){
        for (Medlem m : TraenerController.BedsteStaevneTiderCrawl) {
            m.crawlKonkurrence.get(0);
        }
    }

    public void fillStaevneBryst(){
        for (Medlem m : TraenerController.BedsteStaevneTiderBryst) {
            m.brystKonkurrence.get(0);
        }
    }

    public void fillStaevneButterfly(){
        for (Medlem m : TraenerController.BedsteStaevneTiderButterfly){
            m.butterflyKonkurrence.get(0);
        }
    }

    public void fillTraeningCrawl(){
        for (Medlem m : TraenerController.BedsteTraeningsTiderCrawl) {
            m.crawlTraening.get(0);
        }
    }

    public void fillTraeningBryst(){
        for (Medlem m : TraenerController.BedsteTraeningsTiderBryst) {
            m.brystTraening.get(0);
        }
    }

    public void fillTraeningButterfly(){
        for (Medlem m : TraenerController.BedsteTraeningsTiderButterfly) {
            m.butterflyTraening.get(0);
        }


    }





}