package com.league_management.dao;

import com.league_management.model.Players;

import java.sql.Connection;
import java.util.List;

public class PlayersDAOTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PlayersDAO playersDAO = new PlayersDAO(connection);
           
            // Test retrieving players by PlayerID
            Players playerTest1 = playersDAO.getPlayerByID(1);
            if (playerTest1 != null) {
                System.out.println("Retrieved Stats: " + playerTest1);
            }
            
            Players playerTest2 = playersDAO.getPlayerByID(2);
            if (playerTest2 != null) {
                System.out.println("Retrieved Stats: " + playerTest2);
            }
            
            Players playerTest3 = playersDAO.getPlayerByID(18);
            if (playerTest3 != null) {
                System.out.println("Retrieved Stats: " + playerTest3);
            }
            
            // Test for bad input from player DAO
            Players playerTest4 = playersDAO.getPlayerByID(-1);
            if (playerTest4 != null) {
                System.out.println("Fetched invalid player ID, something went wrong.\n");
            } else {
            	System.out.println("Bad Player ID check passed\n");
            }

            List<Players> playerList = playersDAO.getAllPlayers();
            
            // Test retrieving all players
            for (Players player : playerList) {
            	System.out.println("Player ID: " + player.getPlayerID() + " Player Name: " + player.getName()
            	+ " and their position is " + player.getPosition());
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
