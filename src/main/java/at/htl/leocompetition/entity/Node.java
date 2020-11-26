package at.htl.leocompetition.entity;

public class Node {
    private Long nodeId;
    private Node rightnode;
    private Node leftNode;
    private Node parentNode;
    private Match match;
    private Phase phase;

    public Node(Long nodeId, Node rightnode, Node leftNode, Node parentNode, Match match, Phase phase) {
        this.nodeId = nodeId;
        this.rightnode = rightnode;
        this.leftNode = leftNode;
        this.parentNode = parentNode;
        this.match = match;
        this.phase = phase;
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

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", rightnode=" + rightnode +
                ", leftNode=" + leftNode +
                ", parentNode=" + parentNode +
                ", match=" + match +
                ", phase=" + phase +
                '}';
    }
}
