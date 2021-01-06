package util;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.KeyEvent;

/**
 * KeyManager class detects all key inputs by user
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, enter, space,escape;
    private int horizontalDir = 0;
    private int verticalDir = 0;
    private HashMap<Integer,Vector< KeyManagerListener>> listeners = new HashMap<Integer, Vector<KeyManagerListener>>(0,1);

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
        if(keys[e.getKeyCode()]==false){
            notifyListeners(e);
        }
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            verticalDir = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            verticalDir = 1;
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            escape = true;
        }
        else{
            escape = false;
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

    /**
     * Adds an object to a list of objects that will be notified when a certain keys are pressed.
     * The object will only be notified once one of the keys are pressed and will not be notified again until
     * the key is released and pressed again
     * @param events        The list of key codes of the key events to be listening for
     * @param listener      The object to be notified (this object must implement KeyManagerListener)
     * @author Will
     */
    public void listenFor(int[] events, KeyManagerListener listener){
        for(Integer i : events){
            if(listeners.get(i)==null){
                listeners.put(i,new Vector<KeyManagerListener>(0,1));

            }
            Vector<KeyManagerListener> temp = listeners.get(i);
            temp.add(listener);
                listeners.put(i,temp);
            
        }
    }

    /**
     * Adds an object to a list of objects that will be notified when a certain key is pressed.
     * The object will only be notified once the key is pressed and will not be notified again until
     * the key is released and pressed again
     * @param event         The key code of the key event to be listening for
     * @param listener      The object to be notified (this object must implement KeyManagerListener)
     * @author Will
     */
    public void listenFor(Integer event, KeyManagerListener listener){
        
            if(listeners.get(event)==null){
                listeners.put(event,new Vector<KeyManagerListener>(0,1));

            }
            Vector<KeyManagerListener> temp = listeners.get(event);

            temp.add(listener);
                listeners.put(event,temp);
            
        
    }

    /**
     * Notifies all KeyManagerListeners that are listening for a certain key press
     * @param e     The key event to notify the listeners of
     * @author Will
     */
    private void notifyListeners(KeyEvent e){
        Vector<KeyManagerListener> temp = listeners.get(e.getExtendedKeyCode());
        if(temp!=null){
            for(KeyManagerListener k : temp){
                k.notify(e);
            }
        }
    }
}