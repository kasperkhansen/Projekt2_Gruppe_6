package Testing;
import Controller.MedlemController;
import Model.AktivtMedlem;
import Model.Medlem;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import java.time.LocalDate;

//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
    public static Medlem a = new Medlem("Flemming", 1, 18, LocalDate.of(2003, 1, 1));
    public static Medlem b = new Medlem("Bent", 2, 18, LocalDate.of(2003, 1, 1));
    public static Medlem c = new Medlem("Claus", 3, 18, LocalDate.of(2003, 1, 1));
    public static Medlem d = new Medlem("Dennis", 4, 18, LocalDate.of(2003, 1, 1));
    public static Medlem e = new Medlem("Erik", 5, 18, LocalDate.of(2003, 1, 1));
    public static Medlem f = new Medlem("Gert", 6, 18, LocalDate.of(2003, 1, 1));


    public static void main(String[] args) {


    }
}
