package com.dyto.monnings;

import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dyto.monnings.Managers.BrickManager;
import com.dyto.monnings.Managers.MonningManager;
import com.dyto.monnings.Managers.PortalManager;
import com.dyto.monnings.Managers.WallManager;
import com.dyto.monnings.monning.Monning;
import com.dyto.monnings.monning.StateFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.tmx.*;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class GameActivity extends SimpleBaseGameActivity {

    private static int CAMERA_HEIGHT = 720;
    private static int CAMERA_WIDTH = 1280;
    private ITextureRegion mManTextureRegion;
    protected PhysicsWorld mPhysicsWorld;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private Monning monning;
    protected boolean isTrue = true;
    private TMXTiledMap mTMXTiledMap;

    private TiledTextureRegion mBoxFaceTextureRegion;
    private TiledTextureRegion mCircleFaceTextureRegion;
    private HUD hud;
    
    Body LineJointBodyA;
    Body LineJointBodyB;
    protected int count = 0;
    private TextureRegion brickTextureRegion;
    private TextureRegion buttonTextureRegion;
    private SmoothCamera camera;

    @Override
    public EngineOptions onCreateEngineOptions() {
         camera = new SmoothCamera(0, 0, (int)(CAMERA_WIDTH/1.3), (int)(CAMERA_HEIGHT/1.3), 1000,1000,100);
        //return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
        

         EngineOptions engineOptions = new EngineOptions(true,
                ScreenOrientation.LANDSCAPE_FIXED, new
                FillResolutionPolicy(), camera);
        
        return engineOptions;
    }
    
    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) {
    // Create a fixed step engine updating at 60 steps per second
        return new FixedStepEngine(pEngineOptions, Environment.FPS);
    }
    
    
    
    
    
    @Override
    protected void onCreateResources() {
        
        MonningManager.reset();
        PortalManager.reset();
        WallManager.reset();
        BrickManager.reset();
        System.gc();
        
        this.mEngine.registerUpdateHandler(new FPSLogger());
        
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        try {
            final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, getVertexBufferObjectManager());
            this.mTMXTiledMap = tmxLoader.loadFromAsset("tmx/desert.tmx");
        } catch (final TMXLoadException tmxle) {
            Debug.e(tmxle);
            tmxle.printStackTrace();
            Log.w("Monnings", "Tile not loaded");
        }
        
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 64, 128, TextureOptions.BILINEAR);
        this.mBoxFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_box_tiled.png", 0, 0, 2, 1); // 64x32
        this.mCircleFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_circle_tiled.png", 0, 32, 2, 1); // 64x32
             this.mBitmapTextureAtlas.load();

       try{
            ITexture buttonTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/box.png");
                }
            });
            buttonTexture.load();
            this.buttonTextureRegion =  TextureRegionFactory.extractFromTexture(buttonTexture);
            
            ITexture brickTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssets().open("gfx/brick.png");
                }
            });

            brickTexture.load();
            this.brickTextureRegion  = TextureRegionFactory.extractFromTexture(brickTexture);

         
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Debug.e(e);
        }

    }

    @Override
    protected Scene onCreateScene() {
        // 1 - Create new scene
        final Scene scene = new Scene();
        this.mPhysicsWorld = new FixedStepPhysicsWorld(Environment.FPS, new Vector2(0, Environment.GRAVITY_MONNINGS), false);
        // Sprite backgroundSprite = new Sprite(0, 0,
        // this.mBackgroundTextureRegion, getVertexBufferObjectManager());
        // scene.setColor(Color.TRANSPARENT);
        // TextureRegionFactory.createFromSource(map, map, 0,0);
        // scene.attachChild(backgroundSprite);
        
        
        
        hud = new HUD();
        camera.setHUD(hud);
       
        // scene.setBackground(new Background(0.9f, .9f, .9f));
        for (TMXLayer tmxLayer : this.mTMXTiledMap.getTMXLayers()) {
            scene.attachChild(tmxLayer);
        }
        final ArrayList<TMXObjectGroup> groups = mTMXTiledMap.getTMXObjectGroups();

        ArrayList<TMXObject> objects;

        for (final TMXObjectGroup group : groups) {
            objects = group.getTMXObjects();
            for (final TMXObject object : objects) {
                String type = "";
                if (group.getTMXObjectGroupProperties().size() > 0) {
                    type = group.getTMXObjectGroupProperties().get(0).getValue();
                }

                HashMap<String, String> properties = new HashMap<String, String>();
                int size = object.getTMXObjectProperties().size();
                for (int i = 0; i < size; i++) {
                    properties.put(object.getTMXObjectProperties().get(i).getName(), object.getTMXObjectProperties().get(i).getValue());
                }

                if (properties.containsKey("type")) {
                    type = properties.get("type");
                }

                TMXEntities.addEntity(this, scene, object.getX(), object.getY(), object.getWidth(), object.getHeight(), type, properties, this.getVertexBufferObjectManager());
            }
        }

        monning = addMonning(scene, createMonning());
       // addMonning(scene,createMonning());
        //createMonning();
        MonningManager.getInstance().addEntity(monning);
        getEngine().getCamera().setChaseEntity(monning.getChassis());
        
        
        ButtonSprite newgamebutton = new ButtonSprite(25, 25, buttonTextureRegion, mEngine.getVertexBufferObjectManager())
        {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pTouchEvent.isActionDown()) {
                    Log.w("ddddd","sssss");
                    GameActivity.this.monning.setState(StateFactory.getInstance().getStairBuildingState(monning));
                }
                return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        hud.registerTouchArea(newgamebutton);
        hud.attachChild(newgamebutton);
        
        
        StateFactory.setBrickTextureRegion(this.brickTextureRegion);
        StateFactory.setPhysicsWorld(this.mPhysicsWorld);
        StateFactory.setScene(scene);
        StateFactory.setVertexBufferObjectManager(this.getVertexBufferObjectManager());
 
        //scene.attachChild(newgamebutton);
        
        
        
        //scene.registerUpdateHandler(new MonningGenerator(this, scene, 20, 2f));
        scene.registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void onUpdate(float pSecondsElapsed) {
                mPhysicsWorld.onUpdate(pSecondsElapsed);
            }

            @Override
            public void reset() {
                // TODO Auto-generated method stub
            }
        });
        
        /*
        // This should make the scene touchable
        scene.setOnSceneTouchListener(new IOnSceneTouchListener() {

            @Override
            public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
                if(pSceneTouchEvent.isActionDown()) {
                    
                    GameActivity.this.monning.setState(StateFactory.getInstance().getDiggingState(monning));
                }
                
                return true;
            }
        });
*/
     
        return scene;
    }

    /**
     * @param scene
     */
    public Monning addMonning(final Scene scene, Monning monning) {
        
       // monning.makeSprite(getVertexBufferObjectManager(), mManTextureRegion);
        scene.attachChild(monning.getWheel());
        scene.attachChild(monning.getChassis());
        
        // monning.makeSprite(getVertexBufferObjectManager(), textureRegion)
        monning.makePhysicBody(mPhysicsWorld);
        scene.registerTouchArea(monning);
        scene.registerUpdateHandler(monning);
        return monning;
    }
    
    public Monning createMonning(){
        Monning monning = new Monning();
        
        monning.makeChassis(getVertexBufferObjectManager(), mBoxFaceTextureRegion);
        monning.makeWheel(getVertexBufferObjectManager(), mCircleFaceTextureRegion);
        
        return monning;
    }

}