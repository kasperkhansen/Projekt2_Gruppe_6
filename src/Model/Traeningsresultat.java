package Model;

import java.time.LocalDate;

public class Traeningsresultat {
    double traeningsTid;

    LocalDate traeningsDato;

    public Traeningsresultat(double traeningsTid, LocalDate traeningsDato) {
        this.traeningsTid = traeningsTid;
        this.traeningsDato = traeningsDato;
    }
}
