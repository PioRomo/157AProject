<%@ page import="java.util.List, com.league_management.model.Team" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teams</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        function scrollToRemoveForm() {
            document.getElementById("deleteForm").scrollIntoView({ behavior: "smooth" });
        }
    </script>
</head>
<body class="bg-gradient-to-tl from-cyan-500 from-25% via-cyan-800 via-50% to-blue-500 to-80% min-h-screen">
	<%@ include file="navbar.jsp" %>

    <div class="container mx-auto py-8">
        <!-- Header -->
        <h1 class="text-3xl font-bold text-center mb-8 text-white">Teams Management</h1>
        
        <!-- Add and Remove Buttons -->
        <div class="flex justify-center space-x-4 mb-8">
            <a href="${pageContext.request.contextPath}/addteam" class="bg-green-500 text-white px-6 py-2 rounded-md hover:bg-green-600">Add Team</a>
        </div>

	<div class="flex">
		<div class="p-5 basis-1/5 justify-center align-center">
			<!-- Delete A Team Form -->
	        <div class="bg-white shadow-md rounded-lg p-6" id="deleteForm">
	    		<h2 class="text-xl font-semibold mb-4">Delete a Team</h2>
	    		<form action="teams" method="POST" class="space-y-4">
	        		<!-- Method Spoofing for DELETE -->
	        		<input type="hidden" name="_method" value="DELETE">
	        
	        		<!-- Team ID Input -->
	        		<div>
	            		<label for="teamID" class="block text-sm font-medium text-gray-700">Team ID</label>
	            		<input 
	                		type="number" 
	                		name="teamID" 
	                		id="teamID" 
	                		class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
	                		placeholder="Enter the Team ID to delete" 
	                		required
	            		>
	        		</div>
	
	        		<!-- Delete Button -->
	        		<button 
	            		type="submit" 
	            		class="w-full bg-red-500 text-white rounded-lg px-4 py-2 hover:bg-red-600">
	            		Delete Team
	        		</button>
	    		</form>
			</div>
		</div>
	
	
		<div class="p-5 basis-4/5 justify-center align-center">
	        <!-- Teams List -->
	        <div class="bg-white shadow-md rounded-lg p-6 mb-8">
	            <h2 class="text-2xl font-semibold mb-4">All Teams</h2>
	            <table class="table-auto w-full border-collapse border border-gray-300">
	                <thead>
	                    <tr class="bg-gray-200">
	                        <th class="border border-gray-300 px-4 py-2 text-left">Team ID</th>
	                        <th class="border border-gray-300 px-4 py-2 text-left">Team Name</th>
	                        <th class="border border-gray-300 px-4 py-2 text-left">Wins</th>
	                        <th class="border border-gray-300 px-4 py-2 text-left">Losses</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <%
	                        // Assuming a "teams" attribute is set by the servlet as a List of Team objects
	                        List<com.league_management.model.Team> teams = 
	                            (List<com.league_management.model.Team>) request.getAttribute("teams");
	                        if (teams != null) {
	                            for (com.league_management.model.Team team : teams) {
	                    %>
	                    <tr>
	                        <td class="border border-gray-300 px-4 py-2"><%= team.getTeamID() %></td>
	                        <td class="border border-gray-300 px-4 py-2"><%= team.getTeamName() %></td>
	                        <td class="border border-gray-300 px-4 py-2"><%= team.getWins() %></td>
	                        <td class="border border-gray-300 px-4 py-2"><%= team.getLosses() %></td>
	                    </tr>
	                    <%
	                            }
	                        } else {
	                    %>
	                    <tr>
	                        <td colspan="4" class="border border-gray-300 px-4 py-2 text-center">No teams found.</td>
	                    </tr>
	                    <%
	                        }
	                    %>
	                </tbody>
	            </table>
	        </div>
		</div>
		
	</div>
        
    </div>

</body>
</html>
