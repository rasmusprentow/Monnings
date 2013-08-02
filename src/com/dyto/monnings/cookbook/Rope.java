package com.dyto.monnings.cookbook;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.math.MathConstants;
import org.andengine.util.math.MathUtils;

import android.hardware.SensorManager;
import android.util.FloatMath;

import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.LineJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

public class Rope extends Object {
	
public final int numRopeSegments;
public final float ropeSegmentsLength;
public final float ropeSegmentsWidth;
public final float ropeSegmentsOverlap;
public final Rectangle[] RopeSegments;
public final Body[] RopeSegmentsBodies;
public FixtureDef RopeSegmentFixtureDef;
	
	public Rope(Body pAttachTo, final int pNumRopeSegments, final float pRopeSegmentsLength, final float pRopeSegmentsWidth, final float pRopeSegmentsOverlap,
			final float pMinDensity, final float pMaxDensity, IEntity pScene, PhysicsWorld pPhysicsWorld, VertexBufferObjectManager pVertexBufferObjectManager) {
		numRopeSegments = pNumRopeSegments;
		ropeSegmentsLength = pRopeSegmentsLength;
		ropeSegmentsWidth = pRopeSegmentsWidth;
		ropeSegmentsOverlap = pRopeSegmentsOverlap;
		RopeSegments = new Rectangle[numRopeSegments];
		RopeSegmentsBodies = new Body[numRopeSegments];
		RopeSegmentFixtureDef = PhysicsFactory.createFixtureDef(pMaxDensity, 0.01f, 0.0f);
		for(int i = 0; i < numRopeSegments; i++)
		{
			final RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
			if(i<1)
				RopeSegments[i] = new Rectangle(pAttachTo.getWorldCenter().x * PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, (pAttachTo.getWorldCenter().y * PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT) - (ropeSegmentsLength / 2) + ropeSegmentsOverlap, ropeSegmentsWidth, ropeSegmentsLength, pVertexBufferObjectManager);
			else
				RopeSegments[i] = new Rectangle(RopeSegments[i-1].getX(), RopeSegments[i-1].getY() - RopeSegments[i-1].getHeight() + ropeSegmentsOverlap, ropeSegmentsWidth, ropeSegmentsLength, pVertexBufferObjectManager);
			RopeSegments[i].setColor(0.97f, 0.75f, 0.54f);
			if(i>0) RopeSegmentFixtureDef.density-=(pMaxDensity-pMinDensity)/numRopeSegments;
			RopeSegmentsBodies[i] = PhysicsFactory.createCircleBody(pPhysicsWorld, RopeSegments[i], BodyType.DynamicBody, RopeSegmentFixtureDef);
			RopeSegmentsBodies[i].setAngularDamping(4f);
			RopeSegmentsBodies[i].setLinearDamping(0.5f);
			RopeSegmentsBodies[i].setBullet(true);
			if(i<1)
				revoluteJointDef.initialize(pAttachTo, RopeSegmentsBodies[i], pAttachTo.getWorldCenter());
			else
				revoluteJointDef.initialize(RopeSegmentsBodies[i-1], RopeSegmentsBodies[i], 
						new Vector2(RopeSegmentsBodies[i-1].getWorldCenter().x,
								RopeSegmentsBodies[i-1].getWorldCenter().y - (ropeSegmentsLength/2)/PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT));
			PhysicsConnector RopeSegmentPhysConnector = new PhysicsConnector(RopeSegments[i], RopeSegmentsBodies[i]);
			pPhysicsWorld.registerPhysicsConnector(RopeSegmentPhysConnector);
			revoluteJointDef.collideConnected=false;
			pPhysicsWorld.createJoint(revoluteJointDef);
			pScene.attachChild(RopeSegments[i]);
		}
	}
	
}