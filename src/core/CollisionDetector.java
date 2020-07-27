package core;

import java.util.Vector;

import game.entities.StaticEntity;

/**
 * Collision detector that handles all of the game's collisions
 */

public class CollisionDetector {
    public static final int COLUMN_HEIGHT = 50;
    public static final int TILE_SIDE_LENGTH = 35;

    /**
     * returns the tile in which a point is in
     * 
     * @param xPos      x position of the point to be checked
     * @param yPos      y position of the point to be checked
     */
    private static int getTile(int xPos, int yPos){
        return (int)Math.floor(xPos/TILE_SIDE_LENGTH)*COLUMN_HEIGHT + (int)Math.floor(yPos/TILE_SIDE_LENGTH+1);
    }

    
    private static int[] getOccupyingTiles(StaticEntity entity){
        //entity.getWidth() and entity.getHeight() entity.getX() entity.getY()
        int width = entity.getWidth();
        int height = entity.getHeight();
        int startX = entity.getX();
        int startY = entity.getY();
        int width2;
        int height2;
        int tileCount=0;
        int[] temp = new int[4];
        temp[0] = getTile(startX,startY); //Top Left
        temp[1] = getTile(startX+width, startY); //Top Right
        temp[2] = getTile(startX,startY+height); //Bottom Left
        temp[3] = getTile(startX+width, startY+height); //Bottom Right
        width2 = (temp[1]-temp[0])/50+1;
        height2 = temp[2]-temp[0];
        tileCount = width2*height2;
        int[] ans = new int[tileCount];
        for (int i = 0; i < width2; i++)
        {
            for (int j=0; j<height2; j++)
            {
                ans[j+i*height2]=temp[0]+j+50*i;
            }

        }
        return ans;

        

    }
}