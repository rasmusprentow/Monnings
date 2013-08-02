package com.dyto.monnings.world;

import java.io.InputStream;

import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.bitmap.source.decorator.BaseBitmapTextureAtlasSourceDecorator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.util.Log;

public class WorldTextureDecorator extends BaseBitmapTextureAtlasSourceDecorator {

    

    private Bitmap background;

    public WorldTextureDecorator(IBitmapTextureAtlasSource pBitmapTextureAtlasSource) {
        super(pBitmapTextureAtlasSource);

    }
    
    public WorldTextureDecorator(IBitmapTextureAtlasSource pBitmapTextureAtlasSource, InputStream is){
        this(pBitmapTextureAtlasSource);
        background = BitmapFactory.decodeStream(is);
    }

    protected void onDecorateBitmap(Canvas pCanvas) throws Exception {
       // this.mPaint.setColorFilter(new LightingColorFilter(Color.argb(128, 128, 128, 255), Color.TRANSPARENT));
        
        
        pCanvas.drawBitmap(background, 0, 0, this.mPaint);

    }

    @Override
    public BaseBitmapTextureAtlasSourceDecorator deepCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    public Bitmap getBitmap() {
        return background;
        
    }

}
