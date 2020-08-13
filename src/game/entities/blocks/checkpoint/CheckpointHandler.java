package game.entities.blocks.checkpoint;


import game.entities.Mario;
import game.states.GameState;

public class CheckpointHandler {
    private int marioSpawnTile = 0;
    private Checkpoint currentCheckpoint;

    public void addCheckpoint(Checkpoint c){
        c.setCheckpointhandler(this);

    }

    public void resetToLastCheckpoint(GameState g){
        if(currentCheckpoint==null){
            
            g.setMario(new Mario(marioSpawnTile,35,45,"Resources//Images//Pandas//Panda.gif"));
        }
        else{
            
            g.setMario(new Mario(currentCheckpoint.getTile(),35,45,"Resources//Images//Pandas//Panda.gif"));
        }
    }

    public void setMarioSpawn(int tile){
        marioSpawnTile = tile;
    }

    public void setCurrentCheckpoint(Checkpoint c){
        currentCheckpoint = c;
    }

    
}