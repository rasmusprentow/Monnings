package com.dyto.monnings;

import java.util.HashMap;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import android.app.Activity;
import android.app.WallpaperManager;

public class Entities {
    private static Body body;
    public static final Object LEVEL_TYPE_WALL = "wall";
    public static void addEntity(
      GameActivity pParent,
      Scene pScene,
      int pX,
      int pY,
      int pWidth,
      int pHeight,
      String pType,
      HashMap<String,String> properties,
      VertexBufferObjectManager pVman
            )
    {
      if(pType.equals(Entities.LEVEL_TYPE_WALL)) {
        Entities.addWall(pParent, pScene, pX, pY, pWidth, pHeight,properties, pVman);
      } 
    }

    private static void addWall(
      GameActivity pParent,
      Scene pScene,
      int pX,
      int pY,
      int pWidth,
      int pHeight,
      HashMap<String,String> properties,
      VertexBufferObjectManager pVman)
    {
      final Rectangle wall = new Rectangle(pX, pY, pWidth, pHeight,  pVman);
      wall.setVisible(false);
      WallManager.getInstance().addWall(wall);
      if(properties.containsKey("rotate")){
        wall.setRotation(Float.parseFloat(properties.get("rotate")));
      }
      
       body = PhysicsFactory.createBoxBody(
        pParent.mPhysicsWorld,
        wall, 
        BodyType.StaticBody,
        Environment.WALL_FIXTURE_DEF
      );
      pScene.getFirstChild().attachChild(wall);
    }
  } 