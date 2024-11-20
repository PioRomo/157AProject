package com.league_management.model;

public class Team {
    private int teamID;
    private String teamName;
    private int wins;
    private int losses;

    // Constructors
    public Team() {
    }

    public Team(int teamID, String teamName, int wins, int losses) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.wins = wins;
        this.losses = losses;
    }

    // Getters and Setters
    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + teamID +
                ", teamName='" + teamName + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
