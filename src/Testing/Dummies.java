package Testing;
import Controller.MedlemController;
import Model.Medlem;
import java.time.LocalDate;
//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
    public static Medlem a = new Medlem("Flemming", 1, LocalDate.of(2003, 1, 1));
    public static Medlem b = new Medlem("Bent", 2,  LocalDate.of(2003, 1, 1));
    public static Medlem c = new Medlem("Claus", 3,  LocalDate.of(2003, 1, 1));
    public static Medlem d = new Medlem("Dennis", 1,  LocalDate.of(2003, 1, 1));
    public static Medlem e = new Medlem("Erik", 2, LocalDate.of(2003, 1, 1));
    public static Medlem f = new Medlem("Gert", 3, LocalDate.of(2003, 1, 1));

    public static void main(String[] args) {
        //MedlemController.printAlleMedlemmer(); liste med samtilige medlemmer
        MedlemController.skiftMedlemskab("Flemming");
        // MedlemController.fjernMedlem
        //skift medlemskab
        //MedlemController.betalEngangsbillet(); pass
        //MedlemController.skiftMedlemskab("Flemming");

    }
}
