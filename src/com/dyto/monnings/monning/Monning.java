package com.dyto.monnings.monning;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.LineJointDef;
import com.dyto.monnings.Direction;
import com.dyto.monnings.Environment;
import com.dyto.monnings.Managers.PortalManager;

public class Monning extends MonningBase implements IUpdateHandler {
    // TODO: Make the monning have an extra sprite ensuring that objects can't get stock in the wheel. s
    protected static final int MONNING_TORQUE = 2000;
    protected static final int MONNING_VELOCITY = 8;
    protected static final int MONNING_CHASSIS_WHEEL_DISTANCE = 40;
    protected static final boolean USE_TORQUE_MOVE = false;
    private MonningSprite wheel;
    private MonningSprite chassis;
    private Body chassisBody;
    private Body wheelBody;
    
    private final int  startX = 200;
    private final int startY = 100;
    private Direction direction = Direction.RIGHT;
    private IMonningState state;
    
    public Monning() {
        super();
        setState(new FallingState(this));
        
    }

    public Sprite getWheel() {
        return wheel;
    }


    public Sprite getChassis() {
        return chassis;
    }


    public Body getChassisBody() {
        return chassisBody;
    }


    public Body getWheelBody() {
        return wheelBody;
    }
   
    
    protected void checkForPortal() {
        IShape exit = PortalManager.getInstance().getExit(getSprite());
        if(exit != null)
        {
            chassisBody.setTransform(exit.getX()/32,exit.getY()/32,0);
            wheelBody.setTransform(exit.getX()/32,exit.getY()/32,0);
            
        }
    }

    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction lastDirection) {
        this.direction = lastDirection;
    }

    public void changeDirection() {
        direction = getDirection().getOpposite();
    }


    public Sprite makeWheel(VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion) {
        if(wheel == null)
        {
            wheel = new MonningSprite(startX, startY + MONNING_CHASSIS_WHEEL_DISTANCE, textureRegion, vertexBufferObjectManager){ 
                @Override
                protected void onManagedUpdate(final float pSecondsElapsed) {
                    super.onManagedUpdate(pSecondsElapsed);
                    if(USE_TORQUE_MOVE)
                    {
                        if(!(wheelBody.getAngularVelocity() > MONNING_VELOCITY || wheelBody.getAngularVelocity() < -MONNING_VELOCITY))
                        {
                            wheelBody.applyTorque(getDirection().getNumeralValue() * MONNING_TORQUE); 
                        }
                    }
                    else 
                    {
                        wheelBody.setAngularVelocity(getDirection().getNumeralValue() * MONNING_VELOCITY);
    
                    }
                }
            };
        
        }
        return wheel;
    }
    
    public Sprite makeChassis(VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion) {
        if(chassis == null)
        {
            chassis = new MonningSprite(startX, startY,  textureRegion, vertexBufferObjectManager);
            chassis.setScale(1.2f);
            
        }
        chassis.setListener(this);
        setSprite(chassis);
        return chassis;
    }

  
    
    public  void makePhysicBody(PhysicsWorld pw)
    {
        
        chassisBody = PhysicsFactory.createBoxBody(pw, chassis, BodyType.DynamicBody, Environment.MONNING_FIXTURE_DEF);
        chassisBody.setFixedRotation(true);
        wheelBody = PhysicsFactory.createCircleBody(pw, wheel, BodyType.DynamicBody, Environment.WHEEL_FIXTURE_DEF);
       
        pw.registerPhysicsConnector(new PhysicsConnector(wheel, wheelBody));
        pw.registerPhysicsConnector(new PhysicsConnector(chassis, chassisBody));
        
        final LineJointDef lineJointDef = new LineJointDef();
        lineJointDef.initialize(chassisBody, wheelBody, wheelBody.getWorldCenter(), new Vector2(0f, 1f));
        lineJointDef.collideConnected = true;
        lineJointDef.enableLimit = true;
        lineJointDef.lowerTranslation = 0f;
        lineJointDef.upperTranslation = 0f;
        lineJointDef.enableMotor = false;
        lineJointDef.motorSpeed = 0;// -20f;//-20f;
        lineJointDef.maxMotorForce = 0;// 120f;//120f;
        pw.createJoint(lineJointDef);
         
    }
    
    public Body getBody()
    {
        return chassisBody;
    }
    
   
    
    public float getLowestGround() {
        
        return wheel.getHeight() + wheel.getY();
    }
    
    public float getHeight()
    {
        return  (getLowestGround() - getY());
    }

    public float getX()
    {
        return  getSprite().getX();
        //return (int) getSprite().getScaleX();
    }
    
    public float getY()
    {
        return  getSprite().getY() -5;
     //   return  getSprite().getScaleY();
    }
    
    @Override
    public void setZIndex(int i) {
        super.setZIndex(i);
        this.getChassis().setZIndex(i);
        this.getWheel().setZIndex(i);
        
    }
    
    public void setState(IMonningState state)
    {
        this.state = state;
    }

    @Override
    public void onUpdate(float pSecondsElapsed) {
        
        setState(state.doAction());
        checkForPortal();
    }


    @Override
    public void onDraw(GLState pGLState, Camera pCamera) {
        throw new Error("Who called this?");
        
    }

     public void onTouch() {
        if(!(state instanceof DiggingState))
        {
            this.setState(StateFactory.getInstance().getDiggingState(this)); 
        }
    }
}
