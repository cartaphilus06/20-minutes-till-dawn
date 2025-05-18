package com.tilldawn.Models.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.App;
import com.tilldawn.Models.CollisionRect;

public class Gun {
    private final Sprite sprite;
    private int ammo;
    public Gun(Character character){
        sprite=new Sprite(character.getWeapon().getTexture());
        sprite.setSize(sprite.getWidth()*2.5f, sprite.getHeight()*2.5f);
        sprite.setX(character.getX()+character.getHeroWidth()/1.8f);
        sprite.setY(character.getY()+(character.getHeroHeight()-sprite.getHeight())/2);
        ammo=character.getMaxAmmo();
    }
    public Sprite getSprite(){
        return sprite;
    }
    public int getAmmo(){
        return ammo;
    }
    public void setAmmo(int ammo){
        this.ammo=ammo;
    }
}
