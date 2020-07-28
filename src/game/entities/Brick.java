package game.entities;

import util.Side;

public class Brick extends Entity{
    public Brick(int tile, int width, int height, String imageLocation){
        super(tile, width, height, "Resources//Brick.png");
        
    }


    @Override
	public void update() {
		// TODO Auto-generated method stub
		
    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        // TODO Auto-generated method stub

    }
}