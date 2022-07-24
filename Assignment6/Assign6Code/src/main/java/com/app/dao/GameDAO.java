package com.app.dao;

import com.app.dto.Game;
import com.app.dto.Round;

import java.util.List;

public interface GameDAO {

    public Game NewGame(int ansIn);

    public Game RetrieveGame(int gameid);

    public Round AddRound(Round roundIn);

    public void UpdateGameForNewRound(Game game, Round newRoundIn);

    public List<Game> RetrieveAllGames();

    public List<Round> RetrieveRoundsForGame(int gameid);
}
