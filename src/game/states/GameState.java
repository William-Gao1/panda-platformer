package game.states;
import game.entities.Mario;
import java.awt.Graphics;

/**
 * State that is active when the user is playing the main game  
 */
public class GameState implements State{
    Mario mario;

    public GameState(){
        mario = new Mario(0,0,35,45, "../../../resources/Panda.gif");
    }
    /**
     * @author Ricky
     */
    @Override
    public void tick(Graphics g) {
        

    }
    
}