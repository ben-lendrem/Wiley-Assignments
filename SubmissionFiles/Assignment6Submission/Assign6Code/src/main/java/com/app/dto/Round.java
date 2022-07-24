package com.app.dto;

import java.sql.Timestamp;

public class Round {
    private Integer gameid;

    private Integer roundid;

    private Timestamp currentDateTime;

    private Integer guess;

    private String result;

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public Integer getRoundid() {
        return roundid;
    }

    public void setRoundid(Integer roundid) {
        this.roundid = roundid;
    }

    public Timestamp getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(Timestamp currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public Integer getGuess() {
        return guess;
    }

    public void setGuess(Integer guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
