package at.htl.leocompetition.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Phase {

    String phaseName;
    String description;
    Set<Node> nodes = new HashSet<>();
    Competition competition;

    public Phase(String phaseName, Competition competition) {
        this.phaseName = phaseName;
        this.competition = competition;
    }

    public Phase() {
    }

    public void addNode(Node node) {
        nodes.add(node);
        node.phase = this;  // weil bidirektionale Assoziation
    }


    @Override
    public String toString() {
        return phaseName;
    }
}
