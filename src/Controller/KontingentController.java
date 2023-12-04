package Controller;

import Model.Medlem;

import java.util.ArrayList;

import Model.KontingentBetaling;
import View.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static View.Input.booleanInput;

public class KontingentController {
    private static ArrayList<Medlem> alleMedlemmer = MedlemController.alleMedlemmer;
    private static ArrayList<Medlem> medlemmerMedKontingentBetalt = new ArrayList<>();
    private static ArrayList<Medlem> medlemmerUdenKontingentBetalt = new ArrayList<>();
    private static List<KontingentBetaling> betalinger = new ArrayList<>();
    private MedlemController medlemController;

    public KontingentController(MedlemController medlemController) {
        this.medlemController = medlemController;
    }

    public static void opretBetaling(Medlem medlem) {
        boolean aktivMedlem = !medlem.erPassivtMedlemskab();
        boolean seniorMedlem = medlem.erSenior();
        boolean pensionistRabat = medlem.erPensionist();
        LocalDate foedselsdato = medlem.getFoedselsdato();

        if (medlem.erPassivtMedlemskab()) {
        } else if (medlem.getAlder() < 18) {
        } else if (medlem.getAlder() >= 18 && medlem.getAlder() < 60) {
        } else if (medlem.erPensionist()) {
        } else {
        }

        KontingentBetaling nyBetaling = new KontingentBetaling(aktivMedlem, seniorMedlem, pensionistRabat, foedselsdato);
        betalinger.add(nyBetaling);

        int kontingentBeløb = nyBetaling.beregnKontingent();

        int nr = medlem.getId();

        System.out.println("Betalingen for " +medlem.getNavn()+ " er registreret");
       // System.out.println(nyBetaling.besked());
    }

    public void seOverblik() {
        for (KontingentBetaling betaling : betalinger) {
            System.out.println(betaling.toString());
        }
    }


    public void overblikOverKontingentBetalinger() {
     //    updateLists();
        System.out.println("--------------------------\n");
        System.out.println("Overblik over kontingent betalinger: ");
        System.out.println("Medlemmer med kontingent betalt: ");
        for (Medlem medlem : medlemmerMedKontingentBetalt) {
            System.out.println(medlem.getNavn());
        }
        System.out.println();
        System.out.println("Medlemmer uden kontingent betalt: ");
        for (Medlem medlem : medlemmerUdenKontingentBetalt) {
            System.out.println(medlem.getNavn());
        }
        System.out.println();
        System.out.println("Antal medlemmer med kontingent betalt: " + medlemmerMedKontingentBetalt.size());
        System.out.println("Antal medlemmer uden kontingent betalt: " + medlemmerUdenKontingentBetalt.size());
        System.out.println("--------------------------\n");

        }







    public void registrerKontingentBetaling() {
    }

    public static void koebEnGangsBillet() {
    }



    /* private void updateLists() {
        for (Medlem medlem : alleMedlemmer) {
            if (medlem.getKontingentBetalt()) {
                medlemmerMedKontingentBetalt.add(medlem);
            } else {
                medlemmerUdenKontingentBetalt.add(medlem);
            }
        }
    } */
}
