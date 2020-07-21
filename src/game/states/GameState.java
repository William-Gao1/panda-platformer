package game.states;
import game.entities.Mario;
import game.Game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * State that is active when the user is playing the main game  
 */
public class GameState implements State{
    private Mario mario;
    private Game game;

    public GameState(Game game){
        mario = new Mario(0,0,35,45, "rage-game-improved//Resources//Pandas//Panda.gif");
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
        


        
        mario.update();
        mario.draw(g);

    }
    
}