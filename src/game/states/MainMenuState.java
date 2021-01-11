package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

import javax.swing.SpinnerListModel;

import game.Game;
import util.KeyManagerListener;

public class MainMenuState implements State,KeyManagerListener {
    private final Image TRIANGLE_SELECTOR = Toolkit.getDefaultToolkit().createImage("Resources//Images//MenuSelector.png");
    private final SpinnerListModel SELECTOR_LOCATIONS = new SpinnerListModel(new Integer[] {232,282,332});
    private Game game;
    private Font mainMenuFont;

    public MainMenuState(Game g){
        Game.getKeyManager().listenFor(new int[] {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_ENTER}, this);
        this.game = g;
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
    public void tick(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,game.getWidth(),game.getHeight());
        g.setColor(Color.black);
        g.setFont(mainMenuFont);
        g.drawString("Start Game", 325, 250);
        g.drawString("Options",325,300);
        g.drawString("Exit", 325,350);
        g.drawImage(TRIANGLE_SELECTOR, 300, (int)SELECTOR_LOCATIONS.getValue(), null);

    }

    @Override
    public void notify(KeyEvent e) {
        if(Game.getState()==this){
        if(e.getKeyCode()==KeyEvent.VK_UP&&SELECTOR_LOCATIONS.getPreviousValue()!=null){
            SELECTOR_LOCATIONS.setValue(SELECTOR_LOCATIONS.getPreviousValue());
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN&&SELECTOR_LOCATIONS.getNextValue()!=null){
            SELECTOR_LOCATIONS.setValue(SELECTOR_LOCATIONS.getNextValue());
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if((int)SELECTOR_LOCATIONS.getValue()==232){
                Game.getGameState().getGame().restartGame();
                System.out.println("Start");
            }
            else if((int)SELECTOR_LOCATIONS.getValue()==332){
              
                Runtime.getRuntime().exit(0);
            }
            else if ((int)SELECTOR_LOCATIONS.getValue()==282){
                Game.setState(Game.getSettingState());
            }
        }
    }
    
    }
    
}