package game.entities.blocks;

import game.Game;
import game.entities.Entity;

import util.Side;

public class Brick extends Entity{
    public Brick(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
        
    }


    @Override
	public void update() {
		
		
    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }
    @Override
    public void breakBlock(){
        Game.getGameState().blocks.remove(tile);
    }

   
}