package game.states;

import java.awt.Graphics;

/**
 * Interface that defines a state (e.g GameState, PauseState)
 * @author Will
 */
public interface State {
    public void tick(Graphics g);

	
}