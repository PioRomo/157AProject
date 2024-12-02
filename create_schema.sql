-- Create the database
CREATE DATABASE IF NOT EXISTS 157AProject;
USE 157AProject;

-- Create Teams table
CREATE TABLE Teams (
    TeamID INT AUTO_INCREMENT PRIMARY KEY,
    TeamName VARCHAR(100) NOT NULL,
    Wins INT DEFAULT 0,
    Losses INT DEFAULT 0
);

-- Create Players table
CREATE TABLE Players (
    PlayerID INT AUTO_INCREMENT PRIMARY KEY,
    TeamID INT NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Position VARCHAR(50),
    FOREIGN KEY (TeamID) REFERENCES Teams(TeamID)
);

-- Create Games table 
CREATE TABLE Games (
    GameID INT AUTO_INCREMENT PRIMARY KEY,
    HomeTeamID INT NOT NULL,
    AwayTeamID INT NOT NULL,
    HomeTeamScore INT DEFAULT 0,
    AwayTeamScore INT DEFAULT 0,
    FOREIGN KEY (HomeTeamID) REFERENCES Teams(TeamID),
    FOREIGN KEY (AwayTeamID) REFERENCES Teams(TeamID)
);

-- Create Stats table 
CREATE TABLE Stats (
    PlayerID INT NOT NULL,
    TeamID INT NOT NULL,
    Points INT DEFAULT 0,
    Assists INT DEFAULT 0,
    Rebounds INT DEFAULT 0,
    FOREIGN KEY (PlayerID) REFERENCES Players(PlayerID),
    FOREIGN KEY (TeamID) REFERENCES Teams(TeamID),
    PRIMARY KEY (PlayerID)
);

-- Insert Data
BEGIN;

-- Insert Teams
INSERT INTO Teams (TeamName, Wins, Losses) VALUES
('Tilted Towers Titans', 1, 1),
('Pleasant Park Panthers', 1, 1),
('Salty Springs Snipers', 0, 2),
('Lazy Lagoon Legends', 2, 0),
('Retail Row Raiders', 1, 1),
('Dusty Depot Demolishers', 0, 2),
('Fatal Fields Flyers', 2, 0),
('Greasy Grove Gladiators', 1, 1),
('Haunted Hills Hunters', 0, 2),
('Loot Lake Llamas', 2, 0),
('Shifty Shafts Shredders', 1, 1),
('Sunny Steps Stingers', 0, 2),
('Paradise Palms Prowlers', 1, 1),
('Tomato Town Tigers', 2, 0),
('Moisty Mire Marauders', 1, 1);

-- Insert Players
INSERT INTO Players (TeamID, Name, Position) VALUES
(1, 'Drift', 'Forward'),
(1, 'Jonesy', 'Guard'),
(1, 'Brite Bomber', 'Center'),
(2, 'Raven', 'Forward'),
(2, 'Peely', 'Guard'),
(2, 'Cuddle Team Leader', 'Center'),
(3, 'Omega', 'Forward'),
(3, 'Lynx', 'Guard'),
(3, 'Dark Voyager', 'Center'),
(4, 'Midas', 'Forward'),
(4, 'Eternal Knight', 'Guard'),
(4, 'Kit', 'Center'),
(5, 'Fishstick', 'Forward'),
(5, 'Renegade Raider', 'Guard'),
(5, 'Skull Trooper', 'Center'),
(6, 'Calamity', 'Forward'),
(6, 'Havoc', 'Guard'),
(6, 'Zoey', 'Center'),
(7, 'Wild Card', 'Forward'),
(7, 'Cluck', 'Guard'),
(7, 'Meowscles', 'Center'),
(8, 'Raptor', 'Forward'),
(8, 'Snowheart', 'Guard'),
(8, 'Brutus', 'Center'),
(9, 'Sparkle Specialist', 'Forward'),
(9, 'The Ice King', 'Guard'),
(9, 'The Visitor', 'Center'),
(10, 'Deadfire', 'Forward'),
(10, 'Ghoul Trooper', 'Guard'),
(10, 'Shimmer Specialist', 'Center'),
(11, 'Black Knight', 'Forward'),
(11, 'Fable', 'Guard'),
(11, 'Dusk', 'Center'),
(12, 'Haven', 'Forward'),
(12, 'Giddy-Up', 'Guard'),
(12, 'Harlow', 'Center'),
(13, 'Agent Jones', 'Forward'),
(13, 'Monks', 'Guard'),
(13, 'Sparkplug', 'Center'),
(14, 'Rust Lord', 'Forward'),
(14, 'Wingman', 'Guard'),
(14, 'Summit Seeker', 'Center'),
(15, 'Rox', 'Forward'),
(15, 'Wildcard', 'Guard'),
(15, 'DJ Yonder', 'Center');

