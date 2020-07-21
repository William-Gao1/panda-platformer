package game.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Polygon;


/**
 * Abstract class for every entity in the game
 */
public abstract class StaticEntity{
    
    protected int x, y, width, height;
    protected Image image = Toolkit.getDefaultToolkit().createImage("Panda.png");
    protected Polygon area;
    


    public abstract void update();
    

    public StaticEntity(int startX, int startY, int width, int height, String imageLocation){
        x = startX;
        y = startY;
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
    public boolean collidesWith(StaticEntity e){
        return e.getArea().intersects(area.getBounds());
    }

}