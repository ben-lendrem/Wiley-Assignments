package com.app.controller;


import com.app.dao.GameDAO;
import com.app.dto.Game;
import com.app.dto.Guess;
import com.app.dto.Round;
import com.app.service.GameLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class GameController {

    @Autowired
    private GameDAO dao;

    @Autowired
    private GameLogic service;

    public GameController(GameDAO dao, GameLogic service) {
        this.dao = dao;
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> BeginGame() {
        int newAns = service.GenNewAnswer();
        Game newGame = dao.NewGame(newAns);
        if (newGame == null) {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            service.HideGameAnswer(newGame);
            return new ResponseEntity(newGame, HttpStatus.CREATED);
        }

    }

    @PostMapping("/guess")
    public ResponseEntity<Round> MakeGuess(@RequestBody Guess guessIn) {
        int gameid = guessIn.getGameid();
        int guess = guessIn.getGuess();
        Game currentGame = dao.RetrieveGame(gameid);
        if (currentGame == null || currentGame.isFinished()) { //neater way of not guessing for finished game could be done
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        String guessResult = service.Guess(currentGame, guess);
        //create new round with the game id, guess, and result as inputs
        Round newRound = new Round();
        newRound.setGuess(guess);
        newRound.setResult(guessResult);
        newRound.setGameid(gameid);
        //roundid set in dao
        newRound = dao.AddRound(newRound);

        //updateGame
        dao.UpdateGameForNewRound(currentGame, newRound);

        return ResponseEntity.ok(newRound);
    }

    @GetMapping("/game")
    public List<Game> GetGames() {
        ArrayList<Game> allGames = new ArrayList<>(dao.RetrieveAllGames());
        service.HideAllGameAnswers(allGames);
        return allGames;
    }

    @GetMapping("/game/{gameid}")
    public ResponseEntity<Game> GetGameById(@PathVariable int gameid) {
        Game gById = dao.RetrieveGame(gameid);
        if (gById == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            service.HideGameAnswer(gById);
            return ResponseEntity.ok(gById);
        }


    }

    @GetMapping("/rounds/{gameid}") //DONE
    public ResponseEntity<List<Round>> GetRoundsById(@PathVariable int gameid) {
        if (dao.RetrieveGame(gameid) == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } else {
            List<Round> roundsList = dao.RetrieveRoundsForGame(gameid);
            return ResponseEntity.ok(roundsList);
        }




    }



}


