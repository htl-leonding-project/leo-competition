package at.htl.leocompetition.control;


import at.htl.leocompetition.entity.Competition;
import at.htl.leocompetition.entity.Node;
import at.htl.leocompetition.entity.Phase;
import at.htl.leocompetition.entity.Team;

import java.time.LocalDate;

public class InitBean {

    public InitBean() {
        runPrototype();
    }

    private void runPrototype() {
        LocalDate date = LocalDate.parse("2017-12-15");

        Team team1AHEL = new Team((long) 1,"1AHEL",new Competition((long) 1,date));
        Team team1AHIF = new Team((long) 2,"1AHIF",new Competition((long) 1,date));
        Team team1AHITM = new Team((long) 3,"1AHITM",new Competition((long) 1,date));
        Team team1AHBG = new Team((long) 4,"1AHBG",new Competition((long) 1,date));
        Team team1BHEL = new Team((long) 5,"1BHEL",new Competition((long) 1,date));
        Team team1BHIF = new Team((long) 6,"1BHIF",new Competition((long) 1,date));
        Team team1BHITM = new Team((long) 7,"1BHITM",new Competition((long) 1,date));
        Team team1BHBG = new Team((long) 8,"1BHBG",new Competition((long) 1,date));

        Competition competition = new Competition();

    }

    private void init() {

    }

}
