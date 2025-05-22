package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree extends Monster{
    public Tree(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(Integer.MAX_VALUE);
        setInternalPath("images/Texture2D/T_TreeMonster.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles=TextureRegion.split(monsterTexture,getWidth(),getHeight());
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth()*2,getHeight()*2);
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
