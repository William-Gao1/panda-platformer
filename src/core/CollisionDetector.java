package core;

import util.TileHandler;

import game.entities.Entity;

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
     * @param xPos      x position of the point to be checked
     * @param yPos      y position of the point to be checked
     * @return          the tile that the point is on
     * @author Will
     */
    private static int getTile(int xPos, int yPos){
        return (int)Math.floor(xPos/TILE_SIDE_LENGTH)*COLUMN_HEIGHT + (int)Math.floor(yPos/TILE_SIDE_LENGTH+1);
    }

    /**
     * A method that calculates what tiles an onject is occupying
     * 
     * @param entity    the entity to be checked
     * @return          all the tiles that the object is on
     * @author Ricky
     */
    private static int[] getOccupyingTiles(Entity entity){
        
    }


    /**
     * A method that return all the entities on a particular tile
     * 
     * @param tile      the tile to be checked
     * @return          an array of entities occupying the tile
     * @author Will
     */
    private static Entity[] getEntitiesOnTile(int tile){
        return tileHandler.getEntities(tile);
    }
}