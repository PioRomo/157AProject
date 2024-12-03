package com.league_management.dao;

import com.league_management.model.Games;

import java.sql.Connection;
import java.util.List;

public class GamesDAOTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            GamesDAO gamesDAO = new GamesDAO(connection);
           
            // Test retrieving games by game ID
            Games gamesTest1 = gamesDAO.getGameByID(1);
            if (gamesTest1 != null) {
                System.out.println("Retrieved Stats: " + gamesTest1);
            }
            
            Games gamesTest2 = gamesDAO.getGameByID(5);
            if (gamesTest2 != null) {
                System.out.println("Retrieved Stats: " + gamesTest2);
            }
            
            Games gamesTest3 = gamesDAO.getGameByID(15);
            if (gamesTest3 != null) {
                System.out.println("Retrieved Stats: " + gamesTest3);
            }
            
            // Test fetching for bad game ID
            Games gamesTest4 = gamesDAO.getGameByID(-1);
            if (gamesTest4 != null) {
                System.out.println("Fetched invalid gameID, something went wrong.\n");
            } else {
            	System.out.println("Bad game ID check passed\n");
            }

            List<Games> gameList = gamesDAO.getAllGames();
            
            // Test retrieving all games
            for (Games game : gameList) {
            	System.out.println("Game ID: " + game.getGameID() + " Home Team ID: " + game.getHomeTeamID()
            	+ " Home Team Points: " + game.getHomeTeamScore() + " Away Team ID: " + game.getAwayTeamID() 
            	+ " Away Team Points: " + game.getAwayTeamScore());
            	if (game.getHomeTeamScore() > game.getAwayTeamScore()) {
            		System.out.println("Winner of Game " + game.getGameID() + " should be Home Team " + game.getHomeTeamID() + "\n");
            	} else {
            		System.out.println("Winner of Game " + game.getGameID() + " should be Away Team " + game.getAwayTeamID() + "\n");
            	}
            	
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
