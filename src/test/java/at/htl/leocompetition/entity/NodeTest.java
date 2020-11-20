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
        Node node = new Node((long) 1,null, null,null,team1,team2);

        assertThat(node).isNotNull();
    }


}
