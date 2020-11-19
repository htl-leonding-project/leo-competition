package at.htl.leocompetition.entity;

import java.util.Date;

public class Player {
    private Long playerId;
    private String name;

    public Player() {
    }

    public Player(Long playerId, String name) {
        this.playerId = playerId;
        this.name = name;
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

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                '}';
    }
}
