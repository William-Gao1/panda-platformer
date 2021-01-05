package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.Side;

public class DialogueTrigger extends Entity {
    private boolean active = true;
    public DialogueTrigger(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
        solid = false;
        
    }

    @Override
    public void update() {
        if(active && Game.getGameState().getMario().getX()>=x){
            Game.getGameState().notifyDialogueEventListeners(x);
            active = false;
        }

    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }
}