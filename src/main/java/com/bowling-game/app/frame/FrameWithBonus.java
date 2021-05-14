package com.bowlingGame.app.frame;

import com.bowlingGame.app.interfaces.IFrame;

import java.util.Objects;
import java.util.Stack;

public class FrameWithBonus extends IFrame {
    private IFrame originalGame;

    public FrameWithBonus(IFrame frame, String additionalPlay) {
        super(additionalPlay);
        this.originalGame = frame;
    }

    public FrameWithBonus(IFrame frame, String addPlayOne, String addPlayTwo){
        super(addPlayOne, addPlayOne);
        this.originalGame = frame;
    }

    public FrameWithBonus(IFrame originalGame, Stack<String> bonusPlays) {
        super(bonusPlays);
        this.originalGame = originalGame;
    }

    public IFrame getOriginalGame() {
        return this.originalGame;
    }

    public Stack<String> getBonusPlays() {
        return super.getPlays();
    }

    @Override
    public Stack<String> getPlays() {
        Stack<String> originalGameList = this.originalGame.getPlays();
        Stack<String> superList = super.getPlays();

        Stack<String> newList = new Stack<>();

        newList.addAll(originalGameList);
        newList.addAll(superList);
        newList.removeIf(Objects::isNull);
        return newList;
    }

    @Override
    public void setPlay(String play) {
        if(!originalGame.isDone()) {
            originalGame.setPlay(play);
        } else {
            super.setPlay(play);
        }
    }

    @Override
    public String getPlayAt(int index) {
        int originalPlaySize = this.originalGame.getPlays().size();

        if(index <= originalPlaySize) {
            return this.originalGame.getPlayAt(index - 1);
        }

        int newIndex = index - originalPlaySize;
        return super.getPlays().get(newIndex);
    }

    @Override
    public boolean isDone() {
        boolean isBonusFilled = this.getPlays().stream().noneMatch(play -> play == null || play == "");
        return this.originalGame.isDone() && isBonusFilled ;
    }

    @Override
    public boolean isSparePlay() {
        return this.originalGame.isSparePlay();
    }

    @Override
    public boolean isStrikePlay() {
        return this.originalGame.isStrikePlay();
    }
}
