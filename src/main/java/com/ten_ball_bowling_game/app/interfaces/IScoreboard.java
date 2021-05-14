package com.ten_ball_bowling_game.app.interfaces;

import com.ten_ball_bowling_game.app.game.Game;

import java.util.Map;

public interface IScoreboard {
    public String getScoreBoardForGame(Map<String, Game> game);
}
