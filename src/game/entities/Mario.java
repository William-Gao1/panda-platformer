package game.entities;

import game.Game;
import util.Side;

import java.awt.Graphics;
import java.util.Vector;

import core.CollisionDetector;

public class Mario extends MovableEntity {
    private final int VEL_X_CAP = 5;
    private final int VEL_Y_CAP = 5;
    //Entity brick = EntityFactory.getEntity('b',56);
    public Mario(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        //GameState.blocks.put(56,brick);
    }


    /**
     * Method that updates mario every frame
     */
    @Override
    public void update() {
        accelX = Game.getKeyManager().getHorizontalDir();
        accelY = Game.getKeyManager().getVerticalDir();
        if(Game.getKeyManager().getHorizontalDir() == 0){
            velX = 0;
        }
        if(Game.getKeyManager().getVerticalDir() == 0){
            velY = 0;
        }
        assureVelIsCapped();
        
        // if(CollisionDetector.willCollide(this, (int)Math.round(velX), (int)Math.round(velY),brick)){
        //     CollisionDetector.resolveCollision(this, (int)Math.round(velX), (int)Math.round(velY), brick);
        //     velY = 0;
        //     accelY = 0;
        //     System.out.println("Resolved");
        // }
        super.update();
        checkCollisions();
    }

    /**
     * Method that caps mario's speed horizontally and vertically
     */
    private void assureVelIsCapped(){
        velX = Math.abs(velX) >= VEL_X_CAP ? VEL_X_CAP*velX/Math.abs(velX) : velX;
        velY = Math.abs(velY) >= VEL_Y_CAP ? VEL_Y_CAP*velY/Math.abs(velY) : velY;

    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y,null);
        //g.drawImage(brick.image, brick.getX(), brick.getY(),null);
    }

    @Override
    public void hitSide(Entity e, Side side) {
        // TODO Auto-generated method stub

    }

    private boolean checkCollisions(){
        Vector<Entity> entities = CollisionDetector.getBlockCollisions(this);
        
        if(entities!=null){
            for(Entity e : entities){
                CollisionDetector.resolveCollision(this, (int)Math.round(velX), (int)Math.round(velY), e);
            }
            return true;
        }else{
            return false;
        }
        // if(area.intersects(brick.getArea().getBounds())){
        //     CollisionDetector.resolveCollision(this, (int)Math.round(velX), (int)Math.round(velY), brick);
    
        // }
        // return true;
    }

}