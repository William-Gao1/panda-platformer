package game.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import core.CollisionDetector;
import util.Side;

import java.awt.Polygon;


/**
 * Abstract class for every entity in the game
 */
public abstract class Entity{
    
    protected int x, y, width, height;
    protected Image image = Toolkit.getDefaultToolkit().createImage("Panda.png");
    protected Polygon area;
    


    public abstract void update();
    public abstract void hitSide(Entity e, Side hitSide);
    

    public Entity(int startX, int startY, int width, int height, String imageLocation){
        x = startX;
        y = startY;
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        area = new Polygon(new int[]{x,x+width,x+width,x},new int[]{y,y,y+height,y+height},4);
        
    }

    public Entity(int tile, int width, int height, String imageLocation){
        x = (tile/CollisionDetector.COLUMN_HEIGHT)*CollisionDetector.TILE_SIDE_LENGTH;
        y = (tile%CollisionDetector.COLUMN_HEIGHT-1)*CollisionDetector.TILE_SIDE_LENGTH;
        area = new Polygon(new int[]{x,x+width,x+width,x},new int[]{y,y,y+height,y+height},4);
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        area = new Polygon(new int[]{x,x+width,x+width,x},new int[]{y,y,y+height,y+height},4);
        
        
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

    public Polygon getArea(){
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
        return e.getArea().intersects(area.getBounds());
    }

}