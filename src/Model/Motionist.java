package Model;

import java.time.LocalDate;

public class Motionist extends AktivtMedlem{
    public Motionist(String navn, String medlemskabStr, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemskabStr, alder, foedselsdato, id);
    }
}
