package game.entities.projectiles;

import game.Game;
import game.entities.MovableEntity;

public class Projectile extends MovableEntity {
    public Projectile(int tile, int width, int height, String imageLocation){
        super(tile, height, width, imageLocation);
    }

    @Override
    public void update(){
        super.update();
        if(area.intersects(Game.getGameState().getMario().getArea())){
            Game.getGameState().getMario().loseLife();
        }
    }
}