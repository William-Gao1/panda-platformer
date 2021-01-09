package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.MarioDiesException;
import util.Side;

public class EndBlock extends Entity {

    public EndBlock (int tile, int width, int height, String imageLocation){
        super (tile, width, height, imageLocation);
        solid = false;

    }
    @Override
    public void update() throws MarioDiesException {
        if(Game.getGameState().getMario().getX()>=x){
            // transition level
            Game.getGameState().getGame().goNextLevel();
            
        }

    }

    @Override
    public void hitSide(Entity e, Side hitSide) throws MarioDiesException {
        

    }

    @Override
    public Entity clone(){
        return this;
    }
    
}
