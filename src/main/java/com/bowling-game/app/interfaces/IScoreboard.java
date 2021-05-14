package com.bowlingGame.app.interfaces;

import com.bowlingGame.app.game.Game;

import java.util.Map;

public interface IScoreboard {
    public String getScoreBoardForGame(Map<String, Game> game);
}
