package Model;

import java.time.LocalDate;

public class Traeningsresultat {
    double traeningsTid;

    LocalDate traeningsDato;

    public double getTraeningsTid() {
        return traeningsTid;
    }

    public LocalDate getTraeningsDato() {
        return traeningsDato;
    }

    public Traeningsresultat(double traeningsTid, LocalDate traeningsDato) {
        this.traeningsTid = traeningsTid;
        this.traeningsDato = traeningsDato;
    }
}
