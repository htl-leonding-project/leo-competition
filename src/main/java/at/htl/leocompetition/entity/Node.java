package at.htl.leocompetition.entity;

public class Node {
    private Long nodeId;
    private Node rightnode;
    private Node leftNode;
    private Node parentNode;
    private Team team1;
    private Team team2;

    public Node(Long nodeId, Node rightnode, Node leftNode, Node parentNode, Team team1, Team team2) {
        this.nodeId = nodeId;
        this.rightnode = rightnode;
        this.leftNode = leftNode;
        this.parentNode = parentNode;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Node() {
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

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", rightnode=" + rightnode +
                ", leftNode=" + leftNode +
                ", parentNode=" + parentNode +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
