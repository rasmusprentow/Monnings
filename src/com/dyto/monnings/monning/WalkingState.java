package com.dyto.monnings.monning;

import org.andengine.extension.physics.box2d.util.Vector2Pool;

import com.badlogic.gdx.math.Vector2;

import android.util.Log;

public class WalkingState extends AbstractMonningState {

    public WalkingState(Monning monning) {
        super(monning);
        // TODO Auto-generated constructor stub
    }

    @Override
    public IMonningState doAction() {
        //
        
        
        if(checkForCollisions( ))
        {
       
           // Log.w("New Direction ", "New Direction");
            monning.changeDirection();
        }
    
        
        return this;
    }

}
