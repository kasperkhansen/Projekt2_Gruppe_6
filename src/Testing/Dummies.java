package Testing;
import Model.AktivtMedlem;
import Model.Medlem;

import java.time.LocalDate;

//Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id)

public class Dummies {
   public static void main(String[] args) {
        Medlem a = new Medlem("Find", "aktiv + motionist", 55, LocalDate.of(2023,12,12));
        Medlem b = new Medlem("Finn", "passivt medlem", 56, LocalDate.now());
        Medlem c = new Medlem("Finne", "aktivtivtmedlem", 55, LocalDate.now());
        Medlem d = new Medlem("Fin", "passivtmedlem", 55, LocalDate.now());
        Medlem e = new Medlem("Finden", "aktivtivtmedlem", 55, LocalDate.now());
        Medlem f = new Medlem("Findus", "Passivtmedlem", 55, LocalDate.now());

       System.out.println(a.getMedlemsskabArrayList());























    }
}
