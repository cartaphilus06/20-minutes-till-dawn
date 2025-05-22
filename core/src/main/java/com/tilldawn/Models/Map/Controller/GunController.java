package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Gun;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

import java.util.TimerTask;

public class GunController {
    private final GameMenu view;
    private final Character character;
    private final CharacterController characterController;
    private final Map map;
    private final Gun gun;
    private boolean isReloading;
    public GunController(GameMenu view, Character character, CharacterController characterController,Map map) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.map = map;
        this.gun=new Gun(character);
        isReloading=false;
    }
    public void update(){
        Sprite sprite=gun.getSprite();
        float x=character.getX();
        float y=character.getY()-gun.getSprite().getHeight()/2;
        sprite.setPosition(x, y);
        sprite.draw(view.getStage().getBatch());
        reload();
    }
    public void handleWeaponRotation(int screenX, int screenY) {
        Vector3 worldMouse = view.getCamera().unproject(new Vector3(screenX, screenY, 0));
        Vector3 worldCenter = view.getCamera().unproject(new Vector3(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0));
        Sprite sprite = gun.getSprite();
        sprite.setOrigin(worldCenter.x - sprite.getX(), worldCenter.y - sprite.getY());
        float dx = worldMouse.x - worldCenter.x;
        float dy = worldMouse.y - worldCenter.y;
        float angle = MathUtils.atan2(dy, dx) * MathUtils.radiansToDegrees;
        sprite.setRotation(angle);
        if(dx<0 ^ sprite.isFlipY()){
            sprite.flip(false, true);
        }
    }
    public void reload(){
        if(isReloading) return;
        if(character.getCurrentAmmo()==0){
            Music reloadSound=AssetManager.getReloadSound();
            if(reloadSound.isPlaying()) reloadSound.stop();
            reloadSound.play();
            isReloading=true;
            float reloadTime=character.getReloadTime();
            Gdx.app.postRunnable(() -> {
                Gdx.app.log("GunController", "Started reloading...");
            });
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    character.setCurrentAmmo(character.getWeapon().getMaxAmmo());
                    isReloading=false;
                    Gdx.app.postRunnable(() -> {
                        Gdx.app.log("GunController", "Reloaded: " + character.getCurrentAmmo());
                    });
                }
            }, reloadTime);
        }
    }
    public Gun getGun(){
        return gun;
    }
}
