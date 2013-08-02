package com.dyto.monnings.Managers;

import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;

import com.dyto.monnings.monning.Monning;

public class MonningManager  extends AbstractEntityManager<Monning,Monning>{
    
    
    private MonningManager()
    {
        super();
    }

    private static MonningManager instance;

    public static MonningManager getInstance()
    {
        if(null == instance){
           
            instance = new MonningManager();
        }
        return instance;
    }
    
    public void setZIndex(int i )
    {
        for(Monning monning : this.entities){
            monning.setZIndex(i);
        }
    }

    public static void reset() {
        instance = null;
        
    }
    
    
        
    
}
