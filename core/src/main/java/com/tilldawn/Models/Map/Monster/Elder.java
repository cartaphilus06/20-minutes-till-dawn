package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Elder extends Monster {
    public Elder(int worldX, int worldY) {
        super(worldX, worldY);
        setHp(400);
        setInternalPath("images/Texture2D/ElderBrain.png");
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
