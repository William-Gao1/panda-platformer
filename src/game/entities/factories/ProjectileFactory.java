package game.entities.factories;

import game.entities.Entity;
import game.entities.projectiles.Projectile;

public class ProjectileFactory extends EntityFactory {

    @Override
    public Entity getEntity(char textChar, int tile) {
        Projectile p = null;
        //p.setOrigVeloctities();
        return p;
    }
    
}