package Testing;
        import Controller.MedlemController;
        import Model.Medlem;
        import java.time.LocalDate;
        import java.time.Period;
//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
    public static Medlem a = new Medlem("Flemming", 1, LocalDate.of(2003, 1, 1), 11111111);
    public static Medlem b = new Medlem("Bent", 2,  LocalDate.of(2003, 1, 1), 22222222);
    public static Medlem c = new Medlem("Claus", 3,  LocalDate.of(2003, 1, 1), 33333333);
    public static Medlem d = new Medlem("Dennis", 1,  LocalDate.of(2003, 1, 1), 44444444);
    public static Medlem e = new Medlem("Erik", 2, LocalDate.of(2003, 1, 1), 55555555);
    public static Medlem f = new Medlem("Gert", 3, LocalDate.of(2003, 1, 1), 66666666);

}





