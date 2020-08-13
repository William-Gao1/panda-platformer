package game.entities.blocks;

import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.MarioDiesException;
import util.Side;

import java.awt.Image;
import java.awt.Toolkit;

import core.CollisionDetector;

import java.awt.Graphics;
public class VisibleSpikeBlock extends Entity{
    private int spikeSide = Side.TOP;
    private static Image spikeImage = Toolkit.getDefaultToolkit().createImage("Resources//Images//Projectiles//Spike.png");
    public VisibleSpikeBlock(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update(){

    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException{
        if(e.getClass()==Mario.class&&side.getSide()==spikeSide){
            killMario();
        }
    }

    @Override
    public void draw(Graphics g, int xOffset, int yOffset){
        super.draw(g,xOffset,yOffset);
        g.drawImage(spikeImage, x-xOffset, y-CollisionDetector.TILE_SIDE_LENGTH-yOffset, null);
    }

}