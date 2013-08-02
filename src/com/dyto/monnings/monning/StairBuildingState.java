package com.dyto.monnings.monning;

import com.dyto.monnings.world.Brick;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.dyto.monnings.*;
import com.dyto.monnings.Managers.BrickManager;
import com.dyto.monnings.Managers.WallManager;


public class StairBuildingState extends AbstractMonningState {

     
    
    private PhysicsWorld mPhysicsWorld;
    private TextureRegion brickTexture;
    private VertexBufferObjectManager vertexBufferObjectManager;
    private Scene mScene;

    private int count = 0;
    private int lastBrickY;
    
    
    public StairBuildingState(Monning monning, PhysicsWorld physicsWorld, TextureRegion brickTexture, VertexBufferObjectManager vertexBufferObjectManager, Scene scene) {
        super(monning);
        this.mPhysicsWorld = physicsWorld;
        this.brickTexture = brickTexture;   //TODO: Move to Better Location.
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.mScene = scene;
    }

    @Override
    public IMonningState doAction() {

        final Sprite brick;
        final Body body;
        
       
       
        
        
        if(checkForCollisions())
        {
            monning.changeDirection();
            return StateFactory.getInstance().getWalkingState(monning);
            
        }
        
        if(Environment.BRICK_AMOUNT_TO_LAY <= count)
        {
            return StateFactory.getInstance().getWalkingState(monning);
        }
        
        if(lastBrickY == 0 || lastBrickY >= monning.getY() + Environment.BRICK_HEIGHT  - 1){
           
            
        
            
            brick = new Brick(getMonning().getX()  + getPlacementBuffer(), getMonning().getLowestGround() - Environment.BRICK_HEIGHT, this.brickTexture, this.vertexBufferObjectManager);
            brick.setHeight(Environment.BRICK_HEIGHT);
            brick.setWidth(Environment.BRICK_WIDTH);
            
            if(WallManager.getInstance().checkForCollision(brick))
            {
                
                brick.dispose();
                return StateFactory.getInstance().getWalkingState(monning);
            }
            
            if(BrickManager.getInstance().checkForCollision(brick))
            {
                brick.dispose();
                return this;
            }
            
            BrickManager.getInstance().addEntity(brick);
            
            body = PhysicsFactory.createBoxBody(this.mPhysicsWorld, brick, BodyType.StaticBody, Environment.WALL_FIXTURE_DEF);
            count++;
            this.mScene.attachChild(brick);
            this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(brick, body, true, true));
          
            
            lastBrickY = (int) monning.getY();
        }
        
        return this;
    }

    private int getPlacementBuffer() {
        int placementBuffer = 0;
        if(monning.getDirection() == Direction.RIGHT)
        {
            placementBuffer += getMonning().getWidth() + Environment.BRICK_PLACEMENT_BUFFER;
        } 
        if(monning.getDirection() == Direction.LEFT)
        {
            placementBuffer  -= Environment.BRICK_PLACEMENT_BUFFER + Environment.BRICK_WIDTH;
        }
        return placementBuffer;
    }

}
