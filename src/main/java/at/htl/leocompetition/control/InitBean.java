package at.htl.leocompetition.control;


import at.htl.leocompetition.entity.Competition;
import at.htl.leocompetition.entity.Node;
import at.htl.leocompetition.entity.Phase;
import at.htl.leocompetition.entity.Team;

public class InitBean {

    public InitBean() {
        runPrototype();
    }

    private void runPrototype() {

        Team team1AHEL = new Team((long) 1,"1AHEL");
        Team team1AHIF = new Team((long) 2,"1AHIF");
        Team team1AHITM = new Team((long) 3,"1AHITM");
        Team team1AHBG = new Team((long) 4,"1AHBG");
        Team team1BHEL = new Team((long) 5,"1BHEL");
        Team team1BHIF = new Team((long) 6,"1BHIF");
        Team team1BHITM = new Team((long) 7,"1BHITM");
        Team team1BHBG = new Team((long) 8,"1BHBG");

        Competition competition = new Competition();




    }

    private void init() {

    }

}
