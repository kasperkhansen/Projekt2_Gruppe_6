package Model;

import java.time.LocalDate;

public class Traeningsresultat {
    static double traeningsTid;

    LocalDate traeningsDato;

    public double getTid() {
        return traeningsTid;
    }

    public LocalDate getTraeningsDato() {
        return traeningsDato;
    }

    public Traeningsresultat(double traeningsTid, LocalDate traeningsDato) {
        this.traeningsTid = traeningsTid;
        this.traeningsDato = traeningsDato;
    }

    @Override
    public String toString() {
        return "Traeningsresultat{" +
                "traeningsTid=" + traeningsTid +
                ", traeningsDato=" + traeningsDato +
                '}';
    }

}
