package at.htl.leocompetition.oldentities;

import leoturniercore.DBConnection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TURNIER")
@NamedQueries({
    @NamedQuery(name = "Turnier.findByTurnierid",
                query = "SELECT t FROM Turnier t WHERE t.turnierid = :turnierid"),
    @NamedQuery(name = "Turnier.findByDatum",
                query = "SELECT t FROM Turnier t WHERE t.datum = :datum"),
    @NamedQuery(name = "Turnier.findByTurnierart",
                query = "SELECT t FROM Turnier t WHERE t.turnierart = :turnierart"),
    @NamedQuery(name = "Turnier.findByTurniername",
                query = "SELECT t FROM Turnier t WHERE t.turniername = :turniername"),
    @NamedQuery(name = "Turnier.findByTurnierstatus",
                query = "SELECT t FROM Turnier t WHERE t.turnierstatus = :turnierstatus")})
public class Turnier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TURNIERID", nullable = false)
    private Long turnierid;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "UHRZEIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uhrzeit;
    @Column(name = "TURNIERART")
    private String turnierart;
    @Column(name = "TURNIERNAME")
    private String turniername;
    @Column(name = "TURNIERSTATUS")
    private String turnierstatus;
    @OneToMany(mappedBy = "turnier", cascade=CascadeType.ALL)
    private List<Phase> phasen;
    @OneToMany(mappedBy = "turnier", cascade=CascadeType.ALL)
    private List<Mannschaft> mannschaften;

    public Turnier()
    {
        setPhasen(phasen);
        setDatum(datum);
        setUhrzeit(uhrzeit);
    }

    public Turnier(Long turnierid) 
    {
        setPhasen(phasen);
        setTurnierid(turnierid);
    }
    /**
     * Verschiebt alle Spiele, die nach dem Ausgewählten kommen
     * um eine angegeben Anzahl von Minuten
     * (Anzuwenden bei unerwarteten Pausen)
     * 
     * @param spiel Ab diesem Spiel werden alle Spiele verschoben
     * @param umMinuten Um diese Anzahl von Minuten werden die Spiele verschoben
     * @param connectin Die Connection, mit der die Spiele anschließend persistiert werden
     */
    public void verschiebeAlleSpieleAbUm(Spiel spiel,int umMinuten)
    {
        Phase phase=spiel.getPhase();
        int indInPhase=phase.getSpiele().indexOf(spiel);
        int indInTurnier=this.phasen.indexOf(phase);
        
        for(int i=indInTurnier; i<this.phasen.size(); i++)
        {
            phase=this.getPhasen().get(i);
            for(int j=indInPhase; j<phase.getSpiele().size();j++)
            {
                Spiel sp=phase.getSpiele().get(j);
                sp.setSpielbeginnLong(sp.getSpielbeginnLong() + (umMinuten*60*1000));
                sp.persist();
            }
            // ab der 2. phase soll er bei ind=0 anfangen...
            indInPhase=0;
        }
    }
    

    public Long getTurnierid() {
        return turnierid;
    }

    public void setTurnierid(Long turnierid) {
        this.turnierid = turnierid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Long getDatumLong() {
        return datum.getTime();
    }

    public void setDatumLong(Long datum) {
        this.datum.setTime(datum);
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

    public String getTurnierstatus() {
        return turnierstatus;
    }

    public void setTurnierstatus(String turnierstatus) {
        this.turnierstatus = turnierstatus;
    }

    public List<Phase> getPhasen() {
        return phasen;
    }

    public void setPhasen(List<Phase> phasen) {
        this.phasen = phasen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnierid != null ? turnierid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turnier)) {
            return false;
        }
        Turnier other = (Turnier) object;
        if ((this.turnierid == null && other.turnierid != null) || (this.turnierid != null && !this.turnierid.equals(other.turnierid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Turnier[turnierid=" + turnierid + "]";
    }

    public List<Mannschaft> getMannschaften() {
        return mannschaften;
    }

    public void setMannschaften(List<Mannschaft> mannschaften) {
        this.mannschaften = mannschaften;
    }

    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }
    
    public void delete() {
        DBConnection.deleteTurnier(this);
    }

    public Date getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Date uhrzeit) {
        this.uhrzeit = uhrzeit;
    }
    
    public Long getUhrzeitLong() {
        return uhrzeit.getTime();
    }

    public void setUhrzeitLong(Long uhrzeit) {
        this.uhrzeit.setTime(uhrzeit);
    }
}
