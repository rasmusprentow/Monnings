package com.dyto.monnings;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.dyto.monnings.world.BitMapLandscape;

public interface IMonningState {
    
    public IMonningState doAction();
}
