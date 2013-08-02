package com.dyto.monnings.world;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

public class BitMapLandscape {

    private WorldTextureDecorator world;
    public BitMapLandscape(WorldTextureDecorator worldDecorator) {
        // TODO Auto-generated constructor stub
        world = worldDecorator;
    }

    public boolean isAir(ArrayList<Point> groundLine) {
        for(Point p : groundLine)
        {
            
            if(world.getBitmap().getWidth() <= p.x){
                Log.w("Width " + world.getBitmap().getWidth(), "X: " + p.x);
                return false;
            }
            /*if(world.getBitmap().getHeight() > p.y){
                return false;
            }*/
            if(world.getBitmap().getPixel(p.x, p.y) == Color.BLACK)
            {
                return false;
            }
            
        }
        return true;
    }

    public boolean isGround(ArrayList<Point> frontLine) {
        for(Point p : frontLine)
        {
            
            if(world.getBitmap().getPixel(p.x, p.y) == Color.WHITE)
            {
                return false;
            }
            
        }
        return true;
    }

    public Integer highestGround(ArrayList<Point> frontLine) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Point p : frontLine)
        {
            
            if(world.getBitmap().getPixel(p.x, p.y) == Color.WHITE)
            {
                list.add(p.y);
            }
            
        }
        if(list.size() == 0)
        {
            return 0;
        }
        return Collections.max(list);
    }

}
