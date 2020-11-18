package at.htl.leocompetition.oldentities;

import leoturniercore.DBConnection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SPIEL")
@NamedQueries({@NamedQuery(name = "Spiel.findBySpielid", query = "SELECT s FROM Spiel s WHERE s.spielid = :spielid"), @NamedQuery(name = "Spiel.findByErgebnis1", query = "SELECT s FROM Spiel s WHERE s.ergebnis1 = :ergebnis1"), @NamedQuery(name = "Spiel.findByErgebnis2", query = "SELECT s FROM Spiel s WHERE s.ergebnis2 = :ergebnis2"), @NamedQuery(name = "Spiel.findBySpielbeginn", query = "SELECT s FROM Spiel s WHERE s.spielbeginn = :spielbeginn")})
public class Spiel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SPIELID", nullable = false)
    private Long spielid;
    @Column(name = "ERGEBNIS1", nullable = false)
    private int ergebnis1;
    @Column(name = "ERGEBNIS2", nullable = false)
    private int ergebnis2;
    @Column(name = "PLATZ", nullable = false)
    private int platz;
    @Column(name = "SPIELBEGINN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date spielbeginn;
    @JoinColumn(name = "GRUPPE_GRUPPENID", referencedColumnName = "GRUPPENID")
    @ManyToOne
    private model.Gruppe gruppe;
    @JoinColumn(name = "MANNSCHAFT1_MANNSCHAFTSID", referencedColumnName = "MANNSCHAFTSID")
    @ManyToOne
    private model.Mannschaft mannschaft1;
    @JoinColumn(name = "MANNSCHAFT2_MANNSCHAFTSID", referencedColumnName = "MANNSCHAFTSID")
    @ManyToOne
    private model.Mannschaft mannschaft2;
    @JoinColumn(name = "PHASE_PHASENID", referencedColumnName = "PHASENID")
    @ManyToOne
    private Phase phase;
    @JoinColumn(name = "NODE_ID", referencedColumnName = "ID")
    @OneToOne(cascade=CascadeType.ALL)
    private Node node;

    public Spiel() {
        this.spielbeginn=new Date();
        this.ergebnis1=-1;
        this.ergebnis2=-1;
    }

    public Spiel(Long spielid) {
        this.spielid = spielid;
        this.spielbeginn=new Date();
        this.ergebnis1=-1;
        this.ergebnis2=-1;
    }

    public Spiel(model.Mannschaft mannschaft1, model.Mannschaft mannschaft2)
    {
        this.mannschaft1=mannschaft1;
        this.mannschaft2=mannschaft2;
        this.spielbeginn=new Date();
        this.ergebnis1=-1;
        this.ergebnis2=-1;
    }
    
    public Spiel(Long spielid, int ergebnis1, int ergebnis2) {
        this.spielid = spielid;
        this.ergebnis1 = ergebnis1;
        this.ergebnis2 = ergebnis2;
        this.spielbeginn=new Date();
        this.ergebnis1=-1;
        this.ergebnis2=-1;
    }
    public Spiel(Date spielbeginn)    
    {
        this.spielbeginn=spielbeginn;
        this.ergebnis1=-1;
        this.ergebnis2=-1;
    }
    public Long getSpielid() {
        return spielid;
    }

    /**
     * Mit dieser Methode werden die Ergebnisse für dieses Spiel eingetragen.
     * (in einer Gruppenphase wird, einfach das Ergebnis eingetragen,
     * in einem KO-System steigt die gwinnende Mannschaft auf.
     * 
     * @param erg1 Das Ergebnis, das Mannschaft 1 erzielt hat.
     * @param erg2 Das Ergebnis, das Mannschaft 2 erzielt hat.
     * @param connection Die Datenbank-Connection, mit der Persistiert wird
     * @return ob es funktioniert hat (true oder false)
     */
    public boolean ergebniseintragen(int erg1, int erg2)
    {
        //man kann keine minus-punkte haben...
        //Doch darf es weil -1:-1 ein nicht gespieltes spiel ist...
        //if(erg1<0 || erg2<0)
        //    return false;
        //Schauen was es für eine Phase ist
        if(this.phase.getGruppen().size()<1)
        {
            //es ist eine KO-Phase
            //in der ko-phase kann es kein unentschieden mehr geben
            if(erg1==erg2)
                return false;
            //ergebnis eintragen
            this.ergebnis1=erg1;
            this.ergebnis2=erg2;
                        
            Node n= this.node;
            //Schauen ob es eh nicht das Finale ist...
            if(n.getParent()!=null)
                //ob es nicht das kleine Finale ist...
                if(n.getParent().getCenter()==null||!n.getParent().getCenter().equals(n))
                {
                    //Wer hat gewonnen?
                    if(erg1>erg2)
                    {
                        //schauen ob man im nächsten Spiel Mannschaft 1 oder Mannschaft 2 ist
                        if(n.getParent().getLeft().equals(n))
                        //ist es Mannschaft 1
                        {
                            n.getParent().getSpiel().setMannschaft1(this.getMannschaft1());
                            //Falls es das letzte Spiel vor dem Finale ist
                            if(n.getParent().getParent()==null)
                            {
                                //kommt der Verlierer ins kleine Finale
                                n.getParent().getCenter().getSpiel().setMannschaft1(this.getMannschaft2());
                            }
                        }
                        else
                        //ist es Mannschaft 2
                        {
                            n.getParent().getSpiel().setMannschaft2(this.getMannschaft1());
                            //Falls es das letzte Spiel vor dem Finale ist
                            if(n.getParent().getParent()==null)
                            {
                                //kommt der Verlierer ins kleine Finale
                                n.getParent().getCenter().getSpiel().setMannschaft2(this.getMannschaft2());
                            }
                        }
                    }
                    else
                    {
                        //schauen ob man im nächsten Spiel Mannschaft 1 oder Mannschaft 2 ist
                        if(n.getParent().getLeft().equals(n))
                        //ist es Mannschaft 1
                        {
                            n.getParent().getSpiel().setMannschaft1(this.getMannschaft2());
                            //Falls es das letzte Spiel vor dem Finale ist
                            if(n.getParent().getParent()==null)
                            {
                                //kommt der Verlierer ins kleine Finale
                                n.getParent().getCenter().getSpiel().setMannschaft1(this.getMannschaft1());
                            }
                        }
                        else
                        //ist es Mannschaft 2
                        {
                            n.getParent().getSpiel().setMannschaft2(this.getMannschaft2());
                            //Falls es das letzte Spiel vor dem Finale ist
                            if(n.getParent().getParent()==null)
                            {
                                //kommt der Verlierer ins kleine Finale
                                n.getParent().getCenter().getSpiel().setMannschaft2(this.getMannschaft1());
                            }
                        }
                    }
                }
            //Alles Speichern
            this.persist();
            if(this.node.getParent()!=null)
            {
                this.node.getParent().getSpiel().persist();
                if(this.node.getParent().getCenter()!=null)
                    this.node.getParent().getCenter().getSpiel().persist();
            }
                
            
            return true;
        }
        else
        {
            //es ist eine Gruppenphase
            this.ergebnis1=erg1;
            this.ergebnis2=erg2;
            this.persist();
            return true;
        }
    }
    public void setSpielid(Long spielid) {
        this.spielid = spielid;
    }
    
    
    public int getErgebnis1() {
        return ergebnis1;
    }

    /**
     * @deprecated Ergebnisse sollten über die Methode 
     * "ErgebnisEintragen(..)" gemacht werden!
     * @param ergebnis1 Das ergebnis für Mannschaft 1
     */
    public void setErgebnis1(int ergebnis1) {
        this.ergebnis1 = ergebnis1;
    }

    public int getErgebnis2() {
        return ergebnis2;
    }
    /**
     * @deprecated Ergebnisse sollten über die Methode 
     * "ErgebnisEintragen(..)" gemacht werden!
     * @param ergebnis2 Das ergebnis für Mannschaft 2
     */
    public void setErgebnis2(int ergebnis2) {
        this.ergebnis2 = ergebnis2;
    }

    public Date getSpielbeginn() {
        return spielbeginn;
    }

    public void setSpielbeginn(Date spielbeginn) {
        this.spielbeginn = spielbeginn;
    }
    
    public Long getSpielbeginnLong() {
        return spielbeginn.getTime();
    }

    public void setSpielbeginnLong(Long spielbeginn) {
        this.spielbeginn.setTime(spielbeginn);
    }

    public int getPlatz() {
        return platz;
    }
    
    public void setPlatz(int platz) {
        this.platz = platz;
    }
    
    public model.Gruppe getGruppe() {
        return gruppe;
    }

    public void setGruppe(model.Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public model.Mannschaft getMannschaft1() {
        return mannschaft1;
    }

    public void setMannschaft1(model.Mannschaft mannschaft1) {
        this.mannschaft1 = mannschaft1;
    }

    public model.Mannschaft getMannschaft2() {
        return mannschaft2;
    }

    public void setMannschaft2(model.Mannschaft mannschaft2) {
        this.mannschaft2 = mannschaft2;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spielid != null ? spielid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spiel)) {
            return false;
        }
        Spiel other = (Spiel) object;
        if ((this.spielid == null && other.spielid != null) || (this.spielid != null && !this.spielid.equals(other.spielid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Spiel[spielid=" + spielid + "]";
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void persist()
    {
        EntityManager em = DBConnection.getEntityManager();
        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
    }
    
    public void delete()
    {
        DBConnection.deleteSpiel(this);
    }
}
