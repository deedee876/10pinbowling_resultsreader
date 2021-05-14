package com.bowlingGame.app.scoreboard;

import com.bowlingGame.app.interfaces.IFrame;

public class FrameSum {
    private int frameCounter;
    private IFrame frame;
    private int total;

    public FrameSum(int counter, IFrame frame, int total) {
        this.frameCounter = counter;
        this.frame = frame;
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public IFrame getFrame() {
        return this.frame;
    }

    public int getFrameCounter() {
        return this.frameCounter;
    }

}
