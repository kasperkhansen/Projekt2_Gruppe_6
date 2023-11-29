package Controller;
import Model.*;
import View.Input;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = new ArrayList<>();



    // use cases ---------------------------------------------
    // 1. registrerMedlem
    // 2. skiftMedlemskab
    // 3. betalEngangsbillet

    public static void registrerMedlem() {
        System.out.println("Registrer medlem processing...");

        tilfoejMedlem(new Medlem(Input.getNameInput("Indtast navn"), Input.medlemskabInput(), Input.getAgeInput(), Input.getBirthDateInput()));

    }

    public static void skiftMedlemskabMedInputScan () {
        getMedlemMedInput().setMedlemskab(Input.medlemskabInput());
    }

    public static void skiftMedlemskab(String navn) {
        getMedlemMedNavn(navn).setMedlemskab(Input.medlemskabInput());
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


    // 1 medlem Methods ---------------------------------------------


    // CRUD - tilføj, fjern, get, opdater
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

    public static void opdaterMedlem(Medlem medlem) {
        fjernMedlem(medlem);
        tilfoejMedlem(medlem);
    }


    // 2 medlemskab methods -----------------------------------------



    // 3 service methods ---------------------------------------------
    public static void printAlleMedlemmer(){
        for (Medlem medlem : alleMedlemmer) {
            System.out.print(medlem);
        }
    }

    public static void printMedlemMedNavn(String navn) {
        System.out.println(getMedlemMedNavn(navn));
    }

    public static void printMedlemMedInput() {
        System.out.println("find medlem: ");
        System.out.println(getMedlemMedInput());
    }



    // test
    public static void main(String[] args) {
        System.out.println("debug: tilfoej startet");
        tilfoejMedlem(new Medlem("jens", 1, 20, LocalDate.of(2010,01,01)));
        System.out.println("debug: tilfoej sluttet");
        System.out.println(alleMedlemmer);
        skiftMedlemskabMedInputScan();
        System.out.println(alleMedlemmer);
    }


}