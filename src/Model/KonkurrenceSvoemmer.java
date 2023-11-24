package Model;

import java.time.LocalDate;

public class KonkurrenceSvoemmer extends AktivtMedlem {
    KonkurrenceSvoemmer(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemsskab, alder, foedselsdato, id);
    }
}
