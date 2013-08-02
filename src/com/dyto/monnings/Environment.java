package com.dyto.monnings;

import org.andengine.extension.physics.box2d.PhysicsFactory;

import android.hardware.SensorManager;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Environment {

    /* The categories. */
    public static final short CATEGORYBIT_WALL = 1;
    public static final short CATEGORYBIT_MONNING = 2;
    public static final short CATEGORYBIT_WHEEL = 4;
    
    /* And what should collide with what. */
    public static final short MASKBITS_WALL = CATEGORYBIT_WALL + CATEGORYBIT_MONNING + CATEGORYBIT_WHEEL;
    public static final short MASKBITS_MONNING = CATEGORYBIT_WALL; 
    public static final short MASKBITS_WHEEL = CATEGORYBIT_WALL; 
    
    public static final FixtureDef WALL_FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0f, 0.9f, false, CATEGORYBIT_WALL, MASKBITS_WALL, (short)0);
    public static final FixtureDef MONNING_FIXTURE_DEF = PhysicsFactory.createFixtureDef(10, 0f, 0f, false, CATEGORYBIT_MONNING, MASKBITS_MONNING, (short)0);
    public static final FixtureDef WHEEL_FIXTURE_DEF = PhysicsFactory.createFixtureDef(20, 0f, 10f, false, CATEGORYBIT_WHEEL, MASKBITS_WHEEL, (short)0);
    
    public static final float GRAVITY_MONNINGS = 2*SensorManager.GRAVITY_EARTH;
    
    public static final int BRICK_HEIGHT = 7;
    public static final int BRICK_WIDTH = 30;
    public static final int BRICK_PLACEMENT_BUFFER = 4;
    public static final int BRICK_AMOUNT_TO_LAY = 10;
    
    public static final int DIGGING_PIXELS_PER_DIG = 10;
    public static final int DIGGING_TOTAL_DISTANCE = 300;
    public static final int DIGGING_TIME_PER_DIG = 10; // Measured in steps
    
    public static final int FPS = 60;
    
    
    
    
   /* public static final ShapeManager WallManager = new ShapeManager(); 
    public static final ShapeManager BrickManager = new ShapeManager(); 
    public static final ShapeManager SolidObjectManager = new ShapeManager(); 
    */
    
    static {
        
        
    }
}
