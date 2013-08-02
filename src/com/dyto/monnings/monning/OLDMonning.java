package com.dyto.monnings.monning;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.dyto.monnings.Direction;
import com.dyto.monnings.MovableGameEntity;
import static com.dyto.monnings.Direction.RIGHT;
public class OLDMonning   {
/*
    private IMonningState state;
    private Direction direction = RIGHT;
    public Monning() {
        super();
        

       
        setState(new FallingState(this));
        
       
    }

  
    @Override
    public void onUpdate(float pSecondsElapsed) {
        
        setState(state.doAction());
        checkForPortal();
    }
    
    protected void checkForPortal() {
       
        
    }


    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }

  

    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction lastDirection) {
        this.direction = lastDirection;
    }

    public void changeDirection() {
        // TODO Auto-generated method stub
        direction = getDirection().getOpposite();
    }

    @Override
    public void onTouch() {
        if(!(state instanceof DiggingState))
        {
            this.setState(StateFactory.getInstance().getDiggingState(this)); 
        }
        
    }
   
    public void setState(IMonningState state)
    {
        this.state = state;
    }

    @Override
    public Sprite makeSprite(VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion)
    {
        if(getSprite() == null)
        {
            if(vertexBufferObjectManager == null)
            {
                Log.w("Monning","Vertex buffer is zero");
            }
            MonningSprite spr = new MonningSprite(350, 10, textureRegion, vertexBufferObjectManager);
            spr.setListener(this);
            setSprite(spr);
        }
        return getSprite();
    }


    public float getLowestGround() {
        
        return getSprite().getHeight() + getSprite().getY();
    }


    public float getHeight() 
    {
        return (int) getSprite().getHeight();
    }


    public Vector2 getPosition() 
    {
        return new Vector2(getSprite().getX(), getSprite().getY());
    }


    public float getNorthEastX()
    {
        return (this.getSprite().getX() + this.getSprite().getWidth());
    }


    public float getY() {
        return  getSprite().getY();
    }


    public float getWidth() {
        return  getSprite().getWidth();
    }


    public float getX() {
       
        return  this.getSprite().getX();
    }


    
    
    
    
    
    public void setZIndex(int i) {
        this.getSprite().setZIndex(i);
        
    }


    @Override
    public void attachChild(IEntity pEntity) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void callOnChildren(IEntityParameterCallable pEntityParameterCallable) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void callOnChildren(IEntityParameterCallable pEntityParameterCallable, IEntityMatcher pEntityMatcher) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void toString(StringBuilder pStringBuilder) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void onDraw(GLState pGLState, Camera pCamera) {
        // TODO Auto-generated method stub
        
    }
   

 
    
    */

}
