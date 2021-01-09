package core;

import game.entities.Entity;
import game.entities.projectiles.PiranhaPlant;

public class Camera {
    private float xOffset, yOffset;
    private int maxX, maxY,width,height;
    private static final int UPPER_Y_BOUND = 100;
    private static final int LOWER_Y_BOUND = 100;
    
    boolean turnOn;
    
 
    public Camera(int width, int height) {
       this.width = width;
       this.height = height;
       
 
       
    }

    public void centreAround(Entity e){
        xOffset = e.getX() - 450;
      yOffset = e.getY() - 307;
      maxX = (int)xOffset+width/2;
      maxY = (int)yOffset+height/2;
    }
 
    public void centre(Entity e) {
        if (!(e.getX() < maxX))
        {
            
            
            move(e.getX()-maxX,0);
        }
        
        
        //center camera y
        if(e.getY()<maxY-UPPER_Y_BOUND){
            
            move(0,e.getY()-(maxY- UPPER_Y_BOUND));
        }
          else if(e.getY()>maxY+ LOWER_Y_BOUND) {
            
            move(0,e.getY()-(maxY+LOWER_Y_BOUND));
        }
        
        
 
    }
 
    public void move(float xAmt, float yAmt) {
       xOffset += xAmt;
       yOffset += yAmt;
       maxX = (int)xOffset+width/2;
       maxY = (int)yOffset+height/2;
       
    }
 
    public int getxOffset() {
       return (int)Math.round(xOffset);
    }
 
    public int getyOffset() {
       return (int)Math.round(yOffset);
    }

    public boolean onScreen(Entity e){
        if (e.getX()+e.getWidth()>=(maxX-(width/2)-1000)){
            if(e.getX()<(maxX+width/2+100) || e.getClass() == PiranhaPlant.class){
                return true;
            }
            //return true;
         }
        return false;

    }

	public int getwidth() {
		return width;
	}

	public int getmaxX() {
		return maxX;
	}
 
    // public void setxOffset(float xOffset) {
    //    this.xOffset = xOffset;
    // }
 
    // public void setyOffset(float yOffset) {
    //    this.yOffset = yOffset;
    // }
 
    // public void camOn(boolean turnOn) {
    //    this.turnOn = turnOn;
    // }
 
    // public static boolean shouldDespawn(Block block) {
       
    //       if (block.x+block.width>=(Game.maxX-(Game.width/2)-1600)){
    //          return true;
    //       }
    //       return false;
       
       
    // }
}
