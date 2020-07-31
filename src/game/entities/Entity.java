package game.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.Timer;
import java.awt.Rectangle;

import core.CollisionDetector;
import game.states.GameState;
import util.Side;



/**
 * Abstract class for every entity in the game
 */
public abstract class Entity{
    
    protected int x, y, width, height;
    protected Image image = Toolkit.getDefaultToolkit().createImage("Panda.png");
    protected Rectangle area;
    protected int tile=-1;
    protected Timer deleteTimer;
    protected boolean breakable = true;
    protected boolean solid = true;
    


    public abstract void update();
    public abstract void hitSide(Entity e, Side hitSide);
    

    public Entity(int startX, int startY, int width, int height, String imageLocation){
        x = startX;
        y = startY;
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        area = new Rectangle(x,y,width,height);
        
    }

    public Entity(int tile, int width, int height, String imageLocation){
        x = (tile/CollisionDetector.COLUMN_HEIGHT)*CollisionDetector.TILE_SIDE_LENGTH;
        y = (tile%CollisionDetector.COLUMN_HEIGHT-1)*CollisionDetector.TILE_SIDE_LENGTH;
        area = new Rectangle(x,y,width,height);
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        
        this.tile = tile;
        
        
    }
    /**
     * Draws own image
     * @param g     Graphics object g to draw on
     * @author Will
     */

    public void draw(Graphics g){
        g.drawImage(image, x, y,null);
        
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Rectangle getArea(){
        return area;
    }

    public int getCentreY(){
        return y+(height+1)/2;
    }
    
    public int getCentreX(){
        return x+(width+1)/2;
    }

    public int getHalfHeight(){
        return (height+1)/2;
    }

    public int getHalfWidth(){
        return (width+1)/2;
    }

    public boolean getSolid(){
        return solid;
    }

    public boolean getBreakable(){
        return breakable;
    }

    

    
    /**
     * @param fileLocation      Location where the file is stored - directory starts outside of repository
     *                          directory
     * @author Will
     */
    protected void setImage(String fileLocation){
        image = Toolkit.getDefaultToolkit().createImage(fileLocation);
    }

    /**
     * Checks if a collision occurs between this entity and another
     * @param e     Entity to check collision with
     */
    public boolean collidesWith(Entity e){
        return e.getArea().intersects(area);
    }

    /**
     * A method that deletes this object after a certain time
     * @param timeInMillis      The amount of milliseconds to wait before deleting this object
     * @author Will
     */
    protected void setDeleteTimer(int timeInMillis){
        
        deleteTimer = new Timer(timeInMillis,new ActionListener() 
        {
            public void actionPerformed(ActionEvent e){
                delete();
            }
        });
        deleteTimer.setRepeats(false);
        deleteTimer.start();
    
    
        
    }

    private void delete(){
        //System.out.println(deleteTimer.getDelay());
        
        GameState.blocks.remove(tile);
    }

}