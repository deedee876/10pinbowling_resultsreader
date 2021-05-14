package com.bowlingGame.app.game.utils;

import com.bowlingGame.app.exception.InvalidPlayInputException;
import com.bowlingGame.app.exception.InvalidRollAttemptException;
import com.bowlingGame.app.frame.Frame;
import com.bowlingGame.app.frame.FrameWithBonus;
import com.bowlingGame.app.interfaces.IFrame;
import com.bowlingGame.app.interfaces.IGameFactory;

import java.lang.reflect.InvocationTargetException;

public class GameFactory implements IGameFactory {

    /**
     * Check if play entered is valid
     * @param frame
     * @param play
     * @return
     */
    private boolean isUserPlayValid(IFrame frame, String play) {
        if(frame != null && frame instanceof Frame &&  !frame.isDone()) {
            int proposedTotal = frame.getScore() + GameUtils.getNumericVersionOfPlay(play);
            return proposedTotal <= 10;
        }
        return GameUtils.isValidPlayerResult(play);
    }

    @Override
    public IFrame generateFrameForGame(boolean atLastFrame, int nextFrameIndex, IFrame currentFrame, String play)
            throws InvocationTargetException, IllegalAccessException, InvalidPlayInputException, InvalidRollAttemptException {
        if(!isUserPlayValid(currentFrame, play)) {
            throw new InvalidPlayInputException();
        }

        if(currentFrame != null){
            if(!atLastFrame) {
                Frame frame = generateFrame(currentFrame, play);

                if(nextFrameIndex == 10 && currentFrame.isDone() && frame.isStrikePlay()) {
                    return new FrameWithBonus(frame,  null, null);
                }

                return frame;
            } else {
                FrameWithBonus frameWithBonus = this.generateBonus(currentFrame, play, nextFrameIndex);
                return frameWithBonus == null ? generateFrame(currentFrame, play): frameWithBonus;
            }
        }
        return new Frame(play, null);
    }


    private FrameWithBonus generateBonus(IFrame currentFrame, String play, int atLastFrame) {
        if(currentFrame instanceof Frame) {
            Frame frame = generateFrame(currentFrame, play);

            if(frame.isStrikePlay()) {
                return new FrameWithBonus(currentFrame, (String) null);
            }

        } else if(currentFrame instanceof FrameWithBonus && !currentFrame.isDone()) {
            FrameWithBonus frameWithBonus =
                    new FrameWithBonus(
                            ((FrameWithBonus) currentFrame).getOriginalGame(),
                            ((FrameWithBonus) currentFrame).getBonusPlays()
                    );

            frameWithBonus.setPlay(play);
            return frameWithBonus;
        }
        return null;
    }

    /**
     * Generate the Frame based on previous frame and the play (roll)
     * @param frame
     * @param play
     * @return
     */
    private Frame generateFrame(IFrame frame, String play){
        if(frame != null && !frame.isDone()) {
            Frame newFrame = new Frame();
            frame.getPlays().forEach(currentPlay -> newFrame.setPlay(currentPlay));
            newFrame.setPlay(play);

            return newFrame;
        }

        if(GameUtils.getNumericVersionOfPlay(play) == 10){
            return new Frame("10",null);
        }
        return new Frame(play, null);
    }
}
