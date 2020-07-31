package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.Side;

public class DialogueTrigger extends Entity {
    public DialogueTrigger(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        solid = false;
        
    }

    @Override
    public void update() {
        if(Game.getGameState().getMario().getX()>=x){
            Game.getGameState().notifyDialogueEventListeners(x);
            setDeleteTimer(0);
        }

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }
}