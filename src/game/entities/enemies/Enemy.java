package game.entities.enemies;

import java.util.Vector;

import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import game.entities.MovableEntity;
import util.MarioDiesException;
import util.Side;

public class Enemy extends MovableEntity {

    public Enemy (int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update() throws MarioDiesException {
        checkCollisions();
        super.update();
        
        
        // for(Entity e: CollisionDetector.getBlockCollisions(this)){
        //     CollisionDetector.resolveCollision(this, (int)velX, (int)Math.round(velY), e);
        // }
        
        
    }

    private boolean checkCollisions() throws MarioDiesException {
        Vector<Entity> entity = CollisionDetector.getBlockCollisions(this);
        if(entity.size()==0){
            for(Enemy e : Game.getGameState().enemies){
            if(e!=this&&e.getArea()!=null){
                if(area.intersects(e.getArea())){
                    turnAround();
                    
                   
                    CollisionDetector.resolveCollision(this, (int)velX, (int)velY, e);
                   
                }

            }
        }
        if(area!=null&&area.intersects(Game.getGameState().getMario().getArea())){
            CollisionDetector.resolveCollision(this,(int)velX,(int)velY,Game.getGameState().getMario());
        }
            return false;
        }
        else{
            double velX1 = velX;
            double velY1 = velY;
            for (Entity e : entity){
            CollisionDetector.resolveCollision(this, (int)Math.round(velX1), (int)Math.round(velY1), e);
            }
            return false;
        }
    }

    public void turnAround(){
        velX*=-1;
    }

    public void setVel(int xVel, int yVel){
        velX = xVel;
        velY = yVel;
    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException{
        if(e.getClass()==Mario.class&&solid == true){
            if(side.getSide()!=Side.TOP){
            killMario();
            }
            else{
                Game.getGameState().getMario().hitSide(this, new Side (Side.BOTTOM));

                die();

                
            }
        }
        

        else if(side.getSide() == Side.LEFT||side.getSide() == Side.RIGHT){
            turnAround();
        }
        else{
            velY = 0;
            accelY = 0;
        }
    }

    public void die(){
        Game.getGameState().enemies.remove(this);

    }

    @Override
    protected void delete(){
        Game.getGameState().enemies.remove(this);
    }
    
}