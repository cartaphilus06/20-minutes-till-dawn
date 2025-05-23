package com.tilldawn.Models.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tilldawn.Models.AssetManager;

public class Exp {
    private float x;
    private float y;
    private final Sprite sprite;
    public Exp(){
        sprite = new Sprite(AssetManager.getEggIcon());
    }
    public Exp(float x, float y) {
        this.x = x;
        this.y = y;
        sprite=new Sprite(AssetManager.getEggIcon());
    }
    public void draw(Batch batch){
        sprite.setPosition(x,y);
        sprite.draw(batch);
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    @JsonIgnore
    public Sprite getSprite() {
        return sprite;
    }
}
