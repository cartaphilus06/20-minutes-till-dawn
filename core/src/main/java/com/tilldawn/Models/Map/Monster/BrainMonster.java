package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BrainMonster extends Monster {
    @JsonIgnore
    private Animation<TextureRegion> animation;
    public BrainMonster() {}
    public BrainMonster(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(25);
        setInternalPath("images/Texture2D/BrainMonster.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles = TextureRegion.split(monsterTexture, getWidth(), getHeight());
        animation=new Animation<>(0.2f, tiles[0]);
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth()*2,getHeight()*2);
    }

    public void draw(Batch batch) {
        TextureRegion currentFrame=animation.getKeyFrame(stateTime);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        if(isFacingLeft ^ currentFrame.isFlipX()) currentFrame.flip(true, false);
        sprite.setRegion(currentFrame);
        super.draw(batch);
    }

    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void reinitializeAssets() {
        setInternalPath("images/Texture2D/BrainMonster.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles = TextureRegion.split(monsterTexture, getWidth(), getHeight());
        animation=new Animation<>(0.2f, tiles[0]);
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth()*2,getHeight()*2);
    }
}
