package game.entities;

import core.CollisionDetector;
import game.states.GameState;
import util.Side;

public class Pipe extends Entity {
    public Pipe(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        GameState.blocks.put(tile+CollisionDetector.COLUMN_HEIGHT,new Brick(tile+CollisionDetector.COLUMN_HEIGHT,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,""));
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        // TODO Auto-generated method stub

    }
}