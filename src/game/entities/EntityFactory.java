package game.entities;

import core.CollisionDetector;

public class EntityFactory {
    public static Entity getEntity(char textChar, int tile){
        if(textChar == 'b'){//regular brick
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources/Brick.png");
        }
        else if(textChar == 'm'){//mario spawn point
            return new Mario(tile, 35, 45, "Resources//Pandas//Panda.gif");
        }
        else if (textChar =='q'){//qblock
            return new QBlock(tile, CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"resources//QBlock.gif");
        }
        else if (textChar == 'a'){//invis brick
            return new Brick(tile,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"");
        }
        else if (textChar == 'P'){//pipe head
            return new Pipe(tile,CollisionDetector.TILE_SIDE_LENGTH*2,CollisionDetector.TILE_SIDE_LENGTH,"Resources//PipeHead.png");
        }
        else if(textChar == 'p'){//pipe body
            return new Pipe(tile,CollisionDetector.TILE_SIDE_LENGTH*2,CollisionDetector.TILE_SIDE_LENGTH,"Resources//PipeBody.png");
        }
        else if(textChar == 'g'){//regular brick
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Ground.png");
        }
        else if(textChar == 'c'){//regular brick
            return new Coin(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Coin.gif");
        }
        
        return null;
    }
}