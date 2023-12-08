package Model;

import java.util.Objects;

public class Konkurrenceresultat {
    double tid;

    String staevne;

    int placering;


    public Konkurrenceresultat(double traeningsTid, String staevne, int placering) {
        this.tid = traeningsTid;
        this.staevne = staevne;
        this.placering = placering;
    }

    public int getPlacering() {
        return this.placering;
    }

    public double getTid() {
        return this.tid;
    }


    public String getStaevne() {
        return this.staevne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Konkurrenceresultat)) return false;
        Konkurrenceresultat that = (Konkurrenceresultat) o;
        return Double.compare(that.tid, tid) == 0 &&
                placering == that.placering &&
                Objects.equals(staevne, that.staevne);
    }

    public void printKonkurrenceresultat () {
        System.out.println("Konkurrenceresultat:");
        System.out.println("Stævne: " + staevne+ ", Tid: " + tid + ", Placering: " +placering);
        System.out.println();
    }

    @Override
    public String toString() {
        return "Konkurrenceresultat: " + ", Tid:" + tid + ", Stævne= '" + staevne + '\'' + ", Placering=" + placering;
    }
}
