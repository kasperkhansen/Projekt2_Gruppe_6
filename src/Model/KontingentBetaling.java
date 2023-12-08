package Model;

import java.time.LocalDate;

public class KontingentBetaling {
    public int aktivSeniorKontingent;
    private boolean aktivMedlem;
    private boolean seniorMedlem;
    private boolean pensionistRabat;
    private boolean passivMedlem;
    private LocalDate foedsesldato;
    public int aktivJuniorKontingent = 1000;
    private int aktivSeniorkontingent = 1600;
    public int passivKontingent = 500;
    private boolean betaltKontingent;

    public KontingentBetaling(boolean aktivMedlem, boolean seniorMedlem, boolean pensionistRabat, LocalDate foedsesldato) {
        this.aktivMedlem = aktivMedlem;
        this.seniorMedlem = seniorMedlem;
        this.pensionistRabat = pensionistRabat;
        this.foedsesldato = foedsesldato;
    }

    public void bekræftBetaling() {
        this.betaltKontingent = true;
    }

    public int beregnKontingent() {
        if (this.aktivMedlem) {
            if (this.seniorMedlem) {
                return this.pensionistRabat ? this.beregnRabat(this.aktivSeniorkontingent) : this.aktivSeniorkontingent;
            } else {
                return this.aktivJuniorKontingent;
            }
        } else {
            return this.passivKontingent;
        }
    }

    public int beregnRabat(int pris) {
        return (int)((double)pris * 0.75);
    }

    public String toString() {
        return "KontingentBetaling{aktivMedlem=" + this.aktivMedlem + ", seniorMedlem=" + this.seniorMedlem + ", pensionistRabat= " + this.pensionistRabat + ", betaltKontingent= " + this.betaltKontingent + "}";
    }
}
