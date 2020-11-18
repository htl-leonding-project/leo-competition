package at.htl.leocompetition.entity;

import java.time.LocalDate;
import java.util.Date;

public class Turnier {

    private Long turnierid;
    private LocalDate datum;
    private LocalDate uhrzeit;
    private String turnierart;
    private String turniername;

    public Turnier(Long turnierid, LocalDate datum, LocalDate uhrzeit, String turnierart, String turniername) {
        this.turnierid = turnierid;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.turnierart = turnierart;
        this.turniername = turniername;
    }

    public Turnier() {
    }

    public Long getTurnierid() {
        return turnierid;
    }

    public void setTurnierid(Long turnierid) {
        this.turnierid = turnierid;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(LocalDate uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getTurnierart() {
        return turnierart;
    }

    public void setTurnierart(String turnierart) {
        this.turnierart = turnierart;
    }

    public String getTurniername() {
        return turniername;
    }

    public void setTurniername(String turniername) {
        this.turniername = turniername;
    }

    @Override
    public String toString() {
        return "Turnier{" +
                "turnierid=" + turnierid +
                ", datum=" + datum +
                ", uhrzeit=" + uhrzeit +
                ", turnierart='" + turnierart + '\'' +
                ", turniername='" + turniername + '\'' +
                '}';
    }
}
