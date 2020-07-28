package game.entities;

import util.Side;

public abstract class MovableEntity extends Entity{
    protected double velX = 0, velY = 0, accelX = 0, accelY = 0;
    public MovableEntity(int startX, int startY, int width, int height, String imageLocation) {
        super(startX, startY, width, height, imageLocation);
        
    }

    public MovableEntity(int tile, int height, int width, String imageLocation){
        super(tile, height, width, imageLocation);
    }

    protected void setVelocity(double velX, double velY){
        this.velX = velX;
        this.velY = velY;
    }

    protected void setAcceleration(double accelX, double accelY){
        this.accelX = accelX;
        this.accelY = accelY;
    }

    public void move(int deltaX, int deltaY){
        area.translate(deltaX, deltaY);
        x+=deltaX;
        y+=deltaY;
    }

    public void moveTo(int newX, int newY){
        area.translate(newX-x,newY-y);
        x = newX;
        y = newY;
        
    }

    @Override
    public void update(){
        velX+=accelX;
        velY+=accelY;
        move((int)Math.round(velX),(int)Math.round(velY));
    }

    @Override
    public void hitSide(Entity e, Side side){
        if (side.getSide() == Side.TOP || side.getSide() == Side.BOTTOM){

        }
    }

    protected void stopMovement(){
        velX = 0;
        velY = 0;
        accelX = 0;
        accelY = 0;
    }
    
}