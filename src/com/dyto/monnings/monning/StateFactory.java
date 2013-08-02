package com.dyto.monnings.monning;

import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class StateFactory {
    
    private static StateFactory instance;
    
    private  StateFactory()
    {
        
    }
    
    public static StateFactory getInstance()
    {
        if(null == instance){
            if(!isPreparedCorrectly())
            {
                throw new Error("The Statefactory is not setup completly, please correct");
            }
            instance = new StateFactory();
        }
        return instance;
    }
    
    private static PhysicsWorld physicsWorld;
    private static TextureRegion brickTextureRegion;
    private static VertexBufferObjectManager vertexBufferObjectManager;
    private static Scene scene;
    private static boolean correctlySet = false;
    
    public static void setPhysicsWorld(PhysicsWorld physicsWorld) {
        StateFactory.physicsWorld = physicsWorld;
    }

    public static void setBrickTextureRegion(TextureRegion brickTextureRegion) {
        StateFactory.brickTextureRegion = brickTextureRegion;
    }

    public static void setVertexBufferObjectManager(VertexBufferObjectManager vertexBufferObjectManager) {
        StateFactory.vertexBufferObjectManager = vertexBufferObjectManager;
    }
    
    public static void setScene(Scene scene) {
        StateFactory.scene = scene;
    }
    
    
    
    public static boolean isPreparedCorrectly()
    {
        if(correctlySet)
        {
            return true;
        }
        
        correctlySet  = true;
        if(physicsWorld == null)
        {
            correctlySet = false;
        }
        if(brickTextureRegion == null)
        {
            correctlySet = false;
        }
        if(vertexBufferObjectManager == null)
        {
            correctlySet = false;
        }
        if(scene == null)
        {
            correctlySet = false;
        }
        return correctlySet;
    }
    
    public  WalkingState getWalkingState(Monning monning)
    {
        return new WalkingState(monning);
       
    }
    
    public  FallingState getFallingState(Monning monning)
    {
        return new FallingState(monning);
        
    }
    
    public  DiggingState getDiggingState(Monning monning)
    {
        return  new DiggingState(monning,vertexBufferObjectManager, physicsWorld, scene);
        
    }
    
    public  StairBuildingState getStairBuildingState(Monning monning)
    {
        return  new StairBuildingState(monning, physicsWorld, brickTextureRegion, vertexBufferObjectManager, scene);
        
    }
    
}
