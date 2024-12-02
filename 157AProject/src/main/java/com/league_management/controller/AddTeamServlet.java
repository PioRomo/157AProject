package com.league_management.controller;

import com.league_management.dao.TeamDAO;
import com.league_management.dao.StatsDAO;
import com.league_management.dao.PlayersDAO;
import com.league_management.dao.DatabaseConnection;
import com.league_management.model.Team;
import com.league_management.model.Players;
import com.league_management.model.Stats;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/addteam")
public class AddTeamServlet extends HttpServlet {
    private TeamDAO teamsDAO;
    private PlayersDAO playersDAO;
    private StatsDAO statsDAO; 

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            teamsDAO = new TeamDAO(connection);
            playersDAO = new PlayersDAO(connection);
            statsDAO = new StatsDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addteam.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        int wins = Integer.parseInt(request.getParameter("wins"));
        int losses = Integer.parseInt(request.getParameter("losses"));

        Team newTeam = new Team();
        newTeam.setTeamName(teamName);
        newTeam.setWins(wins);
        newTeam.setLosses(losses);

        try {
            // Add the team and get the team ID
            int teamID = teamsDAO.addTeam(newTeam);

            if (teamID > 0) {
                // Retrieve player information
                String player1Name = request.getParameter("player1Name");
                String player1Position = request.getParameter("player1Position");

                String player2Name = request.getParameter("player2Name");
                String player2Position = request.getParameter("player2Position");

                String player3Name = request.getParameter("player3Name");
                String player3Position = request.getParameter("player3Position");

                // Add players to the database and get their player IDs
                Players p1 = new Players(0, teamID, player1Name, player1Position);
                Players p2 = new Players(0, teamID, player2Name, player2Position);
                Players p3 = new Players(0, teamID, player3Name, player3Position);

                // Add players to the database and get their player IDs
                int p1ID = playersDAO.addPlayer(p1);
                int p2ID = playersDAO.addPlayer(p2);
                int p3ID = playersDAO.addPlayer(p3);

                // After adding players, add stats for each player
                if (p1ID > 0) {
                    statsDAO.addStat(new Stats(p1ID, p1.getName(), teamID, 0, 0, 0));
                }
                if (p2ID > 0) {
                    statsDAO.addStat(new Stats(p2ID, p2.getName(), teamID, 0, 0, 0));
                }
                if (p3ID > 0) {
                    statsDAO.addStat(new Stats(p3ID, p3.getName(), teamID, 0, 0, 0));
                }

                response.sendRedirect("teams");
            } else {
                request.setAttribute("error", "Failed to add team");
                request.getRequestDispatcher("/addTeam.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error adding team and players", e);
        }
    }



}
