package com.ten_ball_bowling_game.app.frame;

import com.ten_ball_bowling_game.app.frame.Frame;
import org.junit.Assert;
import org.junit.Test;

public class FrameUnitTest {

    @Test
    public void testIfTurnComplete() {
        Frame frame = new Frame("3","4");
        Assert.assertTrue(frame.isDone());
    }

    @Test
    public void testIfTurnNotCompleteWithNull() {
        Frame frame = new Frame("3",null);
        Assert.assertFalse(frame.isDone());
    }

    @Test
    public void testIfTurnNotCompleteWithEmptyString() {
        Frame frame = new Frame("3","");
        Assert.assertFalse(frame.isDone());
    }

    @Test
    public void testIfGameIsSpare() {
        Frame frame = new Frame("7","3");
        Assert.assertTrue(frame.isSparePlay());
    }

    @Test
    public void testIfGameIsSpareAndNotStrikeSameTime() {
        Frame frame = new Frame("7","3");
        Assert.assertTrue(frame.isSparePlay() && !frame.isStrikePlay());
    }

    @Test
    public void testIfGameIsNotSpareOrStrike() {
        Frame frame = new Frame("7","2");
        Assert.assertFalse(
                "Regular Game play should not be marked as spare or strike",
                frame.isSparePlay() || frame.isStrikePlay()
        );
    }

    @Test
    public void testIfGameIsStrikeOnFirstPlayWithNull() {
        Frame frame = new Frame("10", null);
        Assert.assertTrue(frame.isStrikePlay());
    }

    @Test
    public void testIfGameIsStrikeOnFirstPlayWithBlank() {
        Frame frame = new Frame("10", "");
        Assert.assertTrue(frame.isStrikePlay());
    }

    @Test
    public void testIfGameIsStrikeOnFirstPlayWithZero() {
        Frame frame = new Frame("10", "0");
        Assert.assertTrue(frame.isStrikePlay());
    }

    @Test
    public void testIfGameIsNotStrikeOnSecondPlay() {
        Frame frame = new Frame("0", "10");
        Assert.assertFalse(frame.isStrikePlay());
    }
    @Test
    public void testIfGameIsNotStrikeOnSecondPlayWithNull() {
        Frame frame = new Frame(null, "10");
        Assert.assertFalse(frame.isStrikePlay());
    }

    @Test
    public void testIfGameIsNotStrikeOnSecondPlayWithBlank() {
        Frame frame = new Frame("", "10");
        Assert.assertFalse(frame.isStrikePlay());
    }


    @Test
    public void testScoreForFoulOnFirstPlay() {
        Frame frame = new Frame("F", "9") ;
        Assert.assertEquals("Value should be equal to the sum of 9", 9, frame.getScore());
    }

    @Test
    public void testScoreForFoulOnSecondPlay() {
        Frame frame = new Frame("2", "F") ;
        Assert.assertEquals("Value should be equal to the sum of 2", 2, frame.getScore());
    }
}
