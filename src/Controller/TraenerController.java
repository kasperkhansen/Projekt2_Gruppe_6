package Controller;
import Comparatorer.*;
import Model.*;
import View.Input;

import java.util.ArrayList;
import java.time.LocalDate;

import static Controller.MedlemController.getMedlemMedId;

public class TraenerController {

    static ArrayList<Medlem> BedsteTraeningsTiderCrawl = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteTraeningsTiderBryst = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteTraeningsTiderButterfly = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderCrawl = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderBryst = new ArrayList<Medlem>();
    static ArrayList<Medlem> BedsteStaevneTiderButterfly = new ArrayList<Medlem>();

    public static void registrerResultat() {
        int id = Input.getIdInput();

        Medlem medlem = getMedlemMedId(id);

        if (medlem != null) {
            int nyDisciplinTid = Input.svoemmeDisciplinValg();
            int nyDisciplinType = Input.konkurrenceValg();

            if (nyDisciplinType == 1) {
                if (medlem.getMedlemsskabsNr()==1){
                    String hvilketStaevne = Input.stringInput("Indtast stævne: ");
                    int hvilkenPlacering = Input.intInput("Indtast placering: ");
                    double nyTid = Input.svoemmeTid();
                    switch (nyDisciplinTid) {
                        case 1:
                            medlem.brystKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                        case 2:
                            medlem.crawlKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                        case 3:
                            medlem.butterflyKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                    }
                }  else {
                    System.out.println("Dette medlem er ikke en konkurrencesvømmer");
                }
            }
            else {
                if (medlem.getMedlemsskabsNr()!=3) {
                    LocalDate hvilkenDato = Input.dateInput("Indtast træningsdato: ");
                    double nyTid = Input.svoemmeTid();
                    switch (nyDisciplinTid) {
                        case 1:
                            medlem.brystTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                        case 2:
                            medlem.crawlTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                        case 3:
                            medlem.butterflyTraening.add(new Traeningsresultat(nyTid, hvilkenDato));

                    }

                    DatabaseController.updaterMedlemFile(medlem);
                } else {
                    System.out.println("Dette medlem er ikke et aktivt medlem");
                }
            }
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }

    }

//-----------------------------------sortering metoder------------------------------------------------------------------

    public static void sortListsBrystKonkurrence(ArrayList<Medlem> list){
        Comparatorer.BrystKonkurrenceComparator brystKonkurrenceResultatSorter = new Comparatorer.BrystKonkurrenceComparator();
        list.sort(brystKonkurrenceResultatSorter);
    }

    public static void sortListsBrystTraening(ArrayList<Medlem> list){
        Comparatorer.BrystTraeningsComparator brystTraeningResultatSorter = new BrystTraeningsComparator();
        list.sort(brystTraeningResultatSorter);
    }

    public static void sortListsCrawlKonkurrence(ArrayList<Medlem> list){
        Comparatorer.CrawlKonkurrenceComparator crawlKonurrenceResultatSorter = new CrawlKonkurrenceComparator();
        list.sort(crawlKonurrenceResultatSorter);
    }

    public static void sortListsCrawlTraening(ArrayList<Medlem> list){
        Comparatorer.CrawlTraeningsComparator crawlTraeningsResultatSorter = new CrawlTraeningsComparator();
        list.sort(crawlTraeningsResultatSorter);
    }

    public static void sortListsButterflyKonkurrence(ArrayList<Medlem> list){
        Comparatorer.ButterflyKonkurrenceComparator buterflyKonkurrenceResultatSorter = new ButterflyKonkurrenceComparator();
        list.sort(buterflyKonkurrenceResultatSorter);
    }

    public static void sortListsButterflyTraening(ArrayList<Medlem> list){
        Comparatorer.ButterflyTraeningsComparator butterflyTraeningResultatSorter = new ButterflyTraeningsComparator();
        list.sort(butterflyTraeningResultatSorter);
    }


    //--------------------------get tider get metoder-------------------------------------------------------------------

    public static ArrayList<Medlem> getBedsteStaevneTidCrawl(){

        double tid1;
        double tid2;
        double tid3;
        double tid4;
        double tid5;
        ArrayList<Medlem> arrayList = new ArrayList<>();
        MedlemController.fillStaevneCrawlMedMedlemmerMedTider();
        sortListsCrawlKonkurrence(BedsteStaevneTiderCrawl);

        for (int i = 0; i<5; i++){
        BedsteStaevneTiderCrawl.get(0);
        arrayList.add(BedsteStaevneTiderCrawl.get(i));
        }
        return arrayList;
    }

    public void getBedsteStaevneTidBryst(){
        MedlemController.fillStaevneBrystMedMedlemmerMedTider();
        sortListsBrystKonkurrence(BedsteStaevneTiderBryst);
    }

    public void getBedsteStaevneTidButterfly(){
        MedlemController.fillStaevneButterflyMedMedlemmerMedTider();
        sortListsButterflyKonkurrence(BedsteStaevneTiderButterfly);
    }

    public void getBedsteTraeningsTidCrawl(){
        MedlemController.fillTraeningCrawlMedMedlemmerMedTider();
        sortListsCrawlTraening(BedsteTraeningsTiderCrawl);
    }

    public void getBedsteTraeningsTidBryst(){
        MedlemController.fillTraeningBrystMedMedlemmerMedTider();
        sortListsBrystTraening(BedsteTraeningsTiderBryst);
    }

    public void getBedsteTraeningsTidButterfly(){
        MedlemController.fillTraeningButterflyMedMedlemmerMedTider();
        sortListsButterflyTraening(BedsteTraeningsTiderButterfly);
    }


}