package at.htl.leocompetition.entity;

import leoturniercore.*;

import javax.persistence.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "PHASE")
@NamedQueries({@NamedQuery(name = "Phase.findByPhasenid", query = "SELECT p FROM Phase p WHERE p.phasenid = :phasenid"),
                @NamedQuery(name = "Phase.findByPhasenname", query = "SELECT p FROM Phase p WHERE p.phasenname = :phasenname")})
public class Phase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PHASENID", nullable = false)
    private Long phasenid;
    @Column(name = "PHASENNAME")
    private String phasenname;
    @Column(name = "SPIELZEITSPIEL")
    private int spielzeitspiel;
    @Column(name = "SPIELFLAECHEN")
    private int spielflaechen;
    @Column(name = "PAUSESPIELE")
    private int pausespiele;
    @Column(name = "PAUSEPHASE")
    private int pausephase;
    @Column(name = "GRUPPENANZAHL")
    private int gruppenanzahl;
    @Column(name = "MANNSCHAFTSANZAHL")
    private int mannschaftsanzahl;
    @Column(name = "SPIELSYSTEM")
    private String spielsystem;
    @Column(name = "GRUPPEN")
    private String gruppenkonstellation;
    @Column(name = "AUFSMANN")
    private int aufsmann;
    @Column(name = "BESTVER")
    private int bestver;
    @Column(name = "AUFSMANNGR")
    private int aufsmanngr;
    @ManyToMany(mappedBy = "phasen", cascade = CascadeType.ALL)
    private List<model.Mannschaft> mannschaften;
    @OneToMany(mappedBy = "phase")
    private List<model.Spiel> spiele;
    @OneToMany(mappedBy = "phase")
    private List<model.Gruppe> gruppen;
    @JoinColumn(name = "TURNIER_TURNIERID", referencedColumnName = "TURNIERID")
    @ManyToOne
    private model.Turnier turnier;
    @OneToMany(mappedBy = "phase")
    private List<Node> nodeList;

    public Phase() {
        this.mannschaften = new ArrayList<model.Mannschaft>();
        this.spiele = new ArrayList<model.Spiel>();
        this.gruppen = new ArrayList<model.Gruppe>();
        this.nodeList = new ArrayList<Node>();
        this.pausephase = 0;
        this.pausespiele = 0;
        this.spielflaechen = 0;
        this.spielzeitspiel = 0;
    }

    /**
     *
     * @param anzahlProGruppe soviele Mannschaften sollen pro Gruppe weiter kommen
     * @param anzahlBesteVerlierer soviele "Beste Verlierer" werden dann noch mitgenommen
     * @param connection Persitenz-Kontext
     * @return
     */
    public List<model.Mannschaft> weiterinPhase(int anzahlProGruppe, int anzahlBesteVerlierer) {
        List<model.Mannschaft> weiter = new ArrayList<model.Mannschaft>();

        //von jeder Gruppe die besten nehmen
        for (model.Gruppe g : this.gruppen) {
            weiter.addAll(g.weiteringruppe(anzahlProGruppe));
        }
        //und dann noch die besten Verlierer
        if (anzahlBesteVerlierer > 0) {
            int anzahl = 0;
            model.Scores newscore = new model.Scores(this);
            List<model.Scores> scores = newscore.getScoresByPhase();

            for (model.Scores s : scores) {
                if (!weiter.contains(s.getMannschaft())) {
                    weiter.add(s.getMannschaft());
                    anzahl++;
                    if (anzahl >= anzahlBesteVerlierer) {
                        break;
                    }
                }
            }
        }
        //Sortieren für Kreuzspiele
        //Achtung!: Wenn beste verlierer mitgenommen werden
        //werden keine Kreuzspiele Garantiert!
        List<model.Mannschaft> weitersort = new ArrayList<model.Mannschaft>();
        for (int i = 0; i < (int) (weiter.size() / 2); i++) {
            weitersort.add(weiter.get(i));
            System.out.println(weiter.get(i).getMannschaftsname());
            weitersort.add(weiter.get(weiter.size() - (1 + i)));
            System.out.println(weiter.get(weiter.size() - (1 + i)).getMannschaftsname());
        }
        return weitersort;
    }

    /**
     * Es werden für spiele dieser phase Zufallsergebnisse eingetragen
     * und diese ausgegeben.
     * 
     * @deprecated Diese Methode dient nur zu Testzwecken!
     * 
     * @param connection Die DB-Connection, mit der persistiert wird
     */
    public void zufallsergebnisseeintragen() {
        Random r = new Random(System.currentTimeMillis());
        int ind = 0;

        for (model.Spiel s : this.spiele)
        {
            while (!s.ergebniseintragen(r.nextInt(5), r.nextInt(5))) 
            {

            }
            System.out.println(s.getMannschaft1().getMannschaftsname() + " - " + s.getMannschaft2().getMannschaftsname() + " " + s.getErgebnis1() + ":" + s.getErgebnis2());
            System.out.flush();
            ind++;
        }
    }

    /**
     * Mit dieser Methode werden die übergebene Anzahl
     * an Gruppen erzeugt und alle Mannschaften der
     * aktuellen Phase auf sie verteilt.
     * 
     * @param anzahlgruppen Wieviele Gruppen es geben soll.
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    public void auslosen(int anzahlgruppen)
    {
        this.initGruppen(anzahlgruppen);

        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().flush();
        DBConnection.getEntityTransaction().commit();

        try
        {
            DBConnection.getEntityManager().refresh(this);
        } 
        catch (javax.persistence.EntityNotFoundException ex)
        {
            System.out.println("Fehler bei refresh!");
        }

        int hilf = 0;
        int hilf2 = this.getMannschaften().size();
        List<model.Mannschaft> nochuebrig = new ArrayList<model.Mannschaft>(this.mannschaften);
//        nochuebrig = connection.getEntityManager().createQuery("select x from Mannschaft x where Phase_Phasenid="+this.getPhasenid()).getResultList();
        int i;
        //Gesetzte Mannschaften verteilen
        for (i = 0; i < hilf2; i++) {
            if (nochuebrig.get(i - hilf).isGesetzt()) {
                this.gruppen.get(hilf % this.gruppen.size()).getMannschaften().add(nochuebrig.get(i - hilf));
                nochuebrig.get(i - hilf).getGruppen().add(this.gruppen.get(hilf % this.gruppen.size()));

                //this.gruppen.get(hilf%this.gruppen.size()).persist();
                nochuebrig.get(i - hilf).persist();

                nochuebrig.remove(i - hilf);
                hilf++;
            }
        }
        Random r = new Random();
        //den Rest auslosen

        //solange noch mannschaften da sind werden sie verteilt
        while (nochuebrig.size() > 0)
        {
            //abwechselnd wird zu jeder gruppe immer eine hinzugefügt

            //beim ersten mal muss mit derjenigen gruppe angefangen werden,
            //die bei den gesetzten Mannschaften am wenigsten erhalten hat
            for (i = hilf % this.gruppen.size(); i < this.gruppen.size(); i++) {
                if (nochuebrig.size() <= 0) {
                    break;
                }
                //eine zufällige, noch nicht vergebene Mannschaft wird ermittelt
                hilf = r.nextInt(nochuebrig.size());
                //sie wird zur gruppe hinzugefügt
                this.gruppen.get(i).getMannschaften().add(nochuebrig.get(hilf));
                nochuebrig.get(hilf).getGruppen().add(this.gruppen.get(i));

                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PERSISTIEREN
                //this.gruppen.get(i).persist();
                System.out.println("Mannschaftsname: " + nochuebrig.get(hilf).getMannschaftsname());
                nochuebrig.get(hilf).persist();

                //und aus dem pool, der noch verfügbaren Mannschaften entfernt
                nochuebrig.remove(hilf);
            }
            //beim 2. durchlauf soll natürlich wieder bei der ersten Gruppe (index=0) angefangen werden
            hilf = 0;
        }
        //die Gruppen ausgeben

        for (int j = 0; j < this.gruppen.size(); j++)
        {
            System.out.println("\nGruppe: " + (j + 1));
            for (int k = 0; k < this.gruppen.get(j).getMannschaften().size(); k++) {
                System.out.println(this.gruppen.get(j).getMannschaften().get(k).getMannschaftsname());
            }
        }
    }

    /**
     * Mit dieser Methode wird die angegebene Anzahl an Gruppen generiert.
     * 
     * @param anzahlgruppen diese Anzahl an Gruppen wird generiert.
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    public void initGruppen(int anzahlgruppen)
    {
        String[] gruppenbez = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < anzahlgruppen; i++) {
            model.Gruppe gr = new model.Gruppe();
            this.gruppen.add(gr);
            gr.setPhase(this);
            if (i > 25) {
                gr.setGruppenname("Gruppe " + i);
            } else {
                gr.setGruppenname("Gruppe " + gruppenbez[i]);
            }
            gr.persist();
        }
    }

    /**
     * Der Spielplan für die aktuelle Phase wird für
     * die in der Phase eingetragenen Bedingungen erstellt.
     * 
     * Es wird entweder ein Gruppen-System-Spielplan (in jeder Gruppe
     * jeder gegen jeden) oder ein KO-System-Spielplan (KO-Baum)
     * erstellt, je nachdem ob es in dieser Phase Guppen gibt oder nicht.
     * 
     * @param startzeit Die Startzeit für diese Phase.
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    public void erstSpielplan(GregorianCalendar startzeit) {
        if (this.gruppen.size() > 0) //Erstelle Spielplan für Gruppenphase
        {
            this.erstSpPlGrPh(this.spielflaechen, startzeit, this.spielzeitspiel, this.pausespiele);
        } else //Erstelle Spielplan für KO-System
        {
            this.erstSpPlKOSys(this.spielflaechen, startzeit, this.spielzeitspiel, this.pausespiele);
        }
    }

    /**
     * Der Spielplan für die aktuelle Phase wird für
     * die übergebenen Bedingungen erstellt.
     * Diese Bedingungen werden in die Phase eingetragen.
     * 
     * Es wird entweder ein Gruppen-System-Spielplan (in jeder Gruppe
     * jeder gegen jeden) oder ein KO-System-Spielplan (KO-Baum)
     * erstellt, je nachdem ob es in dieser Phase Guppen gibt oder nicht.
     * 
     * @param anzahlplaetze auf wievielen Spielflächen gespielt wird
     * @param startzeit startzeit Die Startzeit für diese Phase.
     * @param minprospiel wie lange ein Spiel dauern soll
     * @param minpause wie lange die Pause zwischen den Spielen sein soll
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    public void erstSpielplan(int anzahlplaetze, GregorianCalendar startzeit, int minprospiel, int minpause)
    {
        //da die Werte übergeben wurden, werden diese werte in dieser Phase ersetzt...
        System.out.println("Startzeit: " + startzeit);
        this.spielflaechen = anzahlplaetze;
        this.spielzeitspiel = minprospiel;
        this.pausespiele = minpause;
        //wenn es Gruppen gibt ist es ein Gruppenspiel,
        //sonst ist es ein KO-System
        if (this.gruppen.size() > 0) //Erstelle Spielplan für Gruppenphase
        {
            this.erstSpPlGrPh(anzahlplaetze, startzeit, minprospiel, minpause);
        } else //Erstelle Spielplan für KO-System
        {
            this.erstSpPlKOSys(anzahlplaetze, startzeit, minprospiel, minpause);
        }
    }

    /**
     * Ein Spielplan wird für eine Gruppenphase erstellt 
     * (in jeder Gruppe, Jeder gegen Jeden)
     * 
     * @param anzahlplaetze auf wievielen Spielflächen gespielt wird
     * @param startzeit startzeit Die Startzeit für diese Phase.
     * @param minprospiel wie lange ein Spiel dauern soll
     * @param minpause wie lange die Pause zwischen den Spielen sein soll
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    private void erstSpPlGrPh(int anzahlplaetze, GregorianCalendar startzeit, int minprospiel, int minpause)
    {
        //Den Spielplan für jede Gruppe generieren
        for (int i = 0; i < this.gruppen.size(); i++)
        {
            this.gruppen.get(i).erstGruSpPl();
        }
        //Die Spielpläne zusammenfügen
        boolean fertig = false;
        int i = 0;
        while (!fertig) {
            fertig = true;
            for (int j = 0; j < this.gruppen.size(); j++) {
                if (i < this.gruppen.get(j).getSpiele().size()) {
                    this.spiele.add(this.gruppen.get(j).getSpiele().get(i));
                    this.gruppen.get(j).getSpiele().get(i).setPhase(this);
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PERSISTIEREN
                    //this.persist();
                    this.gruppen.get(j).getSpiele().get(i).persist();
                    fertig = false;
                }
            }
            i++;
        }

        //Die Startzeiten der Spiele berechnen und plaetzte zuteilen
        GregorianCalendar z = startzeit;
        int j = 0;
        while (true)
        {
            for (i = 0; i < anzahlplaetze; i++)
            {
                //wenn einzelne gruppen mehr spiele haben, müssen diese am
                //Schluss drangehängt werden, ohne dass mannschaften 2 mal gleichzeitig spielen
                if (j > 0) {
                    model.Mannschaft aktmann1 = this.spiele.get(j).getMannschaft1();
                    model.Mannschaft aktmann2 = this.spiele.get(j).getMannschaft2();
                    boolean passt = true;
                    int k = j - 1;
                    while (this.spiele.get(k).getSpielbeginnLong() == z.getTimeInMillis()) {
                        model.Mannschaft kmann1 = this.spiele.get(k).getMannschaft1();
                        model.Mannschaft kmann2 = this.spiele.get(k).getMannschaft2();

                        //wenn eine mannschaft 2 mal vorkommt, würde sie doppelt spielen
                        if (aktmann1 == kmann1 || aktmann1 == kmann2 ||
                                aktmann2 == kmann1 || aktmann2 == kmann2) {
                            passt = false;
                            break;
                        }
                        k--;
                        if (k < 0) {
                            break;
                        }
                    }
                    if (!passt) {
                        break;
                    }
                }
                this.spiele.get(j).setSpielbeginnLong(z.getTimeInMillis());
                this.spiele.get(j).setPlatz(i);
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!PERSISTIEREN
                this.spiele.get(j).persist();
                //---------------------------------Eventuell Mannschaften Persistieren....

                //Zur überprüfung ausgeben
                model.Spiel ausg = this.spiele.get(j);
                System.out.println("Platz: " + (ausg.getPlatz() + 1) + " Zeit: " + z.get(Calendar.HOUR) + ":" + z.get(Calendar.MINUTE) + ", Mannschaften: " + ausg.getMannschaft1().getMannschaftsname() + "-" + ausg.getMannschaft2().getMannschaftsname());
                j++;
                if (j >= this.spiele.size()) {
                    break;
                }
            }

            if (j >= this.spiele.size()) {
                break;
            }

            z.setTimeInMillis(z.getTimeInMillis() + (minprospiel + minpause) * 60000);
        }
    }

    /**
     * Es wird ein typischer KO-Baum mit Finale und kleinem Finale erstellt.
     * 
     * @param anzahlplaetze auf wievielen Spielflächen gespielt wird
     * @param startzeit startzeit Die Startzeit für diese Phase.
     * @param minprospiel wie lange ein Spiel dauern soll
     * @param minpause wie lange die Pause zwischen den Spielen sein soll
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    private void erstSpPlKOSys(int anzahlplaetze, GregorianCalendar startzeit, int minprospiel, int minpause)
    {
        //mal die Spiele die man braucht berechnen
        try {
            DBConnection.getEntityManager().refresh(this);
        } catch (Exception ex) {
            System.out.println("Fehler bei Refresh!");
        }
        int hilf = 1;
        while (true) {
            hilf *= 2;
            for (int i = 0; i < hilf; i++) {
                model.Spiel sp = new model.Spiel();
                sp.setPhase(this);
                this.spiele.add(0, sp);
            }
            if (hilf * 2 >= this.getMannschaften().size()) {
                break;
            }
        }
        int anzahlrunde1 = hilf;
        //Startzeiten berechnen
        GregorianCalendar z = startzeit;
        //der index des aktuellen spiels
        int spielnr = 0;
        while (true) {
            int i = 0;
            //die anzahl der spiele pro Runde...
            System.out.println("neue Runde:");
            while (i < hilf) {
                //die anzahl der plaetze...
                for (int j = 0; j < anzahlplaetze; j++) {
                    if (i >= hilf || spielnr >= this.getSpiele().size()) {
                        break;
                    }
                    this.spiele.get(spielnr).setSpielbeginnLong(z.getTimeInMillis());
                    this.spiele.get(spielnr).setPlatz(j);

                    //Ausgabe:
                    System.out.println("Spiel nr: " + ((int) spielnr + 1) + " Zeit: " + z.get(GregorianCalendar.HOUR_OF_DAY) + ":" + z.get(GregorianCalendar.MINUTE) + " Platz: " + j);
                    i++;
                    spielnr++;
                }
                z.setTimeInMillis(z.getTimeInMillis() + (minprospiel + minpause) * 60000);
                if (i >= hilf || spielnr >= this.getSpiele().size()) {
                    break;
                }
            }
            hilf /= 2;
            //TODO: Wegmocha!!!!!!!!
            if (hilf == 0) {
                hilf = 1;
            }

            if (spielnr >= this.getSpiele().size()) {
                break;
            }
        }
        //das finale, und spiel um Platz 3 und 4 noch dazu...
        //platz 3/4
        model.Spiel sp = new model.Spiel(new Timestamp(z.getTimeInMillis()));
        sp.setPhase(this);
        this.spiele.add(sp);

        //Ausgabe:
        System.out.println("Spiel um Platz 3: " + z.get(GregorianCalendar.HOUR) + ":" + z.get(GregorianCalendar.MINUTE));

        z.setTimeInMillis(z.getTimeInMillis() + (minprospiel + minpause) * 60000);
        sp = new model.Spiel(new Timestamp(z.getTimeInMillis()));
        sp.setPhase(this);
        this.spiele.add(sp);

        for (Object s : spiele) {
            ((model.Spiel) s).persist();
            System.out.println("Spielid: " + ((model.Spiel) s).getSpielid());
        }

        //Ausgabe:
        System.out.println("Finale: " + z.get(GregorianCalendar.HOUR) + ":" + z.get(GregorianCalendar.MINUTE));
        spiele = this.getSpiele();
        try {
            DBConnection.getEntityManager().refresh(this);
        } catch (javax.persistence.EntityNotFoundException ex) {
            System.out.println("Fehler bei refresh!");
        }

        //Mannschaften einsetzten (in die erste Runde)
        for (int i = 0; i < anzahlrunde1; i++) {
            this.spiele.get(i).setMannschaft1(this.mannschaften.get(2 * i));
            this.mannschaften.get(2 * i + 1).getSpielList1().add(this.spiele.get(i));

            this.spiele.get(i).setMannschaft2(this.mannschaften.get(2 * i + 1));
            this.mannschaften.get(2 * i + 1).getSpielList1().add(this.spiele.get(i));
        }

        //Persistieren!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        KoTree ko = new KoTree(this.spiele.size());
        ko.addnodestospieleandphase(this.spiele, ko.reverselevelorder(ko.head), this);
        ko.printList(ko.reverselevelorder(ko.head));
    }

    /**
     * Es werden Mannschaften aus einem Textfile in die aktuelle Phase geladen.
     * 
     * @deprecated Nur zu Testzwecken!
     * @param connection Die DB-Connection, mit der persistiert wird.
     */
    public void loadMannschaften()
    {
        model.Mannschaft m;
        BufferedReader br = null;
        try {
            File file = new File(".//Mannschaften.txt");
            br = new BufferedReader(new FileReader(file));
            String z = "";
            while ((z = br.readLine()) != null) {
                String[] split_zeile = z.split("::");
                String[] split_zeile_2 = split_zeile[2].split(":");

                m = new model.Mannschaft(split_zeile[0], split_zeile[1].equals("JA"));

                this.mannschaften.add(m);
                m.getPhasen().add(this);
                m.setTurnier(this.getTurnier());

                m.persist();

                model.Spieler sp;
                for (int i = 0; i < split_zeile_2.length; i++) {
                    String[] hilf = split_zeile_2[i].split(" ");
                    if (hilf.length < 2) {
                        sp = new model.Spieler(hilf[0], "");
                    } else {
                        sp = new model.Spieler(hilf[0], hilf[1]);
                    }
                    m.getSpieler().add(sp);
                    sp.setMannschaft(m);

                    sp.persist();
                }
                m.persist();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Phase(Long phasenid) {
        this.phasenid = phasenid;
    }

    public Long getPhasenid() {
        return phasenid;
    }

    public void setPhasenid(Long phasenid) {
        this.phasenid = phasenid;
    }

    public String getPhasenname() {
        return phasenname;
    }

    public void setPhasenname(String phasenname) {
        this.phasenname = phasenname;
    }

    public List<model.Mannschaft> getMannschaften() {
        return mannschaften;
    }

    public void setMannschaften(List<model.Mannschaft> mannschaften)
    {
        this.mannschaften = mannschaften;
        for (Object m : mannschaften) {
            ((model.Mannschaft) m).getPhasen().add(this);
            ((model.Mannschaft) m).persist();
        }
    }

    public List<model.Spiel> getSpiele() {
        return spiele;
    }

    public void setSpiele(List<model.Spiel> spiele) {
        this.spiele = spiele;
    }

    public List<model.Gruppe> getGruppen() {
        return gruppen;
    }

    public void setGruppen(List<model.Gruppe> gruppen) {
        this.gruppen = gruppen;
    }

    public model.Turnier getTurnier() {
        return turnier;
    }

    public void setTurnier(model.Turnier turnier) {
        this.turnier = turnier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phasenid != null ? phasenid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phase)) {
            return false;
        }
        Phase other = (Phase) object;
        if ((this.phasenid == null && other.phasenid != null) || (this.phasenid != null && !this.phasenid.equals(other.phasenid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Phase[phasenid=" + phasenid + "]";
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void persist()
    {
        DBConnection.getEntityTransaction().begin();
        DBConnection.getEntityManager().flush();
        DBConnection.getEntityManager().persist(this);
        DBConnection.getEntityTransaction().commit();
    }

    public void delete()
    {
        DBConnection.deletePhase(this);
    }

    public int getSpielzeitspiel() {
        return spielzeitspiel;
    }

    public void setSpielzeitspiel(int spielzeitspiel) {
        this.spielzeitspiel = spielzeitspiel;
    }

    public int getSpielflaechen() {
        return spielflaechen;
    }

    public void setSpielflaechen(int spielflaechen) {
        this.spielflaechen = spielflaechen;
    }

    public int getPausespiele() {
        return pausespiele;
    }

    public void setPausespiele(int pausespiele) {
        this.pausespiele = pausespiele;
    }

    public int getPausephase() {
        return pausephase;
    }

    public void setPausephase(int pausephase) {
        this.pausephase = pausephase;
    }

    public int getGruppenanzahl() {
        return gruppenanzahl;
    }

    public void setGruppenanzahl(int gruppenanzahl) {
        this.gruppenanzahl = gruppenanzahl;
    }

    public int getMannschaftsanzahl() {
        return mannschaftsanzahl;
    }

    public void setMannschaftsanzahl(int mannschaftsanzahl) {
        this.mannschaftsanzahl = mannschaftsanzahl;
    }

    public String getSpielsystem() {
        return spielsystem;
    }

    public void setSpielsystem(String spielsystem) {
        this.spielsystem = spielsystem;
    }

    public String getGruppenkonstellation() {
        return gruppenkonstellation;
    }

    public void setGruppenkonstellation(String gruppenkonstellation) {
        this.gruppenkonstellation = gruppenkonstellation;
    }

    public int getAufsmann() {
        return aufsmann;
    }

    public void setAufsmann(int aufsmann) {
        this.aufsmann = aufsmann;
    }

    public int getBestver() {
        return bestver;
    }

    public void setBestver(int bestver) {
        this.bestver = bestver;
    }

    public int getAufsmanngr() {
        return aufsmanngr;
    }

    public void setAufsmanngr(int aufsmanngr) {
        this.aufsmanngr = aufsmanngr;
    }
}
