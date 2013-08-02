package com.dyto.monnings.monning;

import java.util.List;

import com.dyto.monnings.world.Brick;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.dyto.monnings.Direction;
import com.dyto.monnings.Environment;
import com.dyto.monnings.ShapeTools;
import com.dyto.monnings.Managers.MonningManager;
import com.dyto.monnings.Managers.SolidObjectManager;
import com.dyto.monnings.Managers.WallManager;

import android.util.Log;

public class DiggingState extends AbstractMonningState {

    public PhysicsWorldRefactor refactor;
    private VertexBufferObjectManager vboman;
    private PhysicsWorld physicsWorld;
    private Scene scene;
    private int numDigs;
    private int countDigs = 0;
    private int countWaitedSteps = 0;
    private boolean diggingIsStarted = false;
    private boolean isRefactored = false;
    private final Color desert = new Color(253f/255,205f/255,143f/255);
    
    public DiggingState(Monning monning, VertexBufferObjectManager vboman, PhysicsWorld physicsWorld, Scene scene) {
        super(monning);
        refactor = new PhysicsWorldRefactor(vboman, physicsWorld, scene);
        this.vboman = vboman;
        this.physicsWorld = physicsWorld;
        this.scene = scene;
       
        numDigs =  Environment.DIGGING_TOTAL_DISTANCE /Environment.DIGGING_PIXELS_PER_DIG;
        
    }

    @Override
    public IMonningState doAction() {
        
        if(countDigs >= numDigs)
        {
            return StateFactory.getInstance().getWalkingState(monning);
        }
        
        
        if(!diggingIsStarted && checkForCollisions())
        {
            /// Wall reach engage digging mode
            diggingIsStarted = true;
            if(isRefactored == false){
                isRefactored = true;
                refactor.refactor(monning);
            }
        }
        else if( !diggingIsStarted)
        {
            /// Guard for when we are far from wall 
            return this;
        }
        
        float westX = 0; // Always closest to the monning
        if(monning.getDirection() == Direction.RIGHT)
        {
            westX = monning.getX() + monning.getWidth();
        }
        else if(monning.getDirection() == Direction.LEFT)
        {
            westX = monning.getX() - Environment.DIGGING_PIXELS_PER_DIG;
        }
        else {
            throw new Error("Trying to dig from a non moving state");
        }
        
        
        Rectangle digSpace = new Rectangle(westX, monning.getY() -2 ,Environment.DIGGING_PIXELS_PER_DIG, monning.getHeight()+1 , vboman );

        List<IAreaShape> colliders = SolidObjectManager.getInstance().getColliders(digSpace);

        /// Digging is started (Some animation should be played)
        // if(countWaitedSteps == 0)
        {
            if(colliders.size() == 0)
            {

                digSpace.dispose(); // We did not collide with a wall or other objects, so we continue from here.
                return StateFactory.getInstance().getWalkingState(monning);
            }
        }
        
        if(countWaitedSteps < Environment.DIGGING_TIME_PER_DIG){
            countWaitedSteps++;
            digSpace.dispose();
            return this;
        }
        
        /// When here we are done digging and are proceding to next dig (This is where we actually move).
        countWaitedSteps = 0;
        countDigs++;
        diggingIsStarted = false;
        

        
        
        digSpace.setColor(desert);
        digSpace.setZIndex(0);
        digSpace.setIgnoreUpdate(true);
        MonningManager.getInstance().setZIndex(1);
        boolean wasBrick = false;
        boolean addedNewShape = false;
        for(IShape shape : colliders){

            if(shape instanceof Brick)
            {
                ShapeTools.removeShape(shape,physicsWorld,scene);
                wasBrick = true;
                continue;
            }
            wasBrick = false;
            float newShapeWidth;
            float newX;
            if(monning.getDirection() == Direction.RIGHT){
                newX = digSpace.getX() + digSpace.getWidth();
                newShapeWidth = ((RectangularShape) shape).getWidth() - (digSpace.getWidth());
                
            } else {
                newX = shape.getX();
                newShapeWidth = digSpace.getX() - newX;
                
            }
            
            Rectangle newShape = new Rectangle(newX, 
                                                shape.getY(),  
                                                (newShapeWidth),
                                                ((RectangularShape)shape).getHeight(), vboman);
            newShape.setVisible(true);
            ShapeTools.removeShape(shape, physicsWorld, scene);
       
            if(newShapeWidth <= 0)
            {
               break;
            }
            addedNewShape = true;
            
           
            ShapeTools.createWall(newShape, physicsWorld, scene);
           
        }

        if(wasBrick == false){
            scene.attachChild(digSpace); // Testing  (is it testing?) //TODO Investigate why it says testing and remove when discovered.

            scene.sortChildren();
        }
        if(addedNewShape == false)
        {

            return StateFactory.getInstance().getWalkingState(monning);
        }
        
        return this;
    }
    
  

}
