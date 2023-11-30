package Controller;
import Model.*;
import View.Input;

import java.util.ArrayList;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = new ArrayList<>();

    // use cases ---------------------------------------------
    // 1. registrerMedlem
    // 2. skiftMedlemskab
    // 3. betalEngangsbillet

    // medlem
    public static void registrerMedlem() {
        System.out.println("Registrere medlem...");

        tilfoejMedlem(new Medlem(Input.getNameInput("Indtast navn"), Input.medlemskabInput(), Input.getBirthDateInput()));
    }

    public static void tilfoejMedlem(Medlem medlem) {
        if (!alleMedlemmer.contains(medlem)) {
            saveMedlemInFile(medlem);
            alleMedlemmer.add(medlem);
            System.out.println("(debug) Medlem tilføjet: " + medlem.getNavn());
        }
    }
    // skift
    public static void skiftMedlemskabMedInputScan () {
            skiftMedlemskab(Input.getNameInput("Indtast navn:"));
    }

    public static void skiftMedlemskab(String navn) {
        Medlem medlem = getMedlemMedNavn(navn);
            if (medlem != null) {
                int nytMedlemskabNr;

                do {
                    nytMedlemskabNr = Input.medlemskabInput();

                    if (nytMedlemskabNr == medlem.getMedlemskabsNr()) {
                        System.out.println("Dette er det nuværende medlemskab. Vælg et andet for at skifte.");
                    }
                } while (nytMedlemskabNr == medlem.getMedlemskabsNr());

                String valgtMedlemskab;
                switch (nytMedlemskabNr) {
                    case 1:
                        medlem.setMedlemskab(1);
                        valgtMedlemskab = "Aktiv - konkurrence.";
                        System.out.println("Medlemskab for " + getMedlemMedNavn(navn).getNavn() + " er ændret til " + valgtMedlemskab);
                        break;
                    case 2:
                        medlem.setMedlemskab(2);
                        valgtMedlemskab = "Aktiv - motionist.";
                        System.out.println("Medlemskab for " + getMedlemMedNavn(navn).getNavn() + " er ændret til " + valgtMedlemskab);
                        break;
                    case 3:
                        medlem.setMedlemskab(3);
                        valgtMedlemskab = "Passivt.";
                        System.out.println("Medlemskab for " + getMedlemMedNavn(navn).getNavn() + " er ændret til " + valgtMedlemskab);
                        break;
                    default:
                        System.out.println("Ugyldig indtastning, prøv igen");
                        return;
                }

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

                    if (med.isPassivtMedlemskab()) {
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
    // 1 medlem Methods ---------------------------------------------
    // CRUD - tilføj, fjern, get, opdater


    // 2 medlem Methods ---------------------------------------------
    private static void saveMedlemInFile(Medlem medlem) {
        String IDtxt = medlem.getId() + ".txt";
        try {
            DatabaseController.saveMedlemAsFile(medlem, DatabaseController.DATABASE_PATH + IDtxt);
        } catch (Exception e) {
            System.out.println("Error saving member: " + medlem.getNavn());
        }
    }
    public static void fjernMedlem(Medlem medlem) {
        alleMedlemmer.remove(medlem);
        System.out.println("Medlem fjernet: " + medlem.getNavn());
    }
    public static Medlem getMedlemMedNavn(String navn) {
        for (Medlem medlem : alleMedlemmer) {
            if (medlem.getNavn().equals(navn)) {
                return medlem;
            }
        }
        return null;
    }
    public static Medlem getMedlemMedInput() {
        System.out.println("find medlem: ");
        return getMedlemMedNavn(Input.getNameInput("indtast navn: "));
    }
    public static void opdaterMedlem(Medlem medlem) {
        fjernMedlem(medlem);
        tilfoejMedlem(medlem);
    }

    // 3 print methods ---------------------------------------------
    public static void printAlleMedlemmer(){
        for (Medlem medlem : alleMedlemmer) {
            System.out.print(medlem.toString());
        }
    }

    public static void printMedlemMedNavn(String navn) {
        System.out.println(getMedlemMedNavn(navn));
    }

    public static void printMedlemMedInput() {
        System.out.println("find medlem: ");
        System.out.println(getMedlemMedInput());
    }

    public static void updateAlleMedlemmerMed(Medlem loadedMedlem) {
        if (loadedMedlem != null && !alleMedlemmer.contains(loadedMedlem)) {
            alleMedlemmer.add(loadedMedlem);
        }
    }
}