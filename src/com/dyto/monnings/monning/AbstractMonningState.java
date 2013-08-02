package com.dyto.monnings.monning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.dyto.monnings.world.BitMapLandscape;

import android.graphics.Point;
import android.util.Log;

abstract public class AbstractMonningState implements IMonningState {
    protected Monning monning;

    final static int HISTORY = 20; //fps(15) = 5, fps(60) = 20
    protected Queue<Float> previous = new LinkedList<Float>();
    
    protected boolean checkForCollisions()
    {
        if(monning.getBody() == null) return false;
        float i = monning.getBody().getPosition().x;
        
        previous.add(i);
        if(previous.size() < HISTORY)
        {
            return false;
        }
        
        boolean soFarSoGood = true;
        //Log.w("Monning" + monning, "" + i);
      //  Log.w("Monning" + monning, "" + i + " Abs: " + Math.abs(previous.element() - i) + " Max " + Collections.max(previous));
        for(Float elem : previous)
        {
            
            if(Math.abs(elem - i) > 0.5f)  
            {
                soFarSoGood = false;
            }
        }
        if(soFarSoGood)
        {
            previous = new LinkedList<Float>();
            return true;
        }
      
        previous.remove();
        return false;
    }

    public AbstractMonningState(Monning monning) {
       this.monning = monning;
      
    }
    
    public Monning getMonning(){
        return monning;
    }

    


    
   
}
