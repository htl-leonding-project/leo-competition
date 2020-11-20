package at.htl.leocompetition.entity;


import java.util.ArrayList;
import java.util.List;

public class Phase {

    private Long phaseId;
    private String phaseName;
    private List<Node> nodeList = new ArrayList<>();

    public Phase(Long phaseId, String phaseName, List<Node> nodeList) {
        this.phaseId = phaseId;
        this.phaseName = phaseName;
        this.nodeList = nodeList;
    }

    public Phase() {
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        return "Phase{" +
                "phaseId=" + phaseId +
                ", phaseName='" + phaseName + '\'' +
                ", nodeList=" + nodeList +
                '}';
    }
}
