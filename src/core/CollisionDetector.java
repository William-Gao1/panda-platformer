package core;

import util.MarioDiesException;
import util.Side;
import util.TileHandler;

import java.util.Vector;

import game.Game;
import game.entities.Entity;
import game.entities.MovableEntity;
import game.entities.enemies.Enemy;

import java.awt.geom.Rectangle2D;

/**
 * Collision detector that handles all of the game's collisions
 */

public class CollisionDetector {
    public static final int COLUMN_HEIGHT = 50;
    public static final int TILE_SIDE_LENGTH = 35;
    public static TileHandler tileHandler = new TileHandler(0);

    /**
     * returns the tile in which a point is in
     * 
     * @param xPos x position of the point to be checked
     * @param yPos y position of the point to be checked
     * @return the tile that the point is on
     * @author Will
     */
    private static int getTile(int xPos, int yPos) {
        return (int) Math.floor(xPos / TILE_SIDE_LENGTH) * COLUMN_HEIGHT
                + (int) Math.floor(yPos / TILE_SIDE_LENGTH + 1);
    }

    /**
     * A method that calculates what tiles an onject is occupying
     * 
     * @param entity the entity to be checked
     * @param deltaX the change in the x position of the entity
     * @param deltaY the change in the y position of the entity
     * @return all the tiles that the object is on
     * @author Ricky
     */
    private static int[] getOccupyingTiles(Entity entity,int deltaX,int deltaY) {
        // entity.getWidth() and entity.getHeight() entity.getX() entity.getY()
        int width = entity.getWidth();
        int height = entity.getHeight();
        int startX = entity.getX()+deltaX;
        int startY = entity.getY()+deltaY;
        int width2;
        int height2;
        int tileCount = 0;
        int[] temp = new int[4];
        temp[0] = getTile(startX, startY);
        temp[1] = getTile(startX + width-1, startY);
        temp[2] = getTile(startX, startY + height-1);
        temp[3] = getTile(startX + width-1, startY + height-1);
        width2 = (temp[1] - temp[0]) / 50 + 1;
        height2 = temp[2] - temp[0]+1;
        tileCount = width2 * height2;
        int[] ans = new int[tileCount];
        for (int i = 0; i < width2; i++) {
            for (int j = 0; j < height2; j++) {
                ans[j + i * height2] = temp[0] + j + 50 * i;
            }

        }
        // for(int i : ans){
        //     System.out.print(i + " ");
        // }
        // System.out.println("");

        return ans;
    }

    private static int[] getStandingTiles(Entity entity){
        int width = entity.getWidth();
        int height = entity.getHeight();
        int startX = entity.getX();
        int startY = entity.getY();
        int tileCount = 1;
        int[] temp = new int[2];
        temp[0] = getTile(startX,startY+height+1);
        temp[1] = getTile(startX+width,startY+height+1);
        if (temp[0] != temp[1]){
            tileCount = 2;
        }
        int[] ans = new int[tileCount];
        for (int i = 0; i<tileCount; i++){
            ans[i]=temp[i];
        } 
        return ans;
    }

    

