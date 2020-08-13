package game.entities.enemies;

import game.Game;
import util.Side;

public class Goomba extends Enemy {
    public Goomba (int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void die(){
        setImage("Resources//Images//Enemies//CrushedGoomba.png");
        stopMovement();

        solid = false;
        setDeleteTimer(1000);
    }
}