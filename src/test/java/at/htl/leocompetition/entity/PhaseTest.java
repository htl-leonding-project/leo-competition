package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhaseTest {

    @Order(10)
    @Test
    public void createPhase_Test(){
        List<Node> nodeList = new ArrayList<>();
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1, date);

        Phase phase = new Phase((long) 1,"first Round",nodeList, competition);

        assertThat(phase).isNotNull();
    }

    @Order(20)
    @Test
    public void toString_Test(){
        List<Node> nodeList = new ArrayList<>();
        LocalDate date = LocalDate.parse("2017-12-15");
        Competition competition = new Competition((long) 1, date);

        Phase phase = new Phase((long) 1,"first Round",nodeList, competition);

        assertThat(phase.toString()).isEqualTo("Phase{phaseId=1, phaseName='first Round'," +
                " nodeList=[]}");
    }
}
