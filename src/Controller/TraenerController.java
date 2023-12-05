package Controller;

import Model.*;
import View.Input;


import java.util.Collections;
import java.util.Comparator;

import java.time.LocalDate;

import static Controller.MedlemController.alleMedlemmer;
import static Controller.MedlemController.getMedlemMedId;

public class TraenerController {

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
                } else {
                    System.out.println("Dette medlem er ikke et aktivt medlem");
                }
            }
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }
    }

   static class SvoemmedisciplinComparator implements Comparator<Traeningsresultat>{
        @Override
        public int compare(Traeningsresultat o1, Traeningsresultat o2) {
            if (o1.getTid() > o2.getTid())
                return 1;
            if (o1.getTid() == o2.getTid())
                return 0;
            else return -1;
        }
    }

    public static void seTopFemSvoemmereCrawl() {
        SvoemmedisciplinComparator CrawlSammenligner = new SvoemmedisciplinComparator();
        Collections.sort(Medlem.crawlTraening, CrawlSammenligner);
        System.out.println(Medlem.crawlTraening);
    }
    public static void seTopFemSvoemmereBryst() {
            SvoemmedisciplinComparator BrystSammenligner = new SvoemmedisciplinComparator();
            Collections.sort(Medlem.brystTraening, BrystSammenligner);
        System.out.println(Medlem.brystTraening);
    }
    public static void seTopFemSvoemmereButterfly(){
        SvoemmedisciplinComparator ButterflySammenligner = new SvoemmedisciplinComparator();
        Collections.sort(Medlem.butterflyTraening, ButterflySammenligner);
        System.out.println(Medlem.butterflyTraening);
    }


    public static void main(String[] args) {
        Medlem b = new Medlem("Bent", 2,  LocalDate.of(2003, 1, 1), 22222222);
        alleMedlemmer.add(b);
        registrerResultat();
    }
    static class SvoemmedisciplinKonkurrenceResultatComparator implements Comparator<Konkurrenceresultat>{
        @Override
        public int compare(Konkurrenceresultat o1, Konkurrenceresultat o2) {
            if (o1.getTid() > o2.getTid())
                return 1;
            if (o1.getTid() == o2.getTid())
                return 0;
            else return -1;
        }
    }
    public void seTopFemSteavneResultatCrawl() {
        SvoemmedisciplinKonkurrenceResultatComparator staevneCrawlSammenligner = new SvoemmedisciplinKonkurrenceResultatComparator();
        Collections.sort(Medlem.crawlKonkurrence, staevneCrawlSammenligner);
    }

    public void seTopFemSteavneResultatBryst() {
        SvoemmedisciplinKonkurrenceResultatComparator steavneBrystSammenligner = new SvoemmedisciplinKonkurrenceResultatComparator();
        Collections.sort(Medlem.brystKonkurrence, steavneBrystSammenligner);
    }

    public void seTopFemSteavneResultatButterfly() {
        SvoemmedisciplinKonkurrenceResultatComparator steavneButterflySammenligner = new SvoemmedisciplinKonkurrenceResultatComparator();
        Collections.sort(Medlem.butterflyKonkurrence, steavneButterflySammenligner);
    }
}
