package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Gun;
import com.tilldawn.View.GameMenu;

public class GunController {
    private final GameMenu view;
    private final Character character;
    private final CharacterController characterController;
    private final Gun gun;
    public GunController(GameMenu view, Character character, CharacterController characterController) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.gun=new Gun(character);
    }
    public void update(){
        gun.getSprite().draw(view.getStage().getBatch());
    }
    public void handleWeaponRotation(int x, int y) {
        Sprite sprite = gun.getSprite();
        float originX = Gdx.graphics.getWidth()/2f - sprite.getX();
        float originY = Gdx.graphics.getHeight()/2f - sprite.getY();
        sprite.setOrigin(originX, originY);
        //Vector3 target = view.getStage().getCamera().unproject(new Vector3(x, y, 0));
        float dx = x - Gdx.graphics.getWidth()/2f;
        float dy = y - Gdx.graphics.getHeight()/2f;
        float angle = -MathUtils.atan2(dy, dx) * MathUtils.radiansToDegrees;
        if (dx<0 && !sprite.isFlipY()) {
            sprite.flip(false, true);
        } else if (dx>0 && sprite.isFlipY()) {
            sprite.flip(false, true);
        }
        sprite.setRotation(angle);
    }


}
