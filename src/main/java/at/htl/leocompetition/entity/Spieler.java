package at.htl.leocompetition.entity;

import java.util.Date;

public class Spieler {
    private Long spielerid;
    private Date geburtsdatum;
    private String nachname;
    private String vorname;

    public Spieler() {
    }

    public Spieler(Long spielerid, Date geburtsdatum, String nachname, String vorname) {
        this.spielerid = spielerid;
        this.geburtsdatum = geburtsdatum;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public Long getSpielerid() {
        return spielerid;
    }

    public void setSpielerid(Long spielerid) {
        this.spielerid = spielerid;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public String toString() {
        return "Spieler{" +
                "spielerid=" + spielerid +
                ", geburtsdatum=" + geburtsdatum +
                ", nachname='" + nachname + '\'' +
                ", vorname='" + vorname + '\'' +
                '}';
    }
}
