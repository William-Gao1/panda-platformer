package game.entities.projectiles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import core.CollisionDetector;
import game.entities.Entity;
import game.entities.Mario;
import util.MarioDiesException;
import util.Side;



public class BulletBillWall extends Projectile{
    private static Image bulletBill = Toolkit.getDefaultToolkit().createImage("Resources//Images//Projectiles//RBulletBill.gif");
    public BulletBillWall (int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
        moveTo(x,0);
        solid = false;
    }

    @Override
    public void draw(Graphics g, int xOffset, int yOffset){
        for(int i = 0 ; i<CollisionDetector.COLUMN_HEIGHT;i++){
            g.drawImage(bulletBill, x-xOffset, i*CollisionDetector.TILE_SIDE_LENGTH-yOffset, null);
        }
        //System.out.println(area.x);
    }
    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException{
        if(e.getClass()==Mario.class){
killMario();
        }
    }
    
}