package com.tilldawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetHelper {
    private final Texture texture;
    private final TextureRegion[][] tiles;
    private Animation<TextureRegion> animation;
    private float frameDuration;
    public AssetHelper(Texture texture,int width,int height,float frameDuration) {
        this.texture = texture;
        tiles=TextureRegion.split(texture, width, height);
        this.frameDuration = frameDuration;
    }
    public void setAnimation() {
        animation = new Animation<>(frameDuration, tiles[0][0],tiles[0][1],tiles[0][2]);
    }
    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }
    public Animation<TextureRegion> getAnimation() {
        return animation;
    }
    public TextureRegion[] getTiles() {
        return tiles[0];
    }
}
