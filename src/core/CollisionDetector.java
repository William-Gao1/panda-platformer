package core;

import util.TileHandler;

import java.util.Vector;

import game.entities.Entity;
import game.entities.MovableEntity;
import game.states.GameState;

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
    private static int[] getOccupyingTiles(Entity entity) {
        // entity.getWidth() and entity.getHeight() entity.getX() entity.getY()
        int width = entity.getWidth();
        int height = entity.getHeight();
        int startX = entity.getX();
        int startY = entity.getY();
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
        for(int i : ans){
        System.out.print(i + " ");
        
        }
        System.out.print("end");
        System.out.println("");
        return ans;

    }

    

    /**
     * A method that resolves basic head to head collisions
     * @param e         The entity that is moving
     * @param deltaX    The x change in position of the moving entity
     * @param deltaY    The y change in position of the moving entity
     * @param entity    The entity being collided with
     */
    public static void resolveCollision(MovableEntity e, int deltaX, int deltaY, Entity entity) {
        if(Math.abs(deltaY)>Math.abs(deltaX)){
            e.moveTo(e.getX(), e.getY()+(int)Math.round(entity.getCentreY()-e.getCentreY()-Math.signum(entity.getCentreY()-e.getCentreY())*(entity.getHalfHeight()+e.getHalfHeight())));
            
        }
        else{
            e.moveTo(e.getX()+(int)Math.round(entity.getCentreX()-e.getCentreX()-Math.signum(entity.getCentreX()-e.getCentreX())*(entity.getHalfWidth()+e.getHalfWidth())),e.getY());
        }
    }

    
    
    /**
     * A method that gets all of the stationary blocks that an entity is colliding with
     * @param e     The entity to be checked
     * @return      A vector of entities that the entity e is colliding with
     */
    public static Vector<Entity> getBlockCollisions(Entity e){
        Vector<Entity> entities = new Vector<Entity>(0,1);
        for(int i : getOccupyingTiles(e)){
            if(GameState.blocks.get(i)!=null){
                entities.add(GameState.blocks.get(i));
            }
        }
        return entities;
    }


}