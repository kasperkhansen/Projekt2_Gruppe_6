package Controller;
import Model.*;
import View.Input;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = new ArrayList<>();


    // 1 medlem Methods ---------------------------------------------
    private static ArrayList<Medlem> fillAlleMedlemmerArr() {
        ArrayList<Medlem> m = new ArrayList<Medlem>();

        m.add(new Medlem("jens", 1, 20, LocalDate.of(2010,01,01)));
        return m;
    }

    // CRUD - tilføj, fjern, find, opdater
    public static void tilfoejMedlem(Medlem medlem) {
        alleMedlemmer.add(medlem);
        System.out.println("Medlem tilføjet: " + medlem.getNavn());
    }
    public static void fjernMedlem(Medlem medlem) {
        alleMedlemmer.remove(medlem);
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
    // opdater medlem - input: opdateret medlem - process: find og fjern medlem - output: tilføj opdateret medlem
    public static void opdaterMedlem(Medlem medlem) {
        fjernMedlem(medlem);
        tilfoejMedlem(medlem);
    }


    // 2 medlemskab methods -----------------------------------------
    public static void skiftMedlemskabMedInputScan () {
        getMedlemMedInput().setMedlemskab(Input.medlemskabInput());
    }

    public static void skiftMedlemskab(String navn) {
        getMedlemMedNavn(navn).setMedlemskab(Input.medlemskabInput());
    }


    public static void main(String[] args) {
        System.out.println("debug: tilfoej startet");
        tilfoejMedlem(new Medlem("jens", 1, 20, LocalDate.of(2010,01,01)));
        System.out.println("debug: tilfoej sluttet");
        System.out.println(alleMedlemmer);
        skiftMedlemskabMedInputScan();
        System.out.println(alleMedlemmer);
    }


    public static void betalEngangsbillet() {
        System.out.println("Betal engangsbillet processing...");
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



}