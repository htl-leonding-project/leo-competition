package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Order(10)
    @Test
    public void createPlayer_Test() {

        Team team = new Team((long) 1,"team1");
        Player player = new Player((long) 1, "Kelly Tran", team);

        assertThat(player).isNotNull();
    }

    @Order(10)
    @Test
    public void toString_Test() {
        Team team = new Team((long) 1,"team1");
        Player player = new Player((long) 1, "Kelly Tran",team);

        System.out.println(player);
        assertThat(player.toString()).isEqualTo("Player{playerId=1, name='Kelly Tran', team=Team{teamName='team1', teamId=1}}");
    }
}
