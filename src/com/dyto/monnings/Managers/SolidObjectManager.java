package com.dyto.monnings.Managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;


public class SolidObjectManager extends AbstractEntityManager<IAreaShape,IShape> {

    
    private static SolidObjectManager instance;

    public static SolidObjectManager getInstance()
    {
        if(null == instance){
           
            instance = new SolidObjectManager();
        }
        return instance;
    }
    

    private List<IShapeManager> managers; 
    
    private SolidObjectManager()
    {
        
        managers = new ArrayList<IShapeManager>();
        managers.add(WallManager.getInstance());
        managers.add(BrickManager.getInstance());
    }
    
    @Override
    public List<IAreaShape> getColliders(IShape shape) {
        List<IAreaShape> list = new ArrayList<IAreaShape>();
        for(IShapeManager m : managers)
        {
            list.addAll(m.getColliders(shape));
        }
        return list;
    }

    @Override
    public void addEntity(IAreaShape entity) {
        throw new Error("The aggregated object managaer cannot have objects added");
    }

    @Override
    public List<IAreaShape> getEntities() {
        List<IAreaShape> list = new ArrayList<IAreaShape>();
        for(IEntityManager m : managers)
        {
            list.addAll(m.getEntities());
        }
        return list;
    }

    @Override
    public boolean checkForCollision(IShape shape) {
        for(IShapeManager m : managers)
        {
            if(m.checkForCollision(shape)) return true;
        }
        return false;
    }

    public  void removeEntity(IShape face) {
        
        for(IEntityManager m : managers)
        {
            m.removeEntity(face);
        }
    }

}
