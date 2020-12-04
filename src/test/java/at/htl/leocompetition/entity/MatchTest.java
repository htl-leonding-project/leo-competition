package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

    @Order(10)
    @Test
    public void createMatch_Test() {
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1, date);
        Phase phase = new Phase("round 1", competition);
        Team team1 = new Team((long) 1, "1AHITM", competition);
        Team team2 = new Team((long) 1, "1AHIF", competition);
        Match match = new Match((long) 1, team1, team2, 2, 1, phase);


        assertThat(match).isNotNull();
    }

    @Order(20)
    @Test
    public void toString_Test() {
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1, date);
        Phase phase = new Phase("round 1", competition);
        Team team1 = new Team((long) 1, "1AHITM", competition);
        Team team2 = new Team((long) 1, "1AHIF", competition);
        Match match = new Match((long) 1, team1, team2, 2, 1, phase);

        assertThat(match.toString()).isEqualTo("Match (Team '1AHITM' - Team '1AHIF')");
    }
}
