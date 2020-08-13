package game.entities.factories;

import core.CollisionDetector;
import game.entities.Entity;
import game.entities.enemies.*;

public class EnemyFactory extends EntityFactory {
    public Entity getEntity(char textChar, int tile){
        Enemy e = null;
        if(textChar == 'e'){
            e = new Goomba (tile,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"Resources//Images//Enemies//Burgera.gif");
            e.setVel(1,0);
        }
        else if (textChar == 'k'){
            e = new Koopa(tile,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"Resources//Images//Enemies//Koopa.gif");
            e.setVel(1,0);
        }
        if(e!=null){
            e.setOrigVelocities();
        }
        return e;
    }    
}