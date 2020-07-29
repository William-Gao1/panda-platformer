package game.entities;

import util.Side;

public class Coin extends Entity {
    private boolean gotten = false;
    public Coin(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        solid = false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        if(gotten == false && e.getClass()==Mario.class){
            y-=35;
            gotten = true;
            area = null;
            setImage("Resources//GottenCoin.gif");
            setDeleteTimer(680);
        }

    }
}