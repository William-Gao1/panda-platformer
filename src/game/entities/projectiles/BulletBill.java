package game.entities.projectiles;

import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.MarioDiesException;
import util.Side;

public class BulletBill extends Projectile{
    public BulletBill(int tile, int width, int height, String imageLocation){

        super(tile,width,height,imageLocation);
    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException{
        if(e.getClass() == Mario.class){
            if(side.getSide()==Side.TOP){
                
                velX = 0;
                velY = 10;
                Game.getGameState().getMario().hitSide(this, new Side(Side.BOTTOM));

            }
            else{
                killMario();
            }
        }
    }
}