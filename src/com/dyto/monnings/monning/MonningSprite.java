package com.dyto.monnings.monning;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.dyto.monnings.GameEntity;

import android.util.Log;
import android.widget.Toast;

public class MonningSprite extends Sprite {

    private Monning listener;
    public MonningSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }

    public void setListener(Monning ge)
    {
        this.listener = ge;
    }
    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        listener.onTouch();
        return true;
    }
}
