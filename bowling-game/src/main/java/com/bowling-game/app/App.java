package com.bowling-game.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        if (0 < args.length) {
            File inFile = new File(args[0]);
        }

        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(inFile));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
