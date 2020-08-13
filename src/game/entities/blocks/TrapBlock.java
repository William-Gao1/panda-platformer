package game.entities.blocks;

import game.entities.Entity;
import util.Side;

public class TrapBlock extends Entity {
    public TrapBlock (int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }
    @Override
    public void update(){}

    @Override
    public void hitSide(Entity e, Side side){}
    @Override
    public void breakBlock(){
        setDeleteTimer(0);
    }
}