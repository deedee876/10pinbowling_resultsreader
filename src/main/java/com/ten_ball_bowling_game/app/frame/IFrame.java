package com.ten_ball_bowling_game.app.frame;

import java.util.*;

public abstract class IFrame {
    private String[] playStack;

    /**
     * Constructor that sets the array of plays
     * @param playsList list of plays done by player
     */
    protected IFrame(String...playsList) {
        this.playStack = playsList;
    }

    /**
     * Get list of plays ordered by the time executed
     * @return string array with play listing
     */
    public String[] getPlays() {
        return this.playStack;
    }

    public void setPlay(String play) {
        String[] newStack = Arrays.stream(this.playStack).filter(stackPlay -> Objects.nonNull(stackPlay)).toArray(String[]::new);

        if(newStack.length != this.playStack.length) {
            this.playStack[newStack.length] = play;
        }
    }
    /**
     * Get the total score for the frame without external modifications
     * @return score as integer
     */
    public int getScore() {
        return Arrays.stream(this.getPlays())
                .map(play -> {
                    if(Objects.isNull(play) ||  play.equalsIgnoreCase("F")) {
                        return 0;
                    } else {
                        return Integer.parseInt(play);
                    }
                })
                .reduce(0, (playA, playB) -> playA + playB);
    }

    /**
     * Get play done at index specified
     * @param playPoint index + 1
     * @return play done by player
     */
    public abstract String getPlayAt(int playPoint);

    /**
     * Checks to see if all plays are not null/empty
     * @return boolean check based on if the frame is complete
     */
    public boolean isDone() {
        boolean noNullValues =  Arrays.stream(this.playStack).noneMatch(play -> Objects.isNull(play) || play.equals(""));
        return noNullValues || isStrikePlay();
    }

    /**
     * Checks to see if play is a spare
     * @return boolean indicating if play was a spar n e '/'
     */
    public abstract boolean isSparePlay();

    /**
     * Check to see if play is a strike
     * @return boolean indicating if a play was a strike
     */
    public abstract boolean isStrikePlay();
}
