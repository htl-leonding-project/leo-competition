package at.htl.leocompetition.entity;

public class Team {

    private Long teamId;
    private String teamName;
    private Competition competition;


    public Team(Long teamId, String teamName, Competition competition) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.competition = competition;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", competition=" + competition +
                '}';
    }
}
