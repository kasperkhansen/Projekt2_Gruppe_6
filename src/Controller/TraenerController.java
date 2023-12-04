package Controller;

import Model.*;
import View.Input;

import java.time.LocalDate;

import static Controller.MedlemController.getMedlemMedId;

public class TraenerController {

    public static void registrerResultat(int id) {
        Medlem medlem = getMedlemMedId(id);
        if (medlem != null) {
            int nyDisciplinTid = Input.svoemmeDisciplinValg();
            int nyDisciplinType = Input.konkurrenceValg();
            double nyTid = Input.svoemmeTid();
            String valgtDisciplin;

            if (nyDisciplinType == 1) {
                String hvilketStaevne = Input.stringInput("Indtast stævne: ");
                int hvilkenPlacering = Input.intInput("Indtast placering: ");
                switch (nyDisciplinTid) {
                    case 1:
                        medlem.brystKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                    case 2:
                        medlem.crawlKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                    case 3:
                        medlem.butterflyKonkurrence.add(new Konkurrenceresultat(nyTid, hvilketStaevne, hvilkenPlacering));
                }
            }
            else {
                LocalDate hvilkenDato = Input.dateInput("Indtast dato: ");
                switch (nyDisciplinTid) {
                    case 1:
                        medlem.brystTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                    case 2:
                        medlem.crawlTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                    case 3:
                        medlem.butterflyTraening.add(new Traeningsresultat(nyTid, hvilkenDato));
                }
            }
        } else {
            System.out.println("Ingen medlem fundet med dette navn");
        }
    }

    public void seTopFemSvoemmere() {
    }

    public void registrerKonkurrenceSvoemmeresRsultat() {
    }
}
