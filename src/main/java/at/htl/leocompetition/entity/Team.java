package at.htl.leocompetition.entity;

public class Team {

    private String teamName;
    private int teamId;


    public Team(String teamName, int teamId) {
        this.teamName = teamName;
        this.teamId = teamId;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
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
