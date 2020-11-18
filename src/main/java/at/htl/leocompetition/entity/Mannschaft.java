package at.htl.leocompetition.entity;

public class Mannschaft {

    private String teamName;
    private int teamId;
    private String gameList1;
    private String getGameList2;
    private String teams;
    private String player;
    private String phase;

    public Mannschaft(String teamName, int teamId, String gameList1, String getGameList2, String teams, String player, String phase) {
        this.teamName = teamName;
        this.teamId = teamId;
        this.gameList1 = gameList1;
        this.getGameList2 = getGameList2;
        this.teams = teams;
        this.player = player;
        this.phase = phase;
    }

    public Mannschaft() {
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

    public String getGameList1() {
        return gameList1;
    }

    public void setGameList1(String gameList1) {
        this.gameList1 = gameList1;
    }

    public String getGetGameList2() {
        return getGameList2;
    }

    public void setGetGameList2(String getGameList2) {
        this.getGameList2 = getGameList2;
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
                ", gameList1='" + gameList1 + '\'' +
                ", getGameList2='" + getGameList2 + '\'' +
                ", teams='" + teams + '\'' +
                ", player='" + player + '\'' +
                ", phase='" + phase + '\'' +
                '}';
    }
}
