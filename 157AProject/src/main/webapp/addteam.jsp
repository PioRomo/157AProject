<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Team and Players</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-tl from-indigo-500 from-25% via-indigo-800 via-50% to-blue-500 to-80% min-h-screen">

    <%@ include file="navbar.jsp" %>

    <div class="container mx-auto p-6 bg-white shadow-lg rounded-lg mt-8">
        <h1 class="text-3xl font-bold mb-6 text-center text-gray-800">Add Team and Players</h1>

        <form action="${pageContext.request.contextPath}/addteam" method="POST" class="space-y-8">
            <!-- Team Section -->
            <div>
                <h2 class="text-xl font-semibold text-gray-700 mb-4">Team Details</h2>
                <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                    <div>
                        <label for="teamName" class="block text-sm font-medium text-gray-600">Team Name</label>
                        <input type="text" id="teamName" name="teamName" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                    <div>
                        <label for="wins" class="block text-sm font-medium text-gray-600">Wins</label>
                        <input type="number" id="wins" name="wins" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                    <div>
                        <label for="losses" class="block text-sm font-medium text-gray-600">Losses</label>
                        <input type="number" id="losses" name="losses" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                </div>
            </div>

            <!-- Players Section -->
            <div>
                <h2 class="text-xl font-semibold text-gray-700 mb-4">Player Details</h2>
                <div class="space-y-6">
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <input type="text" name="player1Name" placeholder="Player 1 Name" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                        <input list="position-types" type="text" name="player1Position" placeholder="Position" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <input type="text" name="player2Name" placeholder="Player 2 Name" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                        <input list="position-types" type="text" name="player2Position" placeholder="Position" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                        <input type="text" name="player3Name" placeholder="Player 3 Name" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                        <input list="position-types" type="input-field" name="player3Position" placeholder="Position" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" required>
                    </div>
                    <datalist id="position-types">
                    	<option value="Forward" />
                    	<option value="Guard" />
                    	<option value="Center" />
                    </datalist>
                </div>
            </div>

            <!-- Submit Button -->
            <div class="text-center">
                <button type="submit" class="px-6 py-3 bg-indigo-600 text-white rounded-lg shadow-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                    Add Team and Players
                </button>
            </div>
        </form>
    </div>

</body>
</html>
