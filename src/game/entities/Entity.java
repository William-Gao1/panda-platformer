package game.entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Polygon;

/**
 * Abstract class for every entity in the game
 * @author Will
 */
public abstract class Entity{
    
    protected int x, y, width, height;
    protected Image image;
    private Polygon area;


    public abstract void update();
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }

    public Entity(int startX, int startY, int width, int height, String imageLocation){
        x = startX;
        y = startY;
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        area = new Polygon(new int[]{x,x+width,x+width,x},new int[]{y,y,y+height,y+height},4);
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

    protected void setImage(String fileLocation){
        image = Toolkit.getDefaultToolkit().createImage(fileLocation);
    }

    public boolean collidesWith(Entity e){
        return e.getArea().intersects(area.getBounds());
    }

}