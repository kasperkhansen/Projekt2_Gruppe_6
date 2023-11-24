package Model;

import java.time.LocalDate;

public class Medlem {
    private final String navn;
    private String medlemsskab;
    private final int alder;
    private final LocalDate foedselsdato;
    private int id = 1;


    Medlem(String navn, String medlemsskab, int alder, LocalDate foedselsdato, int id){
        this.navn = navn;
        this.medlemsskab = medlemsskab;
        this.alder = alder;
        this.foedselsdato = foedselsdato;
        this.id = id;
        id++;
    }
    //Tjekke om id tæller op ved flere objekter

    public String getNavn() {
        return navn;
    }

    public String getMedlemsskab() {
        return medlemsskab;
    }

    public int getAlder() {
        return alder;
    }

    public LocalDate getFoedselsdato() {
        return foedselsdato;
    }

    public int getId() {
        return id;
    }

}
