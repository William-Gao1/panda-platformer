package game.entities.blocks;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;

import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.MarioDiesException;
import util.Side;

public class InvisSpikeBlock extends Entity {
    private static Image spike = Toolkit.getDefaultToolkit().createImage("Resources//Images//Projectiles//Spike.png");

    private int triggerSide = Side.TOP;
    private boolean drawSpike = false;
    public InvisSpikeBlock(int tile, int width, int height, String imageLocation) {
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g, int xOffset, int yOffset) {
        if(drawSpike){
            g.drawImage(spike, x-xOffset, y-CollisionDetector.TILE_SIDE_LENGTH-yOffset, null);

        }

        super.draw(g,xOffset,yOffset);
    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException {
        if(e.getClass()==Mario.class&&side.getSide()==triggerSide){
            killMario();
            drawSpike = true;

        }
    }
    
}