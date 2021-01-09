package game.entities.factories;

import game.Game;
import game.entities.Entity;
import game.entities.Mario;
import game.entities.blocks.*;
import game.entities.blocks.checkpoint.Checkpoint;
import game.entities.projectiles.PiranhaPlant;
import core.CollisionDetector;

public class BlockFactory extends EntityFactory{
    @Override
    public Entity getEntity(char textChar, int tile){
        if(textChar == 'b'){//regular brick
            return new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if(textChar == 'B'){//regular brick
            Brick b = new Brick(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
            b.setBreakable(false);
            return b;
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
        else if (textChar == 'C'){//cannon
            return new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
        }
        else if (textChar =='m'){//mario spawn point
            Game.getGameState().setMario(new Mario(tile,35,45,"Resources//Images//Pandas//Panda.gif"));
            
            Game.getGameState().getCheckpointHandler().setMarioSpawn(tile);
            
        }
        else if (textChar =='i'){//invis spike block
            return new InvisSpikeBlock(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if (textChar == 'S'){//swtich block regular tempo
            return new SwitchBlock(tile, CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png", true);

        }
        else if (textChar == 's'){//swtich block off tempo
            SwitchBlock s = new SwitchBlock(tile, CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png", false);
            
            return s;
        }
        else if (textChar == 'V'){//visible spike block
            return new VisibleSpikeBlock(tile,CollisionDetector.TILE_SIDE_LENGTH,CollisionDetector.TILE_SIDE_LENGTH,"Resources//Images//Blocks//Brick.png");
        }
        else if (textChar =='W'){//bullet bill wall activator
            return new BulletBillWallActivator(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if (textChar == 'z'){//pipe with pplant
            
            Pipe p =  new Pipe(tile, CollisionDetector.TILE_SIDE_LENGTH*2, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//PipeHead.png");
            p.addPiranhaPlant(new PiranhaPlant(tile-1, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Pandas//Panda.gif"));
            return p;
        }
        else if (textChar =='t'){//pressure plate
            return new PressurePlate(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if (textChar == 'T'){//trap block
            return new TrapBlock(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Brick.png");
        }
        else if (textChar =='n'){//checkpoint
            Checkpoint c = new Checkpoint(tile, 35, 70, "Resources//Images//BeforeCheckpoint.gif");
            Game.getGameState().getCheckpointHandler().addCheckpoint(c);;
            return c;
        }
        else if (textChar == '!'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(2);
            
            return c;
        }
        else if (textChar == '#'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(0.5);
            
            return c;
        }
        else if (textChar == '%'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(1.5);
            
            return c;
        }
        else if (textChar == '&'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(0.25);
            c.setBulletSpawnInterval(5000);
            return c;
        }
        else if (textChar == 'u'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(0);
            c.setBulletYSpeed(-1);
            
            return c;
        }
        else if (textChar == 'U'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(0);
            c.setBulletYSpeed(1);
            
            return c;
        }
        else if (textChar == '@'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(-2);
            
            return c;
        }
        else if (textChar == '$'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(-0.5);
            
            return c;
        }
        else if (textChar == '^'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(-1.5);
            
            return c;
        }
        else if (textChar == '*'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(-0.25);
            c.setBulletSpawnInterval(5000);
            
            return c;
        }
        else if (textChar == 'w'){
            Cannon c = new Cannon(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "Resources//Images//Blocks//Cannon.png");
            c.setBulletXSpeed(-1);
            
            return c;
        }
        else if (textChar == 'N'){
            return new EndBlock(tile, CollisionDetector.TILE_SIDE_LENGTH, CollisionDetector.TILE_SIDE_LENGTH, "");
        }
        
        
        
        return null;
    }
}