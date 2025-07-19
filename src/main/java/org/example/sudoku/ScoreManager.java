package org.example.sudoku;

import java.sql.Time;

public class ScoreManager {
    private Time startTime, endTime;
    private int wrongMoves, hintsUsed, currentScore;

    public ScoreManager(int hintsUsed, Time startTime, Time endTime, int wrongMoves, int currentScore) {
        this.hintsUsed = hintsUsed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.wrongMoves = wrongMoves;
        this.currentScore = currentScore;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getWrongMoves() {
        return wrongMoves;
    }

    public void setWrongMoves(int wrongMoves) {
        this.wrongMoves = wrongMoves;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
