package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {



    @Order(10)
    @Test
    public void createMatch_Test(){
        List<Node> nodeList = new ArrayList<>();

        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1,date);
        Phase phase = new Phase((long) 1,"phase 1",nodeList,competition);
        Team team1 = new Team((long) 1,"1AHITM");
        Team team2 = new Team((long) 1, "1AHIF");
        Match match = new Match((long) 1,team1,team2,2,1,phase);



        assertThat(match).isNotNull();
    }
}
