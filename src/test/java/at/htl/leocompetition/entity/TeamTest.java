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
        Team team = new Team((long) 1,"team1");

        assertThat(team).isNotNull();
    }

    @Order(20)
    @Test
    public void toString_Test(){
        Team team = new Team((long) 1,"team1");

        System.out.println(team);
        assertThat(team.toString()).isEqualTo("Team{teamName='team1', teamId=1}");
    }
}
