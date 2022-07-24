DROP DATABASE IF EXISTS herodb;
CREATE DATABASE herodb;

USE herodb;

CREATE TABLE hero(
	heroID INT PRIMARY KEY AUTO_INCREMENT,
    heroName VARCHAR(50) NOT NULL,
    heroPower VARCHAR(100),
    heroDesc VARCHAR(100),
    isHero BOOL
);

CREATE TABLE location(
	locationID INT PRIMARY KEY AUTO_INCREMENT,
    locationName VARCHAR(30) NOT NULL,
    locationDesc VARCHAR(50),
    locationAddress VARCHAR(100),
    locationCoordinates VARCHAR(20)
);

CREATE TABLE sightings(
	sightingID INT PRIMARY KEY AUTO_INCREMENT,
    heroID INT NOT NULL,
    locationID INT NOT NULL,
    FOREIGN KEY fk_heroID (heroID)
    REFERENCES hero(heroID),
    FOREIGN KEY fk_locationID (locationID)
    REFERENCES location(locationID),
    dateOfSighting DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

	