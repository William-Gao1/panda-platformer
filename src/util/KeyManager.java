package util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * KeyManager class detects all key inputs by user
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right, enter,space;

    /**
     * @author Ricky
     */
    public KeyManager(){
        keys = new boolean[256];
    }

    /**
     * @author Ricky
     */

    public void tick(){
        up = keys[KeyEvent.VK_UP]||keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN]||keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT]||keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT]||keys[KeyEvent.VK_D];
        enter = keys[KeyEvent.VK_ENTER];
        space = keys[KeyEvent.VK_SPACE];
    }
    
    /**
     * @author Ricky
     */
    public void keyPressed(KeyEvent e){
        keys[e.getKeyCode()] = true;
    }

    /**
     * @author Ricky 
     */
    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()] = false;
    }

    /**
     * @author Ricky
     */
    public void keyTyped(KeyEvent e){

    }
}