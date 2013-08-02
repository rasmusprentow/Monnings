package com.dyto.monnings.Managers;

import org.andengine.entity.shape.IShape;



public class WallManager extends ShapeManager {
    
    private WallManager()
    {
        super();
    }

    private static WallManager instance;

    public static WallManager getInstance()
    {
        if(null == instance){
           
            instance = new WallManager();
        }
        return instance;
    }
    
    public static void reset() {
        instance = null;
        
    }
    
    
}
