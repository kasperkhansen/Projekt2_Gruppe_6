package Model;

import java.time.LocalDate;

public class PassivtMedlem extends Medlemskab{
    PassivtMedlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemsskab, alder, foedselsdato, id);
    }
}
