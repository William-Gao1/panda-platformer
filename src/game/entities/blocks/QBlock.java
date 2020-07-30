package game.entities.blocks;

import game.entities.Entity;
import game.entities.Mario;
import util.Side;

public class QBlock extends Entity {
    private boolean gotten = false;
    public QBlock(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update() {
        

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        if(gotten == false&&e.getClass()==Mario.class&&hitSide.getSide()==Side.TOP){
            setImage("resources//BrokenQBlock.png");
            gotten = true;
        }

    }
}