    /**
     * A method that resolves basic head to head collisions
     * 
     * @param e      The entity that is moving
     * @param deltaX The x change in position of the moving entity
     * @param deltaY The y change in position of the moving entity
     * @param entity The entity being collided with
     * @throws MarioDiesException
     */
    public static void resolveCollision(MovableEntity e, int deltaX, int deltaY, Entity entity)
            throws MarioDiesException {
        Rectangle2D overlap = e.getArea().createIntersection(entity.getArea());
        if(overlap.getWidth() > 0 && overlap.getHeight() >0){
        if(!(deltaX == 0 && deltaY == 0))
        //System.out.println(overlap.getWidth() + " " + overlap.getHeight() + " " + deltaX + " " + deltaY);
        //System.out.println("Overlap: "+overlap.getWidth()+" "+overlap.getHeight());
        if(deltaY!=0 && (!(Math.abs(deltaY) > 1 && overlap.getWidth() == 1)) &&(((Math.abs(deltaX) <= 7 && Math.abs(deltaY) <= 7) && (overlap.getWidth()> overlap.getHeight())) || ((Math.abs(deltaX) >= 7 || Math.abs(deltaY) >= 7)&&(overlap.getWidth()/(0.1+(double)Math.abs(deltaX)))>overlap.getHeight()/(double)Math.abs(deltaY)))){
            if(entity.getSolid()==true&&e.getSolid()==true){
            e.move(0,(int)Math.round(entity.getCentreY()-e.getCentreY()-Math.signum(entity.getCentreY()-e.getCentreY())*(entity.getHalfHeight()+e.getHalfHeight()-1)));
                //System.out.println("Ycollision");
        }
            if(deltaY < 0 && Math.signum(entity.getCentreY()-e.getCentreY())==-1.0){
                entity.hitSide(e,new Side(Side.BOTTOM));
                e.hitSide(entity,new Side(Side.TOP));
                //System.out.println("bot");
                //System.out.println(!(deltaY > 1 && deltaX == 1));
            }
            else{
                entity.hitSide(e,new Side(Side.TOP));
                e.hitSide(entity,new Side(Side.BOTTOM));
                //System.out.println("top");
                //System.out.println(e.getClass());
            }
        }
        else if (deltaX!=0){
            if(entity.getSolid()==true&&e.getSolid()==true){
            e.move((int)Math.round(entity.getCentreX()-e.getCentreX()-Math.signum(entity.getCentreX()-e.getCentreX())*(entity.getHalfWidth()+e.getHalfWidth()-1)),0);
            //System.out.println("XCollision");

        }
            if(deltaX > 0){
                entity.hitSide(e, new Side(Side.RIGHT));
                e.hitSide(entity, new Side(Side.LEFT));
                //System.out.println("right");
            }
            else{
                entity.hitSide(e, new Side(Side.LEFT));
                e.hitSide(entity, new Side(Side.RIGHT));
                //System.out.println("left");
            }
        }
    }
    }

    
    
    /**
     * A method that gets all of the stationary blocks that an entity is colliding with
     * @param e     The entity to be checked
     * @return      A vector of entities that the entity e is colliding with
     */
    public static Vector<Entity> getBlockCollisions(Entity e){
        Vector<Entity> entities = new Vector<Entity>(0,1);
        for(int i : getOccupyingTiles(e,0,0)){
            if(Game.getGameState().blocks.get(i)!=null&&Game.getGameState().blocks.get(i).getSolid()==true){
                entities.add(Game.getGameState().blocks.get(i));
            }
        }
        return entities;
    }

    public static Vector<Entity> getBottomBlocks(Entity e, int deltaX, int deltaY){
        Vector<Entity> entities = new Vector<Entity>(0,1);
        for(int i : getOccupyingTiles(e,deltaX,deltaY)){
            if(Game.getGameState().blocks.get(i)!=null&&Game.getGameState().blocks.get(i).getSolid()==true){
                entities.add(Game.getGameState().blocks.get(i));
            }
        }
        return entities;
    }

    public static Vector<Entity> getBlockCollisions(Entity e, int deltaX, int deltaY){
        Vector<Entity> entities = new Vector<Entity>(0,1);
        for(int i : getOccupyingTiles(e,deltaX,deltaY)){
            if(Game.getGameState().blocks.get(i)!=null){
                entities.add(Game.getGameState().blocks.get(i));
            }
        }
        return entities;
    }

    public static Vector<Enemy> getEnemyCollisions(Entity e){
        Vector<Enemy> enemies = new Vector<Enemy>(0,1);
        for(Enemy enemy : Game.getGameState().enemies){
            if(e.getArea().intersects(enemy.getArea())){
                enemies.add(enemy);
            }
        }
        return enemies;
    }


}