# 157A Project: League Management Database
### Pio Romo, Simon Truong

##### Project Overview
<p> The Basketball League Management System is an application that allows you to organize a league by teams, host matches to display wins and losses, 
  and read about an individual player’s statistics. This is relevant because there is no obvious tool that helps organize all of this information neatly and efficiently, 
  so we developed our own. </p>

##### Project Set-Up
1. Before we get started, we need to ensure we have the proper software and dependencies to run this project.<br>
   - This project requires the Eclipse IDE for Java Developers. <br>
     You can download here: https://www.eclipse.org/downloads/packages/release/2024-09/r/eclipse-ide-enterprise-java-and-web-developers
   - This project also requires Apache Tomcat in order to run. We use Tomcat v9.0.89 for this project, <br>
     but any version within Tomcat v9.0.xx should work just fine. You can download here: https://tomcat.apache.org/download-90.cgi
   - This project of course requires both Java and mySQL. Ensure your device contains a JDK, as well as mySQL. <br>
     We use mySQL from the terminal, but you can also use the workbench if you like. Make note of your username and password!
   - Lastly, the  project requires a mySQL connector/ JDBC. This project uses version 9.0.0. <br>
     You can download here: https://downloads.mysql.com/archives/c-j/
     
   > For now, just download the software. We'll show you how to configure it next!
   
2. OK! Now that you've downloaded what's needed, the first step is to clone our repository. <br>
   Open your terminal and run the following command: `git clone https://github.com/PioRomo/157AProject.git`
3. Open Eclipse and import the project. Select **File > Import**. <br>
   In the Import Wizard, click **General > Existing Projects Into Workspace**
   - We need to ensure the project is listed as a Dynamic Web Project. To do this, right-click the project then <br>
     navigate to **Properties > Project Facets**. Both **Java** and **Dynamic Web Module** should be checked. Make sure to *apply*. 
5. Now we add the mySQL connector. In Eclipse, right-click the project and select **Build Path > Configure Build Path** <br>
   In the wizard, go to the **Library** if you aren't there already. Select **Modulepath** then click **Add External JARs** <br>
   Navigate to where you downloaded the mySQL connector and select. You should see the jar listed under **Modulepath**.
6. This is a good segway into setting up MySQL. Navigate to **157AProject > src/main/java > com.league_management.dao > DatabaseConnection.java** <br>
   On line 12, you'll see `return DriverManager.getConnection("jdbc:mysql://localhost:3306/157AProject", "root", "password");` <br>
   Replace `password` with your own credential from earlier. 
7. Head back to your terminal and input: `mysql -u root -p` <br>
   This command will start mySQL. The terminal will prompt you for your password, so enter it. mySQL is now active!
8. Now we will create the database using the following command: `SOURCE ~/Downloads/157AProject/create_schema.sql`
   The database should now exist within mySQL, so switch to it using `USE 157AProject` <br>
9. Now switch back to eclipse. Ensure Tomcat is added to Eclipse: <br>
   **Eclipse > Preferences**. In the wizard, select **Server**. From the dropdown menu, click **Runtime Environments**. <br>
   - If it isn't there already, add Tomcat by selecting **Add**.
   - Select **Apache**, then from the dropdown menu click **Apache Tomcat v9.0** and hit **Next**
   - Browse to the location where v9.0.89 is installed, then click **Finish**. Tomcat should now be listed under **Runtime Environments**
10. Now we need to configure Tomcat to run our project:
   - First, let's add our server view. Navigate to **Window > Show View > Other** <br>
     In the wizard, select **Server > Servers**
   - Open the Server view. You should see a link prompting you to add a server, click it. <br>
     In the wizard, select **Apache > Tomcat v9.0 Server**, then click **Next**. <br>
     Now the **Add and Remove** wizard should be open. On the left side under **Available**, you should see the project listed. Click it, then click **Add** <br>
     The project should move to the right side under **Configured**. Click **Finish**. Now the server should be listed under the Servers view.
11. We can now finally run our project. Right-click the server then hit **Start**. The server should have `[Started, Synchronized]` alongside it in the view. <br> 
    - Open up your web browser and navigate to: http://localhost:8080/157AProject/
    - It should display the home page. You can now toy around with the application. To stop the server, right-click the project and select **Stop**. 
   
   
Any additional configuration steps needed to connect to the database
