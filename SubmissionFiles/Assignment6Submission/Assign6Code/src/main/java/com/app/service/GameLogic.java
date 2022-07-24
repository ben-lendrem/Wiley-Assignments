package com.app.service;

import com.app.dto.Game;
import com.app.dto.Round;

import java.util.List;

public interface GameLogic {
    public String Guess(Game currentGame, int guess);

    public int GenNewAnswer();


    public void HideGameAnswer(Game game);

    public void HideAllGameAnswers(List<Game> gameList);
}
