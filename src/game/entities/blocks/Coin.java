package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.Side;

public class Coin extends Entity {
    private boolean gotten = false;
    public Coin(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        //solid = false;
    }

    @Override
    public void update() {
        

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        if(gotten == false && e.getClass()==Mario.class){
            solid = false;
            y-=35;
            gotten = true;
            Game.getGameState().getMario().addToScore(1);
            //area = null;
            System.out.println(tile);
            setImage("Resources//Images//Blocks//GottenCoin.gif");
            setDeleteTimer(680);
        }

    }

    @Override
    public Entity clone(){
        
        return new Coin(tile, width, height, imageLocationString);
    }
}