package at.htl.leocompetition.control;


import at.htl.leocompetition.entity.Node;
import at.htl.leocompetition.entity.Phase;
import at.htl.leocompetition.entity.Team;

public class InitBean {

    public InitBean() {
        runPrototype();
    }

    private void runPrototype() {



        Team team1AHEL = new Team("1AHEL", 1);
        Team team1AHIF = new Team("1AHIF", 2);
        Team team1AHITM = new Team("1AHITM", 3);
        Team team1AHBG = new Team("1AHBG", 4);
        Team team1BHEL = new Team("1BHEL", 5);
        Team team1BHIF = new Team("1BHIF", 6);
        Team team1BHITM = new Team("1BHITM", 7);
        Team team1BHBG = new Team("1BHBG", 8);


    }

    private void init() {

    }

}
