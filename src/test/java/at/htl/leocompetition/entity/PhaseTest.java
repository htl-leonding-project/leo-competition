package at.htl.leocompetition.entity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhaseTest {

    @Order(10)
    @Test
    public void createPhase_Test(){
        List<Node> nodeList = new ArrayList<>();
        Phase phase = new Phase((long) 1,"first Round",nodeList);

        assertThat(phase).isNotNull();
    }

    @Order(20)
    @Test
    public void toString_Test(){
        List<Node> nodeList = new ArrayList<>();
        Phase phase = new Phase((long) 1,"first Round",nodeList);

        assertThat(phase.toString()).isEqualTo("Phase{phaseId=1, phaseName='first Round'," +
                " nodeList=[]}");
    }
}
