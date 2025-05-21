package com.tilldawn.Models.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Models.AssetManager;

public class Bullet {
    private final Sprite sprite;
    private final Vector2 velocity;
    public Bullet(float x, float y, float angle,float speed) {
        sprite=new Sprite(AssetManager.getBulletTexture());
        sprite.setPosition(x, y);
        float radians= (float) Math.toRadians(angle);
        velocity = new Vector2((float) Math.cos(radians), (float) Math.sin(radians)).scl(speed);
    }
    public void update(float delta) {
        sprite.translate(velocity.x*delta, velocity.y*delta);
    }
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    public Rectangle getBoundsBox() {
        return sprite.getBoundingRectangle();
    }
    public boolean isOutOfBounds(){
        float x=sprite.getX();
        float y=sprite.getY();
        return (x<Map.getWorldMinX() || x>Map.getWorldMaxX() || y<Map.getWorldMinY() || y>Map.getWorldMaxY());
    }
}
