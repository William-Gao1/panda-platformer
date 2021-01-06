package game;

import util.KeyManager;
import util.LevelReader;
import util.Display;
import game.states.State;
import game.entities.factories.BlockFactory;
import game.entities.factories.EnemyFactory;
import game.entities.factories.ProjectileFactory;
import game.states.GameState;
import game.states.MainMenuState;
import game.states.SettingState;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;

/**
 * Game class that handles the overall game mechanics
 */
public class Game implements Runnable{
    private Display display;
    private int width, height;
    private final String TITLE;
    private static KeyManager keyManager;
    private Thread thread;
    private boolean running;
    private final int FPS = 60;
    private static State currentState = null;
    private BufferStrategy bs;
    private static GameState gameState;
    private Graphics g;
    private BlockFactory levelOneBlockFactory = new BlockFactory();
    private EnemyFactory levelOneEnemyFactory = new EnemyFactory();
    private ProjectileFactory levelOneProjectileFactory = new ProjectileFactory();
    private boolean trackTime = false;
    private long time = 0;
    private static MainMenuState mainMenuState;
    private static SettingState settingState;
    private boolean gamePause = false;
    //State mainMenuState;
    //State settingsState;

    /**
     * Constructor that generates the window that the 
     * game will be played in
     * 
     * @param startTitle         title that goes on the window of the game
     * @param startWidth    width of the window
     * @param startHeight   height of the window
     * @author Ricky
     */

    public Game (String startTitle, int startWidth, int startHeight){
        width = startWidth;
        height = startHeight;
        TITLE = startTitle;
        
    }

    /**
     * Starts running the game
     * @author Ricky
     */
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Main loop that runs while the game is running
     * @author Ricky
     */
    @Override
    public void run(){
        init();

        double timePerTick = 1000000000/FPS; //time in nano seconds
        double delta = 0;
        long now;
        long lastTime= System.nanoTime();
        long timer =0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1){
                //render();
                if(trackTime){
                    time = System.nanoTime();
                }
                tick();
                if(trackTime &&System.nanoTime()-time!=0) // 16,666,666 nanosecond timeframe to complete all frame computations max: ~2,000,000
                    System.out.println(System.nanoTime()-time);
                
            
            
            ticks++;
            delta=0;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                //System.out.println(Var.projectiles.size());
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    

    /**
     * Initializes all the variables
     * @author Ricky
     */

     private void init(){
        keyManager = new KeyManager();

        gameState= new GameState(this);

        LevelReader.getBlocks("Resources//Levels/Lvl1.txt",levelOneBlockFactory,levelOneEnemyFactory,levelOneProjectileFactory);
        ((GameState)gameState).createClones();
        

        display = new Display(TITLE,width,height);
        display.getFrame().addKeyListener(keyManager);
        mainMenuState = new MainMenuState(this);
        settingState = new SettingState(this);
        currentState = mainMenuState;
        
        
        
     }

     /**
      * stops the thread and the game
      *@author Ricky
      */
    private synchronized void stop(){
        if (running==false)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that gets called every frame
     * @author Ricky
     */
    private void tick(){
        
        display.getFrame().requestFocus();
        keyManager.tick();

        bs = display.getCanvas().getBufferStrategy();
        if (bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        if(!gamePause)
            currentState.tick(g);
        bs.show();
        g.dispose();
        // if (mario.x > maxX){
        //     maxX = mario.x;
        // }
             
        
    }

    /**
     * @author Ricky
     */
    public static KeyManager getKeyManager(){
        return keyManager;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public static GameState getGameState(){
        return gameState;
    }

    public static SettingState getSettingState(){
        return settingState;
    }

    public static MainMenuState getMainMenuState(){
        return mainMenuState;
    }

    public static void setState(State s){
        currentState = s;
    }

    public static State getState(){
        return currentState;
    }

    public void pauseGame(){
        gamePause=true;
    }

    public void resumeGame(){
        gamePause = false;
    }

    

}