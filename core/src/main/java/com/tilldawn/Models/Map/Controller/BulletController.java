package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Bullet;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Gun;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.Monster;
import com.tilldawn.View.GameMenu;

import java.util.ArrayList;

public class BulletController {
    private final GameMenu view;
    private final GunController gunController;
    private final Character character;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final Map map;
    public BulletController(GameMenu view,GunController gunController,Character character,Map map) {
        this.view = view;
        this.gunController=gunController;
        this.character=character;
        this.map=map;
    }
    public void update(){
        Batch batch=view.getStage().getBatch();
        updateBullets(Gdx.graphics.getDeltaTime());
        drawBullets(batch);
    }
    public void touchDown(int screenX, int screenY) {
        handleInput(screenX, screenY);
    }
    public void handleInput(int screenX, int screenY){
        if (Gdx.input.isButtonJustPressed(App.getCurrentUser().getMovingKeys().getShoot())) {
            Vector3 target = view.getCamera().unproject(new Vector3(screenX, screenY, 0));
            shoot(target.x, target.y);
        }
    }


    public void shoot(float targetX, float targetY) {
        Gun gun = gunController.getGun();
        int projectileCount = character.getProjectile();
        float spreadAngle = 10f;
        float speed = 1000f;
        Sprite gunSprite = gun.getSprite();
        float originX = gunSprite.getX() + gunSprite.getOriginX();
        float originY = gunSprite.getY() + gunSprite.getOriginY();
        float baseAngle = gunSprite.getRotation();
        float muzzleLength = gunSprite.getWidth() / 2f;
        float radians = (float) Math.toRadians(baseAngle);
        float muzzleX = originX + (float)Math.cos(radians) * muzzleLength;
        float muzzleY = originY + (float)Math.sin(radians) * muzzleLength;
        int mid = projectileCount / 2;
        if (character.getCurrentAmmo() > 0) {
            Music shotSound = AssetManager.getShotSound();
            if (shotSound.isPlaying()) shotSound.stop();
            shotSound.play();
            for (int i = 0; i < projectileCount; i++) {
                float offset = (i - mid) * spreadAngle;
                if (projectileCount % 2 == 0) {
                    offset += spreadAngle / 2f;
                }
                float finalAngle = baseAngle + offset;
                bullets.add(new Bullet(muzzleX, muzzleY, finalAngle, speed));
            }
            character.setCurrentAmmo(character.getCurrentAmmo() - 1);
        }
    }

    public void updateBullets(float delta){
        for(int i=bullets.size()-1;i>=0;i--){
            Bullet bullet=bullets.get(i);
            bullet.update(delta);
            if(bullet.isOutOfBounds()) bullets.remove(i);
            Monster monster=map.isMonster(bullet.getBoundsBox());
            if(monster!=null){
                monster.setHp((int)(monster.getHp()-character.getDamage()));
                bullets.remove(i);
            }
        }
    }
    public void drawBullets(Batch batch){
        for(Bullet bullet:bullets){
            bullet.draw(batch);
        }
    }

}
