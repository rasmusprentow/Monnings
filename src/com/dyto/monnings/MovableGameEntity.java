package com.dyto.monnings;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.dyto.monnings.world.BitMapLandscape;

public abstract class MovableGameEntity extends GameEntity implements IUpdateHandler {

    protected Body body;
    
    public MovableGameEntity() {
        super();
    }
    
    
    
    
    public  Body makePhysicBody(PhysicsWorld pw)
    {
        body = (PhysicsFactory.createBoxBody(pw, getSprite(), BodyType.DynamicBody, Environment.MONNING_FIXTURE_DEF));
        pw.registerPhysicsConnector(new PhysicsConnector(getSprite(), body, true, true));
        return getBody();
    }
  
  
    public Body getBody() {
        return body;
    }

  

}
