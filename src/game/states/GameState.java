package game.states;

import game.entities.Entity;
import game.entities.Mario;
import game.entities.blocks.checkpoint.CheckpointHandler;
import game.entities.enemies.Enemy;
import game.entities.projectiles.Projectile;
import util.MarioDiesException;
import game.Game;
import game.dialogue.Dialogue;
import game.dialogue.DialogueEventListener;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import core.Camera;

/**
 * State that is active when the user is playing the main game  
 */
public class GameState implements State{
    private Mario mario;
    private Game game;
    private Dialogue dialogue;
    private Camera camera;
    private ReentrantLock lock = new ReentrantLock();
    
    private Vector<DialogueEventListener> dialogueEventListeners = new Vector<DialogueEventListener>(0,1);
    public HashMap<Integer,Entity> blocks = new HashMap<Integer,Entity>(0,1);
    private HashMap<Integer, Entity> blockClones = new HashMap<Integer,Entity>(0,1);
    public Vector<Projectile> projectiles = new Vector<Projectile>(0,1);
    public Vector<Enemy> enemies = new Vector<Enemy>(0,1); 
    private Vector<Enemy> enemyClones = new Vector<Enemy>(0,1);
    private CheckpointHandler checkpointHandler = new CheckpointHandler();
    
    public GameState(Game game){
        dialogue = new Dialogue(25,420);
        listenForDialogueTrigger(dialogue);
        this.game = game;
        camera = new Camera(game.getWidth(), game.getHeight());
        
        
        


    }
    
    /**
     * @author Ricky
     * @throws MarioDiesException
     */
    @Override
    public void tick(Graphics g) {
        
        try{
        
        g.clearRect(0,0, game.getWidth(), game.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0,0,game.getWidth(),game.getHeight());
        
        
        mario.update();

        for(Entity e:blocks.values()){
            //System.out.println(e.getClass());
            e.update();
            e.draw(g,camera.getxOffset(),camera.getyOffset());
            if(e.isReset()){
                break;
            }
        }
        for(int i = 0; i< enemies.size();i++){
            
            enemies.elementAt(i).draw(g,camera.getxOffset(),camera.getyOffset());
            enemies.elementAt(i).update();
            
        }
        for(int i = 0; i < projectiles.size();i++){
            projectiles.elementAt(i).draw(g,camera.getxOffset(),camera.getyOffset());

            projectiles.elementAt(i).update();
        }
        mario.draw(g,camera.getxOffset(),camera.getyOffset());
        camera.centre(mario);
    }
    catch(MarioDiesException e){
        reset();
    }
    


    }
    
        //dialogue.draw(g);

    

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
        
    }
    public void setMario(Mario m){
        mario = m;
        camera.centreAround(mario);
    }
	public void reset() {
        

        
        blocks.clear();
        projectiles.clear();
        enemies.clear();
        for(Integer i : blockClones.keySet()){
            blocks.put(i,blockClones.get(i).clone());
        }
        for(Enemy e : enemyClones){
            enemies.add((Enemy)e.clone());
        }
        checkpointHandler.resetToLastCheckpoint(this);
    }
    

    
    
    public void createClones(){
        for(Integer i: blocks.keySet()){
            blockClones.put(i,blocks.get(i).clone());
            blocks.get(i).setReset(false);
        }
        for(Enemy e : enemies){
            enemyClones.add(e);

        }
    }

    public void setCheckpointHandler(CheckpointHandler c){
        checkpointHandler = c;
    }

    public CheckpointHandler getCheckpointHandler(){
        return checkpointHandler;
    }

    public Camera getCamera(){
        return camera;
    }
    
}