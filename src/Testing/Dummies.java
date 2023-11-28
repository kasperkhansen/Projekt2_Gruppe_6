package Testing;
import Model.Medlem;
import java.time.LocalDate;
//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
    public static Medlem a = new Medlem("Flemming", 1, 18, LocalDate.of(2003, 1, 1));
    public static Medlem b = new Medlem("Bent", 2, 18, LocalDate.of(2003, 1, 1));
    public static Medlem c = new Medlem("Claus", 3, 18, LocalDate.of(2003, 1, 1));
    public static Medlem d = new Medlem("Dennis", 1, 18, LocalDate.of(2003, 1, 1));
    public static Medlem e = new Medlem("Erik", 2, 18, LocalDate.of(2003, 1, 1));
    public static Medlem f = new Medlem("Gert", 3, 18, LocalDate.of(2003, 1, 1));

    public static void main(String[] args) {
        //MedlemController.printAlleMedlemmer(); liste med samtilige medlemmer
        //skift medlemskab
        //MedlemController.betalEngangsbillet(); pass
        //MedlemController.skiftMedlemskab("Flemming");
    }
}
