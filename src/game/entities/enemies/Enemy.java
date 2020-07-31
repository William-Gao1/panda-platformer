package game.entities.enemies;

import game.entities.MovableEntity;

public class Enemy extends MovableEntity {

    public Enemy (int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update(){
        super.update();
        
    }
    
}