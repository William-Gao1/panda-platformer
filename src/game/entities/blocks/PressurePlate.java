package game.entities.blocks;

import core.CollisionDetector;
import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import util.Side;

public class PressurePlate extends Entity{
    public PressurePlate(int tile, int width, int height, String imageLocation){
        super(tile, width, height, imageLocation);
    }

    @Override
    public void update(){}

    @Override
    public void hitSide(Entity e, Side side){
        if(e.getClass()==Mario.class && side.getSide()==Side.TOP){
            int count = 1;
            Entity possibleTrapBlock = Game.getGameState().blocks.get(tile+CollisionDetector.COLUMN_HEIGHT*count);
            while(possibleTrapBlock!=null &&possibleTrapBlock.getClass()==TrapBlock.class){
                possibleTrapBlock.breakBlock();
                count++;
                possibleTrapBlock = Game.getGameState().blocks.get(tile+CollisionDetector.COLUMN_HEIGHT*count);
            }
            count = 1;
            possibleTrapBlock = Game.getGameState().blocks.get(tile-CollisionDetector.COLUMN_HEIGHT*count);
            while(possibleTrapBlock!=null &&possibleTrapBlock.getClass()==TrapBlock.class){
                possibleTrapBlock.breakBlock();
                count++;
                possibleTrapBlock = Game.getGameState().blocks.get(tile-CollisionDetector.COLUMN_HEIGHT*count);

            }
            breakBlock();
        }
    }

    @Override
    public void breakBlock(){
        setDeleteTimer(0);
    }
    
}