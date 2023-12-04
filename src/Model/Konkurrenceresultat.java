package Model;

import java.time.LocalDate;

public class Konkurrenceresultat {
    double traeningsTid;

    String staevne;

    int placering;

    public Konkurrenceresultat(double traeningsTid, String staevne, int placering) {
        this.traeningsTid = traeningsTid;
        this.staevne = staevne;
        this.placering = placering;
    }
}
