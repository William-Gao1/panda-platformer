package game.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;

import javax.swing.Timer;
import java.awt.Rectangle;

import core.CollisionDetector;
import game.Game;
import util.MarioDiesException;
import util.Side;

/**
 * Abstract class for every entity in the game
 */
public abstract class Entity {
    protected boolean isReset = false;
    protected int x, y, width, height;
    protected Image image = Toolkit.getDefaultToolkit().createImage("Panda.png");
    protected Rectangle area;
    protected int tile = -1;
    protected Timer deleteTimer;
    protected boolean breakable = true;
    protected boolean solid = true;
    protected String imageLocationString;

    public abstract void update() throws MarioDiesException;

    public abstract void hitSide(Entity e, Side hitSide) throws MarioDiesException;

    public Entity(int startX, int startY, int width, int height, String imageLocation) {
        x = startX;
        y = startY;
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        imageLocationString = imageLocation;
        area = new Rectangle(x, y, width, height);

    }

    public Entity(int tile, int width, int height, String imageLocation) {
        x = (tile / CollisionDetector.COLUMN_HEIGHT) * CollisionDetector.TILE_SIDE_LENGTH;
        y = (tile % CollisionDetector.COLUMN_HEIGHT - 1) * CollisionDetector.TILE_SIDE_LENGTH;
        area = new Rectangle(x, y, width, height);
        this.width = width;
        this.height = height;
        setImage(imageLocation);
        imageLocationString = imageLocation;

        this.tile = tile;

    }

    /**
     * Draws own image
     * 
     * @param g Graphics object g to draw on
     * @author Will
     */

    public void draw(Graphics g, int xOffset, int yOffset) {
        g.drawImage(image, x - xOffset, y - yOffset, null);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getArea() {
        return area;
    }

    public int getCentreY() {
        return y + (height + 1) / 2;
    }

    public int getCentreX() {
        return x + (width + 1) / 2;
    }

    public int getHalfHeight() {
        return (height + 1) / 2;
    }

    public int getHalfWidth() {
        return (width + 1) / 2;
    }

    public void setBreakable(boolean b){
        breakable = b;
    }

    public boolean getSolid() {
        return solid;
    }

    public boolean getBreakable() {
        return breakable;
    }

    public String getImageLocation(){
        return imageLocationString;
    }

    public int getTile(){
        return tile;
    }

    /**
     * @param fileLocation Location where the file is stored - directory starts
     *                     outside of repository directory
     * @author Will
     */
    protected void setImage(String fileLocation) {
        image = Toolkit.getDefaultToolkit().createImage(fileLocation);
    }

    /**
     * Checks if a collision occurs between this entity and another
     * 
     * @param e Entity to check collision with
     */
    public boolean collidesWith(Entity e) {
        return e.getArea().intersects(area);
    }

    /**
     * A method that deletes this object after a certain time
     * 
     * @param timeInMillis The amount of milliseconds to wait before deleting this
     *                     object
     * @author Will
     */
    protected void setDeleteTimer(int timeInMillis) {

        deleteTimer = new Timer(timeInMillis, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        deleteTimer.setRepeats(false);
        deleteTimer.start();

    }

    protected void delete() {
        // System.out.println(deleteTimer.getDelay());

        Game.getGameState().blocks.remove(tile);
    }

    public void breakBlock() {

    }

    public boolean isReset(){
        return isReset;
    }
    public void setReset(boolean reset){
isReset = reset;
    }

    public Entity clone() {
        try {
            Constructor<? extends Entity> c = this.getClass().getConstructor(int.class, int.class, int.class,
                    String.class);
            Entity e = c.newInstance(tile,width,height,imageLocationString);
            isReset=true;

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

    protected void killMario() throws MarioDiesException{
        throw new MarioDiesException();
    }

}