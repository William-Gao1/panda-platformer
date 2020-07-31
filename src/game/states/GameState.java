package game.states;

import game.entities.Entity;
import game.entities.Mario;
import game.Game;
import game.dialogue.Dialogue;
import game.dialogue.DialogueEventListener;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Vector;

/**
 * State that is active when the user is playing the main game  
 */
public class GameState implements State{
    private Mario mario;
    private Game game;
    private Dialogue dialogue;
    private Vector<DialogueEventListener> dialogueEventListeners = new Vector<DialogueEventListener>(0,1);
    public static HashMap<Integer,Entity> blocks = new HashMap<Integer,Entity>(0,1);
    
    public GameState(Game game){
        mario = new Mario(1,35,45, "Resources//Images//Pandas//Panda.gif");
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

    public Mario getMario(){
        return mario;
    }

    public void listenForDialogueTrigger(DialogueEventListener d){
        dialogueEventListeners.add(d);
    }

    public void notifyDialogueEventListeners(int dialogueBoxEventCoordinate){
        for(DialogueEventListener d:dialogueEventListeners){
            d.fireDialogueEvent(dialogueBoxEventCoordinate);
        }
        System.out.println("dialogue");
    }
    
}