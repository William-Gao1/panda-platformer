package game.dialogue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.FontFormatException;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import game.Game;
import util.KeyManagerListener;
import java.awt.FontMetrics;

public class Dialogue implements KeyManagerListener, DialogueEventListener {
    private final Image image = Toolkit.getDefaultToolkit().createImage("Resources//Dialogue//box.png");
    private final int x, y;
    public static final int LENGTH = 145;
    private String text = "";
    private int count =-1;
    private Integer[] hashKeys;
    private boolean startDia=false;
    private final Vector<String> cereal = new Vector<String>(0, 1);
    private Vector<String> fittedText = new Vector<String>(0, 1);
    private final FontMetrics metrics;
    private final HashMap<Integer, String> hashbrown = new HashMap<Integer, String>(0, 1);


    Font font;

    public Dialogue(final int x, final int y) {
        this.x = x;
        this.y = y;
        try {
            final FileReader textFile = new FileReader("Resources//Dialogue//text.txt");
            final BufferedReader input = new BufferedReader(textFile);
            String var = input.readLine();
            while (var != null) {
                text = text + " " + var;
                var = input.readLine();
            }
            textFile.close();
            input.close();
        } catch (final IOException e) {
        }
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources//Fonts//galette-med.otf")).deriveFont(23f);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Resources//Fonts//galette-med.otf")));
        } catch (IOException | FontFormatException e) {
        }
        metrics = new FontMetrics(font) {
            private static final long serialVersionUID = 1L;
        };
        sortText(text);
        Game.getKeyManager().listenFor(KeyEvent.VK_ENTER,this);
    }

    public void draw(final Graphics g) { //only draws if startDialogue() set boolean startDia to true
        if (startDia){
            g.drawImage(image, x, y, null); //Dialogue box image
            g.setFont(font);
            for (String s : cereal) {
                g.drawString(s, 75, 475 + 25 * cereal.indexOf(s));
            }
        }
    }

    private void wrapText(final String s) { //wraps text from line to line
        final StringTokenizer st = new StringTokenizer(s);
        int currentLength = 0;
        String output = "";
        String currentToken;
        while (st.hasMoreTokens()) {
            currentToken = st.nextToken();
            if (currentLength == 0) {
                currentLength = currentLength + (int) metrics.getStringBounds(currentToken, null).getWidth()
                        + (int) metrics.getStringBounds(" ", null).getWidth();
                output = currentToken;
            } else if ((currentLength + metrics.getStringBounds(currentToken, null).getWidth()
                    + metrics.getStringBounds(" ", null).getWidth()) < 700) {
                currentLength = currentLength + (int) metrics.getStringBounds(currentToken, null).getWidth()
                        + (int) metrics.getStringBounds(" ", null).getWidth();
                output = output + " " + currentToken;
            } else {
                cereal.add(output);
                currentLength = (int) metrics.getStringBounds(currentToken, null).getWidth();
                output = currentToken;
            }
        }
        cereal.add(output);
    }

    /**
     * Cuts up a string into 3 line dialogue boxes
     * @param s     The string to be split
     * @return      A vector containing the individual dialogue box strings
     */
    private Vector<String> fitText(final String s) { //fits text into box
        Vector<String> dia = new Vector<String>(0, 1);
        int currentLength2 = 0;
        String output2 = "";
        String currentToken2 = "";
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            currentToken2 = st.nextToken();
            if (currentLength2 == 0) {
                currentLength2 = currentLength2 + (int) metrics.getStringBounds(currentToken2, null).getWidth()
                        + (int) metrics.getStringBounds(" ", null).getWidth();
                output2 = currentToken2;
            } else if ((currentLength2 + metrics.getStringBounds(currentToken2, null).getWidth()
                    + metrics.getStringBounds(" ", null).getWidth()) < 2000) {
                currentLength2 = currentLength2 + (int) metrics.getStringBounds(currentToken2, null).getWidth()
                        + (int) metrics.getStringBounds(" ", null).getWidth();
                output2 = output2 + " " + currentToken2;
            } else {
                dia.add(output2);
                currentLength2 = (int) metrics.getStringBounds(currentToken2, null).getWidth();
                output2 = currentToken2;
            }
        }
        dia.add(output2);
        return dia;
    }

    private void sortText(final String s) { //Organizes from text.txt
        final StringTokenizer st = new StringTokenizer(s);
        String currentToken;
        String output ="";
        int hashInt=1000000;
        
        while (st.hasMoreTokens()) {
            currentToken = st.nextToken();
            if (currentToken.equals("|")){ //Sorts by different dialogue lines
                hashbrown.put(hashInt, output);
                output ="";
                hashInt=1000000;
            } else if (currentToken.equals("#")) //Gets the x value it should be initiated by
            {
                try
                {
                hashInt = Integer.parseInt(st.nextToken().trim()); //idk if trim is necessary here
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println("NumberFormatException");
                }
            } else {    //Sorts stuffs
                if (output.equals("")){
                    output=currentToken;
                } else{
                    output = output + " " + currentToken;
                }   
            }
        }
        Integer[] key = hashbrown.keySet().toArray(new Integer[0]);
        hashKeys = key; //Creates array of hashmap keys
    }

    @Override
    public void notify(final KeyEvent e) {
        cereal.removeAllElements();
        count++;
        try {
            wrapText(fittedText.elementAt(count));
        } catch (ArrayIndexOutOfBoundsException f) {
            count=-1;  
            startDia = false;
        }
    }

    private void startDialogue(int x){ //Gets mario x and starts the dialogue
        int count =0;
        if (x>(hashKeys[count])){
            fittedText = fitText(hashbrown.get((hashKeys[count])));
            startDia = true;
            count++;
        }
    }

    @Override
    public void fireDialogueEvent(int xCoordinate) {
        // TODO Auto-generated method stub
        System.out.println("dialogue");
    }
}