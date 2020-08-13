package game.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import core.CollisionDetector;
import util.MarioDiesException;
import util.Side;

public abstract class MovableEntity extends Entity{
    protected double velX = 0, velY = 0, accelX = 0, accelY = 0;
    protected double origVelX = 0;
    protected double origVelY = 0;
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

    public void setOrigVelocities(){
        origVelX = velX;
        origVelY = velY;
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

    public double getVelX() {
        return velX;
    }
    
    public double getVelY() {
        return velY;
    }

    @Override
    public void update() throws MarioDiesException{
        checkShouldFall();

        velX+=accelX;
        velY+=accelY;
        if(velX!=0||velY!=0)
        move((int)Math.round(velX),(int)Math.round(velY));
    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException{
        
    }

    protected void stopMovement(){
        velX = 0;
        velY = 0;
        accelX = 0;
        accelY = 0;
    }

    protected  void checkShouldFall() throws MarioDiesException {
        Vector<Entity> bottomTiles = CollisionDetector.getBottomBlocks(this,0,1);
        
        if(accelY==0.0&&bottomTiles.size()==0){
            accelY=0.4;
        }
        else{
            for(Entity e: bottomTiles){
                e.hitSide(this,new Side(Side.TOP));
                
            }
        }
    }
    @Override
    public Entity clone(){
        try {
            Constructor<? extends MovableEntity> c = this.getClass().getConstructor(int.class, int.class, int.class,
                    String.class);
            MovableEntity e = c.newInstance(tile,width,height,imageLocationString);
            e.setVelocity(origVelX, origVelY);
            e.setOrigVelocities();
            isReset = true;

            return e;
        } catch (NoSuchMethodException n) {
            System.out.println("No Constructor found in object cloning");
            n.printStackTrace();
        } catch (InstantiationException e1) {
            System.out.println("Instantiation exception in object cloning");
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            System.out.println("Illegal Access exception in object cloning");
            e1.printStackTrace();
        } catch (IllegalArgumentException e1) {
            System.out.println("Illegal Argument exception in object cloning");
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            System.out.println("Invocation Target exception in object cloning");
            e1.printStackTrace();
        }
        return null;
    }
    
}