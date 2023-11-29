package Model;
import Controller.DatabaseController;
import Controller.MedlemController;
import View.Input;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Medlem implements Serializable {
    private final String navn;
    private final ArrayList<Medlemskab> medlemsskab = new ArrayList<>();

    private final int alder;
    private final LocalDate foedselsdato;
    private static int id = 1;
    boolean erSenior = false;
    boolean erPensionist = false;

    // 3 options: "aktiv + konkurrence", "aktiv + motionist", "passiv"
    public Medlem(String navn, int medlemsskabNr, int alder, LocalDate foedselsdato){
        this.navn = navn;
        addMedlemskab(medlemsskabNr);
        this.alder = alder;
        this.foedselsdato = foedselsdato;
        id++;
        MedlemController.tilfoejMedlem(this);
        saveMedlem();
    }



    // -----------------------------------------alder metoder------------------------------------------------------------
    public boolean senior(){
        if (alder > 18){
            erSenior = true;
        }
        return erSenior;
    }

    public boolean Pensionist(){
        if (alder >= 60){
            erPensionist = true;
        }
        return erPensionist;
    }


    //-----------------------------------------medlemsskab metoder ------------------------------------------------------


    public void setMedlemskab(int medlemsskabNr) { // options: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        for (int i = 0; i<medlemsskab.size(); i++) {
            medlemsskab.remove(i);
        }

            // take string and add to arraylist
            // options to chose: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        addMedlemskab(medlemsskabNr);

    }

    private void addMedlemskab(int medlemsskabNr) {
        boolean tilfoejMedlemskab = true;
        while (tilfoejMedlemskab) {

            switch (medlemsskabNr) {
                case 1 -> {
                    medlemsskab.add(new KonkurrenceSvoemmer());
                    tilfoejMedlemskab = false;
                }
                case 2 -> {
                    medlemsskab.add(new Motionist());
                    tilfoejMedlemskab = false;
                }
                case 3 -> {
                    medlemsskab.add(new PassivtMedlem());
                    tilfoejMedlemskab = false;
                }

                default -> System.out.println("Invalid Input, Try Again");
            }
            if (!tilfoejMedlemskab) {
                System.out.println("Medlemskab tilføjet");
            } else {
                System.out.println("Medlemskab ikke tilføjet");
                // message: vælg medlemskab options: 1, 2, 3 aktiv + konkurrence, aktiv + motionist, passiv = 3 options
                medlemsskabNr = Input.intInput("Vælg medlemskab: 1. aktiv + konkurrence, 2. aktiv + motionist, 3. passiv");
            }
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

    //--------------------------------------------------set metoder---------------------------------------------------------
    @Override
    public String toString() {
        return " " + navn + medlemsskab+  alder + foedselsdato;
    }

    //--------------------------------------------------data metoder--------------------------------------------------------
    public void saveMedlem() {
        String fileName = getId() + "_" + this.navn.replaceAll("\\s+", "") + ".dat";
        DatabaseController.saveObjectAsFile(this, DatabaseController.DATABASE_PATH + fileName);
    }



}
