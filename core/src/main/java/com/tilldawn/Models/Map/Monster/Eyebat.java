package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Eyebat extends Monster {
    private final Animation<TextureRegion> animation;
    public Eyebat(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(50);
        setInternalPath("images/Texture2D/T_EyeBat.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles=TextureRegion.split(monsterTexture,getWidth(),getHeight());
        animation=new Animation<>(0.2f,tiles[0]);
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth(),getHeight());
    }

    public void draw(Batch batch){
        TextureRegion currentFrame=animation.getKeyFrame(stateTime, true);
        if(isFacingLeft ^ currentFrame.isFlipX()) currentFrame.flip(true, false);
        sprite.setRegion(currentFrame);
        super.draw(batch);
    }

    @Override
    public int getWidth() {
        return 96;
    }

    @Override
    public int getHeight() {
        return 96;
    }
}
