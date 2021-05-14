package com.ten_ball_bowling_game.app.interfaces;

import com.ten_ball_bowling_game.app.exception.InvalidPlayInputException;
import com.ten_ball_bowling_game.app.exception.InvalidRollAttemptException;

import java.lang.reflect.InvocationTargetException;

public interface IGameFactory {
    /**
     * Get appropriate Iframe child object
     * @param isLastFrame
     * @param nextFrameIndex
     * @param frame
     * @param play
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InvalidPlayInputException
     * @throws InvalidRollAttemptException
     */
    IFrame generateFrameForGame(boolean isLastFrame, int nextFrameIndex,  IFrame frame, String play) throws InvocationTargetException, IllegalAccessException, InvalidPlayInputException, InvalidRollAttemptException;
}
