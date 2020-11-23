package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Order(10)
    @Test
    public void createNode_Test(){
        Team team1 = new Team((long) 1,"1AHITM");
        Team team2 = new Team((long) 1, "1AHIF");
        Node node = new Node((long) 1,null, null,null,team1,team2, null);

        assertThat(node).isNotNull();
    }

    @Order(20)
    @Test
    public void toStringNode_Test(){
        Team team1 = new Team((long) 1,"1AHITM");
        Team team2 = new Team((long) 1,"1AHIF");
        Node node = new Node((long) 1,null, null,null,team1,team2,null);

        assertThat(node.toString()).isEqualTo("Node{nodeId=1, rightnode=null, leftNode=null, parentNode=null, " +
                "team1=Team{teamName='1AHITM', teamId=1}, team2=Team{teamName='1AHIF', teamId=1}, phase=null}"
        );
    }
}
