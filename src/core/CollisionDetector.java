package core;

import java.util.Vector;

import game.entities.StaticEntity;

/**
 * Collision detector that handles all of the game's collisions
 */

public class CollisionDetector {
    public static final int COLUMN_HEIGHT = 50;
    public static final int TILE_SIDE_LENGTH = 35;

    /**
     * returns the tile in which a point is in
     * 
     * @param xPos      x position of the point to be checked
     * @param yPos      y position of the point to be checked
     */
    private static int getTile(int xPos, int yPos){
        return (int)Math.floor(xPos/TILE_SIDE_LENGTH)*COLUMN_HEIGHT + (int)Math.floor(yPos/TILE_SIDE_LENGTH+1);
    }

    
    private static int[] getOccupyingTiles(StaticEntity entity){
        
    }
}