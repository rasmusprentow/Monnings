package com.dyto.monnings.Managers;

import org.andengine.entity.shape.IShape;



public class BrickManager extends  ShapeManager{
    
    BrickManager()
    {
        super();
    }

    private static BrickManager instance;

    public static BrickManager getInstance()
    {
        if(null == instance){
           
            instance = new BrickManager();
        }
        return instance;
    }
    
    public static void reset() {
        instance = null;
        
    }
    
}
