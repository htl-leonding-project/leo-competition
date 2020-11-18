package at.htl.leocompetition.oldentities;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SPIELER")
@NamedQueries({@NamedQuery(name = "Spieler.findBySpielerid", query = "SELECT s FROM Spieler s WHERE s.spielerid = :spielerid"), @NamedQuery(name = "Spieler.findByGeburtsdatum", query = "SELECT s FROM Spieler s WHERE s.geburtsdatum = :geburtsdatum"), @NamedQuery(name = "Spieler.findByNachname", query = "SELECT s FROM Spieler s WHERE s.nachname = :nachname"), @NamedQuery(name = "Spieler.findByVorname", query = "SELECT s FROM Spieler s WHERE s.vorname = :vorname")})
public class Spieler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPIELERID", nullable = false)
    private Long spielerid;
    @Column(name = "GEBURTSDATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geburtsdatum;
    @Column(name = "NACHNAME")
    private String nachname;
    @Column(name = "VORNAME")
    private String vorname;
    @JoinColumn(name = "MANNSCHAFT_MANNSCHAFTSID", referencedColumnName = "MANNSCHAFTSID")
    @ManyToOne
    private model.Mannschaft mannschaft;

    public Spieler() {
    }

    public Spieler(String vorname,String nachname,long geburtsdatum)
    {
        this.vorname=vorname;
        this.nachname=nachname;
        this.setGeburtsdatumLong(geburtsdatum);
    }
    public Spieler(String vorname,String nachname)
    {
        this.vorname=vorname;
        this.nachname=nachname;
    }
    
    public Spieler(Long spielerid) {
        this.spielerid = spielerid;
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
    
    public Long getGeburtsdatumLong() {
        return geburtsdatum.getTime();
    }

    public void setGeburtsdatumLong(Long geburtsdatum) {
        this.geburtsdatum.setTime(geburtsdatum);
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

    public model.Mannschaft getMannschaft() {
        return mannschaft;
    }

    public void setMannschaft(model.Mannschaft mannschaft) {
        this.mannschaft = mannschaft;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spielerid != null ? spielerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spieler)) {
            return false;
        }
        Spieler other = (Spieler) object;
        if ((this.spielerid == null && other.spielerid != null) || (this.spielerid != null && !this.spielerid.equals(other.spielerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Spieler[spielerid=" + spielerid + "]";
    }

    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }
    
    public void delete() {
        DBConnection.deleteSpieler(this);
    }
}
