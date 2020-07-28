package game.entities;

import core.CollisionDetector;

public class EntityFactory {
    public static Entity getEntity(char textChar, int tile){
        if(textChar == 'b'){
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Block.png");
        }
        else if(textChar == 'm'){
            return new Mario(tile, 35, 45, "Resources//Pandas//Panda.gif");
        }
        return null;
    }
}