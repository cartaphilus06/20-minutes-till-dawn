package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Bullet;
import com.tilldawn.Models.Map.Character;

import java.util.ArrayList;

public class Eyebat extends Monster {
    @JsonIgnore
    private float shootTimer;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> deathAnimation;
    @JsonIgnore
    private ArrayList<Bullet> bullets=new ArrayList<>();
    public Eyebat() {}
    public Eyebat(float worldX, float worldY) {
        super(worldX, worldY);
        setHp(50);
        setInternalPath("images/Texture2D/T_EyeBat.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles=TextureRegion.split(monsterTexture,getWidth(),getHeight());
        animation=new Animation<>(0.2f,tiles[0]);
        deathAnimation= AssetManager.getDeathAnimation();
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth(),getHeight());
    }

    public void draw(Batch batch){
        TextureRegion currentFrame=animation.getKeyFrame(stateTime, true);
        if(isFacingLeft ^ currentFrame.isFlipX()) currentFrame.flip(true, false);
        sprite.setRegion(currentFrame);
        super.draw(batch);
    }
    public void drawDeathAnimation(Batch batch){
        stateTime=0;
        TextureRegion currentFrame=deathAnimation.getKeyFrame(stateTime, true);
        sprite.setRegion(currentFrame);
        super.draw(batch);
    }

    public void shoot(){
        shootTimer+=Gdx.graphics.getDeltaTime();
        if(shootTimer<3) return;
        Character character= App.getCurrentUser().getCharacter();
        float targetX=character.getX();
        float targetY=character.getY();
        float dx=targetX-x;
        float dy=targetY-y;
        float angle=(float)Math.atan2(dy,dx);
        bullets.add(new Bullet(x,y,(float)Math.toDegrees(angle),500f));
        shootTimer=0;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public int getWidth() {
        return 96;
    }

    @Override
    public int getHeight() {
        return 96;
    }

    @Override
    public void reinitializeAssets() {
        setInternalPath("images/Texture2D/T_EyeBat.png");
        setTexture(new Texture(Gdx.files.internal(getInternalPath())));
        TextureRegion[][] tiles=TextureRegion.split(monsterTexture,getWidth(),getHeight());
        deathAnimation= AssetManager.getDeathAnimation();
        animation=new Animation<>(0.2f,tiles[0]);
        this.sprite=new Sprite(tiles[0][0]);
        this.sprite.setSize(getWidth(),getHeight());
        bullets=new ArrayList<>();
    }
}
