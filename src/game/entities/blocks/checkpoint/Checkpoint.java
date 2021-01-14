package game.entities.blocks.checkpoint;

import game.Game;
import game.entities.Entity;
import util.Side;

public class Checkpoint extends Entity {
    private boolean gotten = false;
    private CheckpointHandler checkPointHandler;
    public Checkpoint(int tile, int width, int height, String imageLocation){
        super(tile,width,height,imageLocation);
        
        solid = false;
    }

    @Override
    public void update(){
        if(gotten == false && Game.getGameState().getMario().getArea().intersects(area)){
            gotten = true;
            checkPointHandler.setCurrentCheckpoint(tile);
            setImage("Resources//Images//AfterCheckpoint.gif");
        }
    }

    @Override
    public void hitSide(Entity e, Side side){
        
    }

    public void setCheckpointhandler(CheckpointHandler c){
        checkPointHandler = c;
    }

    @Override
    public Entity clone() {
        return this;
    }
}