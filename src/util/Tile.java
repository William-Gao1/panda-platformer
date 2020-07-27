package util;

import game.entities.Entity;
import java.util.Vector;
/**
 * A tile object that stores entities that are currently on this tile
 */
public class Tile {
    private int tile;
    private Vector <Entity> entities;

    public Tile(int tile){
        this.tile = tile;
        entities = new Vector<Entity>(0,1);
    }

    public int getTileNumber(){
        return tile;
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public Vector<Entity> getEntities(){
        return entities;
    }
}