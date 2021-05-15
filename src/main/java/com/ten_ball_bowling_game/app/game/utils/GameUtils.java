package com.ten_ball_bowling_game.app.game.utils;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class GameUtils {
    private GameUtils() {}

    public static boolean isValidPlayerResult(String playerResult) throws NumberFormatException {
        return getNumericVersionOfPlay(playerResult) >= 0 && getNumericVersionOfPlay(playerResult) <= 10;
    }

    public static boolean isNumeric(String str) {
        if(Objects.isNull(str)) {
            return false;
        }

        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static int getNumericVersionOfPlay(String play) throws NumberFormatException{
        if(Objects.isNull(play) || play.equals("") || play.equalsIgnoreCase("F")){
            return 0;
        } else {
            return Integer.parseInt(play);
        }
    }
}
