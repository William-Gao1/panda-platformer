package game.entities;

import game.Game;

public class Mario extends MovableEntity {
    private final int VEL_X_CAP = 5;
    private final int VEL_Y_CAP = 10;
    public Mario(int startX, int startY, int width, int height, String imageLocation) {
        super(startX, startY, width, height, imageLocation);
        
    }

    @Override
    public void update() {
        accelX = Game.getKeyManager().getHorizontalDir();
        accelY = Game.getKeyManager().getVerticalDir();
        if(Game.getKeyManager().getHorizontalDir() == 0){
            velX = 0;
        }
        if(Game.getKeyManager().getVerticalDir() == 0){
            velY = 0;
        }
        assureVelIsCapped();
        
        super.update();
    }

    private void assureVelIsCapped(){
        velX = Math.abs(velX) >= VEL_X_CAP ? VEL_X_CAP*velX/Math.abs(velX) : velX;
        velY = Math.abs(velY) >= VEL_Y_CAP ? VEL_Y_CAP*velY/Math.abs(velY) : velY;

    }

    

}