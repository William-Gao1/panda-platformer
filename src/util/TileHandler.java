package util;

import game.entities.Entity;

/**
 * Class that handles all the tiles
 */
public class TileHandler {
    private Tile[] tiles;

    public TileHandler(int numberOfTiles){
        tiles = new Tile[numberOfTiles];
    }
    
    public void addElement(int tile, Entity e){
        tiles[tile].addEntity(e);
    }

    public void removeEntity(int tile, Entity e){
        tiles[tile].removeEntity(e);
    }

    public Entity[] getEntities(int tile){
        return (Entity[])tiles[tile].getEntities().toArray();
    }
}