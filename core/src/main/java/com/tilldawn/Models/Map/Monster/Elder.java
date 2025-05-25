package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Elder extends Monster {
    public Elder() {}
    public Elder(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(400);
        setInternalPath("images/Texture2D/ElderBrain.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        this.sprite=new Sprite(monsterTexture);
        this.sprite.setSize(getWidth()*2, getHeight()*2);
    }

    public void draw(Batch batch) {
        if(isFacingLeft ^ sprite.isFlipX()) sprite.flip(true, false);
        super.draw(batch);
    }

    @Override
    public int getWidth() {
        return 48;
    }

    @Override
    public int getHeight() {
        return 48;
    }

    @Override
    public void reinitializeAssets() {
        setInternalPath("images/Texture2D/ElderBrain.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        this.sprite=new Sprite(monsterTexture);
        this.sprite.setSize(getWidth()*2, getHeight()*2);
    }
}
