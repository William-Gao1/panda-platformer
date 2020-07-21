package util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * KeyManager class detects all key inputs by user
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, enter, space;
    private int horizontalDir = 0;
    private int verticalDir = 0;

    /**
     * @author Ricky
     */
    public KeyManager() {
        keys = new boolean[256];
    }

    /**
     * @author Ricky
     */

    public void tick() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        enter = keys[KeyEvent.VK_ENTER];
        space = keys[KeyEvent.VK_SPACE];
        if(!up&&!down){
            verticalDir = 0;
        }
        if(!left&&!right){
            horizontalDir = 0;
        }
    }

    /**
     * @author Ricky
     */
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            verticalDir = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            verticalDir = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            horizontalDir = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            horizontalDir = -1;
        }
    }

    /**
     * @author Ricky
     */
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    /**
     * @author Ricky
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * @author Will
     */
    public int getHorizontalDir(){
        return horizontalDir;
    }

    /**
     * @author Will
     */
    public int getVerticalDir(){
        return verticalDir;
    }
}