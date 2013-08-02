package com.dyto.monnings.Managers;

import java.util.Stack;
import java.util.Timer;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;

import android.util.Log;

import com.dyto.monnings.Environment;
import com.dyto.monnings.GameActivity;
import com.dyto.monnings.monning.Monning;
import com.dyto.monnings.monning.Monning;

public class MonningGenerator implements IUpdateHandler {

    
    private Scene scene;
    private float interval;
    private GameActivity gameActivity;
    private int callCount = 0;
    private Stack<Monning> monningStack;
    
    /**
     * Attaches a monning to the scene pr interval
     * @param scene The Scene
     * @param count Number of Monnings
     * @param inverval The delay between each monning measured in seconds
     */
    public MonningGenerator(GameActivity ga, Scene scene, int count, float interval) {
        super();
        this.scene = scene;
        this.gameActivity = ga;
        this.interval = interval;
        monningStack = new Stack<Monning>();
        for(int i = 0; i < count; i++){
            Monning monning = ga.createMonning();
            MonningManager.getInstance().addEntity(monning);
            monningStack.push(monning);
        }
    }

    
   
    
    
    @Override
    public void onUpdate(float pSecondsElapsed) {
  //     if(Environment.FPS)
        if(monningStack.empty())
        {
            destroy();
        }
        
        
        
        if(interval*Environment.FPS <= callCount)
        {
            Monning monning = monningStack.pop();
            this.gameActivity.addMonning(this.scene,monning);

            //MonningManager.getInstance().addEntity(monning);
            
            callCount = 0;
        }
        
        ++callCount;
    }

    private void destroy() {
        this.scene.unregisterUpdateHandler(this);
        
    }





    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }
    
   
    

}
