package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {

    @Order(10)
    @Test
    public void createTeam_Test(){
        List<Node> nodeList = new ArrayList<>();
        Team team = new Team((long) 1,"team1");

        assertThat(team).isNotNull();
    }
}
