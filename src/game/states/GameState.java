package game.states;

import game.entities.Entity;
import game.entities.Mario;
import game.Game;
import game.dialogue.Dialogue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

/**
 * State that is active when the user is playing the main game  
 */
public class GameState implements State{
    private Mario mario;
    private Game game;
    private Dialogue dialogue;
    public static HashMap<Integer,Entity> blocks = new HashMap<Integer,Entity>(0,1);
    
    public GameState(Game game){
        mario = new Mario(1,35,45, "Resources//Pandas//Panda.gif");
        dialogue = new Dialogue(25,420);
        this.game = game;
        


    }
    /**
     * @author Ricky
     */
    @Override
    public void tick(Graphics g) {
        g.clearRect(0,0, game.getWidth(), game.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0,0,game.getWidth(),game.getHeight());
        


        for(Entity e:blocks.values()){
            e.update();
            e.draw(g);
        }
        mario.update();
        mario.draw(g);
        dialogue.draw(g);

    }
    
}