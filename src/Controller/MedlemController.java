package Controller;
import Model.*;
import View.Input;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = fillAlleMedlemmerArr();
    public static ArrayList<AktivtMedlem> AktivMedlemsListe = new ArrayList<>();


    // 1 medlem Methods ---------------------------------------------
    private static ArrayList<Medlem> fillAlleMedlemmerArr() {
        ArrayList<Medlem> m = new ArrayList<>();

        m.add(new Medlem("jens", "passivt", 20, LocalDate.of(2010,01,01)));
        return m;
    }

    // CRUD - tilføj, fjern, find, opdater
    public static void tilfoejMedlem(Medlem medlem) {
        alleMedlemmer.add(medlem);
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
        Objects.requireNonNull(getMedlemMedInput()).setMedlemskab(Input.medlemskabInput());
    }
    public static void skiftMedlemskab(String navn) {
        getMedlemMedNavn(navn).setMedlemskab(Input.medlemskabInput());
    }










    // 3 test
    public static void main(String[] args) {
        tilfoejMedlem(new Medlem("Jens", "aktiv + konkurrence", 20, null));



    }
}