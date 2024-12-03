package com.league_management.controller ;

import com.league_management.dao.PlayersDAO;
import com.league_management.model.Players;
import com.league_management.dao.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/players") // Accessible at /players URL
public class PlayersServlet extends HttpServlet {
    private PlayersDAO playerDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            playerDAO = new PlayersDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Database connection initialization failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                // Default action: List all players
                listPlayers(request, response);
            } else if (action.equals("view")) {
                viewPlayer(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error handling GET request", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
            	System.out.println("Deleting Player");
                deletePlayers(request, response);
            } else if ("update".equals(action)) {
            	System.out.println("Updating Player Details");
            	updatePlayers(request, response);
            } else {
            	System.out.println("Creating Player Details");
            	int teamID = Integer.parseInt(request.getParameter("teamID"));

                // Retrieve Player 1 details
                String player1Name = request.getParameter("player1Name");
                String player1Position = request.getParameter("player1Position");

                // Retrieve Player 2 details
                String player2Name = request.getParameter("player2Name");
                String player2Position = request.getParameter("player2Position");

                // Retrieve Player 3 details
                String player3Name = request.getParameter("player3Name");
                String player3Position = request.getParameter("player3Position");

                // Add players to the database
                playerDAO.addPlayer(new Players(0,teamID, player1Name, player1Position));
                playerDAO.addPlayer(new Players(0,teamID, player2Name, player2Position));
                playerDAO.addPlayer(new Players(0,teamID, player3Name, player3Position));

                // Redirect to players list
                response.sendRedirect("players");
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing POST request", e);
        }
    }


    private void listPlayers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Players> players = playerDAO.getAllPlayers();
        request.setAttribute("players", players);
        request.getRequestDispatcher("/players.jsp").forward(request, response); // Forward to JSP
    }

    private void viewPlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        Players player = playerDAO.getPlayerByID(playerId);
        request.setAttribute("player", player);
        request.getRequestDispatcher("/playerDetails.jsp").forward(request, response); // Forward to JSP
    }

    private void addPlayer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        int teamId = Integer.parseInt(request.getParameter("teamId"));

        Players player = new Players(0, teamId, name, position); // ID = 0 since it's auto-generated
        playerDAO.addPlayer(player);
        response.sendRedirect("players"); // Redirect to the player list
    }
    
    private void deletePlayers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int teamID = Integer.parseInt(request.getParameter("teamID"));
        playerDAO.deletePlayersByTeamID(teamID);
        response.sendRedirect("players?action=list"); // Redirect back to players list
    }

    private void updatePlayers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int playerId = Integer.parseInt(request.getParameter("id"));
        String newName = request.getParameter("name");
        String newPosition = request.getParameter("position");
        Players player = playerDAO.getPlayerByID(playerId);
        

        Players updatedPlayer = new Players(playerId, player.getTeamID(), newName, newPosition); // ID must be passed in
        playerDAO.updatePlayer(updatedPlayer);
        response.sendRedirect("players"); // Redirect to the player list
    }
    
   
}