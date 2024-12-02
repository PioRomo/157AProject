<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.league_management.model.Stats" %>
<%@ page import="com.league_management.model.Players" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Player Statistics</title>
    <script src="https://cdn.tailwindcss.com"></script> <!-- Tailwind CSS -->
</head>
<body class="bg-gradient-to-tl from-purple-200 from-25% via-indigo-800 via-50% to-blue-500 to-80% min-h-screen">
	<%@ include file="navbar.jsp" %>
    <div class="container mx-auto py-8">
        <h1 class="text-3xl font-bold text-center mb-8 text-white">Player Statistics</h1>
        
        <%
            // Retrieve the list of stats from the request attribute
            List<Stats> stats = (List<Stats>) request.getAttribute("stats");

            if (stats != null && !stats.isEmpty()) {
        %>
        
        <div class="flex">
        
		<div class="p-5 basis-1/5 justify-center align-center">
			<form action="stats" method="POST" class="max-w-lg mx-auto p-6 bg-white shadow-lg rounded-lg mt-6">
	    			<h2 class="text-2xl font-semibold text-center text-gray-800 mb-6">Update Player Stats</h2>
	
	    			<!-- Player ID -->
	    			<div class="mb-4">
	        			<label for="playerId" class="block text-sm font-medium text-gray-700">Player ID</label>
	        			<input type="number" id="playerId" name="playerId" required
	               			class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
	    			</div>
	
	    			<!-- Points -->
	    			<div class="mb-4">
	        			<label for="points" class="block text-sm font-medium text-gray-700">Points</label>
	        			<input type="number" id="points" name="points" required
	               			class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
	    			</div>
	
	    			<!-- Assists -->
	    			<div class="mb-4">
	        			<label for="assists" class="block text-sm font-medium text-gray-700">Assists</label>
	        			<input type="number" id="assists" name="assists" required
	               			class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
	    			</div>
	
	    			<!-- Rebounds -->
	    			<div class="mb-4">
	        			<label for="rebounds" class="block text-sm font-medium text-gray-700">Rebounds</label>
	        			<input type="number" id="rebounds" name="rebounds" required
	               			class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500" />
	    			</div>
	
	   			 	<!-- Submit Button -->
	    			<div class="mb-6 text-center">
	        			<button type="submit" class="w-full py-2 px-4 bg-blue-500 hover:bg-blue-700 text-white font-semibold rounded-lg shadow-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">
	            		Update Stats
	        			</button>
	    			</div>
			</form>
		</div>
		<div class="p-5 basis-4/5 justify-center align-center">
	        <div class="overflow-x-auto">
	        	
	            <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden" id="statsTable">
	                <thead>
	                    <tr class="bg-gray-800 text-white">
	                    	<th class="px-6 py-3 text-left text-sm font-semibold">PlayerID</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">PlayerName</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">TeamID</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">Points</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">Assists</th>
	                        <th class="px-6 py-3 text-left text-sm font-semibold">Rebounds</th>
	               
	                    </tr>
	                </thead>
	                <tbody>
	                    <%
	                        for (Stats stat : stats) {
	                    %>
	                    <tr class="border-b hover:bg-gray-100">
	                    	<td class="px-6 py-4 text-sm"><%= stat.getPlayerID() %></td>
	                        <td class="px-6 py-4 text-sm"><%= stat.getPlayerName() %></td>
	                        <td class="px-6 py-4 text-sm"><%= stat.getTeamID() %></td>
	                        <td class="px-6 py-4 text-sm"><%= stat.getPoints() %></td>
	                        <td class="px-6 py-4 text-sm"><%= stat.getAssists() %></td>
	                        <td class="px-6 py-4 text-sm"><%= stat.getRebounds() %></td>
	                    </tr>
	                    <%
	                        }
	                    %>
	                </tbody>
	            </table>
			</div>    
	   </div>
	        <%
	            } else {
	        %>
	        <p class="text-center text-gray-600">No statistics available.</p>
	        <%
	            }
	        %>
	   </div>
	   </div>
    
</body>
</html>
