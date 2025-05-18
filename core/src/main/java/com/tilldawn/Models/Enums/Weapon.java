package com.tilldawn.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public enum Weapon{
    SHOTGUN(10,4,1,2,"images/Texture2D/T_Shotgun_SS.png"),
    REVOLVER(20,1,1,6,"images/Texture2D/T_Revolver_SS.png"),
    SMGSDUAL(8,1,1,4,"images/Texture2D/T_DualSMGs_SS.png"),;
    private final int damage;
    private final int projectile;
    private final int reloadTime;
    private final int maxAmmo;
    private final String texturePath;
    private final TextureRegion[][] tiles;
    Weapon(int damage, int projectile, int reloadTime, int maxAmmo, String texturePath) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
        this.texturePath = texturePath;
        tiles=TextureRegion.split(new Texture(texturePath), 16, 16);
    }
    public int getDamage() {
        return damage;
    }
    public int getProjectile() {
        return projectile;
    }
    public int getReloadTime() {
        return reloadTime;
    }
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public String getPath(){
        return texturePath;
    }
    public TextureRegion[][] getTiles(){
        return tiles;
    }
    public ArrayList<TextureRegion> getIcons(){
        ArrayList<TextureRegion> icons;
        switch (this){
            case SHOTGUN:{
                icons = new ArrayList<>(Arrays.asList(getTiles()[0]).subList(0, 4));
                return icons;
            }
            case REVOLVER:
            case SMGSDUAL: {
                icons = new ArrayList<>(Arrays.asList(getTiles()[0]).subList(0, 5));
                return icons;
            }
            default: {
                return null;
            }
        }
    }
    public Animation<TextureRegion> getAnimation(){
        ArrayList<TextureRegion> icons = getIcons();
        icons.add(0,tiles[0][0]);
        TextureRegion[] animations= Objects.requireNonNull(icons).toArray(new TextureRegion[0]);
        return new Animation<>(0.2f,animations);
    }
    public Animation<TextureRegion> getDefaultAnimation(){
        TextureRegion[] icon={tiles[0][0]};
        return new Animation<>(0.3f,icon);
    }
    public int getWidth(){
        return 16;
    }
    public int getHeight(){
        return 16;
    }
    public TextureRegion getTexture(){
        return tiles[0][0];
    }
}
