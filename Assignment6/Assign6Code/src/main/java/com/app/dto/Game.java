package com.app.dto;

public class Game {
    protected Integer gameid;
    protected String answer;
    protected Integer numGuesses;
    protected Boolean finished;
    protected Boolean won; //NULLABLE


    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean isWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Integer getNumGuesses() {
        return numGuesses;
    }

    public void setNumGuesses(Integer numGuesses) {
        this.numGuesses = numGuesses;
    }

    //method for comparing games (probably a way to override equals and get the same effect but for now this will do)
    public boolean equalsGame(Game obj) {
        return  (this.gameid == obj.gameid)     &&
                (this.answer == obj.answer)     &&
                (this.finished == obj.finished) &&
                (this.won == obj.won)           &&
                (this.numGuesses == obj.numGuesses);
    }
}
