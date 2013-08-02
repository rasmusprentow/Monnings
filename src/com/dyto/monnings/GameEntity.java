package com.dyto.monnings;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.IUpdateHandler.IUpdateHandlerMatcher;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityComparator;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.badlogic.gdx.physics.box2d.Body;

abstract public class GameEntity implements IAreaShape {

    protected Sprite sprite;



    
    abstract public Sprite makeSprite(VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion);
   
    protected Sprite getSprite()
    {
        return sprite;
    }

    abstract public void onTouch();
    
    protected void setSprite(Sprite spr) {
        this.sprite = spr;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public boolean isBlendingEnabled() {
        return sprite.isBlendingEnabled();
    }

    public int getBlendFunctionSource() {
        return sprite.getBlendFunctionSource();
    }

    public float getWidthScaled() {
        return sprite.getWidthScaled();
    }

    public float getHeightScaled() {
        return sprite.getHeightScaled();
    }

    public boolean isCulled(Camera pCamera) {
        return sprite.isCulled(pCamera);
    }

    public int getBlendFunctionDestination() {
        return sprite.getBlendFunctionDestination();
    }

    public boolean contains(float pX, float pY) {
        return sprite.contains(pX, pY);
    }

    public ShaderProgram getShaderProgram() {
        return sprite.getShaderProgram();
    }

    public float[] getSceneCenterCoordinates() {
        return sprite.getSceneCenterCoordinates();
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return sprite.getVertexBufferObjectManager();
    }

    public float[] getSceneCenterCoordinates(float[] pReuse) {
        return sprite.getSceneCenterCoordinates(pReuse);
    }

    public boolean collidesWith(IShape pOtherShape) {
        return sprite.collidesWith(pOtherShape);
    }

    public boolean isDisposed() {
        return sprite.isDisposed();
    }

    public boolean isCullingEnabled() {
        return sprite.isCullingEnabled();
    }

    public void dispose() {
        sprite.dispose();
    }

    public boolean isChildrenVisible() {
        return sprite.isChildrenVisible();
    }

    public boolean isIgnoreUpdate() {
        return sprite.isIgnoreUpdate();
    }

    public boolean isChildrenIgnoreUpdate() {
        return sprite.isChildrenIgnoreUpdate();
    }

    public boolean hasParent() {
        return sprite.hasParent();
    }

    public IEntity getParent() {
        return sprite.getParent();
    }

    public int getTag() {
        return sprite.getTag();
    }

    public int getZIndex() {
        return sprite.getZIndex();
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public float getRotationCenterX() {
        return sprite.getRotationCenterX();
    }

    public float getRotationCenterY() {
        return sprite.getRotationCenterY();
    }

    public ITextureRegion getTextureRegion() {
        return sprite.getTextureRegion();
    }

    public boolean isFlippedHorizontal() {
        return sprite.isFlippedHorizontal();
    }

    public boolean isFlippedVertical() {
        return sprite.isFlippedVertical();
    }

    public float getScaleX() {
        return sprite.getScaleX();
    }

    public float getScaleY() {
        return sprite.getScaleY();
    }

    public ISpriteVertexBufferObject getVertexBufferObject() {
        return sprite.getVertexBufferObject();
    }

    public float getScaleCenterX() {
        return sprite.getScaleCenterX();
    }

    public float getScaleCenterY() {
        return sprite.getScaleCenterY();
    }

    public float getSkewX() {
        return sprite.getSkewX();
    }

    public float getSkewY() {
        return sprite.getSkewY();
    }

    public float getSkewCenterX() {
        return sprite.getSkewCenterX();
    }

    public float getSkewCenterY() {
        return sprite.getSkewCenterY();
    }

    public float getRed() {
        return sprite.getRed();
    }

    public float getGreen() {
        return sprite.getGreen();
    }

    public float getBlue() {
        return sprite.getBlue();
    }

    public float getAlpha() {
        return sprite.getAlpha();
    }

    public Color getColor() {
        return sprite.getColor();
    }

    public int getChildCount() {
        return sprite.getChildCount();
    }

    public IEntity getChildByTag(int pTag) {
        return sprite.getChildByTag(pTag);
    }

    public IEntity getChildByIndex(int pIndex) {
        return sprite.getChildByIndex(pIndex);
    }

    public IEntity getChildByMatcher(IEntityMatcher pEntityMatcher) {
        return sprite.getChildByMatcher(pEntityMatcher);
    }

    public IEntity getFirstChild() {
        return sprite.getFirstChild();
    }

    public IEntity getLastChild() {
        return sprite.getLastChild();
    }

    public boolean detachSelf() {
        return sprite.detachSelf();
    }

    public void detachChildren() {
        sprite.detachChildren();
    }

    public boolean detachChild(IEntity pEntity) {
        return sprite.detachChild(pEntity);
    }

    public IEntity detachChild(int pTag) {
        return sprite.detachChild(pTag);
    }

    public IEntity detachChild(IEntityMatcher pEntityMatcher) {
        return sprite.detachChild(pEntityMatcher);
    }

    public boolean detachChildren(IEntityMatcher pEntityMatcher) {
        return sprite.detachChildren(pEntityMatcher);
    }

    public int getUpdateHandlerCount() {
        return sprite.getUpdateHandlerCount();
    }

    public void clearUpdateHandlers() {
        sprite.clearUpdateHandlers();
    }

    public int getEntityModifierCount() {
        return sprite.getEntityModifierCount();
    }

    public void clearEntityModifiers() {
        sprite.clearEntityModifiers();
    }

    public Transformation getLocalToParentTransformation() {
        return sprite.getLocalToParentTransformation();
    }

    public Transformation getParentToLocalTransformation() {
        return sprite.getParentToLocalTransformation();
    }

    public Transformation getLocalToSceneTransformation() {
        return sprite.getLocalToSceneTransformation();
    }

    public Transformation getSceneToLocalTransformation() {
        return sprite.getSceneToLocalTransformation();
    }

    public float[] convertLocalToSceneCoordinates(float pX, float pY) {
        return sprite.convertLocalToSceneCoordinates(pX, pY);
    }

    public float[] convertLocalToSceneCoordinates(float pX, float pY, float[] pReuse) {
        return sprite.convertLocalToSceneCoordinates(pX, pY, pReuse);
    }

    public float[] convertLocalToSceneCoordinates(float[] pCoordinates) {
        return sprite.convertLocalToSceneCoordinates(pCoordinates);
    }

    public float[] convertLocalToSceneCoordinates(float[] pCoordinates, float[] pReuse) {
        return sprite.convertLocalToSceneCoordinates(pCoordinates, pReuse);
    }

    public float[] convertSceneToLocalCoordinates(float pX, float pY) {
        return sprite.convertSceneToLocalCoordinates(pX, pY);
    }

    public float[] convertSceneToLocalCoordinates(float pX, float pY, float[] pReuse) {
        return sprite.convertSceneToLocalCoordinates(pX, pY, pReuse);
    }

    public float[] convertSceneToLocalCoordinates(float[] pCoordinates) {
        return sprite.convertSceneToLocalCoordinates(pCoordinates);
    }

    public float[] convertSceneToLocalCoordinates(float[] pCoordinates, float[] pReuse) {
        return sprite.convertSceneToLocalCoordinates(pCoordinates, pReuse);
    }

    public Object getUserData() {
        return sprite.getUserData();
    }

    public int hashCode() {
        return sprite.hashCode();
    }

    public void setWidth(float pWidth) {
        sprite.setWidth(pWidth);
    }

    public void setHeight(float pHeight) {
        sprite.setHeight(pHeight);
    }

    public void setSize(float pWidth, float pHeight) {
        sprite.setSize(pWidth, pHeight);
    }

    public void setBlendingEnabled(boolean pBlendingEnabled) {
        sprite.setBlendingEnabled(pBlendingEnabled);
    }

    public void setBlendFunctionSource(int pBlendFunctionSource) {
        sprite.setBlendFunctionSource(pBlendFunctionSource);
    }

    public void setBlendFunctionDestination(int pBlendFunctionDestination) {
        sprite.setBlendFunctionDestination(pBlendFunctionDestination);
    }

    public void setBlendFunction(int pBlendFunctionSource, int pBlendFunctionDestination) {
        sprite.setBlendFunction(pBlendFunctionSource, pBlendFunctionDestination);
    }

    public void setShaderProgram(ShaderProgram pShaderProgram) {
        sprite.setShaderProgram(pShaderProgram);
    }

    public void resetRotationCenter() {
        sprite.resetRotationCenter();
    }

    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        return sprite.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
    }

    public void resetScaleCenter() {
        sprite.resetScaleCenter();
    }

    public boolean isVisible() {
        return sprite.isVisible();
    }

    public void resetSkewCenter() {
        sprite.resetSkewCenter();
    }

    public void setVisible(boolean pVisible) {
        sprite.setVisible(pVisible);
    }

    public void setCullingEnabled(boolean pCullingEnabled) {
        sprite.setCullingEnabled(pCullingEnabled);
    }

    public void setChildrenVisible(boolean pChildrenVisible) {
        sprite.setChildrenVisible(pChildrenVisible);
    }

    public void setIgnoreUpdate(boolean pIgnoreUpdate) {
        sprite.setIgnoreUpdate(pIgnoreUpdate);
    }

    public void setChildrenIgnoreUpdate(boolean pChildrenIgnoreUpdate) {
        sprite.setChildrenIgnoreUpdate(pChildrenIgnoreUpdate);
    }

    public void setParent(IEntity pEntity) {
        sprite.setParent(pEntity);
    }

    public void setTag(int pTag) {
        sprite.setTag(pTag);
    }

    public void setZIndex(int pZIndex) {
        sprite.setZIndex(pZIndex);
    }

    public void setX(float pX) {
        sprite.setX(pX);
    }

    public void setY(float pY) {
        sprite.setY(pY);
    }

    public void setPosition(IEntity pOtherEntity) {
        sprite.setPosition(pOtherEntity);
    }

    public void setPosition(float pX, float pY) {
        sprite.setPosition(pX, pY);
    }

    public boolean isRotated() {
        return sprite.isRotated();
    }

    public void setRotation(float pRotation) {
        sprite.setRotation(pRotation);
    }

    public void setRotationCenterX(float pRotationCenterX) {
        sprite.setRotationCenterX(pRotationCenterX);
    }

    public void setRotationCenterY(float pRotationCenterY) {
        sprite.setRotationCenterY(pRotationCenterY);
    }

    public void setFlippedHorizontal(boolean pFlippedHorizontal) {
        sprite.setFlippedHorizontal(pFlippedHorizontal);
    }

    public void setRotationCenter(float pRotationCenterX, float pRotationCenterY) {
        sprite.setRotationCenter(pRotationCenterX, pRotationCenterY);
    }

    public void setFlippedVertical(boolean pFlippedVertical) {
        sprite.setFlippedVertical(pFlippedVertical);
    }

    public boolean isScaled() {
        return sprite.isScaled();
    }

    public void setFlipped(boolean pFlippedHorizontal, boolean pFlippedVertical) {
        sprite.setFlipped(pFlippedHorizontal, pFlippedVertical);
    }

    public void setScaleX(float pScaleX) {
        sprite.setScaleX(pScaleX);
    }

    public void setScaleY(float pScaleY) {
        sprite.setScaleY(pScaleY);
    }

    public void setScale(float pScale) {
        sprite.setScale(pScale);
    }

    public void reset() {
        sprite.reset();
    }

    public void setScale(float pScaleX, float pScaleY) {
        sprite.setScale(pScaleX, pScaleY);
    }

    public void setScaleCenterX(float pScaleCenterX) {
        sprite.setScaleCenterX(pScaleCenterX);
    }

    public void setScaleCenterY(float pScaleCenterY) {
        sprite.setScaleCenterY(pScaleCenterY);
    }

    public void setScaleCenter(float pScaleCenterX, float pScaleCenterY) {
        sprite.setScaleCenter(pScaleCenterX, pScaleCenterY);
    }

    public boolean isSkewed() {
        return sprite.isSkewed();
    }

    public void setSkewX(float pSkewX) {
        sprite.setSkewX(pSkewX);
    }

    public void setSkewY(float pSkewY) {
        sprite.setSkewY(pSkewY);
    }

    public void setSkew(float pSkew) {
        sprite.setSkew(pSkew);
    }

    public void setSkew(float pSkewX, float pSkewY) {
        sprite.setSkew(pSkewX, pSkewY);
    }

    public void setSkewCenterX(float pSkewCenterX) {
        sprite.setSkewCenterX(pSkewCenterX);
    }

    public void setSkewCenterY(float pSkewCenterY) {
        sprite.setSkewCenterY(pSkewCenterY);
    }

    public void setSkewCenter(float pSkewCenterX, float pSkewCenterY) {
        sprite.setSkewCenter(pSkewCenterX, pSkewCenterY);
    }

    public boolean isRotatedOrScaledOrSkewed() {
        return sprite.isRotatedOrScaledOrSkewed();
    }

    public void setColor(Color pColor) {
        sprite.setColor(pColor);
    }

    public void setRed(float pRed) {
        sprite.setRed(pRed);
    }

    public void setGreen(float pGreen) {
        sprite.setGreen(pGreen);
    }

    public void setBlue(float pBlue) {
        sprite.setBlue(pBlue);
    }

    public void setAlpha(float pAlpha) {
        sprite.setAlpha(pAlpha);
    }

    public void setColor(float pRed, float pGreen, float pBlue) {
        sprite.setColor(pRed, pGreen, pBlue);
    }

    public void setColor(float pRed, float pGreen, float pBlue, float pAlpha) {
        sprite.setColor(pRed, pGreen, pBlue, pAlpha);
    }

    public ArrayList<IEntity> query(IEntityMatcher pEntityMatcher) {
        return sprite.query(pEntityMatcher);
    }

    public IEntity queryFirst(IEntityMatcher pEntityMatcher) {
        return sprite.queryFirst(pEntityMatcher);
    }

    public <S extends IEntity> S queryFirstForSubclass(IEntityMatcher pEntityMatcher) {
        return sprite.queryFirstForSubclass(pEntityMatcher);
    }

    public <L extends List<IEntity>> L query(IEntityMatcher pEntityMatcher, L pResult) {
        return sprite.query(pEntityMatcher, pResult);
    }

    public <S extends IEntity> ArrayList<S> queryForSubclass(IEntityMatcher pEntityMatcher) throws ClassCastException {
        return sprite.queryForSubclass(pEntityMatcher);
    }

    public <L extends List<S>, S extends IEntity> L queryForSubclass(IEntityMatcher pEntityMatcher, L pResult) throws ClassCastException {
        return sprite.queryForSubclass(pEntityMatcher, pResult);
    }

    public void sortChildren() {
        sprite.sortChildren();
    }

    public void sortChildren(boolean pImmediate) {
        sprite.sortChildren(pImmediate);
    }

    public void sortChildren(IEntityComparator pEntityComparator) {
        sprite.sortChildren(pEntityComparator);
    }

    public void registerUpdateHandler(IUpdateHandler pUpdateHandler) {
        sprite.registerUpdateHandler(pUpdateHandler);
    }

    public boolean unregisterUpdateHandler(IUpdateHandler pUpdateHandler) {
        return sprite.unregisterUpdateHandler(pUpdateHandler);
    }

    public boolean unregisterUpdateHandlers(IUpdateHandlerMatcher pUpdateHandlerMatcher) {
        return sprite.unregisterUpdateHandlers(pUpdateHandlerMatcher);
    }

    public void registerEntityModifier(IEntityModifier pEntityModifier) {
        sprite.registerEntityModifier(pEntityModifier);
    }

    public boolean unregisterEntityModifier(IEntityModifier pEntityModifier) {
        return sprite.unregisterEntityModifier(pEntityModifier);
    }

    public boolean unregisterEntityModifiers(IEntityModifierMatcher pEntityModifierMatcher) {
        return sprite.unregisterEntityModifiers(pEntityModifierMatcher);
    }

    public void onAttached() {
        sprite.onAttached();
    }

    public void onDetached() {
        sprite.onDetached();
    }

    public void setUserData(Object pUserData) {
        sprite.setUserData(pUserData);
    }
    
}
