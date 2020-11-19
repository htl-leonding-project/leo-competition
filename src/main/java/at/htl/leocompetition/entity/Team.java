package at.htl.leocompetition.entity;

public class Team {

    private String teamName;
    private int teamId;
    private String teams;
    private String player;
    private String phase;

    public Team(String teamName, int teamId, String teams, String player, String phase) {
        this.teamName = teamName;
        this.teamId = teamId;
        this.teams = teams;
        this.player = player;
        this.phase = phase;
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

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "Mannschaft{" +
                "teamName='" + teamName + '\'' +
                ", teamId=" + teamId +
                ", teams='" + teams + '\'' +
                ", player='" + player + '\'' +
                ", phase='" + phase + '\'' +
                '}';
    }
}
