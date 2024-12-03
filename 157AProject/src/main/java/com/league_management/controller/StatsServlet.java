package com.league_management.controller;

import com.league_management.dao.StatsDAO;
import com.league_management.dao.DatabaseConnection;
import com.league_management.model.Stats;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    private StatsDAO statsDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            statsDAO = new StatsDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize StatsDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.isEmpty()) {
                listStats(request, response);  // List stats
            } else if (action.equals("view")) {
                viewStats(request, response);  // View specific player stats
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
        	
        	if ("empty".equals(action)) {
        		System.out.println("Clearing Player Stats");
        		clearStats(request, response);        		
        	}
        	else {
        		System.out.println("Updating Player Stats");
        		updateStats(request, response);
        	}
        	
		} catch (SQLException e) {
			 throw new ServletException("Error processing POST request", e);
		}

        /*try {
            if ("stats".equals(action)) {
            	System.out.println("We are in POST, about to enter updateStats");
                updateStats(request, response);  // Update stats
            } else {
                addStats(request, response);  // Add new stats
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing POST request", e);
        }
        */
    }

    private void listStats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Stats> statsList = statsDAO.getAllStats();
        request.setAttribute("stats", statsList);
        request.getRequestDispatcher("/stats.jsp").forward(request, response);
    }

    private void viewStats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        Stats stats = statsDAO.getStatsByPlayerID(playerId);
        request.setAttribute("stats", stats);
        request.getRequestDispatcher("/statsDetails.jsp").forward(request, response);
    }

    private void addStats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        int points = Integer.parseInt(request.getParameter("points"));
        int assists = Integer.parseInt(request.getParameter("assists"));
        int rebounds = Integer.parseInt(request.getParameter("rebounds"));

        String playerName = statsDAO.getPlayerNameById(playerId); // Fetch player name using player ID

        Stats stats = new Stats(playerId, playerName, teamId, points, assists, rebounds);
        statsDAO.addStat(stats);  // Add new stats to the database

        response.sendRedirect("stats");
    }

    private void updateStats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        	
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        int points = Integer.parseInt(request.getParameter("points"));
        int assists = Integer.parseInt(request.getParameter("assists"));
        int rebounds = Integer.parseInt(request.getParameter("rebounds"));

        // Fetch player name (not mandatory, but can be useful if needed for display)
        String playerName = statsDAO.getPlayerNameById(playerId);
        int teamID = statsDAO.getTeamIdByPlayerId(playerId);

        Stats updatedStats = new Stats(playerId, playerName, teamID, points, assists, rebounds);
        
        // Check if the player already has stats and update them, otherwise add new stats
        statsDAO.updateStats(updatedStats);

        response.sendRedirect("stats");  // Redirect back to stats page
    }
    
    private void clearStats(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int playerId = Integer.parseInt(request.getParameter("playerId"));
    	
    	// Fetch player name (not mandatory, but can be useful if needed for display)
        String playerName = statsDAO.getPlayerNameById(playerId);
        int teamID = statsDAO.getTeamIdByPlayerId(playerId);

        Stats updatedStats = new Stats(playerId, playerName, teamID, 0, 0, 0);
        
        // Check if the player already has stats and update them, otherwise add new stats
        statsDAO.updateStats(updatedStats);

        response.sendRedirect("stats");  // Redirect back to stats page
    	
    }
}
