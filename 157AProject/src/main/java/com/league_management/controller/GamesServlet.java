package com.league_management.controller;

import com.league_management.dao.GamesDAO;
import com.league_management.dao.DatabaseConnection;
import com.league_management.model.Games;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/games")
public class GamesServlet extends HttpServlet {
    private GamesDAO gameDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            gameDAO = new GamesDAO(connection);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize GameDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                listGames(request, response);
            } else if (action.equals("view")) {
                viewGame(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing GET request", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            addGame(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error processing POST request", e);
        }
    }

    private void listGames(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Games> games = gameDAO.getAllGames();
        request.setAttribute("games", games);
        request.getRequestDispatcher("/games.jsp").forward(request, response);
    }

    private void viewGame(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int gameId = Integer.parseInt(request.getParameter("id"));
        Games game = gameDAO.getGameByID(gameId);
        request.setAttribute("game", game);
        request.getRequestDispatcher("/gameDetails.jsp").forward(request, response);
    }

    //Method to add a game to the table
    private void addGame(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            // Retrieve and validate inputs
            String homeTeamIDParam = request.getParameter("homeTeamID");
            String awayTeamIDParam = request.getParameter("awayTeamID");
            String homeTeamScoreParam = request.getParameter("homeTeamScore");
            String awayTeamScoreParam = request.getParameter("awayTeamScore");

            if (homeTeamIDParam == null || awayTeamIDParam == null || homeTeamScoreParam == null || awayTeamScoreParam == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Parse inputs
            int homeTeamID = Integer.parseInt(homeTeamIDParam);
            int awayTeamID = Integer.parseInt(awayTeamIDParam);
            int homeTeamScore = Integer.parseInt(homeTeamScoreParam);
            int awayTeamScore = Integer.parseInt(awayTeamScoreParam);

            // Create a game object
            Games game = new Games(0, homeTeamID, awayTeamID, homeTeamScore, awayTeamScore);

            // Save to database using DAO
            gameDAO.addGame(game);

            // Redirect to games list
            response.sendRedirect("games");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format. Please enter valid integers for all fields.");
            request.getRequestDispatcher("/games.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/games.jsp").forward(request, response);
        }
    }


   
}