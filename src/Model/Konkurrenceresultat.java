package Model;

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
    public String toString() {
        return "Konkurrenceresultat{" +
                "traeningsTid=" + traeningsTid +
                ", staevne='" + staevne + '\'' +
                ", placering=" + placering +
                '}';
    }
}
