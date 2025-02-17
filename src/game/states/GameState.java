package game.states;

import game.entities.Entity;
import game.entities.Mario;
import game.entities.blocks.checkpoint.CheckpointHandler;
import game.entities.enemies.Enemy;
import game.entities.projectiles.Projectile;
import util.LevelReader;
import util.MarioDiesException;
import game.Game;
import game.dialogue.Dialogue;
import game.dialogue.DialogueEventListener;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.Toolkit;

import core.Camera;

/**
 * State that is active when the user is playing the main game
 */
public class GameState implements State {
    private Mario mario;
    private Game game;
    private Dialogue dialogue;
    private Camera camera;
    private ReentrantLock lock = new ReentrantLock();

    private Vector<DialogueEventListener> dialogueEventListeners = new Vector<DialogueEventListener>(0, 1);
    public HashMap<Integer, Entity> blocks = new HashMap<Integer, Entity>(0, 1);
    private HashMap<Integer, Entity> blockClones = new HashMap<Integer, Entity>(0, 1);
    public Vector<Projectile> projectiles = new Vector<Projectile>(0, 1);
    public Vector<Enemy> enemies = new Vector<Enemy>(0, 1);
    public Vector<Integer> toBeDeleted = new Vector<Integer>(0,1);
    private Vector<Enemy> enemyClones = new Vector<Enemy>(0, 1);
    private CheckpointHandler checkpointHandler = new CheckpointHandler();
    private boolean gamePause = false;
    private boolean isDead = false;
    private long deathTime;
    private Image backgroundImg = Toolkit.getDefaultToolkit().createImage("Resources//Images//plains_background.png");

    public GameState(Game game){
        dialogue = new Dialogue(25,430);
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
        
        if (!gamePause) {
            if (Game.getKeyManager().escape) {
                Game.setState(Game.getSettingState());
            }
            try {

                g.clearRect(0, 0, game.getWidth(), game.getHeight());
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, game.getWidth(), game.getHeight());
                //g.drawImage(backgroundImg, 0, 0, game.getWidth(), game.getHeight(), null); //This is for the background

                mario.update();

                for (Entity e : blocks.values()) {
                    // System.out.println(e.getClass());
                    e.update();
                    e.draw(g, camera.getxOffset(), camera.getyOffset());
                    if (e.isReset()) {
                        break;
                    }
                }

                deleteBlocks();
                for (int i = 0; i < enemies.size(); i++) {

                    enemies.elementAt(i).draw(g, camera.getxOffset(), camera.getyOffset());
                    enemies.elementAt(i).update();

                }
                for (int i = 0; i < projectiles.size(); i++) {
                    projectiles.elementAt(i).draw(g, camera.getxOffset(), camera.getyOffset());

                    projectiles.elementAt(i).update();
                }
                // mario.draw(g, camera.getxOffset(), camera.getyOffset());
                camera.centre(mario);
            }

            catch (MarioDiesException e) {
                
                mario.setImage(Mario.dyingMario);
                pauseGame();
                isDead = true;
                deathTime = System.currentTimeMillis();
                
                //reset();
    }
    
        }
    
   

        
        if(isDead){
            g.clearRect(0, 0, game.getWidth(), game.getHeight());
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, game.getWidth(), game.getHeight());
                for (Entity en : blocks.values()) {
                    // System.out.println(e.getClass());

                    en.draw(g, camera.getxOffset(), camera.getyOffset());

                }
                for (int i = 0; i < enemies.size(); i++) {

                    enemies.elementAt(i).draw(g, camera.getxOffset(), camera.getyOffset());

                }
                for (int i = 0; i < projectiles.size(); i++) {
                    projectiles.elementAt(i).draw(g, camera.getxOffset(), camera.getyOffset());

                }
                if(System.currentTimeMillis()-deathTime>2000){
                    Mario.addDeath();
                    isDead = false;
                    resumeGame();
                    Mario.dyingMario =  Toolkit.getDefaultToolkit().createImage("Resources//Images//Pandas//Death-Animation.gif");
                    reset();
                }
            }
            mario.draw(g, camera.getxOffset(), camera.getyOffset());
            dialogue.draw(g);
            g.setFont(Game.getSettingState().getFont());
            g.setColor(Color.BLACK);
            g.drawString("Deaths: " + String.valueOf(Mario.getDeaths()), 100, 100);
            g.drawString("Score: " + String.valueOf(mario.getScore()), 500, 100);
        }
    
        

    

    private void deleteBlocks() {
        for (int i : toBeDeleted){
            blocks.remove(i);
        }
        toBeDeleted.clear();
    }

    public Mario getMario() {
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
        blocks = new HashMap<Integer, Entity>(0, 1);
        projectiles = new Vector<Projectile>(0, 1);
        enemies = new Vector<Enemy>(0, 1);
        System.out.println(Game.level);
        LevelReader.getBlocks(Game.LEVEL_ORDER[Game.level],game.levelOneBlockFactory,game.levelOneEnemyFactory,game.levelOneProjectileFactory);
        checkpointHandler.resetToLastCheckpoint(this);
        // System.out.println("reset");
        // dialogue.diaCount = 0;
        // dialogue.diaPicCount = -1;
        // toBeDeleted.clear();

        // checkpointHandler.resetToLastCheckpoint(this);
        // blocks = new HashMap<Integer, Entity>(0, 1);
        // projectiles = new Vector<Projectile>(0, 1);
        // enemies = new Vector<Enemy>(0, 1);
        // for(Integer i : blockClones.keySet()){
        //     blocks.put(i,blockClones.get(i).clone());
        // }
        // for(Enemy e : enemyClones){
        //     enemies.add((Enemy)e.clone());
        // }

        // System.out.println(projectiles.size());
        
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

    public void pauseGame(){
        gamePause = true;
    }

    public void resumeGame(){
        gamePause = false;
    }

	public Game getGame() {
		return game;
	}

	public void queueDelete(int tile) {
        toBeDeleted.add(tile);
	}

	
    
}