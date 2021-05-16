package com.ten_ball_bowling_game.app.game.utils;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Objects;

public class GameUtils {
    private GameUtils() {}

    public static boolean isNoPlay(String play) {
        return Objects.isNull(play) || play.trim().equals("") || play.equals("F");
    }

    public static boolean isValidPlayerResult(String playerResult)  {
        return getNumericVersionOfPlay(playerResult) >= 0 && getNumericVersionOfPlay(playerResult) <= 10;
    }

    public static boolean isNumeric(String str) {
        if(isNoPlay(str)) {
            return false;
        }

        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static int getNumericVersionOfPlay(String play){
        return isNoPlay(play) ? 0 : Integer.parseInt(play);
    }
}
