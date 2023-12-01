package Controller;

import Model.Bryst;
import Model.Butterfly;
import Model.Crawl;
import Model.Medlem;
import View.Input;

import static Controller.MedlemController.getMedlemMedId;

public class TraenerController {

    public static void registrerResultat(int id) {
        Medlem medlem = getMedlemMedId(id);
        if (medlem != null) {
            int nyDisciplinTid = Input.svoemmeDisciplinValg();
            int nyDisciplinType = Input.konkurrenceValg();
            double nyTid = Input.svoemmeTid();
            String valgtDisciplin;

            switch (nyDisciplinTid) {
                case 1:
                    valgtDisciplin = "Crawl";
                    if (nyDisciplinType == 1){
                        medlem.crawlKonkurrence.add(new Crawl(0, nyTid, true));
                        break;
                    } else {
                        medlem.crawlTraening.add(new Crawl(nyTid, 0, false));
                        break;
                    }
                case 2:
                    valgtDisciplin = "Bryst";
                    if (nyDisciplinType == 1){
                        medlem.brystKonkurrence.add(new Bryst(0, nyTid, true));
                        break;
                    } else {
                        medlem.brystTraening.add(new Bryst(nyTid, 0, false));
                        break;
                    }
                case 3:
                    valgtDisciplin = "Butterfly";
                    if (nyDisciplinType == 1){
                        medlem.butterflyKonkurrence.add(new Butterfly(0, nyTid, true));
                        break;
                    } else {
                        medlem.butterflyTraening.add(new Butterfly(nyTid, 0, false));
                        break;
                    }
                default:
                    System.out.println("Ugyldig indtastning, prøv igen");
                    return;
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
