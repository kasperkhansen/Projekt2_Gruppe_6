package Model;

import View.*;
import Testing.Dummies.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Controller.MedlemController.*;
import Model.Medlemskab;
import Model.Motionist;
import Model.AktivtMedlem;
import Model.KonkurrenceSvoemmer;
import Model.PassivtMedlem;

public class Medlem {
    private final String navn;
    private ArrayList<Medlemskab> medlemsskab = new ArrayList<>();

    private final int alder;
    private final LocalDate foedselsdato;
    private static int id = 1;
    boolean erSenior = false;


    public boolean senior(){
        if (alder > 18){
            erSenior = true;
        }
        return erSenior;
    }


    //-----------------------------------------medlems constructor------------------------------------------------------

    public Medlem(String navn, String medlemsskabStr, int alder, LocalDate foedselsdato){
        this.navn = navn;
        addMedlemskab(medlemsskabStr);
        this.alder = alder;
        this.foedselsdato = foedselsdato;
        id++;
    }


    public void setMedlemskab(String medlemsskabStr) { // options: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        for (int i = 0; i<medlemsskab.size(); i++) {
            medlemsskab.remove(i);
        }

            // take string and add to arraylist
            // options to chose: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        addMedlemskab(medlemsskabStr);

    }

    private void addMedlemskab(String medlemsskabStr) {
        switch (medlemsskabStr) {
            case "aktiv + konkurrence" -> medlemsskab.add(new KonkurrenceSvoemmer(Input.getNameInput("indtast navn: "), "aktiv + konkurrence", Input.getAgeInput(), Input.getBirthDateInput(), id));
            case "aktiv + motionist" -> medlemsskab.add(new Motionist(Input.getNameInput("indtast navn: "), "aktiv + motionist", Input.getAgeInput(), Input.getBirthDateInput(), id));
            case     "passiv" -> medlemsskab.add(new PassivtMedlem(Input.getNameInput("indtast navn: "), "passiv", Input.getAgeInput(), Input.getBirthDateInput(), id));

        }
    }

    public boolean isPassivtMedlemskab () {
        return medlemsskab.get(0) instanceof PassivtMedlem;
    }


    //Tjekke om id tæller op ved flere objekter

    //--------------------------------------------------get metoder---------------------------------------------------------
    public String getNavn() {
        return navn;
    }

    public ArrayList<Medlemskab> getMedlemsskabArrayList() {
        return medlemsskab;
    }

    public int getAlder() {
        return alder;
    }

    public LocalDate getFoedselsdato() {
        return foedselsdato;
    }

    public int getId() {
        return id;
    }





    public static void main(String[] args) {
        medlemsskabTest();
    }

    private static void medlemsskabTest() {
        Medlem medlem = new Medlem("test", "aktiv + konkurrence", 20, LocalDate.of(2000, 1, 1), 1);
        System.out.println(medlem.getMedlemsskabArrayList().get(0).toString());
        medlem.setMedlemskab("aktiv + motionist");
        System.out.println(medlem.getMedlemsskabArrayList().get(0).toString());
        medlem.setMedlemskab("passiv");
        System.out.println(medlem.getMedlemsskabArrayList().get(0).toString());
    }

}

