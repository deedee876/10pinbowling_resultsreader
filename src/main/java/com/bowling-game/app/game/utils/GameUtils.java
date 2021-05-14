package com.bowlingGame.app.game.utils;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class GameUtils {
    private GameUtils() {}

    public static boolean isValidPlayerResult(String playerResult) {
        boolean isValidNumberRange = isNumeric(playerResult) &&
                Integer.parseInt(playerResult) >=0 && Integer.parseInt(playerResult)<= 10;

        return isValidNumberRange ||  playerResult.equals("F");
    }

    public static boolean isNumeric(String str) {
        if(str == null) {
            return false;
        }

        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static int getNumericVersionOfPlay(String play) {
        if(play == null || play == "" || play.equalsIgnoreCase("F")){
            return 0;
        } else {
            return Integer.parseInt(play);
        }
    }
}
