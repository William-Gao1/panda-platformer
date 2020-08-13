package game.entities.enemies;

import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.MarioDiesException;
import util.Side;

public class Koopa extends Enemy{
    //private boolean sliding = false;
    private int stage = 1; // stage 1: regular walking, stage 2: not moving - in shell, stage 3: rolling
    public Koopa(int tile, int width, int height, String imageLocation){
        super(tile,width,height,imageLocation);
    }

    private void toggle(){
        if(stage == 1){
            stage = 2;
            stopMovement();
            setImage("Resources//Images//Enemies//koopaShell.png");

            //TODO: timer till koopa comes out of shell
        }       
        else if (stage ==3){
            stopMovement();
            stage = 2;
            setImage("Resources//Images//Enemies//koopaShell.png");

        } 


    }

    @Override
    public void hitSide(Entity e, Side side) throws MarioDiesException {
        if(e.getClass()!=Mario.class){
            if(stage == 3 && e instanceof Enemy ){
                ((Enemy)e).die();
                //turnAround();
            }

            super.hitSide(e, side);
            if(stage == 3 &&e.getBreakable() == true&&(side.getSide()==Side.LEFT||side.getSide()==Side.RIGHT)){
                e.breakBlock();
            }

        }
        else if(side.getSide()==Side.TOP){
            
            toggle();
        }
        else if(stage ==2){
            if(side.getSide() == Side.LEFT){
                stage = 3;
                velX = -5;
            }
            else if (side.getSide()==Side.RIGHT){
                stage = 3;
                velX = 5;
            }

        }
        else{
    killMario();      
  }
    }

    @Override
    public void die(){
        Game.getGameState().enemies.remove(this);

    }
    
}