package com.bowlingGame.app.frame;

import com.bowlingGame.app.game.utils.GameUtils;
import com.bowlingGame.app.interfaces.IFrame;

public class Frame extends IFrame {
    public Frame() {
        super(null, null);
    }

    public Frame(String playOne, String playTwo) {
        super(playOne, playTwo);
    }

    @Override
    public String getPlayAt(int index) {
        return this.getPlays().get(index - 1);
    }

    @Override
    public boolean isSparePlay() {
        return !this.isStrikePlay() && this.getScore() == 10;
    }

    @Override
    public boolean isStrikePlay() {
        return GameUtils.isNumeric(this.getPlayAt(1)) && GameUtils.getNumericVersionOfPlay(this.getPlayAt(1)) == 10;
    }
}
