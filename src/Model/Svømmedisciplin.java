package Model;

public class Svømmedisciplin {
    double traeningsResultat;
    double konkurrenceResultat;

    boolean konkurrence;

    public Svømmedisciplin(double traeningsResultat, double konkurrenceResultat, boolean konkurrence) {
        this.traeningsResultat = traeningsResultat;
        this.konkurrenceResultat = konkurrenceResultat;
        this.konkurrence = konkurrence;
    }
}
