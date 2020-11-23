package at.htl.leocompetition.entity;


import java.util.ArrayList;
import java.util.List;

public class Phase {

    private Long phaseId;
    private String phaseName;
    private List<Node> nodeList = new ArrayList<>();
    private Competition competition;

    public Phase(Long phaseId, String phaseName, List<Node> nodeList, Competition competition) {
        this.phaseId = phaseId;
        this.phaseName = phaseName;
        this.nodeList = nodeList;
        this.competition = competition;
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


    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public String toString() {
        return "Phase{" +
                "phaseId=" + phaseId +
                ", phaseName='" + phaseName + '\'' +
                ", nodeList=" + nodeList +
                ", competition=" + competition +
                '}';
    }
}
