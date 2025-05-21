package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Bullet;
import com.tilldawn.View.GameMenu;

import java.util.ArrayList;

public class BulletController {
    private final GameMenu view;
    private final GunController gunController;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    public BulletController(GameMenu view,GunController gunController) {
        this.view = view;
        this.gunController=gunController;
    }
    public void update(){
        Batch batch=view.getStage().getBatch();
        handleInput();
        updateBullets(Gdx.graphics.getDeltaTime());
        drawBullets(batch);
    }
    public void handleInput(){
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            shoot();
        }
    }
    public void shoot(){
        Sprite gunSprite=gunController.getGun().getSprite();
        float x=gunSprite.getX()+gunSprite.getOriginX();
        float y=gunSprite.getY()+gunSprite.getOriginY();
        float angle=gunSprite.getRotation();
        bullets.add(new Bullet(x,y,angle,1000f));
    }
    public void updateBullets(float delta){
        for(int i=bullets.size()-1;i>=0;i--){
            Bullet bullet=bullets.get(i);
            bullet.update(delta);
            if(bullet.isOutOfBounds()) bullets.remove(i);
        }
    }
    public void drawBullets(Batch batch){
        for(Bullet bullet:bullets){
            bullet.draw(batch);
        }
    }

}
