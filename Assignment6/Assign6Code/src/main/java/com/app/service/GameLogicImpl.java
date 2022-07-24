package com.app.service;

import com.app.dto.Game;
import com.app.dto.Round;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class GameLogicImpl implements GameLogic {

    @Override
    public String Guess(Game currentGame, int guess) {
        String guessString = String.valueOf(guess);
        String answerString = currentGame.getAnswer();
        int partial = 0;
        int exact = 0;
        //find all partial matches (if number is in the guess)
        partial = CalculatePartial(guessString, answerString);

        //check for exact matches
        exact = CalculateExact(guessString, answerString);
        partial -= exact; //exact match supersedes partial matches

        String out ="e:" + exact + ":p:" + partial;
        return out;
    }

    @Override
    public int GenNewAnswer() {
        //construct list of digits 1 - 9;
        Character[] intValCharArr = new Character[] {'1','2','3','4','5','6','7','8','9'};
        List<Character> intValsList = Arrays.asList(intValCharArr);
        ArrayList<Character> intValsAsChars = new ArrayList<>(intValsList); //convert to arraylist to use remove method
        String outStr = new String();
        //for each digit, pick a random value from the list, remove that value, and add as character
        for (int i = 0; i < 4; ++i) {
            int range = intValsAsChars.size() - 1;
            /*random number 1 to 100 then remainder becomes random number in range
            Doing it this way because generating random number between 0 and range then casting to int, very low chance
            of getting the max value, this way is more even distribution
             */
            int randInt = (int)(Math.random() * 100);
            int randIndex = randInt % range;
            String currentChar = (intValsAsChars.remove(randIndex)).toString();
            outStr = outStr.concat(currentChar);
        }
        int out = Integer.parseInt(outStr);
        return out;
    }


    public void HideGameAnswer(Game game) {
        if (!game.isFinished()) {
            String mask = "XXXX";
            game.setAnswer(mask);
        }
    }

    public void HideAllGameAnswers(List<Game> gameList) {
        for (Game current : gameList) {
            HideGameAnswer(current);
        }
    }

    private int CalculatePartial(String guessIn, String ansIn) {
        int out = 0;
        for (Character currentDigit : guessIn.toCharArray()) {
            int charIndexInAnswer = ansIn.indexOf(currentDigit);
            if (charIndexInAnswer >= 0) { //character (digit) has been found
                out++;
            }
        }
        return out;
    }

    private int CalculateExact(String guessIn, String ansIn) {
        int out = 0;
        for (int i = 0; i < guessIn.length(); ++i) {
            if (guessIn.charAt(i) == ansIn.charAt(i)) {
                out++;
            }
        }
        return out;
    }
}
