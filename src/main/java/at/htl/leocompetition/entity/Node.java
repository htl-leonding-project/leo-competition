package at.htl.leocompetition.entity;

public class Node {
    Long nodeId; // für den Prototypen braucht man noch keine Id
    Node rightnode;
    Node leftNode;
    Node parentNode;
    Match match;
    Phase phase;

    // dieser Konstruktor ist sinnlos
    public Node(Long nodeId, Node rightnode, Node leftNode, Node parentNode, Match match, Phase phase) {
        this.nodeId = nodeId;
        this.rightnode = rightnode;
        this.leftNode = leftNode;
        this.parentNode = parentNode;
        this.match = match;
        this.phase = phase;
    }

    public Node(Match match, Phase phase) {
        this.match = match;
        this.phase = phase;
    }

    public Node() {
    }

    public Node(Long nodeId, Node parentNode, Match match, Phase phase) {
        this.nodeId = nodeId;
        this.parentNode = parentNode;
        this.match = match;
        this.phase = phase;
    }

    public Node(Node rightnode, Node leftNode) {
        this.rightnode = rightnode;
        this.leftNode = leftNode;
    }


    @Override
    public String toString() {
        return "Node: " + match.toString();
    }
}
