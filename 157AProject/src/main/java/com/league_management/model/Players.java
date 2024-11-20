package com.league_management.model;

public class Players {
    private int playerID;
    private int teamID;
    private String name;
    private String position;

    // Constructors
    public Players() {
    }

    public Players(int playerID, int teamID, String name, String position) {
        this.playerID = playerID;
        this.teamID = teamID;
        this.name = name;
        this.position = position;
    }

    // Getters and Setters
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", teamID=" + teamID +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
