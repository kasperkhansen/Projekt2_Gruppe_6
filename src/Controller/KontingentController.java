package Controller;

import Model.Medlem;

import java.util.ArrayList;

import Model.KontingentBetaling;
import View.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KontingentController {
    private static ArrayList<Medlem> alleMedlemmer = MedlemController.alleMedlemmer;
    private static ArrayList<Medlem> medlemmerMedKontingentBetalt = new ArrayList<>();
    private static ArrayList<Medlem> medlemmerUdenKontingentBetalt = new ArrayList<>();
    private List<KontingentBetaling> betalinger;
    private MedlemController medlemController;

    public KontingentController(MedlemController medlemController) {
        this.betalinger = new ArrayList<>();
        this.medlemController = medlemController;
    }

    public void opretBetaling(boolean aktivMedlem, boolean seniorMedlem, boolean pensionistRabat, LocalDate foedselsdato) {
        KontingentBetaling nyBetaling = new KontingentBetaling(aktivMedlem, seniorMedlem, pensionistRabat, foedselsdato);
        betalinger.add(nyBetaling);
        int nr = Input.getIdInput();
    }

    public void seOverblik() {
        for (KontingentBetaling betaling : betalinger) {
            System.out.println(betaling.toString());
        }
    }


    public void overblikOverKontingentBetalinger() {
        updateLists();
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



    private void updateLists() {
        for (Medlem medlem : alleMedlemmer) {
            if (medlem.getKontingentBetalt()) {
                medlemmerMedKontingentBetalt.add(medlem);
            } else {
                medlemmerUdenKontingentBetalt.add(medlem);
            }
        }
    }
}
