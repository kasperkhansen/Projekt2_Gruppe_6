package Controller;
import Comparatorer.*;
import Model.*;
import View.Input;

import java.util.ArrayList;
import java.time.LocalDate;

import static Controller.MedlemController.getMedlemMedId;
import static Controller.MedlemController.opdaterMedlem;

public class TraenerController {

    static ArrayList<Medlem> BedsteTraeningsTiderCrawl = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteTraeningsTiderBryst = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteTraeningsTiderButterfly = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderCrawl = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderBryst = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderButterfly = new ArrayList<Medlem>();

    public static void registrerResultat() {

        System.out.println("Registrering af svømmeresultater...");

        System.out.println();
        System.out.println("Registreret konkurrencesvømmere: ");
        printKonkurrencesvoemmereListe();
        System.out.println();

        int id = Input.intInput("Vælg ved indtastning af medlems id: ");

        Medlem medlem = getMedlemMedId(id);

        if (medlem != null) {
            System.out.println("Medlem valgt: " + medlem.getNavn()+"\n");

            int disciplinValg = Input.svoemmeDisciplinValg(); // 1 = crawl, 2 = bryst, 3 = butterfly
            System.out.println("Disciplin valgt: " + disciplinValg+"\n");
            int traeningEllerKonkurrenceValg = Input.traeningEllerKonkurrenceValg();
            System.out.println();
            System.out.println("Svømmer: " + medlem.getNavn());
            System.out.println("Disciplin: " + disciplinValg);
            System.out.println("Ved: " + traeningEllerKonkurrenceValg);
            System.out.println();

            // 1 = konkurrence, 2 = træning
            if (traeningEllerKonkurrenceValg == 1) {
                if (medlem.getMedlemsskabsNr() == 1) {
                    String hvilketStaevne = Input.stringInput("Indtast stævne: ");
                    int hvilkenPlacering = Input.intInput("Indtast placering: ");
                    double nyTid = Input.svoemmeTid();

                    switch (disciplinValg) {
                        case 1:
                            medlem.crawlKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));

                            break;
                        case 2:
                            medlem.brystKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                            // print konkurrence resultater for this medlem

                            break;
                        case 3:
                            medlem.butterflyKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));

                            break;
                    }
                    DatabaseController.updaterMedlemFile(medlem);
                    System.out.println();
                    System.out.println("Resultatet er nu registreret");
                    System.out.println();

                } else {
                    System.out.println("Dette medlem er ikke en konkurrencesvømmer");
                }
            } else { // 2 = træning (traeningEllerKonkurrenceValg)
                if (medlem.getMedlemsskabsNr() != 3) {
                    LocalDate hvilkenDato = Input.dateInput("Indtast træningsdato: ");
                    double nyTid = Input.svoemmeTid();
                    switch (disciplinValg) {
                        case 1:
                            medlem.crawlTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                            break;
                        case 2:
                            medlem.brystTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                        case 3:
                            medlem.butterflyTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                            break;

                    }
                    DatabaseController.updaterMedlemFile(medlem);
                } else {
                    System.out.println("Dette medlem er ikke et aktivt medlem");
                }
            }
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }

        MedlemController.opdaterMedlem(medlem);

    }

    public static void seAlleMedResultater() {

        for (Medlem medlem : MedlemController.alleMedlemmer) {

            if (medlem.getMedlemsskabsNr() == 1) {
                if (medlem.hasResults()) {
                    System.out.println();
                    System.out.println("Resultater for " + medlem.getNavn() + " (" + medlem.getId() + ")");
                    System.out.println("-----------------------------------");
                    System.out.println("1 Konkurrence resultater: ");
                    System.out.println();
                    medlem.printKonkurrenceresultater();
                    System.out.println();
                    System.out.println("2 Trænings resultater: ");
                    System.out.println();
                    medlem.printTraeningsresultater();
                    System.out.println("-----------------------------------");
                }
            }
        }
    }


//-----------------------------------sortering metoder------------------------------------------------------------------

    public static void sortListsBrystKonkurrence(ArrayList<Medlem> list) {
        Comparatorer.BrystKonkurrenceComparator brystKonkurrenceResultatSorter = new Comparatorer.BrystKonkurrenceComparator();
        list.sort(brystKonkurrenceResultatSorter);
    }

    public static void sortListsBrystTraening(ArrayList<Medlem> list) {
        Comparatorer.BrystTraeningsComparator brystTraeningResultatSorter = new BrystTraeningsComparator();
        list.sort(brystTraeningResultatSorter);
    }

    public static void sortListsCrawlKonkurrence(ArrayList<Medlem> list) {
        Comparatorer.CrawlKonkurrenceComparator crawlKonurrenceResultatSorter = new CrawlKonkurrenceComparator();
        list.sort(crawlKonurrenceResultatSorter);
    }

    public static void sortListsCrawlTraening(ArrayList<Medlem> list) {
        Comparatorer.CrawlTraeningsComparator crawlTraeningsResultatSorter = new CrawlTraeningsComparator();
        list.sort(crawlTraeningsResultatSorter);
    }

    public static void sortListsButterflyKonkurrence(ArrayList<Medlem> list) {
        Comparatorer.ButterflyKonkurrenceComparator buterflyKonkurrenceResultatSorter = new ButterflyKonkurrenceComparator();
        list.sort(buterflyKonkurrenceResultatSorter);
    }

    public static void sortListsButterflyTraening(ArrayList<Medlem> list) {
        Comparatorer.ButterflyTraeningsComparator butterflyTraeningResultatSorter = new ButterflyTraeningsComparator();
        list.sort(butterflyTraeningResultatSorter);
    }


    //--------------------------get tider get metoder-------------------------------------------------------------------

    public static void printKonkurrencesvoemmereListe() {
        int i = 1;
        String tidlNavn = "";
        System.out.println("Antal i alleMedlemmer: " + MedlemController.alleMedlemmer.size());
        System.out.println("Antal i alleMedlemmer get: " + MedlemController.getAlleMedlemmer().size());
        for (Medlem medlem : MedlemController.getAlleMedlemmer()) {
            System.out.println("Medlem: " + medlem.getNavn() + " (" + medlem.getId() + ")"+ " medlemskab: " + medlem.getMedlemsskabsNr());
            if (medlem.getMedlemsskabsNr() == 1) {
                if (!medlem.getNavn().equals(tidlNavn)) {
                    System.out.println(" " + i + ". " + medlem.getNavn() + " (" + medlem.getId() + ")");
                    tidlNavn = medlem.getNavn();
                    i++;
                }
            }
        }

    }


}