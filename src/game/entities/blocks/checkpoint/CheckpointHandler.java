package game.entities.blocks.checkpoint;


import game.Game;
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
            Mario m = new Mario(marioSpawnTile,35,45,"Resources//Images//Pandas//Panda.gif");
            m.addToScore(Game.getGameState().getMario().getScore());
            g.setMario(m);
        }
        else{
            Mario m = new Mario(currentCheckpoint.getTile(),35,45,"Resources//Images//Pandas//Panda.gif");
            m.addToScore(Game.getGameState().getMario().getScore());
            g.setMario(m);
        }
    }

    public void setMarioSpawn(int tile){
        marioSpawnTile = tile;
    }

    public void setCurrentCheckpoint(Checkpoint c){
        currentCheckpoint = c;
    }

    
}