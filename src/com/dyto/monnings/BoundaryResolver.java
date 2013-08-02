package com.dyto.monnings;

import java.util.ArrayList;

import static com.dyto.monnings.Direction.LEFT;
import static com.dyto.monnings.Direction.RIGHT;

import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;

import android.graphics.Point;
import android.util.Log;

public class BoundaryResolver {

    /**
     * Gets the shapes line with highest y minus the two outer most points. 
     *   1 2 3 4 5
     * 1 x x x x x
     * 2 x x x x x
     * 3 x R R R x
     * @param stepheih 
     */
    static public ArrayList<Point> getInnerLowerBoundary(RectangularShape sprite, int stepheih)
    {
       ArrayList<Point> list = new ArrayList<Point>();
      
       /**
        * Height = 3
        * getY = 1;
        * 1 x x x x x
        * 2 x x x x x
        * 3 x x x x x  y = 1 + 3 - 1
        */
       int y = (int) (sprite.getY() + sprite.getHeight())  -stepheih;     
      
       /**
        * GetX = 1;
        * width = 5;
        *   1 2 3 4 5
        * 1 x x x x x
        * 2 x x x x x
        * 3 x s x e x  e = 1 + 5 - 2 = 4
        */
       int start = (int) (sprite.getX() +1);
       int end = (int) (sprite.getX() + sprite.getWidth() -2);
      
      
        
        for(int i = start; i <= end; i++ ) 
        {
            list.add(new Point(i, y));
        }
        
        return list;
    }
    
    static public ArrayList<Point> getOuterLowerGround(RectangularShape sprite, Direction direction, int stepheih)
    {
        ArrayList<Point> list = new ArrayList<Point>();
        
        int y = (int) (sprite.getY() + sprite.getHeight())  -stepheih;
        
        /**
         * GetX = 1;
         * width = 5;
         *   1 2 3 4 5
         * 1 x x x x x
         * 2 x x x x x
         * 3 s x x x e  e = 1 + 5 - 1 = 5
         */
        int end = (int) (sprite.getX() + sprite.getWidth()) -1;
        int start = (int) sprite.getX();
      
      
      

        if(direction == LEFT) 
        {
            /**
             * We are moving to the right and we care to get the front. 
             *   1 2 3 4 5
             * 1 x x x x x
             * 2 x x x x x
             * 3 e x x x x e = s 
             */
            end = start;
           
        }
        
        if(direction == RIGHT) // Right
        {
            /**
             * We are moving to the right and we care to get the front. 
             *   1 2 3 4 5
             * 1 x x x x x
             * 2 x x x x x
             * 3 x x x x e e = s 
             */
            start = end; 
        }
      
        
        for(int i = start; i <= end; i++ ) 
        {
            list.add(new Point(i, y));
        }
       
        return list;
    }
    
    /**
     * Line just beneath the shape
     * @param sprite
     * @return
     */
    static public ArrayList<Point> getGroundLine(RectangularShape sprite)
    {
        ArrayList<Point> list = new ArrayList<Point>();
        
        int y = (int) (sprite.getY() + sprite.getHeight()) ;
        int end = (int) (sprite.getX() + sprite.getWidth()) -1;
        int start = (int) sprite.getX();
  
        
        for(int i = start; i <= end; i++ ) 
        {
            list.add(new Point(i, y));
        }
     
        return list;
    }
    

    
    static private ArrayList<Point> getFrontLine(RectangularShape sprite, int allowSteppingHeight, int topReduces, Direction direction)
    {
        ArrayList<Point> list = new ArrayList<Point>();
        
        /*
        int yStart = (int) (sprite.getY() + sprite.getHeight()) - 1 - allowSteppingHeight;
        int x = 0;
        if(direction == -1)
        {
            x = (int) (sprite.getX() -1);
        } else 
        {   
            x = (int) (sprite.getX() + sprite.getWidth());
        }
        int yEnd = (int) sprite.getY() + topReduces;
        */
        
        int yEnd = (int) ( sprite.getHeight()) - 1 - allowSteppingHeight;
        int x = 0;
        if(direction == RIGHT)
        {
            x = (int) (sprite.getWidth()) -1;
        }
        int yStart = (int) topReduces;
        
        for(int i = (int) yStart; i <= yEnd; i++ )
        {
            float[] eyeCoordinates = sprite.convertLocalToSceneCoordinates(x, i);
            int corX = (int) eyeCoordinates[Sprite.VERTEX_INDEX_X];
            int corY = (int) eyeCoordinates[Sprite.VERTEX_INDEX_Y];
            list.add(new Point(corX, corY));
        }
        
        return list;
    }

    /**
     * The lowest line within the shape
     * @param sprite
     * @param stepheih 
     * @return
     */
    public static ArrayList<Point> getLowerLine(Sprite sprite) {
ArrayList<Point> list = new ArrayList<Point>();
        
        int y = (int) (sprite.getY() + sprite.getHeight()) -1;
        int end = (int) (sprite.getX() + sprite.getWidth()) -1;
        int start = (int) sprite.getX();
  
        
        for(int i = start; i <= end; i++ ) 
        {
            list.add(new Point(i, y));
        }
     
        return list;
    }

    public static ArrayList<Point> getUpperFrontLine(Sprite sprite, int stepheih, Direction direction) {
  ArrayList<Point> list = new ArrayList<Point>();
        
          /**
           * stepheight =1
           * y = 1;
           * height = 4 
           *   1 2 3 4 5
           * 1 x x x x s
           * 2 x x x x e
           * 3 x x x x x
           * 4 x x x x x
           */
        int yEnd = (int) ( sprite.getY() + sprite.getHeight()) - 2 - stepheih;
        
        int x = (int) sprite.getX();
        if(direction == RIGHT)
        {
            x += (int) (sprite.getWidth()) -1;
        }
        int yStart = (int) sprite.getY();
        
        for(int i = (int) yStart; i <= yEnd; i++ )
        {
            
            list.add(new Point(x, i));
        }
        
        return list;
    }

    public static ArrayList<Point> getLowerFrontLine(Sprite sprite, int stepheih, Direction direction) {
        ArrayList<Point> list = new ArrayList<Point>();
        
        /**
         * stepheight =1
         * y = 1;
         * height = 3 
         *   1 2 3 4 5
         * 1 x x x x x
         * 2 x x x x x
         * 3 x x x x s/e
         * 4 x x x x x
         */
      int yStart = (int) ( sprite.getY() + sprite.getHeight() -1 - stepheih);
      int yEnd = (int) ( sprite.getY() + sprite.getHeight()) - 2 ;
      
      int x = (int) sprite.getX();
      if(direction == RIGHT)
      {
          x += (int) (sprite.getWidth()) -1;
      }
     
      
      for(int i = (int) yStart; i <= yEnd; i++ )
      {
          
          list.add(new Point(x, i));
      }
      
      return list;
    }





}
