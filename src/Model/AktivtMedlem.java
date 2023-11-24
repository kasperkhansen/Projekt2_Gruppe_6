package Model;

import java.time.LocalDate;

public class AktivtMedlem extends Medlemskab {
    AktivtMedlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemsskab, alder, foedselsdato, id);
    }
}
