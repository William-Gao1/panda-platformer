package game.entities.blocks;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import core.Camera;
import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import game.entities.projectiles.BulletBill;
import util.Side;

public class Cannon extends Entity {

    /**
     *
     */
    private long lastBulletTime = System.currentTimeMillis();
    private int bulletSpawnInterval = 2000;
    private int bulletSpeed = (int) Mario.VEL_X_CAP/2;
    private int bulletYSpeed = 0;
    private int tile;

    protected Timer closeCannonTimer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //setImage("Images//Cannon.png");
        }
    });

    public Cannon(int tile, int width, int height, String imageLocation) {

        super(tile, width, height, imageLocation);

        closeCannonTimer.setRepeats(false);
        lastBulletTime = 0;
        this.tile = tile;
        // TODO: alignPictureCentre(105, 35);

    }

    // @Override

    // public void update (){
    // onScreen = Camera.shouldDespawn(this)&&x<(Game.maxX+Game.width/2+100);
    // if(stopSpawning==false&&onScreen && System.currentTimeMillis() -
    // lastBulletTime>=bulletSpawnInterval&&bulletSpeed!=2*(int)Mario.accelXCap){
    // lastBulletTime = System.currentTimeMillis();
    // spawnBulletBill();

    // }
    // else if(stopSpawning ==
    // false&&bulletSpeed==2*(int)Mario.accelXCap&&Math.abs(Game.mario.x-x)<400){
    // spawnBulletBill();
    // stopSpawning = true;
    // }
    // }

    @Override
    public void update() {
        
        // onScreen = Camera.shouldDespawn(this)&&x<(Game.maxX+Game.width/2+100);
        if (System.currentTimeMillis() - lastBulletTime >= bulletSpawnInterval) {
            lastBulletTime = System.currentTimeMillis();
            // if(onScreen){
                if(Game.getGameState().getCamera().onScreen(this)){
                    spawnBulletBill();
                }

            // }
        }
    }

    private void spawnBulletBill() {
        //setImage("Images//OpenCannon.png");
        closeCannonTimer.start();
        BulletBill bullet = new BulletBill(tile, 35, 34, "Resources//Images//Projectiles//BulletBill.gif");
        bullet.setVelocity(-bulletSpeed, bulletYSpeed);
        Game.getGameState().projectiles.add(bullet);

    }

    public void setBulletSpawnInterval(int spawnTime){
        bulletSpawnInterval = spawnTime;
    }

    public void setBulletXSpeed(double timesMarioMaxSpeed){
        bulletSpeed  = (int)Math.round(Mario.VEL_X_CAP*timesMarioMaxSpeed);
    }

    public void setActualBulletXSpeed(int actualSpeed){
        bulletSpeed = actualSpeed;
    }

    public void setActualBulletYSpeed(int actualSpeed){
        bulletYSpeed = actualSpeed;
    }
    public void setBulletYSpeed(double timesMarioMaxSpeed){
        bulletYSpeed = (int)Math.round(timesMarioMaxSpeed*Mario.VEL_X_CAP);
    }

    @Override
    public void hitSide(Entity e, Side hitSide) {
        


    }

    @Override
    public Entity clone(){
        Cannon c = new Cannon(tile, width,height,imageLocationString);
        c.setBulletSpawnInterval(bulletSpawnInterval);
        c.setActualBulletXSpeed(bulletSpeed);
        c.setActualBulletYSpeed(bulletYSpeed);
        return c;
    }
}