package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Order(10)
    @Test
    public void createPlayer_Test() {
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1,date);
        Team team = new Team((long) 1,"team1", competition);
        Player player = new Player((long) 1, "Kelly Tran", team);

        assertThat(player).isNotNull();
    }

    @Order(10)
    @Test
    public void toString_Test() {
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1,date);
        Team team = new Team((long) 1,"team1", competition);
        Player player = new Player((long) 1, "Kelly Tran",team);

        assertThat(player.toString()).isEqualTo("Player{playerId=1, name='Kelly Tran', team=Team 'team1'}");
    }
}
