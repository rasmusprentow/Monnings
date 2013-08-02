package com.dyto.monnings;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;

import com.dyto.monnings.monning.StateFactory;


public class WallManager {
    private static WallManager instance;
    
    private  WallManager()
    {
        walls = new ArrayList<IShape>();
    }
    
    public static WallManager getInstance()
    {
        if(null == instance){
           
            instance = new WallManager();
        }
        return instance;
    }
    
    private List<IShape> walls;
    
    public void addWall(IShape wall)
    {
        this.walls.add(wall);
    }
    
    public List<IShape> getWalls()
    {
        return walls;
    }

    public static boolean checkForCollision(Sprite brick) {
        for(IShape shape : WallManager.getInstance().getWalls())
        {
            if(brick.collidesWith(shape))
            {
                return true;
            }
        }
        return false;
    }
}
