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

public class Dialogue implements KeyManagerListener {
    private final Image image = Toolkit.getDefaultToolkit().createImage("Resources//Dialogue//box.png");
    private final int x, y;
    
    public static final int LENGTH = 145;
    private String text = "";
    private String currentToken;
    private String output = "";
    private int count =-1;

    private final Vector<String> cereal = new Vector<String>(0, 1);
    private Vector<String> sortedText = new Vector<String>(0, 1);
    private Vector<Vector<String>> fittedText = new Vector<Vector<String>>(0,1);
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
        sortedText = sortText(text);
        
        for (final String s : sortedText) {
            
            fittedText.add(fitText(s));
        }
        
        System.out.println((fittedText.elementAt(0)).elementAt(5));

        // hashbrown.put(0, "hello");
        // hashbrown.get(0);
        Game.getKeyManager().listenFor(KeyEvent.VK_ENTER,this);
        
    }

    public void draw(final Graphics g) {
        g.drawImage(image, x, y, null);
        
        
        g.setFont(font);
        for (String s : cereal) {
            g.drawString(s, 75, 475 + 25 * cereal.indexOf(s));
            System.out.println(s);
            
        }
        System.out.println("");
        
    }

    private void wrapText(final String s) {
        final StringTokenizer st = new StringTokenizer(s);
        int currentLength = 0;
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
    private Vector<String> fitText(final String s) {
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
                    + metrics.getStringBounds(" ", null).getWidth()) < 1900) {
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

    private Vector<String> sortText(final String s) {
        final Vector<String> dia = new Vector<String>(0, 1);
        final StringTokenizer st = new StringTokenizer(s, "|");
        while (st.hasMoreTokens()) {
            dia.add(st.nextToken());

        }
        return dia;
    }

    

    @Override
    public void notify(final KeyEvent e) {
        cereal.removeAllElements();
        count++;
        try {
            wrapText((fittedText.elementAt(0)).elementAt(count));
            
        } catch (ArrayIndexOutOfBoundsException f) {
            count=-1;
        }
        
    }

    // private int switchDialogue(){
        
    // }


}