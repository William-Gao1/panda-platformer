package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import util.KeyManagerListener;
import java.awt.event.KeyEvent;

import util.Side;

public class Brick extends Entity implements KeyManagerListener{
    public Brick(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
        Game.getKeyManager().listenFor(KeyEvent.VK_A, this);
        System.out.println("Added");
    }


    @Override
	public void update() {
		
		
    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        

    }

    @Override
    public void notify(KeyEvent e){
        System.out.println("hello");
    }
}