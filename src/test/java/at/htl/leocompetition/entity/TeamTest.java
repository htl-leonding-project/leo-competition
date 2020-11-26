package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {

    @Order(10)
    @Test
    public void createTeam_Test(){
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1,date);
        Team team = new Team((long) 1,"team1",competition);

        assertThat(team).isNotNull();
    }

    @Order(20)
    @Test
    public void toString_Test(){
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1,date);
        Team team = new Team((long) 1,"team1",competition);

        assertThat(team.toString()).isEqualTo("Team{teamId=1, teamName='team1', competition=Competition{competitionId=1, date=null}}");
    }
}
