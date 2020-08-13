package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import core.CollisionDetector;
import game.entities.factories.BlockFactory;
import game.entities.factories.EnemyFactory;
import game.entities.factories.ProjectileFactory;
import game.entities.projectiles.Projectile;
import game.Game;
import game.entities.Entity;
import game.entities.enemies.Enemy;

public class LevelReader {


    public static void getBlocks(String fileName,BlockFactory blockFactory,EnemyFactory enemyFactory,ProjectileFactory projectileFactory){
        Vector<Character> chars = getChars(fileName);
        int index = 0;
        Entity e;
        for(Character c : chars){
            if(c!=null){
            e = blockFactory.getEntity(c,index);
            if(e!=null){
                Game.getGameState().blocks.put(index,e);
            }
            else{
                e = enemyFactory.getEntity(c, index);
                if(e!=null){
                    Game.getGameState().enemies.add((Enemy)e);
                }
                else{
                    e = projectileFactory.getEntity(c,index);
                    if(e!=null){
                        Game.getGameState().projectiles.add((Projectile)e);
                    }
                }
            }
            
        }
            index++;
        }
    }

    /**
     * Method that gets all the characters in a vector of a level file
     * 
     * @param fileName      Name of the text file that will be be imported
     * @author Will
     */
    private static Vector<Character> getChars (String fileName){
        Vector<Character> chars = new Vector<Character>();
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            int countColumn = 0;
            
            String line;
            
            for (int i = 0; i<CollisionDetector.COLUMN_HEIGHT;i++){
                line = reader.readLine();
                for(int j = 0; line!=null &&j<line.length();j++){
                    if (i+countColumn*CollisionDetector.COLUMN_HEIGHT>=chars.size()){
                        chars.setSize(i+countColumn*CollisionDetector.COLUMN_HEIGHT+1);
                    }
                    
                    chars.set(i+countColumn*CollisionDetector.COLUMN_HEIGHT, line.charAt(j));
                    countColumn++;
                }
                countColumn = 0;
                
            }
            fr.close();
            reader.close();
            }
            catch (FileNotFoundException e){
                System.out.println("Level Not Found");
            }
            catch (IOException f){
                System.out.print("Oops something went wrong");
            }
            chars.trimToSize();
            return chars;
    }
}