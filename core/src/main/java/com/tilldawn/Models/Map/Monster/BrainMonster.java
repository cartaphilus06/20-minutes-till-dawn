package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BrainMonster extends Monster {
    public BrainMonster(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(25);
        setInternalPath("images/Texture2D/BrainMonster.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        this.sprite=new Sprite(monsterTexture);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
