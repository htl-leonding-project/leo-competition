package at.htl.leocompetition.entity;

import java.util.Date;

public class Player {
    private Long playerId;
    private String name;
    private Team team;

    public Player() {
    }

    public Player(Long playerId, String name, Team team) {
        this.playerId = playerId;
        this.name = name;
        this.team = team;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
