package Model;
import View.Input;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Medlem {
    public static KontingentBetaling getKontingentBetaling;
    private String navn;
    private int medlemsskabsNr;
    private final ArrayList<Medlemskab> medlemsskab = new ArrayList<>();
    private int alder;
    public LocalDate foedselsdato;
    private final int id;
    public boolean erSenior = false;
    public boolean erPensionist = false;
    private boolean kontingentBetalt;
    LocalDate sidstBetalt;

    // 3 options: "aktiv + konkurrence", "aktiv + motionist", "passiv"
    public Medlem(String navn, int medlemsskabsNr, LocalDate foedselsdato, int id){
        this.navn = navn;
        opdaterMedlemskab(medlemsskabsNr);
        this.medlemsskabsNr = medlemsskabsNr;
        this.foedselsdato = foedselsdato;
        //this.alder = udregnAlder();
        this.id = id;
        this.sidstBetalt = LocalDate.now();
        this.kontingentBetalt = false;
    }
    public Medlem (int id) {
        this.id = id;
    }

    // initialisering metode
    /*
    private int udregnAlder () {

        LocalDate nu = LocalDate.now();
        LocalDate foedsel = foedselsdato;

        Period periode = Period.between(foedsel, nu);
        alder = periode.getYears();

        return alder;
    }
    */

    // -----------------------------------------boolean metoder------------------------------------------------------------

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

    public boolean erPassivtMedlemskab () {
        return medlemsskab.get(0) instanceof PassivtMedlem;
    }



    //--------------------------------------------------get metoder---------------------------------------------------------
    public String getNavn() {
        return this.navn;
    }

    public ArrayList<Medlemskab> getMedlemsskabArrayList() {
        return medlemsskab;
    }

    public int getMedlemsskabsNr() {
        return this.medlemsskabsNr;
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

    public LocalDate getSidstBetalt() {
        return sidstBetalt;
    }

    //--------------------------------------------------set metoder---------------------------------------------------------


    public void setKontingentBetalt(boolean kontingentBetalt) {
        this.kontingentBetalt = kontingentBetalt;
    }

    public void setMedlemskab(int nytMedlemsskabNr) { // options: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        for (int i = 0; i<medlemsskab.size(); i++) {
            medlemsskab.remove(i);
        }

        // take string and add to arraylist
        // options to chose: aktiv + konkurrence, aktiv + motionist, passiv = 3 options
        opdaterMedlemskab(nytMedlemsskabNr);
        this.medlemsskabsNr = nytMedlemsskabNr;

    }

    private void opdaterMedlemskab(int nytMedlemsskabNr) {
        boolean validInput = false;
        do {
            if (nytMedlemsskabNr >= 0 && nytMedlemsskabNr <= 3) {
                validInput = true;
                switch (nytMedlemsskabNr) {
                    case 1 -> {
                        medlemsskab.add(new KonkurrenceSvoemmer());
                        break;
                    }
                    case 2 -> {
                        medlemsskab.add(new Motionist());
                        break;
                    }
                    case 3 -> {
                        medlemsskab.add(new PassivtMedlem());
                        break;
                    }
                    default -> System.out.println("Invalid Input, Try Again");
                }
            } else {
                System.out.println("Invalid Input, Try Again");
                nytMedlemsskabNr = Input.intInput("Indtast medlemsskabsnummer: ");
            }
        } while (!validInput);
    }


    public void setSidstBetalt(LocalDate sidstBetalt) {
        this.sidstBetalt = sidstBetalt;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setFoedselsdato(LocalDate foedselsdato) {
        this.foedselsdato = foedselsdato;
    }




    //--------------------------------------------------resultater variabler----------------------------------------------------

    public ArrayList<Traeningsresultat> butterflyTraening = new ArrayList<>();

    public ArrayList<Konkurrenceresultat> butterflyKonkurrence = new ArrayList<>();

    public ArrayList<Traeningsresultat> crawlTraening = new ArrayList<>();

    public ArrayList<Konkurrenceresultat> crawlKonkurrence = new ArrayList<>();

    public ArrayList<Traeningsresultat> brystTraening = new ArrayList<>();

    public ArrayList<Konkurrenceresultat> brystKonkurrence = new ArrayList<>();



    //--------------------------------------------------resultater metoder----------------------------------------------------

    // get
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

    // set
    public void setBrystTraening(ArrayList<Traeningsresultat> brystTraening) {
        this.brystTraening = brystTraening;
    }

    public void setBrystKonkurrence(ArrayList<Konkurrenceresultat> brystKonkurrence) {
        this.brystKonkurrence = brystKonkurrence;
    }

    public void setCrawlTraening(ArrayList<Traeningsresultat> crawlTraening) {
        this.crawlTraening = crawlTraening;
    }

    public void setCrawlKonkurrence(ArrayList<Konkurrenceresultat> crawlKonkurrence) {
        this.crawlKonkurrence = crawlKonkurrence;
    }

    public void setButterflyTraening(ArrayList<Traeningsresultat> butterflyTraening) {
        this.butterflyTraening = butterflyTraening;
    }

    public void setButterflyKonkurrence(ArrayList<Konkurrenceresultat> butterflyKonkurrence) {
        this.butterflyKonkurrence = butterflyKonkurrence;
    }


    // add
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


    // add if new
    public void addIfNewKonkurrenceresultat(Konkurrenceresultat konkurrenceresultat, String disciplin) {
        switch (disciplin) {
            case "Butterfly" -> {
                if (!butterflyKonkurrence.contains(konkurrenceresultat)) {
                    addButterflyKonkurrence(konkurrenceresultat);
                }
            }
            case "Crawl" -> {
                if (!crawlKonkurrence.contains(konkurrenceresultat)) {
                    addCrawlKonkurrence(konkurrenceresultat);
                }
            }
            case "Bryst" -> {
                if (!brystKonkurrence.contains(konkurrenceresultat)) {
                    addBrystKonkurrence(konkurrenceresultat);
                }
            }
        }
    }

    public void addIfNewTraeningsresultat(Traeningsresultat traeningsresultat, String disciplin) {
        switch (disciplin) {
            case "Butterfly" -> {
                if (!butterflyTraening.contains(traeningsresultat)) {
                    addButterflyTraening(traeningsresultat);
                }
            }
            case "Crawl" -> {
                if (!crawlTraening.contains(traeningsresultat)) {
                    addCrawlTraening(traeningsresultat);
                }
            }
            case "Bryst" -> {
                if (!brystTraening.contains(traeningsresultat)) {
                    addBrystTraening(traeningsresultat);
                }
            }
        }
    }





    //--------------------------------------------------service metoder------------------------------------------------------
    public void printTraeningsresultater() {
        if (!crawlTraening.isEmpty()) {
            System.out.println("1 Crawl træning: ");
            for (Traeningsresultat traeningsresultat : crawlTraening) {
                System.out.println(" "+traeningsresultat.toString());
            }
        }
        if (!butterflyTraening.isEmpty()) {
            System.out.println("2 Butterfly træning: ");
            for (Traeningsresultat traeningsresultat : butterflyTraening) {
                System.out.println(" "+traeningsresultat.toString());
            }
        }
        if (!brystTraening.isEmpty()) {
            System.out.println("3 Bryst træning: ");
            for (Traeningsresultat traeningsresultat : brystTraening) {
                System.out.println(" "+traeningsresultat.toString());
            }
        }
    }

    public void printKonkurrenceresultater() {
        if(!crawlKonkurrence.isEmpty()) {
            System.out.println("1 Crawl konkurrence: ");
            for (Konkurrenceresultat konkurrenceresultat : crawlKonkurrence) {
                System.out.println(" "+konkurrenceresultat.toString());
            }
        }
        if(!butterflyKonkurrence.isEmpty()) {
            System.out.println("2 Butterfly konkurrence: ");
            for (Konkurrenceresultat konkurrenceresultat : butterflyKonkurrence) {
                System.out.println(" "+konkurrenceresultat.toString());
            }
        }
        if(!brystKonkurrence.isEmpty()) {
            System.out.println("3 Bryst konkurrence: ");
            for (Konkurrenceresultat konkurrenceresultat : brystKonkurrence) {
                System.out.println(" "+konkurrenceresultat.toString());
            }
        }
    }


    @Override
    public String toString() {
        return "Medlem{" +
                "navn='" + navn + '\'' +
                ", medlemsskabsNr=" + medlemsskabsNr +
                ", alder=" + alder +
                ", foedselsdato=" + foedselsdato +
                ", id=" + id +
                ", erSenior=" + erSenior +
                ", erPensionist=" + erPensionist +
                ", kontingentBetalt=" + kontingentBetalt +
                ", sidstBetalt=" + sidstBetalt +
                '}';
    }






}
