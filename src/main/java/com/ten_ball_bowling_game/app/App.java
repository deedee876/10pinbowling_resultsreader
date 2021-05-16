package com.ten_ball_bowling_game.app;

import com.ten_ball_bowling_game.app.exception.IllegalColumnException;
import com.ten_ball_bowling_game.app.exception.IllegalColumnInputException;
import com.ten_ball_bowling_game.app.game.Game;
import com.ten_ball_bowling_game.app.game.utils.GameFactory;
import com.ten_ball_bowling_game.app.game.utils.GameUtils;
import com.ten_ball_bowling_game.app.interfaces.IScoreboard;
import com.ten_ball_bowling_game.app.scoreboard.Scoreboard;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * The Bowling app main class
 *
 */
public class App {
    private static Map<String, String> getFileAndSeparatorFromUser() {
        Map<String, String> fileData = new HashMap<>();

        String columnSeparator ="\\s";
        Scanner fileDetailsScanner = new Scanner(System.in);

        while(Objects.isNull(fileData.get("filename"))|| fileData.get("filename").equals("")) {
            System.out.println("Enter file path below =>");
            String file = fileDetailsScanner.nextLine();

            File f = new File(file);

            if (f.exists())
                fileData.put("filename", file.trim());
            else {
                fileData.put("filename", null);
                System.out.println("File path invalid...");
            }
        }

        String colSep = fileDetailsScanner.nextLine();

        columnSeparator = colSep == null ? columnSeparator : colSep;
        fileData.put("column_separator", columnSeparator);

        return fileData;
    }

    public static void main(String[] args)  {
        Map<String, Game> playerData = new HashMap<>();
        GameFactory gameFactory = new GameFactory();
        Map<String, String> fileSeparatorMap = getFileAndSeparatorFromUser();

        String file = fileSeparatorMap.get("filename");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sCurrentLine;

            String columnSeparator = fileSeparatorMap.get("column_separator");

            while ((sCurrentLine = br.readLine()) != null) {
                List<String> columns = new ArrayList<>(Arrays.asList(sCurrentLine.split(columnSeparator)));

                if(columns.size() != 2){
                    throw new IllegalColumnException();
                }

                String player = columns.get(0).trim();
                String frameResult = columns.get(1).trim();

                if(player.trim().length() == 0 || !GameUtils.isValidPlayerResult(frameResult)) {
                    throw new IllegalColumnInputException();
                }

                if(!playerData.containsKey(player)) {
                    playerData.put(player, new Game(gameFactory));
                }

                playerData.get(player).roll(frameResult);
            }

            IScoreboard scoreboard = new Scoreboard();
            System.out.println(scoreboard.getScoreBoardForGame(playerData));
        }
        catch (
                IOException | IllegalColumnException | IllegalColumnInputException |
                        IllegalAccessException | InvocationTargetException e
        ) {
            e.printStackTrace();
        }
    }


}
