package game.entities.projectiles;

import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.MovableEntity;
import util.MarioDiesException;

public class Projectile extends MovableEntity {
    public Projectile(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
        //solid = false;
    }

    @Override
    public void update() throws MarioDiesException{
        super.update();
        if(!Game.getGameState().getCamera().onScreen(this)){
            delete();
        }
        if(area.intersects(Game.getGameState().getMario().getArea())){
            CollisionDetector.resolveCollision(this, (int)velX, (int)velY, Game.getGameState().getMario());
        }
    }

    public void setVelocity(int velX, int velY){
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void checkShouldFall(){

    }

    @Override
    protected void delete() {
        Game.getGameState().projectiles.remove(this);
    }
}