package Controller;

import Model.Medlem;

import java.util.ArrayList;

public class KontingentController {
    private static ArrayList<Medlem> alleMedlemmer = MedlemController.alleMedlemmer;
    private static ArrayList<Medlem> medlemmerMedKontingentBetalt = new ArrayList<>();
    private static ArrayList<Medlem> medlemmerUdenKontingentBetalt = new ArrayList<>();

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
