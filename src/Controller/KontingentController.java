package Controller;

import Model.Medlem;

import java.time.Period;
import java.util.ArrayList;

import Model.KontingentBetaling;
import View.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Controller.MedlemController.getAlleMedlemmer;
import static Controller.MedlemController.getMedlemMedId;
import static View.Input.booleanInput;

public class KontingentController {


    private static ArrayList<Medlem> medlemmerMedKontingentBetalt = new ArrayList<>();
    private static ArrayList<Medlem> medlemmerUdenKontingentBetalt = new ArrayList<>();
    private static List<KontingentBetaling> betalinger = new ArrayList<>();


    public static void registrerKontingentBetaling() {
        DatabaseController.loadFilesToArr();
        System.out.println("Registrer kontingent betaling...");

        while (true) {
            MedlemController.printAlleMedlemmerMobilNr();

            int MedlemId = Input.getIdInput();
            Medlem medlem = getMedlemMedId(MedlemId);
            if (medlem == null) {
                System.out.println("Medlem med id " + MedlemId + " findes ikke. Prøv igen.");
            } else {
                KontingentController.opretBetaling(medlem);
                break;
            }
        }
    }

    public static void opretBetaling(Medlem medlem) {
        boolean aktivMedlem = !medlem.erPassivtMedlemskab();
        boolean seniorMedlem = medlem.erSenior();
        boolean pensionistRabat = medlem.erPensionist();
        LocalDate foedselsdato = medlem.getFoedselsdato();
        if (!medlem.erPassivtMedlemskab() && medlem.getAlder() >= 18 && (medlem.getAlder() < 18 || medlem.getAlder() >= 60) && medlem.erPensionist()) {
        }

        KontingentBetaling nyBetaling = new KontingentBetaling(aktivMedlem, seniorMedlem, pensionistRabat, foedselsdato);
        betalinger.add(nyBetaling);

        int kontingentBeløb = nyBetaling.beregnKontingent();
        String medlemskabstype = "";

        int pris;
        if (aktivMedlem) {
            if (seniorMedlem) {
                if (pensionistRabat) {
                    medlemskabstype = "Aktivt senior med pensionistrabat";
                    pris = nyBetaling.beregnRabat(nyBetaling.aktivSeniorKontingent);
                } else {
                    medlemskabstype = "Aktivt senior medlemskab";
                    pris = nyBetaling.aktivSeniorKontingent;
                }
            } else {
                medlemskabstype = "Aktivt junior medlemskab";
                pris = nyBetaling.aktivJuniorKontingent;
            }
        } else {
            medlemskabstype = "Passivt medlemskab";
            pris = nyBetaling.passivKontingent;
        }

        System.out.println(medlemskabstype + " for " + medlem.getNavn() + " er betalt.");
        System.out.println("Total: " + kontingentBeløb + "kr");
        medlem.setKontingentBetalt(true);
        medlem.setSidstBetalt(LocalDate.now());
    }

    public void seOverblik() {
        for (KontingentBetaling betaling : betalinger) {
            System.out.println(betaling.toString());
        }
    }

    public void overblikOverKontingentBetalinger(List<Medlem> alleMedlemmer) {
        LocalDate now = LocalDate.now();
        int i = 1;

        for (Medlem medlem : alleMedlemmer) {
            LocalDate sidstBetalt = medlem.getSidstBetalt();
            Period period = Period.between(sidstBetalt, now);

            KontingentBetaling betaling = new KontingentBetaling(
                    !medlem.erPassivtMedlemskab(),
                    medlem.erSenior,
                    medlem.erPensionist(),
                    medlem.getFoedselsdato()
            );

            if (period.getYears() >= 1) {
                betaling.bekræftBetaling();
                medlem.setKontingentBetalt(true); // marker kontingentet som betalt
                // medlemmerUdenKontingentBetalt.add(medlem);
            }

            if (medlem.harBetaltKontingent()) {
                medlemmerMedKontingentBetalt.add(medlem);
            } else {
                medlemmerUdenKontingentBetalt.add(medlem);
            }
        }

            //    updateLists();
            System.out.println("--------------------------\n");
            System.out.println("Overblik over kontingent betalinger: ");
            System.out.println();
            System.out.println("Medlemmer med kontingent betalt: ");
            for (Medlem m : medlemmerMedKontingentBetalt) {
                System.out.println(" " + i + ". " + m.getNavn());
                i++;
            }
            System.out.println();
            System.out.println("Medlemmer uden kontingent betalt: ");
            for (Medlem m : medlemmerUdenKontingentBetalt) {
                System.out.println(" " + i + ". " + m.getNavn());
                i++;
            }
            System.out.println();
            System.out.println("Antal medlemmer med kontingent betalt: " + medlemmerMedKontingentBetalt.size());
            System.out.println("Antal medlemmer uden kontingent betalt: " + medlemmerUdenKontingentBetalt.size());
            System.out.println("--------------------------\n");

        }

    }

