package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.MarioKeyListener;
import util.Side;


public class SwitchBlock extends Entity implements MarioKeyListener{
    private boolean visible = true;
    private final boolean ONTEMPO;
    private final String visibleImage;

    public SwitchBlock(int tile, int width, int height, String imageLocation, boolean onTempo) {
        super(tile, width, height, imageLocation);
        Game.getGameState().getMario().listenForJump(this);
        visibleImage = imageLocation;
        if (!onTempo){
            toggle();
        }
        ONTEMPO = onTempo;
    }

    @Override
    public void update() {
       

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }

    @Override
    public void notifyJump() {
        toggle();

    }

    public void toggle(){
        //TODO: break if enemy or mario is in it
        if(visible){
            solid = false;
            image = null;
        }
        else{
            solid = true;
            setImage(visibleImage);

        }
        visible = !visible;
    }

    
    @Override
    public Entity clone(){
        return new SwitchBlock(tile, width, height, imageLocationString, ONTEMPO);
    }
    
    
}