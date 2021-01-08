package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.MarioKeyListener;
import util.Side;


public class SwitchBlock extends Entity implements MarioKeyListener{
    private boolean visible = true;
    private final String visibleImage;

    public SwitchBlock(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        Game.getGameState().getMario().listenForJump(this);
        visibleImage = imageLocation;
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

    public Entity clone(){
        SwitchBlock s = new SwitchBlock(tile, width, height, imageLocationString);
        if (!solid){
            s.toggle();
        }
        return s;
    }

    
    
}