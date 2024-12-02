package com.league_management.model;

public class Stats {
    private int playerID;
    private String playerName; 
    private int teamID;
    private int points;
    private int assists;
    private int rebounds;

    // Constructors
    public Stats() {
    }

    public Stats(int playerID, String playerName, int teamID, int points, int assists, int rebounds) {
        this.playerID = playerID;
        this.playerName = playerName; 
        this.teamID = teamID;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
    }

    // Getters and Setters
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    
    public String getPlayerName() {
    	return playerName; 
    }
    
    public void setPlayerName() {
    	this.playerName = playerName; 
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "playerID=" + playerID +
                ", teamID=" + teamID +
                ", points=" + points +
                ", assists=" + assists +
                ", rebounds=" + rebounds +
                '}';
    }
}
