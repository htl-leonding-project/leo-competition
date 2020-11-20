package at.htl.leocompetition.entity;

public class Team {

    private Long teamId;
    private String teamName;


    public Team(Long teamId,String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
