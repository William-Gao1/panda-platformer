package game.entities.blocks;

import core.CollisionDetector;
import game.states.GameState;
import game.entities.Entity;
import util.Side;

public class Pipe extends Entity {
    public Pipe(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        GameState.blocks.put(tile+CollisionDetector.COLUMN_HEIGHT,new Brick(tile+CollisionDetector.COLUMN_HEIGHT,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,""));
    }

    @Override
    public void update() {
        

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }
}