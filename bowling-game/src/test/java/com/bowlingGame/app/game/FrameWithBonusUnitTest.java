package com.bowlingGame.app.game;

import org.junit.Assert;
import org.junit.Test;

public class FrameWithBonusUnitTest {

    @Test
    public void testSizeOfListForFrameTenWithStrikePlayAndOneAdditionalPlay() {
        Frame frame = new Frame("10", "0");

        FrameWithBonus frameWithBonus = new FrameWithBonus(frame, "6");
        Assert.assertEquals(3, frameWithBonus.getPlays().size());
    }

    @Test
    public void testSizeOfListForFrameTenWithStrikePlayAndTwoAdditionalPlay() {
        Frame frame = new Frame("10", "0");

        FrameWithBonus frameWithBonus = new FrameWithBonus(frame, "6","4");
        Assert.assertEquals(4, frameWithBonus.getPlays().size());
    }

    @Test
    public void testSizeOfListForFrameTenWithOneAdditionalPlay() {
        Frame frame = new Frame("5", "5");

        FrameWithBonus frameWithBonus = new FrameWithBonus(frame, "6");
        Assert.assertEquals(3, frameWithBonus.getPlays().size());
    }

    @Test
    public void testSizeOfListForFrameTenWithTwoAdditionalPlay() {
        Frame frame = new Frame("5", "5");

        FrameWithBonus frameWithBonus = new FrameWithBonus(frame, "6","5");
        Assert.assertEquals(4, frameWithBonus.getPlays().size());
    }
}
