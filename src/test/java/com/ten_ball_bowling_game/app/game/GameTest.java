package com.ten_ball_bowling_game.app.game;

import com.ten_ball_bowling_game.app.exception.InvalidPlayInputException;
import com.ten_ball_bowling_game.app.game.utils.GameFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;

public class GameTest {
    private GameFactory gameFactory;

    @Test
    public void testGameStartAtZeroAtInitial() {
        Game game = new Game(null);
        Assert.assertEquals("Game starts at zero", 0, game.getTotalScore());
    }

    @Test
    public void testFirstRollWithSuccess() throws InvocationTargetException, IllegalAccessException {
        GameFactory gameFactory = new GameFactory();
        Game game = new Game(gameFactory);

        game.roll("4");
        Assert.assertEquals(4, game.getTotalScore());
    }

    @Test(expected = InvalidPlayInputException.class)
    public void testRollWithInvalidEntryActual() throws IllegalAccessException, InvocationTargetException {
        GameFactory innerGameFactory = new GameFactory();
        Game game = new Game(innerGameFactory);
        game.roll("R");
    }

    @Test
    public void testCreationOfFramesBasedOnIfCurrentFrameIsDone() throws  InvocationTargetException, IllegalAccessException {
        GameFactory innerGameFactory = new GameFactory();
        Game game = new Game(innerGameFactory);

        game.roll("3");
        game.roll("7");
        game.roll("F");

        Assert.assertEquals(2, game.getFrameSum().size());
    }

    @Test(expected = InvalidPlayInputException.class)
    public void testInvalidPlayIfTotalRollNotEqualToTen() throws InvocationTargetException, IllegalAccessException {
        GameFactory innerGameFactory = new GameFactory();
        Game game = new Game(innerGameFactory);

        game.roll("3");
        game.roll("9");
//        game.roll("F");
    }

    @Test
    public void testCalculateScoreWithSpare() throws InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("10");
        game.roll("7");
        game.roll("3");
        game.roll("F");
        game.roll("4");

        Assert.assertEquals(34, game.getTotalScore());
    }

    @Test
    public void testCalculateFullGameWithStrikeBonusRound() throws InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("10");
        game.roll("7");
        game.roll("3");
        game.roll("9");
        game.roll("0");
        game.roll("10");
        game.roll("0");
        game.roll("8");
        game.roll("8");
        game.roll("2");
        game.roll("F");
        game.roll("6");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("8");
        game.roll("1");

        Assert.assertEquals(167, game.getTotalScore());
    }

    @Test
    public void testCalculateFullGameWithNoBonusRound() throws InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("10");
        game.roll("7");
        game.roll("3");
        game.roll("9");
        game.roll("0");
        game.roll("10");
        game.roll("0");
        game.roll("8");
        game.roll("8");
        game.roll("2");
        game.roll("F");
        game.roll("6");
        game.roll("10");
        game.roll("7");
        game.roll("3");
        game.roll("8");
        game.roll("1");

        Assert.assertEquals(137, game.getTotalScore());
    }

    @Test
    public void testCalculateFullGameWithSpareBonusRound() throws InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("10");

        game.roll("7");
        game.roll("3");

        game.roll("9");
        game.roll("0");

        game.roll("10");

        game.roll("0");
        game.roll("8");

        game.roll("8");
        game.roll("2");

        game.roll("F");
        game.roll("6");

        game.roll("10");

        game.roll("7");
        game.roll("3");

        game.roll("8");
        game.roll("2");
        game.roll("10");

        Assert.assertEquals(148, game.getTotalScore());
    }

    @Test
    public void testCalculateFullScoreWithStrikes() throws  InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");
        game.roll("10");

        Assert.assertEquals(300, game.getTotalScore());
        Assert.assertTrue(game.isPerfectGame());
    }

    @Test
    public void testCalculateFullScoreWithGutter() throws  InvocationTargetException, IllegalAccessException {
        GameFactory factory = new GameFactory();
        Game game =  new Game(factory);

        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");
        game.roll("0");

        Assert.assertEquals(0, game.getTotalScore());
        Assert.assertTrue(game.isGutterGame());
    }
}