-- Insert Stats 
INSERT INTO Stats (PlayerID, TeamID, Points, Assists, Rebounds) VALUES
-- Team 1 
(1, 1, 15, 4, 6),  
(2, 1, 12, 3, 5),  
(3, 1, 12, 2, 4),  

-- Team 2 
(4, 2, 14, 5, 6),  
(5, 2, 12, 4, 5),  
(6, 2, 14, 3, 6),  

-- Team 3
(7, 3, 12, 4, 5),  
(8, 3, 11, 3, 4),  
(9, 3, 11, 4, 5),  

-- Team 4 
(10, 4, 14, 5, 6), 
(11, 4, 14, 4, 5), 
(12, 4, 14, 3, 6), 

-- Team 5 
(13, 5, 14, 5, 6), 
(14, 5, 13, 4, 5),
(15, 5, 13, 3, 5), 

-- Team 6 
(16, 6, 12, 3, 5), 
(17, 6, 11, 4, 5), 
(18, 6, 12, 3, 5), 

-- Team 7 
(19, 7, 14, 5, 6), 
(20, 7, 14, 4, 5),
(21, 7, 14, 3, 6), 

-- Team 8 
(22, 8, 13, 5, 6), 
(23, 8, 14, 4, 6), 
(24, 8, 14, 3, 6), 

-- Team 9
(25, 9, 10, 3, 5),
(26, 9, 9, 4, 5),  
(27, 9, 9, 3, 4),  

-- Team 10 
(28, 10, 14, 5, 6), 
(29, 10, 14, 4, 5), 
(30, 10, 14, 3, 6), 

-- Team 11 
(31, 11, 14, 5, 6), 
(32, 11, 13, 4, 6), 
(33, 11, 14, 3, 6), 

-- Team 12 
(34, 12, 12, 4, 5), 
(35, 12, 12, 3, 4), 
(36, 12, 12, 4, 5), 

-- Team 13 
(37, 13, 14, 5, 6), 
(38, 13, 13, 4, 5), 
(39, 13, 14, 3, 6), 

-- Team 14 
(40, 14, 14, 5, 6), 
(41, 14, 14, 4, 5), 
(42, 14, 14, 3, 6), 

-- Team 15 
(43, 15, 12, 4, 5), 
(44, 15, 12, 3, 4), 
(45, 15, 12, 4, 5); 

-- Insert Games
INSERT INTO Games (HomeTeamID, AwayTeamID, HomeTeamScore, AwayTeamScore) VALUES
(1, 2, 21, 19),
( 3, 4, 18, 21),
(5, 6, 21, 17),
(7, 8, 21, 20),
(9, 10, 15, 21),
(11, 12, 21, 19),
(13, 14, 20, 21),
(15, 1, 21, 18),
(2, 3, 21, 16),
(4, 5, 21, 19),
(6, 7, 18, 21),
(8, 9, 21, 13),
(10, 11, 21, 20),
(12, 13, 17, 21),
(14, 15, 21, 15);
COMMIT;
