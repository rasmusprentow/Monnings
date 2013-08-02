package com.dyto.monnings.Managers;

import java.util.HashMap;
import java.util.Map;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;

import com.dyto.monnings.Portal;

import android.util.SparseArray;

public class PortalManager {

    private static PortalManager instance;
    private Map<String, Portal> portals;
    
    private  PortalManager()
    {
        portals = new HashMap<String, Portal>();
    }
    
    public static PortalManager getInstance() {
       if(instance == null)
       {
           instance = new PortalManager();
       }
       return instance;
    }
    
    public void addEntrance(IShape wall, String id) {
       if(portals.get(id) == null)
       {
           portals.put(id, new Portal(id));
       }
       portals.get(id).addEntrance((wall));
    }
    

    public IShape getExit(Sprite sprite) {
       for(Portal s : portals.values())
       {
           IShape exit = s.inPortal(sprite);
           if(exit != null)
           {
               return exit;
           }
       }
       
       
        return null;
    }

     public void addExit(IShape wall, String id) {
       if(portals.get(id) == null)
       {
           portals.put(id, new Portal(id));
       }
       portals.get(id).setExit((wall));
    }
  
    
     public static void reset() {
         instance = null;
         
     }
     
    
    


}
