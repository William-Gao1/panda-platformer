package game.entities.blocks;

import game.entities.Entity;
import game.entities.Mario;
import util.Side;

public class Coin extends Entity {
    private boolean gotten = false;
    public Coin(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        solid = false;
    }

    @Override
    public void update() {
        

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        if(gotten == false && e.getClass()==Mario.class){
            y-=35;
            gotten = true;
            //area = null;
            setImage("Resources//Images//Blocks//GottenCoin.gif");
            setDeleteTimer(680);
        }

    }
}