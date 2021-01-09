package game.entities.projectiles;


import game.entities.Mario;
import game.entities.Entity;
import util.MarioDiesException;
import util.Side;

public class PiranhaPlant extends Projectile{
    private long downTime = System.currentTimeMillis();
    private long upTime = System.currentTimeMillis();
    private final  int DOWN_TIME_INTERVAL = 3000;
    private final int UP_TIME_INTERVAL = 3000;
    private final int SPEED = 2;
    private boolean offTempo = false;
    private int originalYCoord;
    private boolean upsideDown = false;

    private boolean up = false;
    private boolean down = true;
    private final int EXTENSION_HEIGHT = 60;
    public PiranhaPlant(int tile, int width, int height, String imageLocation){
        super(tile, width, height,imageLocation);
        move(18,40);

        originalYCoord = y;
    }

    @Override
    public void update() throws MarioDiesException{
        if(down == true){//if dormant
            if(System.currentTimeMillis() - downTime >= DOWN_TIME_INTERVAL){//if time to come up
                velY = -SPEED;
                down = false;
            }
        }
        else if (up == true){
            if(System.currentTimeMillis()-upTime >=UP_TIME_INTERVAL){//if time to go down
                velY = SPEED;
                up = false;
            }
        }
        else{
            if(y<=originalYCoord-EXTENSION_HEIGHT){
                up = true;
                y = originalYCoord - EXTENSION_HEIGHT;
                upTime = System.currentTimeMillis();
                velY = 0;
            }
            else if(y>=originalYCoord){
                down = true;
                downTime = System.currentTimeMillis();
                y = originalYCoord;
                velY = 0;
            }
        }
        super.update();
    }


    public void setOffTempo() {
        this.downTime += 3000;
        offTempo = true;
    }

    public void setUpsideDown(){
        move(0,50);
        originalYCoord = y;
        upsideDown = true;
    }


    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException {
        if(e.getClass() == Mario.class){
            killMario();
        }
    }

    @Override
    public Entity clone(){
        PiranhaPlant pp = (PiranhaPlant)super.clone();
        if (offTempo){
            pp.setOffTempo();
        }
        if(upsideDown){
            pp.setUpsideDown();
        }

        return pp;
    }
    

}