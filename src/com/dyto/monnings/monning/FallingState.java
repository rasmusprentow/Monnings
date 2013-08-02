package com.dyto.monnings.monning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.andengine.entity.sprite.Sprite;

import android.graphics.Point;
import android.util.Log;

import com.badlogic.gdx.physics.box2d.Body;
import com.dyto.monnings.world.BitMapLandscape;

public class FallingState extends AbstractMonningState {
    
 

    
  

    

    public FallingState(Monning monning) {
        super(monning);
       
        
    }
   
    
    @Override
    public IMonningState doAction() {
        
         if(checkForCollisions())
         {
             Log.w("averagre " , "Walking");
             return StateFactory.getInstance().getWalkingState(monning);
           
         }
         
         
         
       
         
         return this;
        /*
        ArrayList<Point> line = BoundaryResolver.getGroundLine(getSprite());
        if(getLandscape().isAir(line)){
            //Log.w("FallingState","IS AIr" + line);
            getBody().setLinearVelocity(0, 3);
            return this;
        } else {
        //    Log.w("FallingState","not Air");
            getBody().setLinearVelocity(0, 0);
            return StateFactory.getWalkingState(monning);
        }
        */
    }

    
  
}
