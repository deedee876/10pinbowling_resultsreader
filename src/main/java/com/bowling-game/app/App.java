package com.bowlingGame.app;

import com.bowlingGame.app.exception.IllegalColumnException;
import com.bowlingGame.app.exception.IllegalColumnInputException;
import com.bowlingGame.app.exception.InvalidPlayInputException;
import com.bowlingGame.app.exception.InvalidRollAttemptException;
import com.bowlingGame.app.game.Game;
import com.bowlingGame.app.game.utils.GameFactory;
import com.bowlingGame.app.game.utils.GameUtils;
import com.bowlingGame.app.interfaces.IScoreboard;
import com.bowlingGame.app.scoreboard.Scoreboard;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Map<String, Game> playerData = new HashMap<>();
        GameFactory gameFactory = new GameFactory();
        String columnSeparator ="\\s";

        Scanner fileDetailsScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter file: ");

        String file = fileDetailsScanner.nextLine();  // Read user input
        File inFile = new File(file);

        System.out.println("Enter column separator eg (default: '\\s') : ");
        String colSep = fileDetailsScanner.nextLine();
        columnSeparator = colSep == null ? columnSeparator : colSep;

        BufferedReader br = null;
        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader(inFile));

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

        catch (IOException | IllegalColumnException | IllegalColumnInputException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvalidPlayInputException e) {
            e.printStackTrace();
        } catch (InvalidRollAttemptException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
