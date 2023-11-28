package Testing;
import Controller.MedlemController;
import Model.AktivtMedlem;
import Model.Medlem;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import java.time.LocalDate;

//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
    public static Medlem a = new Medlem("Find", "aktiv + motionist", 55, LocalDate.of(2023,12,12));
    public static Medlem b = new Medlem("Finn", "passivt medlem", 56, LocalDate.of(2000,12,9));
    public static Medlem c = new Medlem("Finne", "aktivtivtmedlem", 55, LocalDate.of(1982,4,3));
    public static Medlem d = new Medlem("Fin", "passivtmedlem", 55, LocalDate.of(2005,2,4));
    public static Medlem e = new Medlem("Finden", "aktivtivtmedlem", 55, LocalDate.of(2003,10,12));
    public static Medlem f = new Medlem("Findus", "Passivtmedlem", 55, LocalDate.of(2002,4,2));

    public static void main(String[] args) {
        MedlemController.skiftMedlemskab(f.getNavn());

    }
}
