package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tree extends Monster{
    public Tree(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(Integer.MAX_VALUE);
        setInternalPath("images/Texture2D/T_TreeMonster.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        this.sprite=new Sprite(monsterTexture);
    }
}
