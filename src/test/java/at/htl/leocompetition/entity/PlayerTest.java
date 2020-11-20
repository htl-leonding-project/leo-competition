package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Order(10)
    @Test
    public void createPlayer_Test() {

        Player player = new Player((long) 1, "Kelly Tran");

        assertThat(player).isNotNull();
    }

    @Order(10)
    @Test
    public void toString_Test() {
        Player player = new Player((long) 1, "Kelly Tran");

        System.out.println(player);
        assertThat(player.toString()).isEqualTo("Player{playerId=1, name='Kelly Tran'}");
    }
}
