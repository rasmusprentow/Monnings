package com.dyto.monnings;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.shape.IShape;

public class Portal {

    private IShape exit;
    private List<IShape> entrances;
    private String id;
    
    public Portal(String id)
    {
        this.id = id;
        entrances = new ArrayList<IShape>();
    }
    
    public void setExit(IShape exit) {
        this.exit = exit;
    }

    public boolean addEntrance(IShape object) {
        return entrances.add(object);
    }
    
    public IShape inPortal(IShape entity)
    {
        for (IShape entrance : entrances)
        {
            if(entrance.collidesWith(entity)){
                if(exit == null)
                {
                    throw new Error("The exit of this portal is not set. Id: " + id);
                }
                return exit;
            }
        } 
        return null;
    }
    
    
    
}
