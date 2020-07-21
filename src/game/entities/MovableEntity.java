package game.entities;

public abstract class MovableEntity extends StaticEntity{
    protected double velX = 0, velY = 0, accelX = 0, accelY = 0;
    public MovableEntity(int startX, int startY, int width, int height, String imageLocation) {
        super(startX, startY, width, height, imageLocation);
        
    }

    protected void setVelocity(double velX, double velY){
        this.velX = velX;
        this.velY = velY;
    }

    protected void setAcceleration(double accelX, double accelY){
        this.accelX = accelX;
        this.accelY = accelY;
    }

    protected void move(int deltaX, int deltaY){
        area.translate(deltaX, deltaY);
        x+=deltaX;
        y+=deltaY;
    }

    protected void moveTo(int newX, int newY){
        area.translate(newX-x,newY-y);
        x = newX;
        y = newY;
        
    }

    @Override
    public void update(){
        velX+=accelX;
        velY+=accelY;
        move((int)Math.round(velX+accelX),(int)Math.round(velY+accelY));
    }

    protected void stopMovement(){
        velX = 0;
        velY = 0;
        accelX = 0;
        accelY = 0;
    }
    
}