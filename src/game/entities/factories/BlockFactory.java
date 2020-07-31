package game.entities.factories;

import game.entities.Entity;
import game.entities.Mario;
import game.entities.blocks.*;

import core.CollisionDetector;

public class BlockFactory extends EntityFactory{
    @Override
    public Entity getEntity(char textChar, int tile){
        if(textChar == 'b'){//regular brick
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if(textChar == 'm'){//mario spawn point
            return new Mario(tile, 35, 45, "Resources//Images//Pandas//Panda.gif");
        }
        else if (textChar =='q'){//qblock
            return new QBlock(tile, CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"resources//Images//Blocks//QBlock.gif");
        }
        else if (textChar == 'a'){//invis brick
            return new Brick(tile,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"");
        }
        else if (textChar == 'P'){//pipe head
            return new Pipe(tile,CollisionDetector.TILE_SIDE_LENGTH*2,CollisionDetector.TILE_SIDE_LENGTH,"Resources//Images//Blocks//PipeHead.png");
        }
        else if(textChar == 'p'){//pipe body
            return new Pipe(tile,CollisionDetector.TILE_SIDE_LENGTH*2,CollisionDetector.TILE_SIDE_LENGTH,"Resources//Images//Blocks//PipeBody.png");
        }
        else if(textChar == 'g'){//ground
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Ground.png");
        }
        else if(textChar == 'c'){//coin
            return new Coin(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Coin.gif");
        }
        else if (textChar == 'd'){//dialogue trigger block
            return new DialogueTrigger(tile, 1, 1, "");
        }
        
        return null;
    }
}