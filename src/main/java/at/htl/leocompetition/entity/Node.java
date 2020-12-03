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


    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Node getRightnode() {
        return rightnode;
    }

    public void setRightnode(Node rightnode) {
        this.rightnode = rightnode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "Node: " + match.toString();
    }
}
