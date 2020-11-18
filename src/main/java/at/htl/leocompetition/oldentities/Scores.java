package at.htl.leocompetition.oldentities;

import leoturniercore.DBConnection;
import view.LeoLookAndFeel;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scores {

    private Phase phase;
    private model.Gruppe gruppe;
    private model.Mannschaft mannschaft;
    private int points;
    private int goals_shot;
    private int goals_got;
    private EntityManager em;
    private Connection conn;

    /**
     * @return the phase
     */
    public Scores() {
    }

    public Scores(Phase phase)
    {
        setPhase(phase);
        this.em = DBConnection.getEntityManager();
        try {
            UIManager.setLookAndFeel(new LeoLookAndFeel().getLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Scores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Scores(model.Gruppe gruppe)
    {
        setGruppe(gruppe);
        this.em = DBConnection.getEntityManager();
        try {
            UIManager.setLookAndFeel(new LeoLookAndFeel().getLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Scores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List getScoresByPhase()
    {
        if (this.phase == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Phase nicht gesetzt!", "Fehler", JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            try {
                conn = null;
                List<Scores> resultList = new LinkedList<Scores>();
                try{
                    Class.forName ("oracle.jdbc.OracleDriver").newInstance();
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.htl-leonding.ac.at:1521:ORACLEDB", "hd070103", "xvp8t14zss");
                } catch(Exception e){}

                ResultSet rs = conn.createStatement().executeQuery("select s.Phase_Phasenid phasenid, s.Gruppe_Gruppenid gruppenid," +
                        " s.Mannschaftsid, m.mannschaftsname, s.points, s.goals_shot, s.goals_got from SCORES_M1M2 s, " +
                        "Mannschaft m where s.mannschaftsid=m.MANNSCHAFTSID and s.Phase_Phasenid=" + this.phase.getPhasenid() +
                        " order by points desc, (goals_shot - goals_got) desc, goals_shot desc");
                while (rs.next()) {
                    Scores score = new Scores();
                    score.setPhase((Phase) (em.createQuery("select x from Phase x where phasenid=" + rs.getString(1)).getResultList()).get(0));
                    score.setGruppe((model.Gruppe) (em.createQuery("select x from Gruppe x where gruppenid=" + rs.getString(2)).getResultList()).get(0));
                    score.setMannschaft((model.Mannschaft) (em.createQuery("select x from Mannschaft x where mannschaftsid=" + rs.getString(3)).getResultList()).get(0));
                    score.setPoints(Integer.parseInt(rs.getString(5)));
                    score.setGoals_shot(Integer.parseInt(rs.getString(6)));
                    score.setGoals_got(Integer.parseInt(rs.getString(7)));
//                    System.out.println("Phase: " + score.getPhase().getPhasenid());
//                    System.out.println("Punkte: " + score.getPoints());
//                    System.out.println("Differenz: " + (score.getGoals_shot() - score.getGoals_got()));
//                    System.out.println("Geschossen: " + score.getGoals_shot());
                    resultList.add(score);
                }
                conn.createStatement().close();
                return resultList;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public List getScoresByGruppe()
    {
        if (this.gruppe == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Gruppe nicht gesetzt!", "Fehler", JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            try {
                List<Scores> resultList = new LinkedList<Scores>();
                ResultSet rs = conn.createStatement().executeQuery("select s.Phase_Phasenid phasenid, s.Gruppe_Gruppenid gruppenid, s.Mannschaftsid, m.mannschaftsname, s.points, s.goals_shot, s.goals_got from SCORES_M1M2 s, Mannschaft m where s.mannschaftsid=m.MANNSCHAFTSID and s.Gruppe_Gruppenid=" + this.gruppe.getGruppenid() + " order by points desc, (goals_shot - goals_got) desc, goals_shot desc");
                while (rs.next()) {
                    Scores score = new Scores();
                    score.setPhase((Phase) (em.createQuery("select x from Phase x where phasenid=" + rs.getString(1)).getResultList()).get(0));
                    score.setGruppe((model.Gruppe) (em.createQuery("select x from Gruppe x where gruppenid=" + rs.getString(2)).getResultList()).get(0));
                    score.setMannschaft((model.Mannschaft) (em.createQuery("select x from Mannschaft x where mannschaftsid=" + rs.getString(3)).getResultList()).get(0));
                    score.setPoints(Integer.parseInt(rs.getString(5)));
                    score.setGoals_shot(Integer.parseInt(rs.getString(6)));
                    score.setGoals_got(Integer.parseInt(rs.getString(7)));
                    System.out.println("Phase: " + score.getPhase().getPhasenid());
                    System.out.println("Punkte: " + score.getPoints());
                    System.out.println("Differenz: " + (score.getGoals_shot() - score.getGoals_got()));
                    System.out.println("Geschossen: " + score.getGoals_shot());
                    resultList.add(score);
                }
                conn.createStatement().close();
                return resultList;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public Phase getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    /**
     * @return the gruppe
     */
    public model.Gruppe getGruppe() {
        return gruppe;
    }

    /**
     * @param gruppe the gruppe to set
     */
    public void setGruppe(model.Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    /**
     * @return the mannschaft
     */
    public model.Mannschaft getMannschaft() {
        return mannschaft;
    }

    /**
     * @param mannschaft the mannschaft to set
     */
    public void setMannschaft(model.Mannschaft mannschaft) {
        this.mannschaft = mannschaft;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the goals_shot
     */
    public int getGoals_shot() {
        return goals_shot;
    }

    /**
     * @param goals_shot the goals_shot to set
     */
    public void setGoals_shot(int goals_shot) {
        this.goals_shot = goals_shot;
    }

    /**
     * @return the goals_got
     */
    public int getGoals_got() {
        return goals_got;
    }

    /**
     * @param goals_got the goals_got to set
     */
    public void setGoals_got(int goals_got) {
        this.goals_got = goals_got;
    }
}
