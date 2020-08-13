package game.entities.blocks;

import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.projectiles.BulletBillWall;
import util.Side;



public class BulletBillWallActivator extends Entity{
    private boolean activated = false;
    private BulletBillWall wall;
    public BulletBillWallActivator(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update(){
        if(!activated&&Game.getGameState().getMario().getX()>=x){
            activated = true;
            wall = new BulletBillWall(tile-10*CollisionDetector.COLUMN_HEIGHT,35,CollisionDetector.TILE_SIDE_LENGTH*CollisionDetector.COLUMN_HEIGHT,"");
            wall.setVelocity(1, 0);
            Game.getGameState().projectiles.add(wall);
        }
        
    }

    

    @Override
    public void hitSide(Entity e, Side side){

    }
    
}