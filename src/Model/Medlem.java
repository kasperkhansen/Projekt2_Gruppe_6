package Model;
import View.Input;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Medlem {
    public static KontingentBetaling getKontingentBetaling;
    private final String navn;
    private final ArrayList<Medlemskab> medlemsskab = new ArrayList<>();

    public static final ArrayList<Traeningsresultat> butterflyTraening = new ArrayList<>();

    public static final ArrayList<Konkurrenceresultat> butterflyKonkurrence = new ArrayList<>();

    public static final ArrayList<Traeningsresultat> crawlTraening = new ArrayList<>();

    public static final ArrayList<Konkurrenceresultat> crawlKonkurrence = new ArrayList<>();

    public static final ArrayList<Traeningsresultat> brystTraening = new ArrayList<>();

    public static final ArrayList<Konkurrenceresultat> brystKonkurrence = new ArrayList<>();


    private int medlemsskabsNr;

    private int alder;
    public final LocalDate foedselsdato;
    private final int id;
    public boolean erSenior = false;
    public boolean erPensionist = false;
    private boolean kontingentBetalt;

    LocalDate sidstBetalt;

    // 3 options: "aktiv + konkurrence", "aktiv + motionist", "passiv"
    public Medlem(String navn, int medlemsskabNr, LocalDate foedselsdato, int id){
        this.navn = navn;
        addMedlemskab(medlemsskabNr);
        this.medlemsskabsNr = medlemsskabNr;
        this.foedselsdato = foedselsdato;
        this.alder = udregnAlder();
        this.id = id;
        this.sidstBetalt = LocalDate.now();
        this.kontingentBetalt = false;
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

    public boolean harBetaltKontingent() {
        return kontingentBetalt;
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

    public void setKontingentBetalt(boolean kontingentBetalt) {
        this.kontingentBetalt = kontingentBetalt;
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
        return this.navn;
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



    public int getMedlemskabNr() {
        return medlemsskabsNr;
    }

    public void setSidstBetalt(LocalDate sidstBetalt) {
        this.sidstBetalt = sidstBetalt;
    }

    // get metoder til Traening og Konkurrence
    public ArrayList<Traeningsresultat> getButterflyTraening() {
        return butterflyTraening;
    }

    public ArrayList<Traeningsresultat> getCrawlTraening() {
        return crawlTraening;
    }

    public ArrayList<Traeningsresultat> getBrystTraening() {
        return brystTraening;
    }

    public ArrayList<Konkurrenceresultat> getButterflyKonkurrence() {
        return butterflyKonkurrence;
    }

    public ArrayList<Konkurrenceresultat> getCrawlKonkurrence() {
        return crawlKonkurrence;
    }

    public ArrayList<Konkurrenceresultat> getBrystKonkurrence() {
        return brystKonkurrence;
    }



    public LocalDate getSidstBetalt() {
        return sidstBetalt;
    }


    public void addButterflyTraening(Traeningsresultat traeningsresultat) {
        butterflyTraening.add(traeningsresultat);
    }

    public void addCrawlTraening(Traeningsresultat traeningsresultat) {
        crawlTraening.add(traeningsresultat);
    }

    public void addBrystTraening(Traeningsresultat traeningsresultat) {
        brystTraening.add(traeningsresultat);
    }

    public void addButterflyKonkurrence(Konkurrenceresultat konkurrenceresultat) {
        butterflyKonkurrence.add(konkurrenceresultat);
    }

    public void addCrawlKonkurrence(Konkurrenceresultat konkurrenceresultat) {
        crawlKonkurrence.add(konkurrenceresultat);
    }

    public void addBrystKonkurrence(Konkurrenceresultat konkurrenceresultat) {
        brystKonkurrence.add(konkurrenceresultat);
    }


    public void clearResultater() {
        butterflyTraening.clear();
        crawlTraening.clear();
        brystTraening.clear();
        butterflyKonkurrence.clear();
        crawlKonkurrence.clear();
        brystKonkurrence.clear();
    }

    public void addTraeningsresultat(Traeningsresultat traeningsresultat, String disciplin) {
        if (disciplin.equals("Butterfly")) {
            addButterflyTraening(traeningsresultat);
        } else if (disciplin.equals("Crawl")) {
            addCrawlTraening(traeningsresultat);
        } else if (disciplin.equals("Bryst")) {
            addBrystTraening(traeningsresultat);
        }
    }


    public void addKonkurrenceresultat(Konkurrenceresultat konkurrenceresultat, String disciplin) {

        if (disciplin.equals("Butterfly")) {
            addButterflyKonkurrence(konkurrenceresultat);
        } else if (disciplin.equals("Crawl")) {
            addCrawlKonkurrence(konkurrenceresultat);
        } else if (disciplin.equals("Bryst")) {
            addBrystKonkurrence(konkurrenceresultat);
        }
    }




    @Override
    public String toString() {
        return " " + navn +", "+ medlemsskab.get(0).toString() + ", "+  alder +", "+ foedselsdato + ", id: " + id;
    }

    public void printTraeningsresultater() {
        System.out.println("Butterfly træning: ");
        for (Traeningsresultat traeningsresultat : butterflyTraening) {
            System.out.println(traeningsresultat.toString());
        }
        System.out.println("Crawl træning: ");
        for (Traeningsresultat traeningsresultat : crawlTraening) {
            System.out.println(traeningsresultat.toString());
        }
        System.out.println("Bryst træning: ");
        for (Traeningsresultat traeningsresultat : brystTraening) {
            System.out.println(traeningsresultat.toString());
        }
    }

    public void printKonkurrenceresultater() {
        System.out.println("Butterfly konkurrence: ");
        for (Konkurrenceresultat konkurrenceresultat : butterflyKonkurrence) {
            System.out.println(konkurrenceresultat.toString());
        }
        System.out.println("Crawl konkurrence: ");
        for (Konkurrenceresultat konkurrenceresultat : crawlKonkurrence) {
            System.out.println(konkurrenceresultat.toString());
        }
        System.out.println("Bryst konkurrence: ");
        for (Konkurrenceresultat konkurrenceresultat : brystKonkurrence) {
            System.out.println(konkurrenceresultat.toString());
        }
    }
}
