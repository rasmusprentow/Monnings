package com.dyto.monnings;

import java.util.List;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.dyto.monnings.Managers.SolidObjectManager;
import com.dyto.monnings.Managers.WallManager;
import com.dyto.monnings.monning.PhysicsWorldRefactor;

public final class ShapeTools {

    /**
     * Removes a shape entirely.
     * 
     * @param shape
     * @param physicsWorld
     * @param scene
     */
    public static void removeShape(final IShape shape, final PhysicsWorld physicsWorld,final Scene scene) 
    {
        final PhysicsConnector facePhysicsConnector = physicsWorld.getPhysicsConnectorManager().findPhysicsConnectorByShape(shape);
        if (facePhysicsConnector == null) {
            return;
        }
        physicsWorld.unregisterPhysicsConnector(facePhysicsConnector);
        physicsWorld.destroyBody(facePhysicsConnector.getBody());

        SolidObjectManager.getInstance().removeEntity(shape);

        scene.unregisterTouchArea(shape);
        scene.detachChild(shape);

        shape.dispose();
        
       // System.gc();
    }

    public static void createWall(final List<IAreaShape> list, final PhysicsWorld physicsWorld, final Scene scene) 
    {
        for (IAreaShape shape : list) {
            createWall(shape, physicsWorld, scene);
        }
    }
    

    /**
     * @param shape
     * @param physicsWorld
     * @param scene
     */
    public static void createWall(final IAreaShape shape, final PhysicsWorld physicsWorld, final Scene scene) 
    {
        shape.setVisible(false);
        WallManager.getInstance().addEntity(shape);

        Body body = PhysicsFactory.createBoxBody(

        physicsWorld, shape, BodyType.StaticBody, Environment.WALL_FIXTURE_DEF);
        scene.getFirstChild().attachChild(shape);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(shape, body, true, true));

    }

}
