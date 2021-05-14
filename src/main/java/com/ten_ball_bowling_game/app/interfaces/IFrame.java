package com.ten_ball_bowling_game.app.interfaces;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public abstract class IFrame {
    private Stack<String> playStack;

    /**
     * Constructor that sets the array of plays
     * @param playsList list of plays done by player
     */
    public IFrame(String...playsList){
        this.playStack = new Stack<String>();
        this.playStack.addAll(Arrays.asList(playsList));
    }

    public IFrame(Stack<String> playStack){
        this.playStack = playStack;
    }

    /**
     * Get list of plays ordered by the time executed
     * @return string array with play listing
     */
    public Stack<String> getPlays() {
        return this.playStack;
    }

    public void setPlay(String play) {
        this.playStack.removeAll(Collections.singleton(null));
        this.playStack.push(play);
    }

    /**
     * Get the total score for the frame without external modifications
     * @return score as integer
     */
    public int getScore() {
        return this.getPlays().stream()
                .map(play -> {
                    if(play == null || play.equalsIgnoreCase("F")) {
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
        boolean noNullValues = this.getPlays().stream().noneMatch(play -> play == null || play == "");
        return this.getPlays().size() > 0  && (noNullValues || isStrikePlay());
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
