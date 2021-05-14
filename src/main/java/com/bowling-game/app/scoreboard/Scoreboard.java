package com.bowlingGame.app.scoreboard;

import com.bowlingGame.app.frame.FrameWithBonus;
import com.bowlingGame.app.game.Game;
import com.bowlingGame.app.interfaces.IFrame;
import com.bowlingGame.app.interfaces.IScoreboard;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Scoreboard implements IScoreboard {

    public String getScoreBoardForGame(Map<String, Game> game) {
        AtomicReference<String> scoreboard = new AtomicReference<>(this.generateScoreBoardHeader());

        game.entrySet().forEach(playerGame -> {
            scoreboard.set(scoreboard.get() + generatePlayerScoreBoard(playerGame.getKey(), playerGame.getValue()));
        });

        return scoreboard.get();
    }

    private String generatePlayerScoreBoard(String player, Game playerGame) {
        List<FrameSum> frameIntegerMap = playerGame.getFrameSum();

        AtomicReference<String> playerScore = new AtomicReference<>("");

        playerScore.set(String.format("%s\n", player));
        playerScore.set(playerScore.get() + "" + this.generatePitFallsRow(playerGame.getFrameSum()));
        playerScore.set(playerScore.get() + "" + this.generateScoreRow(playerGame.getFrameSum()));

        return playerScore.get();
    }

    private String generateScoreBoardHeader() {
        AtomicReference<String> scoreBoardHeaderStr = new AtomicReference<>("Frame\t");
        IntStream.range(0, 10)
                .forEach(index -> {
                    String ending = index == 9 ? "" : " \t";
                    scoreBoardHeaderStr.set(scoreBoardHeaderStr.get() + "\t " + (index + 1) + ending);
                });
        return scoreBoardHeaderStr.get() + "\n";
    }

    private String generatePitFallsRow(List<FrameSum>frameSum) {
        AtomicReference<String> pitfallRow = new AtomicReference<>("");

        AtomicInteger index = new AtomicInteger();
        frameSum.stream().forEach((frame) -> {
            String frameStr = "";
            IFrame iFrame = frame.getFrame();
            if(iFrame instanceof FrameWithBonus) {
                String starting = index.get() == 0 ? "" : " \t";

                frameStr = frameStr + String.format("%s%s\t%s\t%s",
                        starting, iFrame.isStrikePlay() ? "X" : iFrame.getPlayAt(1),
                        iFrame.isSparePlay() ? "/" :  iFrame.getPlayAt(1),
                        iFrame.getPlayAt(2));
            } else {
                if(iFrame.isStrikePlay()){
                    String starting = index.get() == 0 ? "\t" : " \t\t";
                    frameStr = frameStr + starting +"X";
                } else {
                    String starting = index.get() == 0 ? "" : " \t";
                    frameStr = frameStr + String.format("%s%s\t%s", starting, iFrame.getPlayAt(1), iFrame.isSparePlay() ? "/": iFrame.getPlayAt(2));
                }
            }

            pitfallRow.set(pitfallRow.get() + "" + frameStr);
            index.getAndIncrement();
        });
        return String.format("Pitfalls\t%s\n", pitfallRow.get());
    }

    private String generateScoreRow(List<FrameSum>frameSum){
        AtomicReference<String> scoreRow = new AtomicReference<>("");


//        System.out.println("Frame Size"+ frameSum.size());
        IntStream.range(0, frameSum.size())
                .forEach(index -> {
//                    String starting = index == 0 ? "\t" : " \t\t";
                    if(index == (frameSum.size() - 1)){
                        scoreRow.set(scoreRow.get() + "" + frameSum.get(index).getTotal());
                    } else {
                        scoreRow.set(scoreRow.get() + "" + frameSum.get(index).getTotal() + "\t\t");
                    }
                });

        return String.format("Score\t\t%s\n", scoreRow.get());
    }
}
