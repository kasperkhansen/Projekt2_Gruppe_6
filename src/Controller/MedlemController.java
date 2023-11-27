package Controller;

import Model.*;
import View.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = fillAlleMedlemmerArr();
    public static ArrayList<AktivtMedlem> AktivMedlemsListe = new ArrayList<>();
    public static ArrayList<PassivtMedlem> PassivtMedlemsListe = new ArrayList<>();
    public static ArrayList<Motionist> MotionistListe = new ArrayList<>();
    public static ArrayList<KonkurrenceSvoemmer> KonkurrenceSvoemmerListe = new ArrayList<>();
    public static ArrayList<ArrayList> TopFemKonkurencSvoemmere;

    private static ArrayList<Medlem> fillAlleMedlemmerArr() {
        ArrayList<Medlem> m = new ArrayList<>();

        m.add(new Medlem("jens", "passivt", 20, LocalDate.of(2010,01,01), 1));
        return m;
    }

    public static void addMedlem(Medlem medlem) {
        alleMedlemmer.add(medlem);
    }

    public static void skiftMedlemskab () {

        Objects.requireNonNull(getMedlem()).setMedlemskab(Input.medlemskabInput());

    }

    private static Medlem getMedlem() {
        for (Medlem medlem : alleMedlemmer) {
            if (medlem.getNavn().equals(Input.getNameInput("indtast navn: "))) {
                return medlem;
            }
        }
        return null;
    }


}