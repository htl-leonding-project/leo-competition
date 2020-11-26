package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Order(10)
    @Test
    public void createNode_Test(){
        LocalDate date = LocalDate.parse("2017-12-15");

        Competition competition = new Competition((long) 1,date);

        ArrayList<Node> nodelist = new ArrayList<>();
        Team team1 = new Team((long) 1,"1AHITM");
        Team team2 = new Team((long) 1, "1AHIF");
        Phase phase = new Phase((long)1, "Phase1", nodelist,competition);

        Match match = new Match((long) 1, team1, team2, 1, 1, phase);


        Node node = new Node((long) 1,null, null,null,match,phase);

        assertThat(node).isNotNull();
    }

    @Order(20)
    @Test
    public void toStringNode_Test(){
        LocalDate date = LocalDate.parse("2017-12-15");

        Competition competition = new Competition((long) 1,date);

        ArrayList<Node> nodelist = new ArrayList<>();
        Team team1 = new Team((long) 1,"1AHITM");
        Team team2 = new Team((long) 1, "1AHIF");
        Phase phase = new Phase((long)1, "Phase1", nodelist,competition);

        Match match = new Match((long) 1, team1, team2, 1, 1, phase);


        Node node = new Node((long) 1,null, null,null,match,phase;

        assertThat(node.toString()).isEqualTo("Node{nodeId=1, rightnode=null, leftNode=null, parentNode=null, " +
                "team1=Team{teamName='1AHITM', teamId=1}, team2=Team{teamName='1AHIF', teamId=1}, phase=null}"
        );
    }
}
