package Controller;

import Model.*;
import View.Input;

import java.util.ArrayList;
import java.util.Objects;

public class MedlemController {
    public static ArrayList<Medlem> alleMedlemmer = new ArrayList<>();
    public static ArrayList<AktivtMedlem> AktivMedlemsListe = new ArrayList<>();
    public static ArrayList<PassivtMedlem> PassivtMedlemsListe = new ArrayList<>();
    public static ArrayList<Motionist> MotionistListe = new ArrayList<>();
    public static ArrayList<KonkurrenceSvoemmer> KonkurrenceSvoemmerListe = new ArrayList<>();
    public static ArrayList<ArrayList> TopFemKonkurencSvoemmere;


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


    public static void main(String[] args) {
        addMedlem(new Medlem("Jens", "aktiv + konkurrence", 20, null, 1));


        skiftMedlemskab();
    }
}