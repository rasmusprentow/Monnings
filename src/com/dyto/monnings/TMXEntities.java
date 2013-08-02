package com.dyto.monnings;

import java.util.HashMap;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IShape;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.dyto.monnings.Managers.PortalManager;
import com.dyto.monnings.Managers.WallManager;

import android.app.Activity;
import android.app.WallpaperManager;
import android.util.Log;

public class TMXEntities {
    public static final Object LEVEL_TYPE_WALL = "wall";
    public static final Object LEVEL_TYPE_PORTAL = "portal";
    public static final Object LEVEL_TYPE_OBSIDIAN = "obsidian";
    public static final Object LEVEL_PORTAL_EXIT = "exit";
    public static final Object LEVEL_PORTAL_ENTRANCE = "entrance";
    public static final Object LEVEL_PORTAL_ID = "id";
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
      if(pType.equals(TMXEntities.LEVEL_TYPE_WALL)) {
        TMXEntities.addWall(pParent, pScene, pX, pY, pWidth, pHeight,properties, pVman, true);
      } 
      if(pType.equals(TMXEntities.LEVEL_TYPE_OBSIDIAN)) {
          TMXEntities.addWall(pParent, pScene, pX, pY, pWidth, pHeight,properties, pVman, false);
        } 
      
      if(pType.equals(TMXEntities.LEVEL_TYPE_PORTAL)) {
          TMXEntities.addPortal(pParent, pScene, pX, pY, pWidth, pHeight,properties, pVman);
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
      VertexBufferObjectManager pVman,
      boolean isWall
      )
    {
        
      final Rectangle wall = new Rectangle(pX, pY, pWidth, pHeight,  pVman);
    //  if(isWall)
          wall.setVisible(false);
      wall.setIgnoreUpdate(true);
      if(isWall)
          WallManager.getInstance().addEntity(wall);
     /* if(properties.containsKey("rotate")){
        wall.setRotation(Float.parseFloat(properties.get("rotate")));
      } */
      
       Body body = PhysicsFactory.createBoxBody(
        pParent.mPhysicsWorld,
        wall, 
        BodyType.StaticBody,
        Environment.WALL_FIXTURE_DEF
      );
      pScene.getFirstChild().attachChild(wall);
      pParent.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(wall, body, true, true));
    }
    
    private static void addPortal(
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
            wall.setIgnoreUpdate(true);
           // Log.w("test : " + properties.get("portaltype") , " weee" );
            if(properties.get("portaltype").equals(LEVEL_PORTAL_ENTRANCE)){
               PortalManager.getInstance().addEntrance( wall, properties.get(LEVEL_PORTAL_ID));
            }
            else 
            {
                PortalManager.getInstance().addExit(wall, properties.get(LEVEL_PORTAL_ID)); 
            }
            
            
           
             
            
            pScene.getFirstChild().attachChild(wall);
          }
  } 