package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ListIterator;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.FontFormatException;

import game.Game;
import util.KeyManagerListener;

public class SettingState implements State, KeyManagerListener {
    private final Image TRIANGLE_SELECTOR = Toolkit.getDefaultToolkit().createImage("Resources//Images//MenuSelector.png");
    private Font mainMenuFont;
    private Game game;
    private final Integer[] SELECTOR_LOCATIONS = {232,282,332};
    private int index = 0;
    private int selectorYPos = SELECTOR_LOCATIONS[0];

    public SettingState(Game g){
        game = g;
        Game.getKeyManager().listenFor(new int[] {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_ENTER}, this);
        
        try{
            mainMenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("Resources//Fonts//Mario.ttf")).deriveFont(24f);
        }
        catch(IOException f){
            f.printStackTrace();
        }
        catch (FontFormatException f){
            f.printStackTrace();
        }
    }

    @Override
    public void notify(KeyEvent e) {
        if(Game.getState()==this){
        if(e.getKeyCode() == KeyEvent.VK_UP&&index>0){
            index--;

        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN&&index<SELECTOR_LOCATIONS.length-1){
            index++;
        }
        else if (e.getKeyCode()==KeyEvent.VK_ENTER){
            if(index==0){
                toggleSound();
            }
            else if(index ==1){
                toggleMusic();
            }
            else if (index == 2){
                Game.setState(Game.getMainMenuState());
            }
        }
    }
    }

    @Override
    public void tick(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,game.getWidth(),game.getHeight());
        g.setColor(Color.black);
        g.setFont(mainMenuFont);
        g.drawString("Toggle Sound", 319, 250);
        g.drawString("Toggle Music",319,300);
        g.drawString("Main Menu", 325,350);
        g.drawImage(TRIANGLE_SELECTOR, 300, SELECTOR_LOCATIONS[index], null);


    }

    private void toggleSound(){

    }

    private void toggleMusic(){

    }
    
}