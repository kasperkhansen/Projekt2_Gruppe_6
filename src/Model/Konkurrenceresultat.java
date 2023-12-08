package Model;

import java.util.Objects;

public class Konkurrenceresultat {
    double traeningsTid;

    String staevne;

    int placering;


    public Konkurrenceresultat(double traeningsTid, String staevne, int placering) {
        this.traeningsTid = traeningsTid;
        this.staevne = staevne;
        this.placering = placering;
    }

    public int getPlacering() {
        return this.placering;
    }

    public double getTid() {
        return this.traeningsTid;
    }


    public String getStaevne() {
        return this.staevne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Konkurrenceresultat)) return false;
        Konkurrenceresultat that = (Konkurrenceresultat) o;
        return Double.compare(that.traeningsTid, traeningsTid) == 0 &&
                placering == that.placering &&
                Objects.equals(staevne, that.staevne);
    }

    @Override
    public String toString() {
        return "Konkurrenceresultat{" +
                "traeningsTid=" + traeningsTid +
                ", staevne='" + staevne + '\'' +
                ", placering=" + placering +
                '}';
    }
}
