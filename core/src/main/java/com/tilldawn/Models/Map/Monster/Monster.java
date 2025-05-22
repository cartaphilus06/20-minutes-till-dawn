package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public abstract class Monster {
    protected Sprite sprite;
    protected int hp;
    protected float x;
    protected float y;
    protected String internalPath;
    protected Texture monsterTexture;
    protected int pathIndex;
    protected float speed=100f;
    public Monster(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp(){
        return hp;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    protected void setInternalPath(String internalPath) {
        this.internalPath = internalPath;
    }
    public void update(){

    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    public void setTexture(Texture texture){
        monsterTexture = texture;
    }
    public abstract int getWidth();
    public abstract int getHeight();
    protected String getInternalPath() {
        return internalPath;
    }
    public Sprite getSprite() {
        return sprite;
    }
}
