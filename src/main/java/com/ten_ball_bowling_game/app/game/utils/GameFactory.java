package com.ten_ball_bowling_game.app.game.utils;

import com.ten_ball_bowling_game.app.exception.InvalidPlayInputException;
import com.ten_ball_bowling_game.app.frame.Frame;
import com.ten_ball_bowling_game.app.frame.FrameWithBonus;
import com.ten_ball_bowling_game.app.frame.IFrame;
import com.ten_ball_bowling_game.app.interfaces.IGameFactory;

import java.util.Arrays;

public class GameFactory implements IGameFactory {

    /**
     * Check if play entered is valid
     * @param frame
     * @param play
     * @return
     */
    private boolean isUserPlayValid(IFrame frame, String play) {
        try {
            if(frame instanceof Frame &&  !frame.isDone()) {
                int proposedTotal = frame.getScore() + GameUtils.getNumericVersionOfPlay(play);
                return proposedTotal >= 0 && proposedTotal <= 10;
            }
            return GameUtils.isValidPlayerResult(play);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public IFrame generateFrameForGame(boolean atLastFrame, int nextFrameIndex, IFrame currentFrame, String play)
            throws InvalidPlayInputException {

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
//                System.out.println("Play Value: " + play);
//
//                System.out.println("B4 Play: " + Arrays.toString(currentFrame.getPlays()));

                FrameWithBonus frameWithBonus = this.generateBonus(currentFrame, play);

                System.out.println("Play: " + Arrays.toString(frameWithBonus.getPlays()));

                return frameWithBonus == null ? generateFrame(currentFrame, play): frameWithBonus;
            }
        }
        return new Frame(play, null);
    }


    private FrameWithBonus generateBonus(IFrame currentFrame, String play) {
        if(currentFrame instanceof Frame) {
            Frame frame = generateFrame(currentFrame, play);

            if(frame.isStrikePlay()) {
                return new FrameWithBonus(currentFrame, null, null);
            } else if(frame.isSparePlay()) {
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
            Arrays.stream(frame.getPlays()).forEach(currentPlay -> newFrame.setPlay(currentPlay));
            newFrame.setPlay(play);
            return newFrame;
        }

        if(GameUtils.getNumericVersionOfPlay(play) == 10){
            return new Frame("10",null);
        }
        return new Frame(play, null);
    }
}
