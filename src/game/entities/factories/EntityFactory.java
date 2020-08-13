package game.entities.factories;

import game.entities.Entity;

public abstract class EntityFactory {
    public abstract Entity getEntity(char textChar, int tile );
}