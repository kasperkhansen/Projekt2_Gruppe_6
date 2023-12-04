package Model;

public class Konkurrenceresultat {
    double traeningsTid;

    String staevne;

    int placering;

    public int getPlacering() {
        return placering;
    }

    public double getTid() {
        return traeningsTid;
    }

    public String getStaevne() {
        return staevne;
    }

    public Konkurrenceresultat(double traeningsTid, String staevne, int placering) {
        this.traeningsTid = traeningsTid;
        this.staevne = staevne;
        this.placering = placering;
    }

}
