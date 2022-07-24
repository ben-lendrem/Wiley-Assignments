DROP DATABASE IF EXISTS numbergame;
CREATE DATABASE numbergame;
USE numbergame;

CREATE TABLE game (
	gameid INT(255) PRIMARY KEY AUTO_INCREMENT,
    answer INT(4) NOT NULL,
    numGuesses INT(2) NOT NULL,
    finished BOOL NOT NULL,
    won BOOL
);


CREATE TABLE Round (
	roundid INT(255) NOT NULL,
    gameid INT(255) NOT NULL,
	FOREIGN KEY fk_gameid (gameid)
    REFERENCES game(gameid),
	PRIMARY KEY pk_round (roundid, gameid),
	date_and_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    guess INT(4) NOT NULL,
	result VARCHAR(10) NOT NULL
);