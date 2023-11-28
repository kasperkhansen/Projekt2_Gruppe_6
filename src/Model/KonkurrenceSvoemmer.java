package Model;
import java.time.LocalDate;
public class KonkurrenceSvoemmer extends AktivtMedlem {
    KonkurrenceSvoemmer(String navn, String medlemskabStr, int alder, LocalDate foedselsdato, int id) {
        super(navn, medlemskabStr, alder, foedselsdato, id);
    }
}
