package at.htl.leocompetition.entity;


import java.util.*;

public class Phase {

    String phaseName;
    String description;
    List<Node> nodes = new LinkedList<>();
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

    public Node get(int index) {
        if (index < this.size()) {
            return nodes.get(index);
        }
        throw new IndexOutOfBoundsException("Node-index in phase out of bound");
    }

    public int size() {
        return nodes.size();
    }

    @Override
    public String toString() {
        return phaseName;
    }
}
