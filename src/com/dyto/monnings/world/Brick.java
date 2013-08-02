package com.dyto.monnings.world;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 7/30/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Brick extends Sprite {
    public Brick(float v, float v1, TextureRegion brickTexture, VertexBufferObjectManager vertexBufferObjectManager) {
        super( v,  v1,  brickTexture,  vertexBufferObjectManager);
    }
}
