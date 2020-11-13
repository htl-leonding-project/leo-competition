package at.htl.leocompetition.entity;

public class V_Ergebnisse {
    private Spiel spiel;
    private String ergebnis;
    private int punkte_m1;
    private int punkte_m2;

    /**
     * @return the spiel
     */
    public Spiel getSpiel() {
        return spiel;
    }

    /**
     * @param spiel the spiel to set
     */
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    /**
     * @return the ergebnis
     */
    public String getErgebnis() {
        return ergebnis;
    }

    /**
     * @param ergebnis the ergebnis to set
     */
    public void setErgebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }

    /**
     * @return the punkte_m1
     */
    public int getPunkte_m1() {
        return punkte_m1;
    }

    /**
     * @param punkte_m1 the punkte_m1 to set
     */
    public void setPunkte_m1(int punkte_m1) {
        this.punkte_m1 = punkte_m1;
    }

    /**
     * @return the punkte_m2
     */
    public int getPunkte_m2() {
        return punkte_m2;
    }

    /**
     * @param punkte_m2 the punkte_m2 to set
     */
    public void setPunkte_m2(int punkte_m2) {
        this.punkte_m2 = punkte_m2;
    }
}
