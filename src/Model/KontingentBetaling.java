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

    public KontingentBetaling (boolean aktivMedlem, boolean seniorMedlem, boolean pensionistRabat, LocalDate foedsesldato) {
        this.aktivMedlem=aktivMedlem;
        this.seniorMedlem=seniorMedlem;
        this.pensionistRabat=pensionistRabat;
        this.foedsesldato=foedsesldato;
    }


    public void bekræftBetaling () {
        this.betaltKontingent=true;
    }

    public int beregnKontingent () {
        if (aktivMedlem) {
            if (seniorMedlem) {
                if (pensionistRabat) {
                    return beregnRabat(aktivSeniorkontingent);
                } else {
                    return aktivSeniorkontingent;
                }
            } else {
                return aktivJuniorKontingent;
            }
        } else {
            return passivKontingent;
        }
    }

    public int beregnRabat(int pris) {
        return (int) (pris * 0.75); // 25% rabat
    }

    public void besked () {

    }

    public String toString() {
        return "KontingentBetaling{" +
                "aktivMedlem=" + aktivMedlem +
                ", seniorMedlem=" + seniorMedlem +
                ", pensionistRabat= " + pensionistRabat +
                ", betaltKontingent= " + betaltKontingent +
                '}' ;
    }
}

