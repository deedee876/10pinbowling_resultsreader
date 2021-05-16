package com.ten_ball_bowling_game.app.scoreboard;

import com.ten_ball_bowling_game.app.frame.IFrame;

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
