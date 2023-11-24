package Model;

import java.time.LocalDate;

public class Motionist extends AktivtMedlem{
    Motionist(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemsskab, alder, foedselsdato, id);
    }
}
