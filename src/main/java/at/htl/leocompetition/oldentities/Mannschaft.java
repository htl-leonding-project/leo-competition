package at.htl.leocompetition.oldentities;

import leoturniercore.DBConnection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANNSCHAFT")
@NamedQueries({@NamedQuery(name = "Mannschaft.findByMannschaftsid", query = "SELECT m FROM Mannschaft m WHERE m.mannschaftsid = :mannschaftsid"), @NamedQuery(name = "Mannschaft.findByGesetzt", query = "SELECT m FROM Mannschaft m WHERE m.gesetzt = :gesetzt"), @NamedQuery(name = "Mannschaft.findByMannschaftsname", query = "SELECT m FROM Mannschaft m WHERE m.mannschaftsname = :mannschaftsname")})
public class Mannschaft implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MANNSCHAFTSID", nullable = false)
    private Long mannschaftsid;
    @Column(name = "GESETZT", nullable = false)
    private short gesetzt;
    @Column(name = "MANNSCHAFTSNAME")
    private String mannschaftsname;
    @ManyToMany(mappedBy = "mannschaften", cascade=CascadeType.ALL)
    private List<Gruppe> gruppen;
    @JoinTable(name = "MANNSCHAFT_PHASE", joinColumns = {@JoinColumn(name = "MANNSCHAFTLIST_MANNSCHAFTSID", referencedColumnName = "MANNSCHAFTSID")}, inverseJoinColumns = {@JoinColumn(name = "PHASELIST_PHASENID", referencedColumnName = "PHASENID")})
    @ManyToMany
    private List<Phase> phasen;
    @JoinColumn(name = "TURNIER_TURNIERID", referencedColumnName = "TURNIERID")
    @ManyToOne
    private model.Turnier turnier;
    @OneToMany(mappedBy = "mannschaft1", cascade=CascadeType.ALL)
    private List<Spiel> spielList1;
    @OneToMany(mappedBy = "mannschaft2", cascade=CascadeType.ALL)
    private List<Spiel> spielList2;
    @OneToMany(mappedBy = "mannschaft", cascade=CascadeType.ALL)
    private List<Spieler> spieler;


    public Mannschaft()
    { 
        this.spielList1=new ArrayList<Spiel>();
        this.spielList2=new ArrayList<Spiel>();
        this.gruppen=new ArrayList<Gruppe>();
        this.spieler=new ArrayList<Spieler>();
        this.phasen=new ArrayList<Phase>();
    }
    public Mannschaft(Long mannschaftsid) {
        this.mannschaftsid = mannschaftsid;
        this.spielList1=new ArrayList<Spiel>();
        this.spielList2=new ArrayList<Spiel>();
        this.gruppen=new ArrayList<Gruppe>();
        this.spieler=new ArrayList<Spieler>();
        this.phasen=new ArrayList<Phase>();
    }

    public Mannschaft(Long mannschaftsid, short gesetzt) {
        this.mannschaftsid = mannschaftsid;
        this.gesetzt = gesetzt;
        this.spielList1=new ArrayList<Spiel>();
        this.spielList2=new ArrayList<Spiel>();
        this.gruppen=new ArrayList<Gruppe>();
        this.spieler=new ArrayList<Spieler>();
        this.phasen=new ArrayList<Phase>();
    }
    
    public Mannschaft(String name)
    {
        setMannschaftsname(mannschaftsname);
        setGesetzt((short)0);
        setSpielList1(spielList1);
        setSpielList2(spielList2);
        setGruppen(gruppen);
        setSpieler(spieler);
        setPhasen(phasen);
    }
    public Mannschaft(String name,boolean istgesetzt)
    {
        setMannschaftsname(name);
        setGesetzt(istgesetzt);
        setSpielList1(spielList1);
        setSpielList2(spielList2);
        setGruppen(gruppen);
        setSpieler(spieler);
        setPhasen(phasen);
    }
    public Mannschaft(String name,boolean istgesetzt,List<Spieler> spieler)
    {
        setMannschaftsname(mannschaftsname);
        setGesetzt(istgesetzt);
        setSpielList1(spielList1);
        setSpielList2(spielList2);
        setGruppen(gruppen);
        setSpieler(spieler);
        setPhasen(phasen);
    }
    

    public Long getMannschaftsid() {
        return mannschaftsid;
    }

    public void setMannschaftsid(Long mannschaftsid) {
        this.mannschaftsid = mannschaftsid;
    }

    public short getGesetzt() 
    {
        return gesetzt;
    }
    public boolean isGesetzt()
    {
        if(this.gesetzt==1)
            return true;
        else
            return false;
    }
    public void setGesetzt(boolean gesetzt) {
        if(gesetzt)
            this.gesetzt = (short)1;
        else
            this.gesetzt=(short)0;
    }
    public void setGesetzt(short gesetzt) {
        this.gesetzt=gesetzt;
    }
    public String getMannschaftsname() {
        return mannschaftsname;
    }

    public void setMannschaftsname(String mannschaftsname) {
        this.mannschaftsname = mannschaftsname;
    }

    public List<Gruppe> getGruppen() {
        return gruppen;
    }

    public void setGruppen(List<Gruppe> gruppen) {
        this.gruppen = gruppen;
    }

    public List<Phase> getPhasen() {
        return phasen;
    }

    public void setPhasen(List<Phase> phasen) {
        this.phasen = phasen;
    }

    public List<Spiel> getSpielList1() {
        return spielList1;
    }

    public void setSpielList1(List<Spiel> spielList1) {
        this.spielList1 = spielList1;
    }

    public List<Spiel> getSpielList2() {
        return spielList2;
    }

    public void setSpielList2(List<Spiel> spielList2) {
        this.spielList2 = spielList2;
    }

    public List<Spieler> getSpieler() {
        return spieler;
    }

    public void setSpieler(List<Spieler> spieler) {
        this.spieler = spieler;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mannschaftsid != null ? mannschaftsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mannschaft)) {
            return false;
        }
        Mannschaft other = (Mannschaft) object;
        if ((this.mannschaftsid == null && other.mannschaftsid != null) || (this.mannschaftsid != null && !this.mannschaftsid.equals(other.mannschaftsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mannschaft[mannschaftsid=" + mannschaftsid + "]";
    }

    public model.Turnier getTurnier() {
        return turnier;
    }

    public void setTurnier(model.Turnier turnier) {
        this.turnier = turnier;
    }

    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }
    
    public void delete()
    {
        DBConnection.deleteMannschaft(this);
    }
}
