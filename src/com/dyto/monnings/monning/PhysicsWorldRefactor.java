package com.dyto.monnings.monning;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.dyto.monnings.Direction;
import com.dyto.monnings.Environment;
import com.dyto.monnings.ShapeTools;
import com.dyto.monnings.Managers.SolidObjectManager;
import com.dyto.monnings.Managers.WallManager;

public class PhysicsWorldRefactor {

    
    private VertexBufferObjectManager vertexBufferObjectManager;
    private PhysicsWorld physicsWorld;
    private Scene scene;

    private Color topColor;
    private Color butColor;
    public PhysicsWorldRefactor(VertexBufferObjectManager vertexBufferObjectManager, PhysicsWorld physicsWorld, Scene scene) {
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.physicsWorld = physicsWorld;
        this.scene = scene;
    }
    
    public void refactor(final Monning digger)
    {
        int overLayBuffer = 3;
        float westX = 0; // Always closest to the monning
        float eastX  = 0; // if left that'll be east otherwise west
        
        if(digger.getDirection() == Direction.RIGHT)
        {
            westX = digger.getX() + digger.getWidth();
            eastX = westX + Environment.DIGGING_TOTAL_DISTANCE;
        }
        else if(digger.getDirection() == Direction.LEFT)
        {
            eastX = digger.getX();
            westX = eastX - Environment.DIGGING_TOTAL_DISTANCE;
        }
        else {
            throw new Error("Trying to dig from a non moving position");
        }
        
        Line upperLine = new Line(westX, digger.getY() - overLayBuffer, eastX,  digger.getY() - overLayBuffer, vertexBufferObjectManager);
     
        
        Line lowerLine = new Line(westX, digger.getY()  + digger.getHeight(), eastX, digger.getY()  + digger.getHeight(), vertexBufferObjectManager);
        List<IAreaShape> upperColliders = SolidObjectManager.getInstance().getColliders(upperLine);
        topColor = (Color.BLUE);   
        butColor = (Color.YELLOW);   
        ShapeTools.createWall( splitShapesByLine(upperColliders,upperLine),this.physicsWorld, this.scene);
        
        
        topColor = (Color.BLACK);   
        butColor = (Color.GREEN);   
        
        List<IAreaShape> lowerColliders = SolidObjectManager.getInstance().getColliders(lowerLine);
        
        
        ShapeTools.createWall( splitShapesByLine(lowerColliders,lowerLine),this.physicsWorld, this.scene);
        
    }

   
    
    
    private List<IAreaShape>  splitShapesByLine(List<IAreaShape> colliders, Line line)
    {
        List<IAreaShape> newRects = new ArrayList<IAreaShape>();
        
        for(IAreaShape shape : colliders)
        {
            if(shape instanceof Rectangle)
            {
                if((int)line.getY1() == (int)line.getY2()){ // Horizontal
                    Rectangle newTop = new Rectangle(shape.getX(), shape.getY() , ( shape).getWidth(), line.getY1() - shape.getY(), vertexBufferObjectManager);
                    Rectangle newButtom = new Rectangle(shape.getX(),  line.getY1(), (shape).getWidth(),( shape).getHeight()- newTop.getHeight() , vertexBufferObjectManager);
                    newRects.add(newTop);
                    newRects.add(newButtom);
                }
                ShapeTools.removeShape(shape,this.physicsWorld, this.scene);
            }
        }
        return newRects;
    }
   
    
    
}
