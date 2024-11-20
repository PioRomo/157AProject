package com.league_management.model;

public class Games {
    private int gameID;
    private int homeTeamID;
    private int awayTeamID;
    private int homeTeamScore;
    private int awayTeamScore;

    // Constructors
    public Games() {
    }

    public Games(int gameID, int homeTeamID, int awayTeamID, int homeTeamScore, int awayTeamScore) {
        this.gameID = gameID;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    // Getters and Setters
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getHomeTeamID() {
        return homeTeamID;
    }

    public void setHomeTeamID(int homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    public int getAwayTeamID() {
        return awayTeamID;
    }

    public void setAwayTeamID(int awayTeamID) {
        this.awayTeamID = awayTeamID;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameID=" + gameID +
                ", homeTeamID=" + homeTeamID +
                ", awayTeamID=" + awayTeamID +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                '}';
    }
}
