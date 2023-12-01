package Model;
import Controller.DatabaseController;
import Controller.MedlemController;
import View.Input;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Medlem {
    private final String navn;
    private final ArrayList<Medlemskab> medlemsskab = new ArrayList<>();

    public final ArrayList<Butterfly> butterflyTraening = new ArrayList<>();

    public final ArrayList<Butterfly> butterflyKonkurrence = new ArrayList<>();

    public final ArrayList<Crawl> crawlTraening = new ArrayList<>();

    public final ArrayList<Crawl> crawlKonkurrence = new ArrayList<>();

    public final ArrayList<Bryst> brystTraening = new ArrayList<>();

    public final ArrayList<Bryst> brystKonkurrence = new ArrayList<>();

    private int medlemsskabNr;

    private int medlemsskabsNr;

    private int alder;
    public final LocalDate foedselsdato;
    private final int id;
    public boolean erSenior = false;
    public boolean erPensionist = false;

    // 3 options: "aktiv + konkurrence", "aktiv + motionist", "passiv"
    public Medlem(String navn, int medlemsskabNr, LocalDate foedselsdato, int id){
        this.navn = navn;
        addMedlemskab(medlemsskabNr);
        this.medlemsskabsNr = medlemsskabNr;
        this.foedselsdato = foedselsdato;
        this.alder = udregnAlder();
        this.id = id;
    }



    // -----------------------------------------alder metoder------------------------------------------------------------
    int udregnAlder () {
      LocalDate nu = LocalDate.now();
      LocalDate foedsel = foedselsdato;

       Period periode = Period.between(foedsel, nu);
       alder = periode.getYears();

       return alder;
   }

    public boolean erSenior(){
        if (alder > 18){
            erSenior = true;
        }
        return erSenior;
    }

    public boolean erPensionist(){
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
            } else {
                System.out.println("Medlemskab ikke tilføjet");
                // message: vælg medlemskab options: 1, 2, 3 aktiv + konkurrence, aktiv + motionist, passiv = 3 options
                medlemsskabNr = Input.intInput("Vælg medlemskab: 1. aktiv + konkurrence, 2. aktiv + motionist, 3. passiv");
            }
        }
    }

    public boolean erPassivtMedlemskab () {
        return medlemsskab.get(0) instanceof PassivtMedlem;
    }

    /*public boolean erKontingentBetalt() {
        if (kontingentBetaling.betalt) {
            return true;
        } else {
            return false;
        }
    }*/

    //Tjekke om id tæller op ved flere objekter

    //--------------------------------------------------get metoder---------------------------------------------------------
    public String getNavn() {
        return navn;
    }

    public ArrayList<Medlemskab> getMedlemsskabArrayList() {
        return medlemsskab;
    }

    public int getMedlemsskabsNr() {
        return medlemsskabsNr;
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


    public int getMedlemskabNr() {
        return medlemsskabsNr;
    }


}
