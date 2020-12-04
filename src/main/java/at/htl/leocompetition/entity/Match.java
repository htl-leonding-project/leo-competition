package at.htl.leocompetition.entity;

public class Match {

    Long matchId;
    Team team1;
    Team team2;
    int pointsTeam1;
    int pointsTeam2;
    Phase phase;

    public Match() {
    }

    public Match(Team team1, Team team2, Phase phase) {
        this.team1 = team1;
        this.team2 = team2;
        this.phase = phase;
    }

    // dieser Konstruktor ist sinnlos
    // 1. wird die Id von der DB vergeben
    // 2. kenne ich die Ergebnisse noch nicht (points)
    public Match(Long matchId, Team team1, Team team2, int pointsTeam1, int pointsTeam2, Phase phase) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.pointsTeam1 = pointsTeam1;
        this.pointsTeam2 = pointsTeam2;
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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

    public int getPointsTeam1() {
        return pointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        this.pointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return pointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        this.pointsTeam2 = pointsTeam2;
    }

    public Team getWinner() {
        return pointsTeam1 > pointsTeam2 ? team1 : team2;
    }

    public Team getLoser() {
        return pointsTeam1 < pointsTeam2 ? team1 : team2;
    }

    @Override
    public String toString() {
        return "Match (" + team1 + " - " + team2 + ")";


    }
}
