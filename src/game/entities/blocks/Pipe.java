package game.entities.blocks;



import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.projectiles.PiranhaPlant;
import util.Side;



public class Pipe extends Entity {

    PiranhaPlant pplant = null;
    public Pipe(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        Game.getGameState().blocks.put(tile+CollisionDetector.COLUMN_HEIGHT,new Brick(tile+CollisionDetector.COLUMN_HEIGHT,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,""));
    }

    @Override
    public void update() {
        
        

    }

    

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }

    public void addPiranhaPlant(PiranhaPlant p){
        pplant = p;
        Game.getGameState().projectiles.add(pplant);
    }

    public Entity clone(){
        Pipe p = new Pipe(tile, width, height, imageLocationString);
        p.addPiranhaPlant((PiranhaPlant)pplant.clone());
        return p;
    }
}