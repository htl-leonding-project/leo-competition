package at.htl.leocompetition.entity;

import leoturniercore.DBConnection;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GRUPPE")
@NamedQueries({@NamedQuery(name = "Gruppe.findByGruppenid", 
                query = "SELECT g FROM Gruppe g WHERE g.gruppenid = :gruppenid"),
                            @NamedQuery(name = "Gruppe.findByGruppenname",
                query = "SELECT g FROM Gruppe g WHERE g.gruppenname = :gruppenname")})

public class Gruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRUPPENID", nullable = false)
    private Long gruppenid;
    @Column(name = "GRUPPENNAME")
    private String gruppenname;
    @JoinTable(name = "MANNSCHAFT_GRUPPE", joinColumns = {@JoinColumn(name = "GRUPPELIST_GRUPPENID",
                referencedColumnName = "GRUPPENID")},
                inverseJoinColumns = {@JoinColumn(name = "MANNSCHAFT_MANNSCHAFTSID",
                referencedColumnName = "MANNSCHAFTSID")})
    @ManyToMany
    private List<model.Mannschaft> mannschaften;
    @OneToMany(mappedBy = "gruppe", cascade=CascadeType.ALL)
    private List<Spiel> spiele;
    @JoinColumn(name = "PHASE_PHASENID", referencedColumnName = "PHASENID")
    @ManyToOne
    private Phase phase;
    

    public Gruppe(Long gruppenid) {
        this.gruppenid = gruppenid;
    }
    
    public Gruppe()
    {
        this.mannschaften=new ArrayList<model.Mannschaft>();
        this.spiele=new ArrayList<Spiel>();
    }

    /**
     * Schaut welche Mannschaften dieser Gruppe weiter in die nächste Phase kommen sollen
     *
     * @see model.Phase.weiterInPhase(...)
     * @param anzahl so viele Mannschaften kommen weiter
     * @param connection der Persistenz-kontext
     * @return die Liste der Mannschaften, die in dieser Gruppe weiter kommen sollen
     */
    public List<model.Mannschaft> weiteringruppe(int anzahl)
    {
        List<model.Mannschaft> weiter =new ArrayList<model.Mannschaft>();
        if(anzahl>0)
        {
            Scores newscore=new Scores(this);
            List<Scores> scores=newscore.getScoresByGruppe();
            int anz=0;
            for(Scores s:scores)
            {
                weiter.add(s.getMannschaft());
                anz++;
                if(anz>=anzahl)
                    break;
            }
        }
        return weiter;
    }
    /**
     * Für diese Gruppe Wird ein Spielplan
     * (Jeder gegen jeden)
     * erzeugt.
     */
    public void erstGruSpPl()
    {
        //int anzSpiele=this.mannschaften.size()*(this.mannschaften.size()-1)/2;
        int[][] spieleints=getfile(this.mannschaften.size());
        
        Spiel s;
        for(int i=0; i<spieleints.length; i++)
        {
            s=new Spiel(this.mannschaften.get(spieleints[i][0]),this.mannschaften.get(spieleints[i][1]));
            this.mannschaften.get(spieleints[i][0]).getSpielList1().add(s);
            this.mannschaften.get(spieleints[i][1]).getSpielList2().add(s);
            
            this.spiele.add(s);
            s.setGruppe(this);
        }
    }
    
    /**
     * Eine Spielplanmöglichkeit für die angegeben Anzahl von Mannschaften
     * wird eingelesen und zurückgegeben.
     * 
     * @param anzmannsch für diese anzahl von Mannschaften wird eingelesen.
     * @return Die Spiele in folge, jeweils mit dem Index der jeweiligen Mannschaft.
     */
    private int[][] getfile(int anzmannsch)
    {
        List<String> list1=new ArrayList<String>();
        try {
            String filename = ".//Spielplaene//Teams ("+String.valueOf(anzmannsch)+").txt";

            BufferedReader rd = new BufferedReader(new FileReader(filename));

            String line = null;
            while ((line = rd.readLine()) != null) {
                if(!(line.startsWith("#")||line.startsWith("Round")||line.isEmpty()||line.contains("Bye")))
                {
                    list1.add(line);
                    //System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int [][] spiellist =new int [list1.size()][2];
        String [] help,help2;
        for (int i=0; i<list1.size(); i++)
        {
            //die Zahlen aus den Strings herausfiltern
            help=list1.get(i).split("\t");
            help2=help[1].split(" ");
            spiellist[i][0]=Integer.valueOf(help2[1])-1;
            help2=help[2].split(" ");
            spiellist[i][1]=Integer.valueOf(help2[1])-1;
        }
        return spiellist;
    }

    public Long getGruppenid() {
        return gruppenid;
    }

    public void setGruppenid(Long gruppenid) {
        this.gruppenid = gruppenid;
    }

    public String getGruppenname() {
        return gruppenname;
    }

    public void setGruppenname(String gruppenname) {
        this.gruppenname = gruppenname;
    }

    public List<model.Mannschaft> getMannschaften() {
        return mannschaften;
    }

    public void setMannschaften(List<model.Mannschaft> mannschaften) {
        this.mannschaften = mannschaften;
        for (Object m : mannschaften) {
            ((model.Mannschaft) m).getGruppen().add(this);
            ((model.Mannschaft) m).persist();
        }
    }

    public List<Spiel> getSpiele() {
        return spiele;
    }

    public void setSpiele(List<Spiel> spiele) {
        this.spiele = spiele;
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
        hash += (gruppenid != null ? gruppenid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruppe)) {
            return false;
        }
        Gruppe other = (Gruppe) object;
        if ((this.gruppenid == null && other.gruppenid != null) || (this.gruppenid != null && !this.gruppenid.equals(other.gruppenid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Gruppe[gruppenid=" + gruppenid + "]";
    }
    
    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }
    
    public void delete()
    {
        DBConnection.deleteGruppe(this);
    }
}
