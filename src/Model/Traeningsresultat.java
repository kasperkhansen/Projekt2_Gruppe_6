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

    public void printTræningsResultat () {
        System.out.println("Træningsresultat:");
        System.out.println("Træningstid: " + traeningsTid + ", Træningsdato: " + traeningsDato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Traeningsresultat)) return false;
        Traeningsresultat that = (Traeningsresultat) o;
        return Double.compare(that.traeningsTid, traeningsTid) == 0 &&
                traeningsDato.equals(that.traeningsDato);
    }

    @Override
    public String toString() {
        return "Traeningsresultat{" +
                "traeningsTid=" + traeningsTid +
                ", traeningsDato=" + traeningsDato +
                '}';
    }

}
